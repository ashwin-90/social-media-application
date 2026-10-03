package com.ash.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ash.models.Reels;

public interface Reelsrepository extends JpaRepository<Reels,Integer>{

	public List<Reels> findByUserId(Integer userId);
}
