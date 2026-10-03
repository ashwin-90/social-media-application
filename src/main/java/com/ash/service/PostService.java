package com.ash.service;

import java.util.List;

import com.ash.models.Post;



public interface PostService {

	Post createnewpost(Post post , Integer userid)throws Exception;
	
	
	String deletepost(Integer postid,Integer userid) throws Exception;
	
	List<Post>findpostbyuserid(Integer userid);
	
	Post findpostbyid(Integer postid) throws Exception;
	
	List<Post>findallpost();
	
	Post savedpost(Integer postid , Integer userid ) throws Exception;
	
	Post likepost(Integer postid,Integer userid) throws Exception;
	
	
}
