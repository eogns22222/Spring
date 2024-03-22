package kr.co.photo.board.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kr.co.photo.board.dao.BoardDAO;
import kr.co.photo.board.dto.BoardDTO;
import kr.co.photo.board.dto.PhotoDTO;

@Service
public class BoardService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardDAO dao; 
	
	// 사진 불러오기시 server.xml 에 아래 내용 추가해야 함
	// <Context docBase="C:/upload" path="/photo"/>
	public String file_root = "C:/upload/";
	
	public List<BoardDTO> list() {
		logger.info("[리스트]도착");
		return dao.list();
	}

	public void del(String idx) {
		
		List<PhotoDTO> list = dao.photos(idx);
		logger.info("list : {} ", list);
		
		for (PhotoDTO photo : list) {
			Path path = Paths.get(file_root + photo.getNew_filename());
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		dao.del(idx);
		
	}

	public int write(MultipartFile[] photos, Map<String, String> param) {
		int row = -1;
		// insert 후 생성된 idx 가져오는 방법
		// 조건1. 파라메터는 DTO 로 넣을것
		BoardDTO dto = new BoardDTO();
		dto.setUser_name(param.get("user_name"));
		dto.setSubject(param.get("subject"));
		dto.setContent(param.get("content"));
		
		row = dao.write(dto); // 글쓰기 완료 후
		// 조건 3. 이후 dto 에서 저장된 키 값을 받아 온다.
		int idx = dto.getIdx();
		logger.info("idx = " + idx);
		
		if(row > 0) {
			fileSave(idx, photos); // 파일 저장
		}
		
		return row;
	}
	

	public void fileSave(int idx, MultipartFile[] photos) {
		
		for (MultipartFile photo : photos) {
			
			// 1. 업로드할 파일명이 있는가?
			String fileName = photo.getOriginalFilename();
			logger.info("fileName : " + fileName);
			
			if(!fileName.equals("")) { // 파일명이 있다면 == 업로드 파일이 있다면
				// 1. 기존 파일명에서 확장자 추출(high.gif)
				// 1.1 split 활용 방법
//			String arr[] = fileName.split("\\.");
//			String ext = "." + arr[arr.length - 1];
				// 1-2 subString 활용 방법
				String ext = fileName.substring(fileName.lastIndexOf("."));
				logger.info(ext);
				// 2. 새 파일명 생성
				String newFileName = System.currentTimeMillis() + ext;
				logger.info(fileName + " -> " + newFileName);
				// 3. 파일 저장
				try {
					byte[] bytes = photo.getBytes(); // MultipartFile 로 부터 바이너리 추출
					Path path = Paths.get(file_root + newFileName); // 저장 경로 지정
					Files.write(path, bytes); // 저장
					dao.fileWrite(fileName, newFileName, idx);
					Thread.sleep(1); // 파일명을 위해 강제 휴식 부여
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

	public void detail(String idx, Model model) {
		dao.upHit(idx);
		BoardDTO dto = dao.detail(idx);
		model.addAttribute("bbs", dto);
		List<PhotoDTO> list = dao.photos(idx);
		logger.info("list : {} ", list);
		model.addAttribute("photos", list);
	}

	public void updateForm(String idx, Model model) {
		BoardDTO dto = dao.detail(idx);
		model.addAttribute("bbs", dto);
		List<PhotoDTO> list = dao.photos(idx);
		logger.info("list : {} ", list);
		model.addAttribute("photos", list);
	}

	public void update(MultipartFile[] photos, Map<String, String> param) {
		int row = -1;
		
		row = dao.update(param); 
		
		if(row > 0) {
			int idx = Integer.parseInt(param.get("idx"));
			logger.info("idx = " + idx);
			fileSave(idx, photos); // 파일 저장
		}
		
	}
	
//	public void update(Map<String, String> param) {
//		dao.update(param);
//	}

}















