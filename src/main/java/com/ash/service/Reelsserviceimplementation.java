package com.ash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ash.models.Reels;
import com.ash.models.Users;
import com.ash.reposetory.Reelsrepository;
@Service
public class Reelsserviceimplementation implements Reelsservice {
    @Autowired
	private Reelsrepository reelsrepo;
    @Autowired
    private Userservice userservice;
	
	@Override
	public Reels createreel(Reels reel, Users user) {
		Reels createreel=new Reels();
		
		createreel.setTitle(reel.getTitle());
		createreel.setUser(user);
		createreel.setVidio(reel.getVidio());
		
		reelsrepo.save(createreel);
		return createreel;
	}

	@Override
	public List<Reels> findallreels() {
		
		return reelsrepo.findAll();
	}

	@Override
	public List<Reels> findusersreel(Integer userId) throws Exception {
		Users user=userservice.finduserbtid(userId);
		return reelsrepo.findByUserId(userId);
	}

}
