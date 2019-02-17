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
		
		//�̹��� ���������� �̰� ��ߵǴ�����.
		//�ٸ� ����� ���� �����Ͱ� �Ȱ��� �̹����� �߰��� ��츸 ����մϴ�.
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
		//�ٶ��� �״�� �ٿ� �־���
		String subject = request.getParameter("subject");
		System.out.println(subject);
		
		System.out.println("nickname : " + nickName);
		System.out.println("id : " + id);
		System.out.println("subject : " + subject);
		System.out.println("title : " + title);
		System.out.println("���� �� content : " + content);
		
		Suggestion_board_DTO dto = new Suggestion_board_DTO();
        
        dto.setTitle(title);
        dto.setNickname(nickName);
        dto.setId(id);
        dto.setSubject(subject);
        
      //  content.replace("\r\n", "<br>");
      //  System.out.println("���Ҵ�!!");
       // content.replace("\n", "<br>");
        //System.out.println("���� �� : " + content);
       
        dto.setContent(content);
        
        System.out.println("���� ����Ʈ ũ�� : " + fileList.size());
        
        //���ε��ϱ�� �� �̹����� ������ ����Ǵ� �������� �̹����� ����Ǵ� ��ġ��� ���ø� �˴ϴ�.
        String path = "E:\\8�� �ڹ� �������� �缺(������)\\(2019.01.02 ~ 2019.02.13) ������Ʈ\\���ε� �̹���\\";//�̹��� ����Ǵ� ��ġ
        
        int fileNum = 0;//���� � ���Դ��� Ȯ��
        ArrayList<String> fileInfo = new ArrayList<String>();
        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); // ���� ���� ��
            long fileSize = mf.getSize(); // ���� ������

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            String safeFile = System.currentTimeMillis() + originFileName;//�տ� �ð��� �ٿ��� ���� ������ �ö󰡵� �̸��� �������� �ʾƿ�.
            fileInfo.add(safeFile);
            fileNum++;
            
            if(originFileName == null || fileSize == 0)
            	fileNum = 0;
            else {
            	String saveFile = path + System.currentTimeMillis() + originFileName;
                try {
                    mf.transferTo(new File(saveFile));//���� Path ��ο� �̹����� �߰��ϰ� �ش� �̹����� DB�� ���ε���
                    //�ҽ� ��ĥ�� ���� Path�� ��ġ�� �� ������ �������ּ���.\\���� �޾��ּž� �ؿ�.
                    //�̰� ������ DB�� ���� ���ϴϱ� ���� ��Ź�����.
                    
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
        
        if(fileNum == 0) {//�̹��� ������ ���� ���(���� �ȳ־ 1�� �Ǳ淡 �����ϴ� ���� �߰��Ͽ� ������)
        	try {
    			dao.insertTipBoard(dto, fileNum);
    			System.out.println(fileNum + " : �̹��� ����.");
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        else if(fileNum == 1) {//�̹��� ���� �ϳ��� ���
        	dto.setImagename1(fileInfo.get(0));
        	dto.setImg_path1(path);
        	
        	 try {
     			dao.insertTipBoard(dto, fileNum);
     			System.out.println(fileNum + " : �̹��� �ϳ�(" + dto.getImg_path1() + dto.getImagename1() + ")");
     		} catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        }
        
        else if(fileNum == 2) {//�̹��� ���� �ΰ��� ���
        	dto.setImagename1(fileInfo.get(0));
        	dto.setImg_path1(path);
     
        	dto.setImagename2(fileInfo.get(1));
        	dto.setImg_path2(path);
        	
        	 try {
     			dao.insertTipBoard(dto, fileNum);
     			System.out.println(fileNum + " : �̹��� �� �� ù��°(" + dto.getImg_path1() + dto.getImagename1() + ")");
     			System.out.println(fileNum + " : �̹��� �� �� �ι�°(" + dto.getImg_path2() + dto.getImagename2() + ")");
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


