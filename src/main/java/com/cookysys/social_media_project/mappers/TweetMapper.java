package com.cookysys.social_media_project.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cookysys.social_media_project.dtos.TweetRequestDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.entities.Tweet;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {

    TweetResponseDto entityToDto(Tweet tweet);

    List<TweetResponseDto> entitiesToDtos(List<Tweet> tweets);
    
    Tweet dtoToEntity(TweetRequestDto tweetRequestDto);

}
