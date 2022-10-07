package com.cookysys.social_media_project.mappers;

import java.util.List;

import org.mapstruct.Mapper;


import com.cookysys.social_media_project.dtos.TweetRequestDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.entities.Hashtag;

import com.cookysys.social_media_project.entities.Tweet;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {

List<TweetResponseDto> entitiesToDto(List<Tweet> tweets);

List<TweetResponseDto> entityToDto(Tweet tweet);
    
Tweet dtoToEntity(TweetRequestDto tweetRequestDto);

}
