package kr.ajax.board.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ajax.board.dao.BoardDAO;
import kr.ajax.board.dto.BoardDTO;
import kr.ajax.board.dto.PhotoDTO;

@Service
public class BoardService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired BoardDAO boardDAO;
	private String file_root = "C:/upload/";
	
	public List<BoardDTO> list() {
		return boardDAO.list();
	}

//	public int del(List<String> delList) {
//		int cnt = 0;
//		List<PhotoDTO> list = new ArrayList<PhotoDTO>();
//		for (String s : delList) {
//			
//			// 1. 게시글에 연결된 파일명 확보
//			list = boardDAO.photos(s);
//			logger.info("list : {}", list);
//			// 2. bbs 에서 해당 글 삭제
//			boardDAO.del(s);
//		}
//		// 3. 파일 삭제
//		for (PhotoDTO photo : list) {
//			
//			Path path = Paths.get(file_root + photo.getNew_filename());
//			try {
//				Files.delete(path);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		
//		
//		return cnt;
//	}
	
	public int del(List<String> delList) {
		int cnt = 0;
		for (String idx : delList) {
			
			// 1. 게시글에 연결된 파일명 확보
			List<String> files = boardDAO.getFiles(idx);
			cnt += boardDAO.del2(idx); // 2. bbs 에서 해당 글 삭제
			// 3. 파일  삭제
			logger.info("files : []", files);
			delFile(files);
		}
		
		return cnt;
	}

	private void delFile(List<String> files) {
		for (String name : files) {
			File file = new File(file_root + name);
			if(file.exists()) {
				file.delete();
			}
		}
	}
	
}



























