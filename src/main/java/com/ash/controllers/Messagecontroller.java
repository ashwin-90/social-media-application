package com.ash.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ash.models.Message;
import com.ash.models.Users;
import com.ash.service.Messageservice;
import com.ash.service.Userservice;

@RestController
public class Messagecontroller {
@Autowired
private Messageservice messageserv;
@Autowired
private Userservice userserv;

@PostMapping("/api/messages/chat/{chatId}")
public Message createmeassage(@RequestBody Message req,@RequestHeader("Authorization") String jwt,@PathVariable Integer chatId) throws Exception {
	
	Users user=userserv.finduserbyjwt(jwt);
	
	Message message=messageserv.createmessage(user, chatId, req);
	
	return message;
}

@GetMapping("/api/messages/chat/{chatId}")
public List<Message> findmeassage(@RequestHeader("Authorization") String jwt,@PathVariable Integer chatId) throws Exception {
	
	Users user=userserv.finduserbyjwt(jwt);
	
	List<Message> message=messageserv.findchatmessages(chatId);
	
	return message;
}


}
