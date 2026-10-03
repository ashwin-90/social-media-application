package com.ash.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ash.models.Chat;
import com.ash.models.Users;
import com.ash.reposetory.Chatrepository;

@Service
public class Chatserviceimplementation implements Chatservice {
   @Autowired
   private Chatrepository chatrepo;
	@Override
	public Chat createchat(Users requser, Users user2) {
		Chat isexist=chatrepo.findChatByUsersId(user2,requser);
		
		if(isexist!=null) {
			return isexist;
		}
		Chat chat =new Chat();
		
		chat.getUsers().add(user2);
		chat.getUsers().add(requser);
		chat.setTimestamp(LocalDateTime.now());
		
		return chatrepo.save(chat);
	}

	@Override
	public Chat findchatbyid(Integer chatid) throws Exception {
		Optional<Chat> chat=chatrepo.findById(chatid); 
		
		if(chat.isEmpty()) {
			throw new Exception("chat not exist ");
		}
		return chat.get();
	}

	@Override
	public List<Chat> finduserschat(Integer userid) {
		
		return chatrepo.findByUsersId(userid);
	}

}
