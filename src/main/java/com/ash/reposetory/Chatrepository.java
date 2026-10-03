package com.ash.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ash.models.Chat;
import com.ash.models.Users;

public interface Chatrepository extends JpaRepository<Chat,Integer>{
public List<Chat>findByUsersId(Integer userId);

@Query("select c from Chat c Where :user Member of c.users And :requser Member of c.users")
public Chat findChatByUsersId(@Param("user") Users user, @Param("requser") Users requser);

}
