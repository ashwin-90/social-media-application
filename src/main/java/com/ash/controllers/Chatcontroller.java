package com.ash.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ash.models.Chat;
import com.ash.models.Users;
import com.ash.request.Createchatrequest;
import com.ash.service.Chatservice;
import com.ash.service.Userservice;

@RestController
public class Chatcontroller {
	@Autowired
	private Chatservice chatserv;
	@Autowired
	private Userservice userserv;
	
	@PostMapping("/api/chats")
	public Chat createchat(@RequestHeader("Authorization") String jwt,@RequestBody Createchatrequest req) throws Exception {
		Users requser=userserv.finduserbyjwt(jwt);
		Users user2=userserv.finduserbtid(req.getUserid());
		Chat chat=chatserv.createchat(requser, user2);
		return chat;
	}

	@GetMapping("/api/chats/findchat")
	public List<Chat> findchat(@RequestHeader("Authorization") String jwt) {
		Users user=userserv.finduserbyjwt(jwt);
		List<Chat> chat=chatserv.finduserschat(user.getId());
		return chat;
	}

}
