package bit.or.kr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GoController {
	
	@RequestMapping("/index.htm")
	public String index() {
		return "index";
	}

	@RequestMapping("/echo-ws.htm")
	public String echo_ws() {
		return "echo-ws";
	}
	
	@RequestMapping("/chat-ws.htm")
	public String chat_ws() {
		return "chat-ws";
	}

}
