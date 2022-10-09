package com.cookysys.social_media_project.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.dtos.CredentialsDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.dtos.UserRequestDto;
import com.cookysys.social_media_project.dtos.UserResponseDto;
import com.cookysys.social_media_project.mappers.UserMapper;
import com.cookysys.social_media_project.services.UserService;
import com.cookysys.social_media_project.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private UserMapper userMapper;
	
	@Override
	public List<UserResponseDto> getAllUsers() {
		
		return userMapper.entityToResponseDto(userRepository.findAllByDeletedFalse());
	}

	@Override
	public UserResponseDto createUser(UserRequestDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto updateUser(String username, UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto deleteUser(String username, CredentialsDto credentialsDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void followUser(CredentialsDto credentialsDto, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unfollowUser(CredentialsDto credentialsDto, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TweetResponseDto> getFeed(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getFollowing(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getTweets(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getFollowers(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getMentions(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
