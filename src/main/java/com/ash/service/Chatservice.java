package com.ash.service;

import java.util.List;

import com.ash.models.Chat;
import com.ash.models.Users;

public interface Chatservice {

	public Chat createchat(Users requser , Users user2);
	
	public Chat findchatbyid(Integer chatid) throws Exception;
	
	public List<Chat>finduserschat(Integer userid);
}
