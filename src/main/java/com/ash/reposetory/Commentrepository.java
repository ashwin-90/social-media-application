package com.ash.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ash.models.Comment;

public interface Commentrepository extends JpaRepository<Comment , Integer>{

}
