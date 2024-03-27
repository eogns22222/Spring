package kr.co.photo.board.controller;

import java.util.Arrays;
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
import kr.co.photo.member.dto.MemberDTO;

@Controller
public class BoardController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	BoardService service;

	@RequestMapping(value = "/list")
	public String list(HttpSession session, Model model) {
		String page = "login";
		logger.info("list 드왔다");
		MemberDTO info = (MemberDTO) session.getAttribute("loginInfo");
		
		if (info != null) {
			page = "list";
			List<BoardDTO> list = service.list();
			logger.info("글 갯수(리스트 사이즈) : " + list.size());
			model.addAttribute("list", list);
			model.addAttribute("loginBox", "<div>Hi, " + info.getId() + "님 <a href='logout'>로그아웃</a></div>");
		} else {
			model.addAttribute("msg", "로그인이 필요한 서비스 입니다.");
		}

		return page;
	}

	// 000.go <- ~ 하는 페이지로 가줘
	// 000.do 또는 000.action <- ~ 해줘
	@RequestMapping(value = "/write.go")
	public String writeGo() {
		return "writeForm";
	}
	
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String writeDo(MultipartFile[] photos, HttpSession session, @RequestParam Map<String,String> params, Model model) {
		logger.info("params : {}", params);
		logger.info("photos : {}", Arrays.toString(photos));
		String page = "redirect:/list";
		
		if(session.getAttribute("loginId") != null) {
			page = service.write(photos, params);
		}
		
		return page;
	}
	
}




























