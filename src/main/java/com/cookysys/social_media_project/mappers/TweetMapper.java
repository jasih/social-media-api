package com.cookysys.social_media_project.Mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {
    
}
