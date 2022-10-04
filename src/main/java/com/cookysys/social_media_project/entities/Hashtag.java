package com.cookysys.social_media_project.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity

public class Hashtag {
	
	@Id
	@GeneratedValue
	private Long id;	
	
	@Column(nullable=false, unique=true)
	private String label;
	
	@Column(nullable=false, updatable=false)
	@CreationTimestamp
	private Timestamp firstUsed;
	
	@Column(nullable=false)
	@UpdateTimestamp
	private Timestamp lastUsed;
	
	@ManyToMany(mappedBy = "hashtags")
	private List<Tweet> tweets;

}
