package com.ash.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ash.models.Comment;
import com.ash.models.Post;
import com.ash.models.Users;
import com.ash.reposetory.Commentrepository;
import com.ash.reposetory.Postrepository;
@Service
public class Commentserviceimplementation implements Commentservice{

	 @Autowired
	private  Commentrepository commentrepository;
	 @Autowired
	 private PostService postservice;
	 @Autowired
	 private Userservice userservice;
	 @Autowired
	 private Postrepository postrepository;
	 
	@Override
	public Comment createcomment(Comment comment, Integer postid, Integer userid) throws Exception {
		
		Users user=userservice.finduserbtid(userid);
		
		Post post=postservice.findpostbyid(postid);
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
	    Comment savedcomment =commentrepository.save(comment);
	
		
		post.getComments().add(savedcomment);
		postrepository.save(post);
		
		return savedcomment;
	}

	@Override
	public Comment likedcomment(Integer commentid, Integer userid) throws Exception {
		Comment comment=findcommentbyid(commentid);
		Users user=userservice.finduserbtid(userid);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}else {
			comment.getLiked().remove(user);
		}
		
		return commentrepository.save(comment);
	}

	@Override
	public Comment findcommentbyid(Integer commentid) throws Exception {
		Optional<Comment>opt=commentrepository.findById(commentid);
		
		if(opt.isEmpty()) {
		throw new Exception("comment not exist");	
		}
		return opt.get();
	}

}
