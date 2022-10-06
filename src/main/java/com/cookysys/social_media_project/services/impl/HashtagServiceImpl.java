package com.cookysys.social_media_project.services.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.dtos.HashtagDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.entities.Hashtag;
import com.cookysys.social_media_project.entities.Tweet;
import com.cookysys.social_media_project.exceptions.NotFoundException;
import com.cookysys.social_media_project.mappers.HashtagMapper;
import com.cookysys.social_media_project.repositories.HashtagRepository;
import com.cookysys.social_media_project.repositories.TweetRepository;
import com.cookysys.social_media_project.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
	
	private HashtagRepository hashtagRepository;
	private HashtagMapper hashtagMapper;
	
	private TweetRepository tweetRepository;
	
//	private Hashtag getLabel(String label) {
//		Optional<Hashtag> hashtag = hashtagRepository.findByLabel(label);
//		return hashtag.get();
//	}
	
	//retrieves all hashtags tracked by the database
	@Override
	public List<HashtagDto> getAllHashtags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}

	//retrieves all (non-deleted) tweets with the given hashtag label
	//Notes: 
	//	- tweets should appear in reverse chronological order
	//	- if no hashtag with the given label exists, return an error
	//  - a tweet is considered "tagged" by a hashtag if the tweet has content	+ hashtag's label appears in that content following a #
	@Override
	public List<TweetResponseDto> getTweetsWithLabel(String label) {
		
//		Optional<Hashtag> hashtag = hashtagRepository.findByLabel(label); 
//		
//		if (hashtag.isEmpty()) {
//			throw new NotFoundException("No hashtag was found with this label: " + label);
//		}
		
		//TODO: need to implement reverse order + check for tweet content
//		return hashtagMapper.entityToDto(hashtag.get());	
		return null;
	}
}
