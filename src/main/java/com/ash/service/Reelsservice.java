package com.ash.service;

import java.util.List;

import com.ash.models.Reels;
import com.ash.models.Users;

public interface Reelsservice {

	
	public Reels createreel(Reels reel , Users user);
	
	public List<Reels>findallreels();
	
	public List<Reels>findusersreel(Integer userId) throws Exception;
}
