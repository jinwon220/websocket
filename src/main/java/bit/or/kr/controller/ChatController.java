package bit.or.kr.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import bit.or.kr.dto.Room;
import bit.or.kr.service.ChatService;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService service;
	
	@Autowired
	private View jsonview;
	
	@RequestMapping(value="/waitting.htm", method=RequestMethod.GET)
	public String waitting() {
		return "chat.waitting";
	}
	
	@RequestMapping(value="/waitting.htm", method=RequestMethod.POST)
	public @ResponseBody String insertroom(@RequestBody String roomname) {
		try {
			roomname = URLDecoder.decode(roomname, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int result = service.insert(roomname.substring(9));
		String check = "";
		if (result > 0) check = "1";
		else check = "0";
		
		return check;
	}
	
	@RequestMapping(value="/showroom.htm", method=RequestMethod.POST)
	public View showroom(Model model) {
		List<Room> list = service.selects();
		model.addAttribute("data", list);
		return jsonview;
	}
	
	@RequestMapping(value="/room*.htm", method=RequestMethod.POST)
	public String room(String roomnumber, Model model, Principal principal) {
		Room room = service.select(Integer.parseInt(roomnumber));
		
		model.addAttribute("room", room);
		model.addAttribute("userid", principal.getName());
		return "chat.room";
	}
}
