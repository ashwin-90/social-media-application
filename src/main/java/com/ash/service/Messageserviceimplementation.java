package com.ash.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ash.models.Chat;
import com.ash.models.Message;
import com.ash.models.Users;
import com.ash.reposetory.Chatrepository;
import com.ash.reposetory.Messagerepository;
@Service
public class Messageserviceimplementation implements Messageservice {
    @Autowired
	private Messagerepository messagerepo;
    @Autowired
    private Chatservice chatserv;
    
    @Autowired
    private Chatrepository chatrepo;
	
	@Override
	public Message createmessage(Users user, Integer chatid, Message req) throws Exception {
		Message message =new Message();
		 
		Chat chat=chatserv.findchatbyid(chatid);
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		Message savedmessage= messagerepo.save(message);
		
		chat.getMessages().add(savedmessage);
		chatrepo.save(chat);
		
		return savedmessage;
	}

	@Override
	public List<Message> findchatmessages(Integer chatid) throws Exception {
		Chat chat=chatserv.findchatbyid(chatid);
		
		return messagerepo.findByChatId(chatid);
	}

}
