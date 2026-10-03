package com.ash.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ash.exceptions.UserException;
import com.ash.models.Users;
import com.ash.reposetory.Userrepository;
import com.ash.service.Userservice;

@RestController
public class Usercontroller {
	@Autowired
	Userrepository userrepository;
	
	@Autowired
	Userservice userservice;
	
	

	@GetMapping("/api/user")
	public List<Users> getUsers() {
		List<Users>users=userrepository.findAll();
		return users;
		
	}
	
	@GetMapping("/api/user/{userid}")
	public Users getUsersbyid(@PathVariable("userid")Integer id) throws UserException {
		
		Users user=userservice.finduserbtid(id);
		return user;
	}
	
	
	
	@PutMapping("/api/users")
	public Users updateuser(@RequestHeader("Authorization")String jwt,@RequestBody Users user) throws UserException {
	    Users requser=userservice.finduserbyjwt(jwt);
		
		Users updated=userservice.updateuser(user,requser.getId() );
		
		return updated ;
	}
	
	@PutMapping("/api/user/follow/{userid2}")
	public Users followuserhandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer userid2) throws UserException {
		Users requser=userservice.finduserbyjwt(jwt);
		
		Users user=userservice.followuser(requser.getId(),userid2);
		return user;
		
	}
	
	@GetMapping("/api/user/search")
	public List<Users> serchuser(@RequestParam("query") String query){
		
		List<Users>user=userservice.seachuser(query);
		return user; 
	}
	@GetMapping("/api/users/profile")
	public Users getuserfromtoken(@RequestHeader("Authorization")String jwt) {
	    
		

		Users user=userservice.finduserbyjwt(jwt);
		user.setPassword(null);
		return user;
	}
}
