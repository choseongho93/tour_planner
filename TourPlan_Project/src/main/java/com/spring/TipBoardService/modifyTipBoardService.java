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
		
		//�̹��� ���������� �̰� ��ߵǴ�����.
		//�ٸ� ����� ���� �����Ͱ� �Ȱ��� �̹����� �߰��� ��츸 ����մϴ�.
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)map.get("request");
		
		HttpSession session = request.getSession(true);
		Tip_board_DAO dao = new Tip_board_DAO();
		Tip_board_DTO dto = new Tip_board_DTO();
			
		List<MultipartFile> fileList = request.getFiles("file");
		String boardNumber = request.getParameter("boardnumber");//�� ��° �Խñ����� Ȯ��
		
		dto = dao.selectTipView(boardNumber);//������ ������ �����´�.
		
		String nickname = (String)session.getAttribute("userNickName");
		String id = (String)session.getAttribute("userId");
		String subject = request.getParameter("indexOfModiTipBoard");
		String title = request.getParameter("ModiTipBoardTitle");
		String content = request.getParameter("modiBoardContent");
		
	//	System.out.println("nickname : " + nickname);
	//	System.out.println("id : " + id);
	//	System.out.println("subject : " + subject);
	//	System.out.println("title : " + title);
	//	System.out.println("���� �� content : " + content);
	
        dto.setTitle(title);//����
    //    dto.setNickname(nickname);//�г���
    //    dto.setId(id);//���̵�
        dto.setSubject(subject);//�з�
        dto.setContent(content);//����
 
        System.out.println("���� ����Ʈ ũ�� : " + fileList.size());
        
       //�̹����� ���� �������� �ƴϸ� ������� Ȯ���� �ϱ� ���� ����
        String originalName1 = request.getParameter("hiddenFileName1");//ù��°ĭ �������� �̸�
        String origialName2 = request.getParameter("hiddenFileName2");//�ι�°ĭ �������� �̸�
        String newFileName1 = request.getParameter("attatchment1");//ù��°ĭ ����� �̸�
        String newFileName2 = request.getParameter("attatchment2");//�ι�°ĭ ����� �̸�
        
       System.out.println("origialName1 : " + originalName1 + " / origialName2 : " + origialName2);
       System.out.println("newFileName1 : " + newFileName1 + " / newFileName2 : " + newFileName2);
        
        //========���� ���ε��� ������ �����ϴ��� ���ο� ����========================
        //���ε��ϱ�� �� �̹����� ������ ����Ǵ� �������� �̹����� ����Ǵ� ��ġ��� ���ø� �˴ϴ�.
        String path = "E:\\8�� �ڹ� �������� �缺(������)\\(2019.01.02 ~ 2019.02.13) ������Ʈ\\���ε� �̹���\\";//�̹��� ����Ǵ� ��ġ
        
        //====�ϴ� �̹����� �޾Ƶΰ� ����� ���� ���� �ٸ��� �̹����� �ֵ��� �ϰڴ�...-��-....
        int[] file = null;
        int fileNum = 0;//���� � ���Դ��� Ȯ��
        
        ArrayList<String> fileInfo = new ArrayList<String>();//���� ������ ����.
        
        for (MultipartFile mf : fileList) {//���� �̹��� ������ �ϳ��� ó������ �ݺ���
            String originFileName = mf.getOriginalFilename(); // ���� ���� ��
            long fileSize = mf.getSize(); // ���� ������

            //�Էµƴ� ���� ���� �̸��� ���� ũ��
            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            String safeFile = System.currentTimeMillis() + originFileName;//�տ� �ð��� �ٿ��� ���� ������ �ö󰡵� �̸��� �������� �ʾƿ�.
            fileInfo.add(safeFile);
            fileNum++;
            
            if(originFileName == null || fileSize == 0)
            //�̹����� ������ �ʾҴµ��� 1�� �Ǵ� ��찡 �߻��Ͽ� �ش� ��쿡 ������ ��� ���� 0���� �ʱ�ȭ��.
            	fileNum = 0;
            
            //�̹����� ���� �������� ���� ��� ����
            else {
            	String saveFile = path + System.currentTimeMillis() + originFileName;
                try {
                    mf.transferTo(new File(saveFile));//���� Path ��ο� �̹����� �߰��ϰ� �ش� �̹����� DB�� ���ε���
                    //�ҽ� ��ĥ�� ���� Path�� ��ġ�� �� ������ �������ּ���.\\���� �޾��ּž� �ؿ�.
                    //�̰� ������ DB�� ���� ���ϴϱ� ���� ��Ź�����.
                    
                } catch (IllegalStateException e) {e.printStackTrace();} 
                catch (IOException e) {e.printStackTrace();}        
            }  
        }
        //======������ �������� ���� �̹����� �ϴ� �� �ޱ� ��. ���� ������ �Ʒ�����..
    
       //�̹����� ���� ���� null�� ǥ�õǰ� �̹����� ���� ���� ������ �ȴ�
       // origialName1 : 1547548870247cigago.png / origialName2 : null(����� ��)
       // newFileName1 :  / newFileName2 : null(���� ��)
       
        System.out.println("fileNum(�̹��� ���� ����) : " + fileNum); 
        int lastImgNum = 0;
        
       //1. 1�� ĭ�� ����µ� 2��ĭ�� �� �� ���(1���� ��)
        	//1. �̹����� 0�� -> 2�� ������ 1�� �̵� �� 2��ĭ null - x
        	//2. �̹����� 1�� -> 1�� ���뿡 �̹��� �Է� - 0
        	//3. �̹����� 2�� -> 1��ĭ �̹����� �Է� - 0
        
        boolean bool = true;
        
        if(originalName1 == null) {
        	bool = true;
        }else {
        	bool = false;
        }
        
     //   System.out.println("originalName1�� null�ΰ� : " + bool);//null�� �ƴ�.
      //  System.out.println("newFileName1.equals('') : " + newFileName1.equals(""));//true
        
       if((originalName1==null || newFileName1.equals("")) && (origialName2 != null && newFileName2.equals("") == false))//�̹����� �����ų� �ִٰ� ���� ���
   	   //if(newFileName1.equals("") && origialName2 != null)//�̹����� �����ų� �ִٰ� ���� ���
       {//1��ĭ�� ���ų� ��������!! 2��ĭ�� ���� �����߾��� �������� ���� ���
   		   System.out.println("1 ����!!");
    	   if(fileNum == 0) {//�̹��� 0���� ���
    		   System.out.println("1-0");
    		   //2�� ������ 1������ �̵�
    		   dto.setImage1(dto.getImage2());
    		   dto.setImagename1(dto.getImagename2());
    		   dto.setImg_path1(dto.getImg_path2());
    		   
    		   //2�� ĭ�� ��� null�� ��ü�Ѵ�.
    		   dto.setImage2(null);
    		   dto.setImagename2(null);
    		   dto.setImg_path2(null);
    		   
    		   lastImgNum = 1;
    	   }
    	   else if(fileNum >= 1) {//�̹����� �Ѱ� �̻� �������� �� ������ �ϳ� ���̹Ƿ� ���� �Էµ� �̹��� �ϳ��� ���� ��ϵ�.
    		   System.out.println("1-1,2");
    		   dto.setImagename1(fileInfo.get(0));
    		   dto.setImg_path1(path);
    		   
    		   lastImgNum = 2;
    		   System.out.println(fileNum + " : �߰��� �̹��� �ϳ�(" + dto.getImg_path1() + dto.getImagename1() + ")");
    	   }
       }
        
      //2. 1�� ĭ�� ����µ� 2��ĭ�� �� ���(�Ѵ� ��)
        	//1. �̹����� 0�� -> ��� null�� �Է� - 0
    		//2. �̹����� 1�� -> 1�� ���뿡 �̹��� �Է� 2���� null�� �Է� - x
    		//3. �̹����� 2�� -> �� ���� ��� ������� - 0
       else if((originalName1==null || newFileName1.equals("") == true) && (origialName2 == null || newFileName2.equals("") == true)) {
    	 //1���� ����ְų� ���� ������!! 2���� �������ų� ������ ���.
    	   System.out.println("2 ����!!");
    	   if(fileNum == 0) {//�̹����� 0���� ��� �̹��� ��θ� ��� �ʱ�ȭ �ϱ� ���� ��� null�� ��ü�Ѵ�.
    		   System.out.println("2-0");
    		   dto.setImage1(null);
    		   dto.setImagename1(null);
    		   dto.setImg_path1(null);
    		   
    		   dto.setImage2(null);
    		   dto.setImagename2(null);
    		   dto.setImg_path2(null);
    		   
    		   lastImgNum = 0;
    	   }
    	   else if(fileNum == 1) {//�̹��� 1���� ��� 1�� ĭ�� �̹����� �ְ� 2�� ĭ�� ��� null�� �ʱ�ȭ
    		   System.out.println("2-1");
    		   dto.setImagename1(fileInfo.get(0));
    		   dto.setImg_path1(path);
    		   System.out.println(fileNum + " : �߰��� �̹��� 1���� 1��°(" + path + fileInfo.get(0) + ")");
    		   
    		   dto.setImage2(null);
    		   dto.setImagename2(null);
    		   dto.setImg_path2(null);
    		   
    		   lastImgNum = 1;
    	   }
    	   else if(fileNum == 2) {//�̹��� �ΰ��� ��� ���� ���������
    		   System.out.println("2-2");
    		   dto.setImagename1(fileInfo.get(0));
    		   dto.setImg_path1(path);
    		   System.out.println(fileNum + " : �߰��� �̹��� 2���� 1��°(" + dto.getImg_path1() + dto.getImagename1() + ")");
        
    		   dto.setImagename2(fileInfo.get(1));
    		   dto.setImg_path2(path);
    		   System.out.println(fileNum + " : �߰��� �̹��� 2���� 2��°(" + dto.getImg_path2() + dto.getImagename2() + ")");
    		   
    		   lastImgNum = 2;
    	   }
       }
              
       //3. 1�� ĭ �Ⱥ�� 2��ĭ ��� �ִ� ���(2���� ��)
        	//1. �̹����� 0�� -> 2�� ���뿡 null�� �Է� - x
    		//2. �̹����� 1�� -> 2���� �̹��� �Է� - 0
    		//3. �̹����� 2�� -> 2���� ù��° �̹����� �Է� - 0
       else if((originalName1!=null && newFileName1.equals("") == false) && (origialName2 == null || newFileName2.equals(""))) {
    	   System.out.println("3 ����!!");
    	   //1�� ĭ�� ���� ������� �ʾҰ� ���������� �ʾҰ�!!! 2�� ĭ�� ����ְų� ���� ���� ���
    	   if(fileNum == 0) {//2�� ���뿡 null�Է�
    		   System.out.println("3-0");
    		   dto.setImage2(null);
    		   dto.setImagename2(null);
    		   dto.setImg_path2(null);
    		   
    		   lastImgNum = 1;
    	   }
    	   else if(fileNum >= 1) {//�̹����� �ϳ� �̻��̻��̸� ù ��° �̹����� �Է��Ѵ�. ������ �� �ڸ� �����ϱ� �� �ڸ��� �ϳ�����...����...
    		   System.out.println("3-1,2");
    		   dto.setImagename2(fileInfo.get(0));
    		   dto.setImg_path2(path);
    		   System.out.println(fileNum + " : �߰��� �̹��� 1���� 1��°(" + dto.getImg_path2() + dto.getImagename2() + ")");
    		   
    		   lastImgNum = 2;
    	   }
       }                
       //4. 1�� ĭ �Ⱥ�� 2��ĭ �� �� ���(�Ѵ� ��)
        	//1. �̹����� 0�� -> �۸� �����ϰ� �н�!!!
       else if((originalName1!=null || newFileName1.equals("") == false) && (origialName2 != null || newFileName2.equals("") == false)) {
    	   //1���� ���� �־��µ��� �������� �۾Ұ�!! 2���� ���� �־��� �������� ����!!!!
    	   System.out.println("�̹����� ���̻� �߰� �Ұ��ϰų� ����� �̹����� ����..��������������������!!!");
    	   lastImgNum = 2;
       }
       else {
    	   System.out.println("�������� �������� �ʾ�.. �� �ȴ� �;���... ����..");
    	   lastImgNum = 404;
       }

      dao.modifyTipBoard(dto, boardNumber, lastImgNum);  
	}
}
