package bit.or.kr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.or.kr.dto.Member;
import bit.or.kr.service.JoinService;

@Controller
@RequestMapping
public class JoinController {
	
	@Autowired
	private JoinService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value="/join.htm", method=RequestMethod.GET)
	public String join() {
		return "chat.login";
	}
	
	@RequestMapping(value="/join.htm", method=RequestMethod.POST)
	public String logincheck(Member member, Model model) {
		int result = 0;
		String viewpage = "";
		
		Member member2 = service.select(member.getUserid());
		
	    if (member2 == null) {
	    	member2 = new Member(member.getUserid(), member.getPassword(), "ROLE_USER");
	    	member.setPassword(this.bCryptPasswordEncoder.encode(member.getPassword()));
	    	
	    	result = service.insert(member);
	    }
	    
	    model.addAttribute("userid", member.getUserid());
	    model.addAttribute("password", member.getPassword());
		return "chat.loginstart";
	}
	
}
