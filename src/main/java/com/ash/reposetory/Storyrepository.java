package com.ash.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ash.models.Story;

public interface Storyrepository extends JpaRepository<Story,Integer>{

	public List<Story> findByUserId(Integer userId);
}
