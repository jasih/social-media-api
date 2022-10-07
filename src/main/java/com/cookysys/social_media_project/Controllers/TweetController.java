package com.cookysys.social_media_project.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.cookysys.social_media_project.dtos.TweetRequestDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.dtos.UserRequestDto;
import com.cookysys.social_media_project.dtos.UserResponseDto;
import com.cookysys.social_media_project.services.TweetService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/tweets")
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;

    @GetMapping
    public List<TweetResponseDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @PostMapping
    public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.createTweet(tweetRequestDto);
    }

    @GetMapping(value="/{id}")
    public TweetResponseDto getTweet(@PathVariable("id") Long id) {
        return tweetService.getTweet(id);
    }

    @DeleteMapping(value="/{id}")
    public TweetResponseDto deleteTweet(@PathVariable("id") Long id) {
        return tweetService.deleteTweet(id);
    }

    @PostMapping(value="/{id}/like")
    public TweetResponseDto likeTweet(@PathVariable("id") Long id, @RequestBody UserRequestDto user) {
        return tweetService.likeTweet(id, user);
    }

    @PostMapping(value="/{id}/reply")
    public TweetResponseDto replyToTweet(@PathVariable("id") Long id, @PathVariable("reply") String reply) {
        return tweetService.replyToTweet(id, reply);
    }

    @GetMapping(value="/{id}/tags")
    public TweetResponseDto getHashtagOfTweet(@PathVariable("id") Long id) {
        return tweetService.getHashtagOfTweet(id);
    }

    @PostMapping(value="/{id}/repost")
    public TweetResponseDto repostTweet(@PathVariable("id") Long id, @RequestBody UserRequestDto user) {
        return tweetService.repostTweet(id, user);
    }

    @GetMapping(value="/{id}/likes")
    public TweetResponseDto getUsersWhoLikedATweet(@PathVariable("id") Long id) {
        return tweetService.getUsersWhoLikedATweet(id);
    }

    @GetMapping(value="path")
    public TweetResponseDto getContextOfTweet(@PathVariable("id") Long id) {
        return tweetService.getContextOfTweet(id);
    }
    
    @GetMapping("/{id}/replies")
    public List<TweetResponseDto> getRepliesToTweets(@PathVariable Long id, @RequestBody UserRequestDto user) {
    	return tweetService.getRepliesToTweets(id, user);
    }
    
    @GetMapping("/{id}/reposts")
    public List<TweetResponseDto> getRepostsOfTweets(@PathVariable Long id, @RequestBody UserRequestDto user) {
    	return tweetService.getRepostsOfTweets(id, user);
    }

    @GetMapping("/{id}/mentions")
    public List<UserResponseDto> getMentions(@PathVariable Long id, @RequestBody UserRequestDto user) {
    	return tweetService.getMentions(id, user);
    }
    
}
