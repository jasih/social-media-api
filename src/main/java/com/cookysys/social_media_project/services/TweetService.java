package com.cookysys.social_media_project.services;

import java.util.List;

import com.cookysys.social_media_project.dtos.TweetRequestDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.dtos.UserRequestDto;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();

    TweetResponseDto createTweet(TweetRequestDto tweet);

    TweetResponseDto getTweet(Long id);

    TweetResponseDto deleteTweet(Long id);

    TweetResponseDto likeTweet(Long id, UserRequestDto user);

    TweetResponseDto replyToTweet(Long id, String reply);

    TweetResponseDto getHashtagOfTweet(Long id);

    TweetResponseDto repostTweet(Long id, UserRequestDto user);

    TweetResponseDto getUsersWhoLikedATweet(Long id);

    TweetResponseDto getContextOfTweet(Long id);

}
