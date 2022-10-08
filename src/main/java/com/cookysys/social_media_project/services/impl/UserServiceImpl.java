package com.cookysys.social_media_project.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.dtos.CredentialsDto;
import com.cookysys.social_media_project.dtos.ProfileDto;
//import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.dtos.UserRequestDto;
import com.cookysys.social_media_project.dtos.UserResponseDto;
import com.cookysys.social_media_project.embeddables.ProfileEmbeddable;
import com.cookysys.social_media_project.entities.User;
import com.cookysys.social_media_project.exceptions.BadRequestException;
import com.cookysys.social_media_project.exceptions.NotAuthorizedException;
import com.cookysys.social_media_project.exceptions.NotFoundException;
import com.cookysys.social_media_project.mappers.UserMapper;
import com.cookysys.social_media_project.repositories.UserRepository;
import com.cookysys.social_media_project.services.UserService;
import com.cookysys.social_media_project.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final ValidateService validateService;
	
	private void validateUserRequest(UserRequestDto userRequestDto) {
		if (userRequestDto.getCredentials() == null || userRequestDto.getCredentials().getUsername() == null || userRequestDto.getCredentials().getPassword() == null || userRequestDto.getProfile() == null || userRequestDto.getProfile().getEmail() == null) {
            throw new BadRequestException("All fields are required");
        }
	}
	
//	private User getUser(String username) {
//		Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
//		if(optionalUser.isEmpty()) {
//			throw new NotFoundException("No user found with this username" + username);
//		}
//		return optionalUser.get();
//	}
	
	private User handleUserExists(User user) {
        if (user.isDeleted()) {
            user.setDeleted(false);
            userRepository.saveAndFlush(user);
            return user;
        }
        throw new BadRequestException("User exists and is active");
    }
	
	private User handleNotUserExists(User userToSave) {
        userRepository.saveAndFlush(userToSave);
        return userToSave;
    }
	
	private User validateUsername(String username, String message) {
        User user = userRepository.findByCredentialsUsername(username)
                .orElseThrow(() -> new NotFoundException(message));

        if (user.isDeleted()) {
            throw new NotFoundException("User Is Inactive");
        }

        return user;
    }
	
	@Override
	public List<UserResponseDto> getAllUsers() {
		
		return userMapper.entitiesToResponseDto(userRepository.findAll());
	}

	@Override
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		validateUserRequest(userRequestDto);
		
		String username = userRequestDto.getCredentials().getUsername();

        User userToSave = userMapper.requestDtoToEntity(userRequestDto);
        Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
        User user =
                optionalUser.map(this::handleUserExists).orElseGet(() -> handleNotUserExists(userToSave));

        return userMapper.entityToResponseDto(user);
	}

	@Override
	public UserResponseDto getSpecificUser(String username) {
		
		User user = validateUsername(username, "Invalid User");
		
		return userMapper.entityToResponseDto(user);
	}

	@Override
	public UserResponseDto updateUser(String username, UserRequestDto userRequestDto) {
		
		Optional<User> optionalUser =
                userRepository.findByCredentialsUsernameAndDeletedFalse(username);

        User userToUpdate = optionalUser.orElseThrow(() -> new NotFoundException(
                "User doesn't exist"));

        if (userToUpdate.getCredentials().getPassword().equals(userRequestDto.getCredentials().getPassword()) && userToUpdate.getCredentials().getUsername().equals(userRequestDto.getCredentials().getUsername())) {

            ProfileDto profileDto = userRequestDto.getProfile();
            ProfileEmbeddable newProfile = new ProfileEmbeddable();
            newProfile.setFirstName(profileDto.getFirstName());
            newProfile.setLastName(profileDto.getLastName());
            newProfile.setEmail(profileDto.getEmail());
            newProfile.setPhoneNumber(profileDto.getPhoneNumber());

            userToUpdate.setProfile(newProfile);

            userRepository.saveAndFlush(userToUpdate);

            return userMapper.entityToResponseDto(userToUpdate);
        }

        throw new NotAuthorizedException("Username or password do not match");
	}

	@Override
	public UserResponseDto deleteUser(String username, CredentialsDto credentialsDto) {
		final User userToDelete = validateService.authenticate(credentialsDto);
		
		userToDelete.setDeleted(true);
		return userMapper.entityToResponseDto(userRepository.saveAndFlush(userToDelete));
		
	}
	

//	@Override
//	public void followUser(CredentialsDto credentialsDto, String username) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void unfollowUser(CredentialsDto credentialsDto, String username) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<TweetResponseDto> getFeed(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<UserResponseDto> getFollowing(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<TweetResponseDto> getTweets(String tweets) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<UserResponseDto> getFollowers(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<TweetResponseDto> getMentions(String tweets) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}