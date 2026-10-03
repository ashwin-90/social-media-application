package com.ash.service;

import java.time.LocalDateTime;
import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ash.models.Post;
import com.ash.models.Users;
import com.ash.reposetory.Postrepository;
import com.ash.reposetory.Userrepository;
@Service
public class Postserviceimplementation implements PostService{
    @Autowired
	Postrepository postrepository;
    
    @Autowired
	Userservice userservice;
    
    @Autowired
    Userrepository userrepository;
    
	@Override
	public Post createnewpost(Post post, Integer userid) throws Exception {
		
		Users user=userservice.finduserbtid(userid);
		
		Post newpost=new Post();
		newpost.setCaption(post.getCaption());
		newpost.setImage(post.getImage());
		newpost.setCreatedate(LocalDateTime.now());
		newpost.setVideo(post.getVideo());
		newpost.setUser(user);
		postrepository.save(newpost);
		return newpost;
		
	}

	@Override
	public String deletepost(Integer postid, Integer userid) throws Exception {
		Post post=findpostbyid(postid);
		Users user=userservice.finduserbtid(userid);
		
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("you cannot delete another user post");
		}
		postrepository.delete(post);
		
		return "post deleted succesfully";
	}

	@Override
	public List<Post> findpostbyuserid(Integer userid) {
		
		return postrepository.findPostByUserId(userid);
	}

	@Override
	public Post findpostbyid(Integer postid) throws Exception {
		Optional<Post> otp=postrepository.findById(postid);
		if(otp.isEmpty()) {
			throw new Exception("post not found with id "+postid);
		}
		return otp.get();
	}

	@Override
	public List<Post> findallpost() {
		
		return postrepository.findAll();
	}

	@Override
	public Post savedpost(Integer postid, Integer userid) throws Exception {
		
		Post post=findpostbyid(postid);
		Users user=userservice.finduserbtid(userid);
		
		if(user.getSavedpost().contains(post)) {
			user.getSavedpost().remove(post);
		}
		else {
			user.getSavedpost().add(post);
		}
		 userrepository.save(user);
		return post;
	}

	@Override
	public Post likepost(Integer postid, Integer userid) throws Exception {
		Post post=findpostbyid(postid);
		Users user=userservice.finduserbtid(userid);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);	
		}
		
		
		
		return postrepository.save(post);
	}

}
