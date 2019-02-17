package com.spring.suggestionBoard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spirng.DAO.Suggestion_board_DAO;
import com.spirng.DAO.Suggestion_board_DTO;

public class Write_Suggestion_board implements Suggestion_boardService{

	@Override
	public int execute01(Model model) {
		return 0;
	}

	@Override
	public void execute(Model model){
		// TODO Auto-generated method stub		
		Map<String, Object> map = model.asMap();
		
		//이미지 가져오려면 이거 써야되더라고요.
		//다른 기능은 원래 쓰던것과 똑같고 이미지가 추가될 경우만 사용합니다.
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)map.get("request");
		
		HttpSession session = request.getSession(true);
		
		List<MultipartFile> fileList = request.getFiles("image");
		
		String id = (String) session.getAttribute("UserID");
		System.out.println("id : "+id);
		String nickName = (String) session.getAttribute("UserNickname");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(content);
		content = content.replace("\r\n","<br>");
		//줄띄어쓰기 그대로 붙여 넣어짐
		String subject = request.getParameter("subject");
		System.out.println(subject);
		
		System.out.println("nickname : " + nickName);
		System.out.println("id : " + id);
		System.out.println("subject : " + subject);
		System.out.println("title : " + title);
		System.out.println("수정 전 content : " + content);
		
		Suggestion_board_DTO dto = new Suggestion_board_DTO();
        
        dto.setTitle(title);
        dto.setNickname(nickName);
        dto.setId(id);
        dto.setSubject(subject);
        
      //  content.replace("\r\n", "<br>");
      //  System.out.println("돌았다!!");
       // content.replace("\n", "<br>");
        //System.out.println("수정 후 : " + content);
       
        dto.setContent(content);
        
        System.out.println("파일 리스트 크기 : " + fileList.size());
        
        //업로드하기로 한 이미지가 서버에 저장되는 과정에서 이미지가 저장되는 위치라고 보시면 됩니다.
        String path = "E:\\8월 자바 웹개발자 양성(백현정)\\(2019.01.02 ~ 2019.02.13) 프로젝트\\업로드 이미지\\";//이미지 저장되는 위치
        
        int fileNum = 0;//파일 몇개 들어왔는지 확인
        ArrayList<String> fileInfo = new ArrayList<String>();
        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            String safeFile = System.currentTimeMillis() + originFileName;//앞에 시간을 붙여서 같은 파일이 올라가도 이름이 겹쳐지지 않아요.
            fileInfo.add(safeFile);
            fileNum++;
            
            if(originFileName == null || fileSize == 0)
            	fileNum = 0;
            else {
            	String saveFile = path + System.currentTimeMillis() + originFileName;
                try {
                    mf.transferTo(new File(saveFile));//위의 Path 경로에 이미지를 추가하고 해당 이미지를 DB에 업로드함
                    //소스 합칠때 위에 Path를 합치는 분 폴더로 변경해주세요.\\까지 달아주셔야 해요.
                    //이거 빠지면 DB에 저장 못하니까 주의 부탁드려요.
                    
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }        
            }  
        }
        
        System.out.println("fileNum : " + fileNum);
        
        Suggestion_board_DAO dao = new Suggestion_board_DAO();
        
        if(fileNum == 0) {//이미지 파일이 없는 경우(파일 안넣어도 1이 되길래 제거하는 값을 추가하여 연산함)
        	try {
    			dao.insertTipBoard(dto, fileNum);
    			System.out.println(fileNum + " : 이미지 없음.");
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        else if(fileNum == 1) {//이미지 파일 하나인 경우
        	dto.setImagename1(fileInfo.get(0));
        	dto.setImg_path1(path);
        	
        	 try {
     			dao.insertTipBoard(dto, fileNum);
     			System.out.println(fileNum + " : 이미지 하나(" + dto.getImg_path1() + dto.getImagename1() + ")");
     		} catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        }
        
        else if(fileNum == 2) {//이미지 파일 두개인 경우
        	dto.setImagename1(fileInfo.get(0));
        	dto.setImg_path1(path);
     
        	dto.setImagename2(fileInfo.get(1));
        	dto.setImg_path2(path);
        	
        	 try {
     			dao.insertTipBoard(dto, fileNum);
     			System.out.println(fileNum + " : 이미지 둘 중 첫번째(" + dto.getImg_path1() + dto.getImagename1() + ")");
     			System.out.println(fileNum + " : 이미지 둘 중 두번째(" + dto.getImg_path2() + dto.getImagename2() + ")");
     		} catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        }
	}

	@Override
	public int register(MultipartHttpServletRequest rquest) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}
}


