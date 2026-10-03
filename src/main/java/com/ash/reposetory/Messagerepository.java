package com.ash.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ash.models.Message;
import com.mysql.cj.Messages;

public interface Messagerepository extends JpaRepository<Message, Integer> {

	public List<Message> findByChatId(Integer chatid);
}
