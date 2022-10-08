package com.cookysys.social_media_project.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cookysys.social_media_project.dtos.TweetRequestDto;
import com.cookysys.social_media_project.dtos.UserRequestDto;
import com.cookysys.social_media_project.dtos.UserResponseDto;
import com.cookysys.social_media_project.entities.User;

@Mapper(componentModel = "spring", uses = { ProfileMapper.class, CredentialsMapper.class })
public interface UserMapper {
    
    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToResponseDto(User user);

	List<UserResponseDto> entityToResponseDto(List<User> users);

	User dtoToEntity(TweetRequestDto tweetRequestDto);
    
    
}
