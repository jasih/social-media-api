package com.cookysys.social_media_project.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cookysys.social_media_project.dtos.UserResponseDto;
import com.cookysys.social_media_project.entities.User;

@Mapper(componentModel = "spring", uses = { ProfileMapper.class, CredentialsMapper.class })
public interface UserMapper {
    
    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToDto(User user);
    
}
