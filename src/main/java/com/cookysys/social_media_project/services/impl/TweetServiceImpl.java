package com.cookysys.social_media_project.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.dtos.TweetRequestDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.dtos.UserRequestDto;
import com.cookysys.social_media_project.dtos.UserResponseDto;
import com.cookysys.social_media_project.entities.Tweet;
import com.cookysys.social_media_project.entities.User;
import com.cookysys.social_media_project.exceptions.NotFoundException;
import com.cookysys.social_media_project.mappers.TweetMapper;
import com.cookysys.social_media_project.mappers.UserMapper;
import com.cookysys.social_media_project.repositories.TweetRepository;
import com.cookysys.social_media_project.repositories.UserRepository;
import com.cookysys.social_media_project.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

	private final TweetMapper tweetMapper;
	private final TweetRepository tweetRepository;
	
	private final UserMapper userMapper;
	private final UserRepository userRepository;
	
	@Override
	public List<TweetResponseDto> getAllTweets() {
		return tweetMapper.entitiesToDtos(tweetRepository.findAll(Sort.by(Sort.Direction.DESC)));
	}

	@Override
	public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto getTweet(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto deleteTweet(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto likeTweet(Long id, UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto replyToTweet(Long id, String reply) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto getHashtagOfTweet(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto repostTweet(Long id, UserRequestDto userRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto getUsersWhoLikedATweet(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto getContextOfTweet(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	// Retrieves the direct replies to the tweet with the given id. If that tweet is
	// deleted or otherwise doesn’t exist, an error should be sent in lieu of a response.
	// Deleted replies to the tweet should be excluded from the response.
	@Override
	public List<TweetResponseDto> getRepliesToTweets(Long id) {
		Tweet tweet = tweetRepository.findByIdAndDeletedFalse(id);
		if (tweet == null) {
			throw new NotFoundException("No tweet was found with this id: " + id + ". Or this tweet has been deleted.");
		}
		
		List<Tweet> userReplies = tweet.getReplies();
		List<Tweet> newList = new ArrayList<>();
		for (Tweet reply : userReplies) {
			if (!reply.isDeleted()) {
				newList.add(reply);
			}
		}
	
		return tweetMapper.entitiesToDtos(newList);
	}

	// Retrieves the direct reposts of the tweet with the given id. If that tweet is
	// deleted or otherwise doesn’t exist, an error should be sent in lieu of a
	// response.
	// Deleted reposts of the tweet should be excluded from the response.
	@Override
	public List<TweetResponseDto> getRepostsOfTweets(Long id) {
		Tweet tweet = tweetRepository.findByIdAndDeletedFalse(id);
		if (tweet == null) {
			throw new NotFoundException("No tweet was found with this id: " + id + ". Or this tweet has been deleted.");
		}
		
		List<Tweet> userReposts = tweet.getReposts();
		List<Tweet> newList = new ArrayList<>();
		for (Tweet repost : userReposts) {
			if (!repost.isDeleted()) {
				newList.add(repost);
			}
		}
	
		return tweetMapper.entitiesToDtos(newList);
	}

	// Retrieves the users mentioned in the tweet with the given id. 
	// If that tweet is deleted or otherwise doesn’t exist, an error should be sent in lieu of a response.
	// Deleted users should be excluded from the response.
	// **IMPORTANT** Remember that tags and mentions must be parsed by the server!
	@Override
	public List<UserResponseDto> getMentions(Long id) {
		Tweet tweet = tweetRepository.findByIdAndDeletedFalse(id);
		if (tweet == null) {
			throw new NotFoundException("No tweet was found with this id: " + id + ". Or this tweet has been deleted.");
		}
		
		List<User> userList = new ArrayList<>();
		for (User user : tweet.getUserMentioned()) {
			if (!user.isDeleted()) {
				userList.add(user);
			}
		}
		
		return userMapper.entityToResponseDto(userList);
	}
}
