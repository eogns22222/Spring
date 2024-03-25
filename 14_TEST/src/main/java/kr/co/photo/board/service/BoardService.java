package kr.co.photo.board.service;

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
	String file_root = "C:/upload/";
	
	public List<BoardDTO> list() {
		return dao.list();
	}

	public void write(Map<String, String> param, MultipartFile[] photos) {
		int row = -1;
		
		BoardDTO dto = new BoardDTO();
		dto.setSubject(param.get("subject"));
		dto.setUser_name(param.get("user_name"));
		dto.setContent(param.get("content"));
		
		row = dao.write(dto);
		int idx = dto.getIdx();
		logger.info("idx : " + idx);
		
		if(row > 0) {
			fileSave(photos, idx);
		}
		
	}

	private void fileSave(MultipartFile[] photos, int idx) {
		
		for (MultipartFile photo : photos) {
			
			String fileName = photo.getOriginalFilename();
			logger.info("fileName : " + fileName);
			
			if(!fileName.equals("")) {
				String ext = fileName.substring(fileName.lastIndexOf("."));
				logger.info(ext);
				
				String new_fileName = System.currentTimeMillis() + ext;
				
				try {
					byte[] bytes = photo.getBytes();
					Path path = Paths.get(file_root + new_fileName);
					Files.write(path, bytes);
					dao.writeFile(fileName, new_fileName, idx);
					Thread.sleep(1);
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
		model.addAttribute("photos", list);
	}

	public void updateForm(String idx, Model model) {
		BoardDTO dto = dao.detail(idx);
		model.addAttribute("bbs", dto);
		List<PhotoDTO> list = dao.photos(idx);
		model.addAttribute("photos", list);
		
	}

	public void update(Map<String, String> param, MultipartFile[] photos) {
		int row = -1;
		
		row = dao.update(param);
		
		if(row > 0) {
			int idx = Integer.parseInt(param.get("idx"));
			
			fileSave(photos, idx);
		}
		
	}

	public void del(String idx) {
		
		List<PhotoDTO> list = dao.photos(idx);
		
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
	
}




































