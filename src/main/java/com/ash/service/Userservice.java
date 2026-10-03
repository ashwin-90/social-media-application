package com.ash.service;

import java.util.List;

import com.ash.exceptions.UserException;
import com.ash.models.Users;

public interface Userservice {

	
	public Users registeruser(Users user);
	
	public Users finduserbtid(Integer id) throws UserException;
	
	public Users finduserbyemail(String email);
	
	public Users followuser(Integer userid1 , Integer userid2) throws UserException;
	
	public Users updateuser(Users user,Integer userid) throws UserException;
	
	public List<Users>seachuser(String query);
	
	public Users finduserbyjwt(String jwt);
	
}
