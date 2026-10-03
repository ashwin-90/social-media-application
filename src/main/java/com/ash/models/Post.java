package com.ash.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
	
     @Id
     @GeneratedValue(strategy =GenerationType.AUTO)
	private Integer id;
     
     private String caption;  
     
     private String image;
     
     private String video;
     
     @ManyToOne
     private Users user;
     
     private LocalDateTime createdate;
     
     @OneToMany
     private List<Comment>comments=new ArrayList<>();
     
     
     @OneToMany
     private List<Users>liked=new ArrayList<>();
     
     public Post(){
    	 
     }

	
	 public Post(Integer id, String caption, String image, String video, Users user, LocalDateTime createdate,
			List<Comment> comments, List<Users> liked) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.video = video;
		this.user = user;
		this.createdate = createdate;
		this.comments = comments;
		this.liked = liked;
	}



	 public List<Comment> getComments() {
		return comments;
	}


	 public void setComments(List<Comment> comments) {
		 this.comments = comments;
	 }


	 public List<Users> getLiked() {
		return liked;
	}



	 public void setLiked(List<Users> liked) {
		 this.liked = liked;
	 }



	 public Integer getId() {
		 return id;
	 }

	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public String getCaption() {
		 return caption;
	 }

	 public void setCaption(String caption) {
		 this.caption = caption;
	 }

	 public String getImage() {
		 return image;
	 }

	 public void setImage(String image) {
		 this.image = image;
	 }

	 public String getVideo() {
		 return video;
	 }

	 public void setVideo(String video) {
		 this.video = video;
	 }

	 public Users getUser() {
		 return user;
	 }

	 public void setUser(Users user) {
		 this.user = user;
	 }

	 public LocalDateTime getCreatedate() {
		 return createdate;
	 }

	 public void setCreatedate(LocalDateTime createdat) {
		 this.createdate = createdate;
	 }
	
	
}
