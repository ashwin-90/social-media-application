package com.ash.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ash.models.Story;
import com.ash.models.Users;
import com.ash.service.Storyservice;
import com.ash.service.Userservice;
@RestController
public class Storycontroller {
    @Autowired
	private Storyservice storyserv;
    @Autowired
    private Userservice userserv;
    
    @PostMapping("/api/story")
    public Story createstory(@RequestBody Story story , @RequestHeader("Authorization")String jwt) throws Exception {
    	Users user=userserv.finduserbyjwt(jwt);
    	Story created=storyserv.createstory(story, user.getId());
    	
    	return created;
    }
    
    @GetMapping("/api/find/story/{userid}")
    public List<Story> findstorybyuserid(@PathVariable Integer userid ,@RequestHeader("Authorization")String jwt){
    	Users user=userserv.finduserbyjwt(jwt);
    	return storyserv.findstorybyuserid(userid);
    }
	
}
