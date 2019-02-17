package com.spring.TipBoardService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spirng.DAO.Tip_board_DAO;
import com.spirng.DAO.Tip_board_DTO;

public class modifyTipBoardService implements TipBoardService{
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		//dao.modifyTipBoard(subject, title, content, num);
		Map<String, Object> map = model.asMap();
		
		//이미지 가져오려면 이거 써야되더라고요.
		//다른 기능은 원래 쓰던것과 똑같고 이미지가 추가될 경우만 사용합니다.
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)map.get("request");
		
		HttpSession session = request.getSession(true);
		Tip_board_DAO dao = new Tip_board_DAO();
		Tip_board_DTO dto = new Tip_board_DTO();
			
		List<MultipartFile> fileList = request.getFiles("file");
		String boardNumber = request.getParameter("boardnumber");//몇 번째 게시글인지 확인
		
		dto = dao.selectTipView(boardNumber);//기존의 정보를 가져온다.
		
		String nickname = (String)session.getAttribute("userNickName");
		String id = (String)session.getAttribute("userId");
		String subject = request.getParameter("indexOfModiTipBoard");
		String title = request.getParameter("ModiTipBoardTitle");
		String content = request.getParameter("modiBoardContent");
		
	//	System.out.println("nickname : " + nickname);
	//	System.out.println("id : " + id);
	//	System.out.println("subject : " + subject);
	//	System.out.println("title : " + title);
	//	System.out.println("수정 전 content : " + content);
	
        dto.setTitle(title);//제목
    //    dto.setNickname(nickname);//닉네임
    //    dto.setId(id);//아이디
        dto.setSubject(subject);//분류
        dto.setContent(content);//내용
 
        System.out.println("파일 리스트 크기 : " + fileList.size());
        
       //이미지가 원래 없었는지 아니면 지운건지 확인을 하기 위한 변수
        String originalName1 = request.getParameter("hiddenFileName1");//첫번째칸 오리지널 이름
        String origialName2 = request.getParameter("hiddenFileName2");//두번째칸 오리지널 이름
        String newFileName1 = request.getParameter("attatchment1");//첫번째칸 변경된 이름
        String newFileName2 = request.getParameter("attatchment2");//두번째칸 변경된 이름
        
       System.out.println("origialName1 : " + originalName1 + " / origialName2 : " + origialName2);
       System.out.println("newFileName1 : " + newFileName1 + " / newFileName2 : " + newFileName2);
        
        //========새로 업로드한 파일이 존재하는지 여부에 따라========================
        //업로드하기로 한 이미지가 서버에 저장되는 과정에서 이미지가 저장되는 위치라고 보시면 됩니다.
        String path = "E:\\8월 자바 웹개발자 양성(백현정)\\(2019.01.02 ~ 2019.02.13) 프로젝트\\업로드 이미지\\";//이미지 저장되는 위치
        
        //====일단 이미지를 받아두고 경우의 수에 따라서 다르게 이미지를 넣도록 하겠다...-ㅁ-....
        int[] file = null;
        int fileNum = 0;//파일 몇개 들어왔는지 확인
        
        ArrayList<String> fileInfo = new ArrayList<String>();//파일 정보를 저장.
        
        for (MultipartFile mf : fileList) {//들어온 이미지 파일을 하나씩 처리해줄 반복문
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈

            //입력됐던 원래 파일 이름과 파일 크기
            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            String safeFile = System.currentTimeMillis() + originFileName;//앞에 시간을 붙여서 같은 파일이 올라가도 이름이 겹쳐지지 않아요.
            fileInfo.add(safeFile);
            fileNum++;
            
            if(originFileName == null || fileSize == 0)
            //이미지가 들어오지 않았는데도 1이 되는 경우가 발생하여 해당 경우에 부합할 경우 값을 0으로 초기화함.
            	fileNum = 0;
            
            //이미지가 들어온 갯수별로 이후 기능 적용
            else {
            	String saveFile = path + System.currentTimeMillis() + originFileName;
                try {
                    mf.transferTo(new File(saveFile));//위의 Path 경로에 이미지를 추가하고 해당 이미지를 DB에 업로드함
                    //소스 합칠때 위에 Path를 합치는 분 폴더로 변경해주세요.\\까지 달아주셔야 해요.
                    //이거 빠지면 DB에 저장 못하니까 주의 부탁드려요.
                    
                } catch (IllegalStateException e) {e.printStackTrace();} 
                catch (IOException e) {e.printStackTrace();}        
            }  
        }
        //======위까지 과정에서 들어온 이미지를 일단 다 받긴 함. 추후 연산은 아래에서..
    
       //이미지가 없는 경우는 null로 표시되고 이미지를 지운 경우는 공백이 된다
       // origialName1 : 1547548870247cigago.png / origialName2 : null(지우기 전)
       // newFileName1 :  / newFileName2 : null(지운 후)
       
        System.out.println("fileNum(이미지 파일 갯수) : " + fileNum); 
        int lastImgNum = 0;
        
       //1. 1번 칸이 비었는데 2번칸은 안 빈 경우(1번만 빔)
        	//1. 이미지가 0개 -> 2번 내용을 1로 이동 후 2번칸 null - x
        	//2. 이미지가 1개 -> 1번 내용에 이미지 입력 - 0
        	//3. 이미지가 2개 -> 1번칸 이미지만 입력 - 0
        
        boolean bool = true;
        
        if(originalName1 == null) {
        	bool = true;
        }else {
        	bool = false;
        }
        
     //   System.out.println("originalName1이 null인가 : " + bool);//null은 아님.
      //  System.out.println("newFileName1.equals('') : " + newFileName1.equals(""));//true
        
       if((originalName1==null || newFileName1.equals("")) && (origialName2 != null && newFileName2.equals("") == false))//이미지가 없었거나 있다가 지운 경우
   	   //if(newFileName1.equals("") && origialName2 != null)//이미지가 없었거나 있다가 지운 경우
       {//1번칸이 없거나 지워졌고!! 2번칸이 원래 존재했었고 지워지지 않은 경우
   		   System.out.println("1 들어옴!!");
    	   if(fileNum == 0) {//이미지 0개인 경우
    		   System.out.println("1-0");
    		   //2번 내용을 1번으로 이동
    		   dto.setImage1(dto.getImage2());
    		   dto.setImagename1(dto.getImagename2());
    		   dto.setImg_path1(dto.getImg_path2());
    		   
    		   //2번 칸을 모두 null로 교체한다.
    		   dto.setImage2(null);
    		   dto.setImagename2(null);
    		   dto.setImg_path2(null);
    		   
    		   lastImgNum = 1;
    	   }
    	   else if(fileNum >= 1) {//이미지가 한개 이상 들어왔지만 들어갈 공간은 하나 뿐이므로 먼저 입력된 이미지 하나만 새로 등록됨.
    		   System.out.println("1-1,2");
    		   dto.setImagename1(fileInfo.get(0));
    		   dto.setImg_path1(path);
    		   
    		   lastImgNum = 2;
    		   System.out.println(fileNum + " : 추가된 이미지 하나(" + dto.getImg_path1() + dto.getImagename1() + ")");
    	   }
       }
        
      //2. 1번 칸이 비었는데 2번칸도 빈 경우(둘다 빔)
        	//1. 이미지가 0개 -> 모두 null로 입력 - 0
    		//2. 이미지가 1개 -> 1번 내용에 이미지 입력 2번은 null로 입력 - x
    		//3. 이미지가 2개 -> 두 개에 모두 덮어버림 - 0
       else if((originalName1==null || newFileName1.equals("") == true) && (origialName2 == null || newFileName2.equals("") == true)) {
    	 //1번이 비어있거나 원래 없었고!! 2번도 지워졌거나 없었던 경우.
    	   System.out.println("2 들어옴!!");
    	   if(fileNum == 0) {//이미지가 0개인 경우 이미지 경로를 모두 초기화 하기 위해 모두 null로 교체한다.
    		   System.out.println("2-0");
    		   dto.setImage1(null);
    		   dto.setImagename1(null);
    		   dto.setImg_path1(null);
    		   
    		   dto.setImage2(null);
    		   dto.setImagename2(null);
    		   dto.setImg_path2(null);
    		   
    		   lastImgNum = 0;
    	   }
    	   else if(fileNum == 1) {//이미지 1개인 경우 1번 칸에 이미지를 넣고 2번 칸은 모두 null로 초기화
    		   System.out.println("2-1");
    		   dto.setImagename1(fileInfo.get(0));
    		   dto.setImg_path1(path);
    		   System.out.println(fileNum + " : 추가된 이미지 1개중 1번째(" + path + fileInfo.get(0) + ")");
    		   
    		   dto.setImage2(null);
    		   dto.setImagename2(null);
    		   dto.setImg_path2(null);
    		   
    		   lastImgNum = 1;
    	   }
    	   else if(fileNum == 2) {//이미지 두개인 경우 새로 덮어씌워버림
    		   System.out.println("2-2");
    		   dto.setImagename1(fileInfo.get(0));
    		   dto.setImg_path1(path);
    		   System.out.println(fileNum + " : 추가된 이미지 2개중 1번째(" + dto.getImg_path1() + dto.getImagename1() + ")");
        
    		   dto.setImagename2(fileInfo.get(1));
    		   dto.setImg_path2(path);
    		   System.out.println(fileNum + " : 추가된 이미지 2개중 2번째(" + dto.getImg_path2() + dto.getImagename2() + ")");
    		   
    		   lastImgNum = 2;
    	   }
       }
              
       //3. 1번 칸 안비고 2번칸 비어 있는 경우(2번만 빔)
        	//1. 이미지가 0개 -> 2번 내용에 null로 입력 - x
    		//2. 이미지가 1개 -> 2번에 이미지 입력 - 0
    		//3. 이미지가 2개 -> 2번에 첫번째 이미지만 입력 - 0
       else if((originalName1!=null && newFileName1.equals("") == false) && (origialName2 == null || newFileName2.equals(""))) {
    	   System.out.println("3 들어옴!!");
    	   //1번 칸이 원래 비어있지 않았고 지워지지도 않았고!!! 2번 칸이 비어있거나 원래 없는 경우
    	   if(fileNum == 0) {//2번 내용에 null입력
    		   System.out.println("3-0");
    		   dto.setImage2(null);
    		   dto.setImagename2(null);
    		   dto.setImg_path2(null);
    		   
    		   lastImgNum = 1;
    	   }
    	   else if(fileNum >= 1) {//이미지가 하나 이상이상이면 첫 번째 이미지만 입력한다. 어차피 한 자리 있으니까 들어갈 자리는 하나뿐임...ㅇㅇ...
    		   System.out.println("3-1,2");
    		   dto.setImagename2(fileInfo.get(0));
    		   dto.setImg_path2(path);
    		   System.out.println(fileNum + " : 추가된 이미지 1개중 1번째(" + dto.getImg_path2() + dto.getImagename2() + ")");
    		   
    		   lastImgNum = 2;
    	   }
       }                
       //4. 1번 칸 안비고 2번칸 안 빈 경우(둘다 참)
        	//1. 이미지가 0개 -> 글만 수정하고 패스!!!
       else if((originalName1!=null || newFileName1.equals("") == false) && (origialName2 != null || newFileName2.equals("") == false)) {
    	   //1번이 원래 있었는데다 지워지지 앟았고!! 2번도 원래 있었고 지워지지 않은!!!!
    	   System.out.println("이미지가 더이상 추가 불가하거나 변경될 이미지가 없음..ㅇㅂㅇㅋㅋㅋ꺄르르르!!!");
    	   lastImgNum = 2;
       }
       else {
    	   System.out.println("왠일인지 낯설지가 않아.. 잘 된다 싶었다... ㅅㅂ..");
    	   lastImgNum = 404;
       }

      dao.modifyTipBoard(dto, boardNumber, lastImgNum);  
	}
}
