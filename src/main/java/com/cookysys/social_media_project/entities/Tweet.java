package com.cookysys.social_media_project.entities;

import java.sql.Timestamp;
import java.util.*;

import javax.persistence.*;


import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data

public class Tweet {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private User author;
	
	@Column(nullable=false, updatable=false)
	@CreationTimestamp
	private Timestamp joined;
	
	@Column(nullable=false)
	private boolean deleted;
	
	private String content;
	
	@ManyToOne
	@JoinColumn
	private Tweet inReplyTo;
	
	
	@ManyToOne
	@JoinColumn
	private Tweet repostOf;
	
	@ManyToMany
	@JoinTable
	private List<Hashtag> hashtags;
	
	@ManyToMany
	@JoinTable
	private List<User> userMentioned;

}
