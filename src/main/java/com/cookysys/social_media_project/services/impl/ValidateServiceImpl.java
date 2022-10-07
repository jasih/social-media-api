package com.cookysys.social_media_project.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.entities.User;
import com.cookysys.social_media_project.exceptions.BadRequestException;
import com.cookysys.social_media_project.repositories.UserRepository;
import com.cookysys.social_media_project.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService  {
	
	private final UserRepository userRepository;
	
	@Override
	public boolean userNameExists(String username) {
		 Optional<User> optionalUser = userRepository.findByCredentialsExist(username);
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
		Optional<User> optionalUser = userRepository.findByCredentialsExist(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getCredentials().getUsername().equals(username)) {
                throw new BadRequestException("A user with username : " + username + " already exists");
            }
        }
        return true;
	}

}
