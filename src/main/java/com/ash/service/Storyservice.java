package com.ash.service;

import java.util.List;

import com.ash.models.Story;

public interface Storyservice {

	public Story createstory(Story story,Integer userId) throws Exception;
	
	public List<Story> findstorybyuserid(Integer userid);
}
