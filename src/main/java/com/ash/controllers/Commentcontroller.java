package com.ash.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ash.models.Comment;
import com.ash.models.Users;
import com.ash.service.Commentservice;
import com.ash.service.Userservice;

@RestController
public class Commentcontroller {
    @Autowired
	private Commentservice commentservice;
    @Autowired
    private Userservice userservice;
    
    @PostMapping("/api/comments/post/{postid}")
    public Comment createcomment(@RequestBody Comment comment , @RequestHeader("Authorization") String jwt,@PathVariable Integer postid) throws Exception {
    	Users user=userservice.finduserbyjwt(jwt);
    	
    	Comment createdcomment = commentservice.createcomment(comment, postid, user.getId());
    	
    	return createdcomment;
    }
    
    @PutMapping("/api/comments/like/{commentid}")
    public Comment likecomment(  @RequestHeader("Authorization") String jwt,@PathVariable Integer commentid) throws Exception {
    	Users user=userservice.finduserbyjwt(jwt);
    	
    	Comment likedcomment = commentservice.likedcomment(commentid, user.getId());
    	
    	
    	return likedcomment;
    }
    
    @GetMapping("/api/comments/all/{commentid}")
    public Comment getcomment( @RequestHeader("Authorization") String jwt,@PathVariable Integer commentid) throws Exception {
    	
    	Comment comment=commentservice.findcommentbyid(commentid);
    	
    	return comment;
    }
	
    
}
