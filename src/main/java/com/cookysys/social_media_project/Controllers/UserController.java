package com.cookysys.social_media_project.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.cookysys.social_media_project.services.UserService;
import com.cookysys.social_media_project.dtos.CredentialsDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.dtos.UserRequestDto;
import com.cookysys.social_media_project.dtos.UserResponseDto;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
	
	private UserService userService;
		
	@GetMapping
	public List<UserResponseDto> getAllUsers(){
		
		return userService.getAllUsers();
	}
	
	@PostMapping
	public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto){
		return userService.createUser(userRequestDto);
	}
	
	@GetMapping("/@{username}")
	public UserResponseDto getUser(@PathVariable String username) {
		return userService.getUser(username);
	}
	
	@PatchMapping("/@{username}")
	public UserResponseDto updateUser(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
		return userService.updateUser(username, userRequestDto);
	}
	
	@DeleteMapping("/@{username}")
	public UserResponseDto deleteUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
		return userService.deleteUser(username, credentialsDto);
	}
	
	@PostMapping("/@{username}/follow")
	public void addFollower(@RequestBody CredentialsDto credentialsDto, @PathVariable String username) {
		userService.followUser(credentialsDto, username);
	}
	
	@PostMapping("/@{username}/unfollow")
	public void removeFollower(@RequestBody CredentialsDto credentialsDto, @PathVariable String username) {
		userService.unfollowUser(credentialsDto, username);
	}
	
	@GetMapping("/@{username}/feed")
	public List<TweetResponseDto> getFeed(@PathVariable String username){
		return userService.getFeed(username);
	}
	
	

}
