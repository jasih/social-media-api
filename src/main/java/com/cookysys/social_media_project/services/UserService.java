package com.cookysys.social_media_project.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.dtos.CredentialsDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.dtos.UserRequestDto;
import com.cookysys.social_media_project.dtos.UserResponseDto;

@Service
public interface UserService {
	
	/*
	 * GET users
		Retrieves all active (non-deleted) users as an array.
	 */

	List<UserResponseDto> getAllUsers();
	
	/*
	 * POST users
		Creates a new user.
	 */
	UserResponseDto createUser(UserRequestDto user);
	
	/*
	 * GET users/@{username}
		Retrieves a user with the given username
	 */

	UserResponseDto getUser(String username);
	
	/*
	 * PATCH users/@{username}
		Updates the profile of a user with the given username. 
	 */

	UserResponseDto updateUser(String username, UserRequestDto userRequestDto);
	
	/*
	 * DELETE users/@{username}
		“Deletes” a user with the given username
	 */

	UserResponseDto deleteUser(String username, CredentialsDto credentialsDto);
	
	/*
	 * POST users/@{username}/follow
		Subscribes the user whose credentials are provided by the request body to the user whose username is given in the url
	 */

	void followUser(CredentialsDto credentialsDto, String username);
	
	/*
	 * POST users/@{username}/unfollow
		Unsubscribes the user whose credentials are provided by the request body from the user whose username is given in the url.
	 */

	void unfollowUser(CredentialsDto credentialsDto, String username);
	
	/*
	 * GET users/@{username}/feed
		Retrieves all (non-deleted) tweets authored by the user with the given username, as well as all (non-deleted) tweets authored by users the given user is following. 
	 */

	List<TweetResponseDto> getFeed(String username);

	
	
	
	
	

}
