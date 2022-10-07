package com.cookysys.social_media_project.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.entities.Hashtag;
import com.cookysys.social_media_project.entities.Tweet;
import com.cookysys.social_media_project.exceptions.NotFoundException;
import com.cookysys.social_media_project.mappers.HashtagMapper;
import com.cookysys.social_media_project.repositories.HashtagRepository;
import com.cookysys.social_media_project.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService  {
	
	private final HashtagRepository hashtagRepository;
	
	@Override
	public boolean userNameExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userNameAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//TODO: checks the string, but not special characters
	@Override
	public boolean labelExists(String label) {
		List<Hashtag> hashtags = hashtagRepository.findByLabelContainingIgnoreCase(label);
		for (Hashtag hashtag : hashtags) {
			if (!hashtags.isEmpty()) {
				return true;
			} else {
				throw new NotFoundException("No label with this label found: " + label);
			}
		}
		return false;
	}

}
