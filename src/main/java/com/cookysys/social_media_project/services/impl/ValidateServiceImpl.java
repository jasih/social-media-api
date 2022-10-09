package com.cookysys.social_media_project.services.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.entities.Hashtag;
import com.cookysys.social_media_project.entities.Tweet;
import com.cookysys.social_media_project.entities.User;
import com.cookysys.social_media_project.mappers.HashtagMapper;
import com.cookysys.social_media_project.repositories.HashtagRepository;
import com.cookysys.social_media_project.repositories.UserRepository;
import com.cookysys.social_media_project.dtos.CredentialsDto;
import com.cookysys.social_media_project.exceptions.BadRequestException;
import com.cookysys.social_media_project.exceptions.NotAuthorizedException;
import com.cookysys.social_media_project.exceptions.NotFoundException;
import com.cookysys.social_media_project.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService  {
	
	private final HashtagRepository hashtagRepository;
	private final UserRepository userRepository;
	
	public boolean userNameExists(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
		if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getCredentials().getUsername().equals(username)) {
                return true;
            }
        }
        return false;
	}

	@Override
	public boolean userNameAvailable(String username) {
		Optional<User> optionalUser = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getCredentials().getUsername().equals(username)) {
                throw new BadRequestException("A user already exists with the username : " + username);
            }
        }
        return true;
	}
	
	
	//TODO: checks the string, but not special characters
	@Override
	public boolean labelExists(String label) {
		List<Hashtag> hashtags = hashtagRepository.findAllByLabelContainingIgnoreCase(label);
		for (Hashtag hashtag : hashtags) {
			if (!hashtags.isEmpty()) {
				return true;
			} else {
				throw new NotFoundException("No label with this label found: " + label);
			}
		}
		return false;
	}

	public User authenticate(CredentialsDto credentialsDto) throws NotAuthorizedException {
        final Optional<CredentialsDto> credentials = Optional.ofNullable(credentialsDto);
        final String username = credentials.map(CredentialsDto::getUsername).flatMap(Optional::ofNullable).orElse("");
        final String password = credentials.map(CredentialsDto::getPassword).flatMap(Optional::ofNullable).orElse("");
        return userRepository.findByCredentialsUsernameAndDeletedFalse(username)
            .filter(u -> u.getCredentials().getPassword().equals(password))
            .orElseThrow(() -> new NotAuthorizedException("Invalid User Credentials"));
    }

}
