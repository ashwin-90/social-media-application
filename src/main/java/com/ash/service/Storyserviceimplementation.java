package com.ash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ash.models.Story;
import com.ash.models.Users;
import com.ash.reposetory.Storyrepository;
import com.ash.reposetory.Userrepository;
@Service
public class Storyserviceimplementation implements Storyservice {
    @Autowired
	private Storyrepository storyrepo;
    
    private Userrepository userrepo;
	
	@Override
	public Story createstory(Story story, Integer userId) throws Exception {
		Story st=new Story();
		st.setCaption(story.getCaption());
		st.setImage(story.getImage());
		st.setTimestamp(story.getTimestamp());
		Optional<Users> user=userrepo.findById(userId);
		if(user.isEmpty()) {
			throw new Exception("invalid user id");
			
		}else {
			st.setUser(user.get());
		}
		storyrepo.save(st);
		return st;
	}

	@Override
	public List<Story> findstorybyuserid(Integer userid) {
		return storyrepo.findByUserId(userid);
	}

}
