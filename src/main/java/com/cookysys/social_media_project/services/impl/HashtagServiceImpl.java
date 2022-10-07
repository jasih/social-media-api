package com.cookysys.social_media_project.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.dtos.HashtagDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.entities.Hashtag;
import com.cookysys.social_media_project.entities.Tweet;
import com.cookysys.social_media_project.exceptions.NotFoundException;
import com.cookysys.social_media_project.mappers.HashtagMapper;
import com.cookysys.social_media_project.mappers.TweetMapper;
import com.cookysys.social_media_project.repositories.HashtagRepository;
import com.cookysys.social_media_project.repositories.TweetRepository;
import com.cookysys.social_media_project.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;

	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;

	private Hashtag getLabel(String label) {
		Optional<Hashtag> hashtag = hashtagRepository.findByLabelContainingIgnoreCase(label);
		return hashtag.get();
	}

	// retrieves all hashtags tracked by the database
	@Override
	public List<HashtagDto> getAllHashtags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll(Sort.by(Sort.Direction.DESC, "label")));
	}

	// retrieves all (non-deleted) tweets with the given hashtag label
	// Notes:
	// - tweets should appear in reverse chronological order
	// - if no hashtag with the given label exists, return an error
	// - a tweet is considered "tagged" by a hashtag if the tweet has content +
	// hashtag's label appears in that content following a #
	@Override
	public List<TweetResponseDto> getTweetsWithLabel(String label) {
		Hashtag hashtagWithLabel = getLabel(label);
		List<Tweet> tweets = hashtagWithLabel.getTweets();
		for (Tweet tweet : tweets) {
			if (!label.matches(hashtagWithLabel.getLabel())) {
				throw new NotFoundException("No hashtag was found with this label: " + label);
			}
			return tweetMapper.entitiesToDto(tweets);
		}

		return tweetMapper.entitiesToDto(tweets);
	}

}
