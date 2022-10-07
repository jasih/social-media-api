package com.cookysys.social_media_project.services.impl;

import java.util.List;
import java.util.Optional;

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

	//TODO: for the 3 below, need to exclude deleted items + check if it builds and runs
	// Retrieves the direct replies to the tweet with the given id. If that tweet is
	// deleted or otherwise doesn’t exist, an error should be sent in lieu of a
	// response.
	// Deleted replies to the tweet should be excluded from the response.
	@Override
	public List<TweetResponseDto> getRepliesToTweets(Long id, UserRequestDto user) {
		Optional<Tweet> tweets = tweetRepository.findById(id);
		List<Tweet> userReplies = tweets.get().getReplies();
		for (Tweet reply : userReplies) {
			if (tweets.isEmpty() && tweets.isPresent() == false) {
				throw new NotFoundException("No tweet was found with this id: " + id +". Or this tweet has been deleted.");
			}
			return tweetMapper.entitiesToDto(userReplies);
		}
		return tweetMapper.entitiesToDto(userReplies);
	}

	// Retrieves the direct reposts of the tweet with the given id. If that tweet is
	// deleted or otherwise doesn’t exist, an error should be sent in lieu of a
	// response.
	// Deleted reposts of the tweet should be excluded from the response.
	@Override
	public List<TweetResponseDto> getRepostsOfTweets(Long id, UserRequestDto user) {
		Optional<Tweet> tweets = tweetRepository.findById(id);
		List<Tweet> userReposts = tweets.get().getReposts();
		for (Tweet repost : userReposts) {
			if (tweets.isEmpty() && tweets.isPresent() == false) {
				throw new NotFoundException("No tweet was found with this id: " + id +". Or this tweet has been deleted.");
			}
			return tweetMapper.entitiesToDto(userReposts);
		}
		return tweetMapper.entitiesToDto(userReposts);
	}

	// Retrieves the users mentioned in the tweet with the given id. If that tweet
	// is deleted or otherwise doesn’t exist, an error should be sent in lieu of a
	// response.
	// Deleted users should be excluded from the response.
	// **IMPORTANT** Remember that tags and mentions must be parsed by the server!
	@Override
	public List<UserResponseDto> getMentions(Long id, UserRequestDto user) {
		Optional<Tweet> tweets = tweetRepository.findById(id);
		List<User> userMentions = tweets.get().getUserMentioned();
		for (User mention : userMentions) {
			if (tweets.isEmpty() && tweets.isPresent() == false) {
				throw new NotFoundException("No tweet was found with this id: " + id +". Or this tweet has been deleted.");
			}
			return userMapper.entitiesToDto(userMentions);
		}
		return userMapper.entitiesToDto(userMentions);
	}
}
