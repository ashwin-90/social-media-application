package com.ash.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ash.models.Post;
import com.ash.models.Users;
import com.ash.reposetory.Postrepository;
import com.ash.response.Apiresponse;
import com.ash.service.PostService;
import com.ash.service.Userservice;

@RestController
public class Postcontroller {
    @Autowired
	Postrepository postrepository;
    
    @Autowired
    PostService postservice;
    @Autowired
    Userservice userservice;
    
    @PostMapping("/api/posts")
    public ResponseEntity<Post>createpost(@RequestHeader("Authorization")String jwt,@RequestBody Post post) throws Exception{
    	Users user=userservice.finduserbyjwt(jwt);
    	Post createdpost=postservice.createnewpost(post, user.getId());
    	return new ResponseEntity<>(createdpost,HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/posts/{postid}")
    public ResponseEntity<Apiresponse> deletepost(@RequestHeader("Authorization")String jwt,@PathVariable Integer postid) throws Exception{
    	Users user=userservice.finduserbyjwt(jwt);
    	String message =postservice.deletepost(postid, user.getId());
    	Apiresponse res=new Apiresponse(message,true);
    	return new ResponseEntity<>(res,HttpStatus.OK);
    }
    
    @GetMapping("/posts/{postid}")
    public ResponseEntity<Post> findpostbyid(@PathVariable Integer postid) throws Exception{
    	
    	Post post=postservice.findpostbyid(postid);
    	return  new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    	
    }
    
    @GetMapping("/posts/user/{userid}")
    public ResponseEntity<List<Post>>finduserspost(@PathVariable Integer userid){
    
    	List<Post>post=postservice.findpostbyuserid(userid);
    	
    	return new ResponseEntity<>(post,HttpStatus.OK);
    }
    
    @GetMapping("/posts")
    public ResponseEntity<List<Post>>findallpost(){
    
    	List<Post>post=postservice.findallpost();
    	
    	return new ResponseEntity<>(post,HttpStatus.OK);
    }
    
    
    @PutMapping("/posts/save/{postid}")
    public ResponseEntity<Post> savedposthandeler(@RequestHeader("Authorization")String jwt,@PathVariable Integer postid ) throws Exception{
    	Users user=userservice.finduserbyjwt(jwt);
    	Post post=postservice.savedpost(postid,user.getId());
    	return  new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    	
    }
    
    @PutMapping("/posts/liked/{postid}")
    public ResponseEntity<Post> likedposthandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer postid ) throws Exception{
    	Users user=userservice.finduserbyjwt(jwt);
    	
    	Post post=postservice.likepost(postid, user.getId());
    	return  new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    	
    }
    
    
    
}
