package com.cookysys.social_media_project.services.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.dtos.TweetRequestDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.dtos.UserRequestDto;
import com.cookysys.social_media_project.mappers.TweetMapper;
import com.cookysys.social_media_project.repositories.TweetRepository;
import com.cookysys.social_media_project.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetMapper tweetMapper;
    private final TweetRepository tweetRepository;

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

}
