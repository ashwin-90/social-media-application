package com.ash.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ash.models.Reels;
import com.ash.models.Users;
import com.ash.service.Reelsservice;
import com.ash.service.Userservice;

@RestController
public class Reelscontroller {
    @Autowired
	private Reelsservice reelsservice;
    @Autowired
    private Userservice userservice;
    
    @PostMapping("/api/reel/create")
    public Reels createreels(@RequestBody Reels reel,@RequestHeader("Authorization") String jwt ) {
    	Users user=userservice.finduserbyjwt(jwt);
    	
    	Reels createdreel=reelsservice.createreel(reel, user);
    	
    	return createdreel;
    	
    }
    
    @GetMapping("/api/reel/find")
    public List<Reels> findreels() {
    	
    	List<Reels> createdreel=reelsservice.findallreels();
    	
    	return createdreel;
    	
    }
    
    @GetMapping("/api/reel/findbyuserid/{userid}")
    public List<Reels> findreelbyuserid(@PathVariable Integer userid) throws Exception{
    	List<Reels>reels=reelsservice.findusersreel(userid);
    	return reels;
    }
    
    
    
}
