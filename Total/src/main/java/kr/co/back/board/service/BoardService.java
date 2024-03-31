package kr.co.back.board.service;

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
import org.springframework.web.multipart.MultipartFile;

import kr.co.back.board.dao.BoardDAO;
import kr.co.back.board.dto.BoardDTO;

@Service
public class BoardService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardDAO boardDAO;
	String file_root = "C:/upload/";
	
	public Map<String, Object> list(int currPage, int pagePerNum) {
		
		int start = (currPage - 1) * pagePerNum;
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<BoardDTO> list = boardDAO.list(pagePerNum, start);
		result.put("list", list);
		result.put("currPage", currPage);
		result.put("totalPages", boardDAO.allCount(pagePerNum));
		
		return result;
	}

	public int write(MultipartFile[] photos, Map<String, String> param) {
		int row = 0;
		
		BoardDTO dto = new BoardDTO();
		dto.setSubject(param.get("subject"));
		dto.setUser_name(param.get("user_name"));
		dto.setContent(param.get("content"));
		
		row = boardDAO.write(dto);
		
		int idx = dto.getIdx();
		
		if(row > 0) {
			fileSave(idx, photos);
		}
		
		return row;
	}

	private void fileSave(int idx, MultipartFile[] photos) {
		
		for (MultipartFile photo : photos) {
			String file_name = photo.getOriginalFilename();
			
			if(!file_name.equals("")) {
				String ext = file_name.substring(file_name.lastIndexOf("."));
				logger.info("ext : " + ext);
				String new_fileName = System.currentTimeMillis() + ext;
				try {
					byte[] bytes = photo.getBytes();
					Path path = Paths.get(file_root + new_fileName);
					Files.write(path, bytes);
					boardDAO.writeFile(file_name, new_fileName, idx);
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}























