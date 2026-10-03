package com.ash.service;

import com.ash.models.Comment;

public interface Commentservice {

	public Comment createcomment(Comment comment , Integer postid , Integer userid) throws Exception;
	
	public Comment likedcomment(Integer commentid , Integer userid) throws Exception;
	
	public Comment findcommentbyid(Integer commentid) throws Exception;
	
}
