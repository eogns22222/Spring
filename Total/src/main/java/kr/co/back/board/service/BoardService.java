package kr.co.back.board.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kr.co.back.board.dao.BoardDAO;
import kr.co.back.board.dto.BoardDTO;
import kr.co.back.board.dto.PhotoDTO;

@Service
public class BoardService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardDAO boardDAO;
	
	String file_root = "C:/upload/";
	
	public Map<String, Object> list(int currPage, int pagePerCnt) {
		
		int start = (currPage - 1) * pagePerCnt;
		
		List<BoardDTO> list = boardDAO.list(pagePerCnt, start);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("currPage", currPage);
		result.put("totalPage", boardDAO.allCnt(pagePerCnt));
		
		return result;
	}

	public int del(List<String> delList) {
		int cnt = 0;
		
		for (String n : delList) {
			
			List<String> list = boardDAO.getFiles(n);
			logger.info("list : {}", list);
			
			cnt += boardDAO.del(n);
			
			delFile(list);
			
		}
		
		return cnt;
	}

	private void delFile(List<String> list) {
		
		for (String s : list) {
			File file = new File(file_root + s);
			if(file.exists()) {
				file.delete();
			}
		}
		
	}

	public void write(MultipartFile[] photos, Map<String, String> param) {
		int row = -1;
		
		BoardDTO dto = new BoardDTO();
		dto.setSubject(param.get("subject"));
		dto.setContent(param.get("content"));
		dto.setUser_name(param.get("user_name"));
		
		row = boardDAO.write(dto);
		
		int idx = dto.getIdx();
		
		if(row > 0) {
			fileSave(idx, photos);
		}
		
	}

	private void fileSave(int idx, MultipartFile[] photos) {
		
		for (MultipartFile photo : photos) {
			
			String file_name = photo.getOriginalFilename();
			
			if(!file_name.equals("")) {
				
				String ext = file_name.substring(file_name.lastIndexOf("."));
				
				String new_fileName = System.currentTimeMillis() + ext;
				
				try {
					byte[] bytes = photo.getBytes();
					Path path = Paths.get(file_root + new_fileName);
					Files.write(path, bytes);
					boardDAO.fileWrite(file_name, new_fileName, idx);
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}

	public void detail(String idx, Model model) {
		boardDAO.upHit(idx);
		
		BoardDTO dto = boardDAO.detail(idx);
		model.addAttribute("bbs", dto);
		List<PhotoDTO> list = boardDAO.photoWrite(idx);
		model.addAttribute("photos", list);
	}

	public void updateForm(String idx, Model model) {
		BoardDTO dto = boardDAO.detail(idx);
		model.addAttribute("bbs", dto);
		List<PhotoDTO> list = boardDAO.photoWrite(idx);
		model.addAttribute("photos", list);
		
	}

	public void update(MultipartFile[] photos, Map<String, String> param) {
		int row = -1;
		
		row = boardDAO.update(param);
		
		if(row > 0) {
			int idx = Integer.parseInt(param.get("idx"));
			fileSave(idx, photos);
		}
		
	}
	
	
	
}



























