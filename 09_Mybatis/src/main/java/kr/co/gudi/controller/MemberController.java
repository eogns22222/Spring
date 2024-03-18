package kr.co.gudi.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.gudi.service.MemberService;

@Controller
public class MemberController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberService service; // @Service 어노테이션이 있어야 이렇게 사용 가능
	
	@RequestMapping(value = "/")
	public String main() {
		logger.info("메인 요청");
		return "joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, HttpServletRequest req) throws UnsupportedEncodingException {
		
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		logger.info(id + " " + pw + " " + name + " " + age + " " + gender + " " + email);
		
		String msg = "회원등록에 실패 하였습니다.";
		
		if(service.join(id, pw, name, age, gender, email) == 1) {
			msg = "회원 등록에 성공 했습니다.";
		}
		
		model.addAttribute("msg", msg);
		
		return "joinForm";
	}
}


























