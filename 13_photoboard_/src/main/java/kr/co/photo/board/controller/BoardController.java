package kr.co.photo.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.photo.board.dto.BoardDTO;
import kr.co.photo.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired BoardService service;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//리스트불러오기
	@RequestMapping(value="/list")
	public String list(Model model,HttpSession session) {
		logger.info("[리스트] 불러오기 시도");
		String page = "login";
		String msg = "로그인이 필요한 서비스 입니다.";
		String id = (String) session.getAttribute("loginId");
		
		if(id!=null){
			page= "list";
			List<BoardDTO> list = service.list();
			model.addAttribute("list",list);
			model.addAttribute("loginBox","<div>안녕하세요"+id+"님<a href='logout'>로그아웃</a><div>");
		}else {
			model.addAttribute("msg","로그인이 필요한 서비스 입니다.");
		}
			
		return page;
	}
	
	//삭제 하기
	@RequestMapping(value="/del")
	public String del(HttpSession session, String idx){
		logger.info("삭제요청");
		String page = "redirect:/";
		if(session.getAttribute("loginId")!=null) {
			service.del(idx);
		
			page="redirect:/list";
		}
		
		return page;
		
	}

	//글쓰기
	@RequestMapping(value="/writeForm")
	public String writeForm() {
		
		return "writeForm";
	}
	
	// multipart 는 다른 파라메터 보다 앞에 있어야 함
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(MultipartFile[] photos, HttpSession session, @RequestParam Map<String,String>param) {
		logger.info("글작성 요청");
		String page = "redirect:/list";
		if(session.getAttribute("loginId")!=null) {
			int row = service.write(photos, param);
			if(row < 1) {
				page ="writeForm";
			}
		}
		
		return page;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/detail")
	public String detail(String idx, HttpSession session, Model model) {
		String page="login";
		logger.info("detail idx="+idx);
		
		if(session.getAttribute("loginId")!= null) {
			page = "detail";
//			BoardDTO dto = service.detail(idx);
//			model.addAttribute("bbs", dto);
			// model 줄테니 여기에 bbs 와 photos 담아와라
			service.detail(idx, model);
		}
		
		return page;
	}
	
	// 수정 페이지
	@RequestMapping(value = "/updateForm")
	public String updateForm(HttpSession session, Model model, String idx) {
		logger.info("수정 페이지");
		String page = "login";
		
		if(session.getAttribute("loginId")!= null) {
			page = "updateForm";
			service.updateForm(idx, model);
		}
		
		return page;
	}
	
	// 수정
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(HttpSession session, @RequestParam Map<String,String> param) {
//		logger.info("수정");
//		String page = "redirect:/list";
//		
//		if(session.getAttribute("loginId") != null) {
//			page = "redirect:/detail?idx=" + param.get("idx");
//			service.update(param);
//		}
//		
//		return page;
//	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MultipartFile[] photos, HttpSession session, @RequestParam Map<String,String> param) {
		logger.info("수정");
		String page = "redirect:/list";
		
		if(session.getAttribute("loginId") != null) {
			page = "redirect:/detail?idx=" + param.get("idx");
			service.update(photos, param);
		}
		
		return page;
	}
	
	
}





























