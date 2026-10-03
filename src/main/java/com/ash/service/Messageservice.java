package com.ash.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ash.models.Message;
import com.ash.models.Users;
@Service
public interface Messageservice {

	public Message createmessage(Users user,Integer chatid,Message req) throws Exception;
	
	public List<Message>findchatmessages(Integer chatid) throws Exception;
}
