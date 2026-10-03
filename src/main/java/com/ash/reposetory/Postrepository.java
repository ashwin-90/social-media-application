package com.ash.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ash.models.Post;

public interface Postrepository extends JpaRepository<Post,Integer> {
//    @Query("select p from Post p.user.id=:userid")
	List<Post>findPostByUserId(Integer userid);
}

