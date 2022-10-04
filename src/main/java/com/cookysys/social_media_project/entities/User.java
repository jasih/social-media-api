package com.cookysys.social_media_project.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user_table")

public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, updatable=false)
	@CreationTimestamp
	private Timestamp joined;
	
	@Column(nullable=false)
	private boolean deleted;
	
	@OneToMany(mappedBy = "author")
	private List <Tweet> tweets;
	
	@ManyToMany(mappedBy = "followers")
	private List<User>following;
	
	@ManyToMany
	@JoinTable
	private List<User> followers;
	
	@ManyToMany(mappedBy = "userMentioned")
	private List<Tweet> mentions;
	

}
