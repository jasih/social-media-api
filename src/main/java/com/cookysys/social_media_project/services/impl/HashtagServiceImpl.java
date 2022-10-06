package com.cookysys.social_media_project.services.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.dtos.HashtagDto;
import com.cookysys.social_media_project.entities.Hashtag;
import com.cookysys.social_media_project.mappers.HashtagMapper;
import com.cookysys.social_media_project.repositories.HashtagRepository;
import com.cookysys.social_media_project.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
	
	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;
	
	//retrieves all hashtags tracked by the database
	@Override
	public List<HashtagDto> getAllHashtags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll(Sort.by(Sort.Direction.DESC, "label")));
	}

	//retrieves all (non-deleted) tweets with the given hashtag label
	//Notes: 
	//	- tweets should appear in reverse chronological order
	//	- if no hashtag with the given label exists, return an error
	//  - a tweet is considered "tagged" by a hashtag if the tweet has content	+ hashtag's label appears in that content following a #

}
