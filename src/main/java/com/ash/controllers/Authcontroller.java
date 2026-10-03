package com.ash.controllers;

import java.net.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ash.config.Jwtprovider;
import com.ash.models.Users;
import com.ash.reposetory.Userrepository;
import com.ash.request.Loginrequest;
import com.ash.response.Authresponse;
import com.ash.service.Customeruserdetailsservice;
import com.ash.service.Userservice;

@RestController
@RequestMapping("/auth")
public class Authcontroller {
    @Autowired
	private Userservice userservice;
    
    @Autowired
    private Userrepository userrepository;
    
    @Autowired
    private PasswordEncoder passwordencoder;
    
    @Autowired
    private Customeruserdetailsservice customeruserdetails;

	@PostMapping("/signup")
	public Authresponse createuser(@RequestBody Users user) throws Exception {
		
		Users isexist=userrepository.findByEmail(user.getEmail());
		
		if(isexist!=null) {
			throw new Exception("email already used with another account ");
		}
		
		Users newuser=new Users();
		newuser.setEmail(user.getEmail());
		newuser.setFirstName(user.getFirstName());
		newuser.setLastName(user.getLastName());
	    newuser.setPassword(passwordencoder.encode(user.getPassword()));
	    
	    
		Users saveduser=userrepository.save(newuser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(saveduser.getEmail(), saveduser.getPassword());
		
		String token =Jwtprovider.generatetoken(authentication);
		
		Authresponse res=new Authresponse(token,"register success");
		
		
		return res;
	}
	
	@PostMapping("/signin")
	 public Authresponse sigin(@RequestBody Loginrequest Loginrequest) {
		 
		 Authentication authentication =authenticate(Loginrequest.getEmail(),Loginrequest.getPassword());
		 
		 
			
			String token =Jwtprovider.generatetoken(authentication);
			
			Authresponse res=new Authresponse(token,"Login success");
		 
		 return res;
	 }


	 private Authentication authenticate(String email, String password) {
		
		 UserDetails userdetails=customeruserdetails.loadUserByUsername(email);
		 
		 if(userdetails==null) {
			 throw new BadCredentialsException("invalid username");
		 }
		 if(!passwordencoder.matches(password, userdetails.getPassword())) {
			 throw new BadCredentialsException("invalid user name or password");
		 }
		return new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
	 }
}
