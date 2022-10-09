package com.cookysys.social_media_project.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cookysys.social_media_project.dtos.ContextDto;
import com.cookysys.social_media_project.dtos.CredentialsDto;
import com.cookysys.social_media_project.dtos.HashtagDto;
import com.cookysys.social_media_project.dtos.TweetRequestDto;
import com.cookysys.social_media_project.dtos.TweetResponseDto;
import com.cookysys.social_media_project.dtos.UserResponseDto;
import com.cookysys.social_media_project.embeddables.CredentialsEmbeddable;
import com.cookysys.social_media_project.entities.Tweet;
import com.cookysys.social_media_project.entities.User;
import com.cookysys.social_media_project.exceptions.BadRequestException;
import com.cookysys.social_media_project.exceptions.NotAuthorizedException;
import com.cookysys.social_media_project.exceptions.NotFoundException;
import com.cookysys.social_media_project.mappers.CredentialsMapper;
import com.cookysys.social_media_project.mappers.HashtagMapper;
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
    private final UserMapper userMapper;
    private final HashtagMapper hashtagMapper;
    private final CredentialsMapper credentialsMapper;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final CredentialsDto credentialsDto;

    private Tweet checkTweet(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if (optionalTweet.isEmpty() || optionalTweet.get().isDeleted()) {
            throw new NotFoundException("No tweet found with the id " + id);
        }
        return optionalTweet.get();
    }

    private void validateTweet(TweetRequestDto tweetRequestDto) {
        if (tweetRequestDto.getContent() == null || tweetRequestDto.getCredentials() == null) {
            throw new BadRequestException("Invalid input.");
        }
    }

    private User findUser(String username) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user == null) {
                throw new NotFoundException("User not found.");
            }
            if (!user.getCredentials().getPassword().equals(credentialsDto.getPassword())) {
                throw new NotAuthorizedException("Your do not have permission to repost this tweet.");
            }
            if (user.getCredentials().getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<TweetResponseDto> getAllTweets() {
        List<Tweet> tweets = tweetRepository.findAll();
        List<TweetResponseDto> tweetsToGet = new ArrayList<>(
                tweetMapper.entitiesToResponseDtos(tweetRepository.findAll(Sort.by(Sort.Direction.DESC))));
        for (Tweet tweet : tweets) {
            if (!tweet.isDeleted()) {
                TweetResponseDto tweetToGet = tweetMapper.entityToResponseDto(tweet);
                UserResponseDto authorOf = tweetToGet.getAuthor();
                authorOf.setUsername(tweet.getAuthor().getCredentials().getUsername());
                tweetToGet.setAuthor(authorOf);
                tweetsToGet.add(tweetToGet);
            }
        }
        return tweetsToGet;
    }

    @Override
    public TweetResponseDto getTweet(Long id) {
        Tweet tweetToGet = checkTweet(id);
        TweetResponseDto tweet = tweetMapper.entityToResponseDto(tweetToGet);
        UserResponseDto tweeter = tweet.getAuthor();
        tweeter.setUsername(tweetToGet.getAuthor().getCredentials().getUsername());
        tweet.setAuthor(tweeter);
        return tweet;
    }

    @Override
    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
        validateTweet(tweetRequestDto);
        Tweet newTweet = tweetMapper.requestDtoToEntity(tweetRequestDto);
        CredentialsEmbeddable credentials = credentialsMapper.requestDtoToEntity(tweetRequestDto.getCredentials());

        User user = findUser(credentials.getUsername());
        newTweet.setAuthor(user);
        tweetRepository.saveAndFlush(newTweet);

        List<Tweet> tweets = user.getTweets();
        tweets.add(newTweet);
        user.setTweets(tweets);
        user.setDeleted(false);
        userRepository.saveAndFlush(user);

        // implement method that processes mentions and hashtags

        TweetResponseDto createdTweet = tweetMapper.entityToResponseDto(newTweet);
        UserResponseDto tweetCreator = createdTweet.getAuthor();
        tweetCreator.setUsername(user.getCredentials().getUsername());
        createdTweet.setAuthor(tweetCreator);

        return createdTweet;
    }

    @Override
    public TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto) {
        Tweet tweetToDelete = checkTweet(id);
        User user = findUser(credentialsDto.getUsername());

        TweetResponseDto deleteTweet = tweetMapper.entityToResponseDto(tweetToDelete);
        UserResponseDto deleter = userMapper.entityToResponseDto(user);
        deleter.setUsername(user.getCredentials().getUsername());
        deleteTweet.setAuthor(deleter);
        tweetToDelete.setDeleted(true);
        tweetRepository.saveAndFlush(tweetToDelete);

        return deleteTweet;
    }

    @Override
    public TweetResponseDto likeTweet(Long id, CredentialsDto credentialsDto) {
        Tweet tweetToLike = checkTweet(id);
        User liker = findUser(credentialsDto.getUsername());

        List<Tweet> tweets = liker.getLikedTweets();
        tweets.add(tweetToLike);
        liker.setLikedTweets(tweets);
        userRepository.saveAndFlush(liker);

        TweetResponseDto likedTweet = tweetMapper.entityToResponseDto(tweetToLike);
        UserResponseDto userWhoLiked = likedTweet.getAuthor();
        userWhoLiked.setUsername(liker.getCredentials().getUsername());
        likedTweet.setAuthor(userWhoLiked);

        return likedTweet;
    }

    @Override
    public TweetResponseDto replyToTweet(Long id, TweetRequestDto tweetRequestDto) {
        validateTweet(tweetRequestDto);
        Tweet tweetToReplyTo = checkTweet(id);
        Tweet tweetReply = tweetMapper.requestDtoToEntity(tweetRequestDto);
        CredentialsEmbeddable credentials = credentialsMapper.requestDtoToEntity((tweetRequestDto.getCredentials()));

        User replier = findUser(credentials.getUsername());
        tweetReply.setAuthor(replier);
        tweetRepository.saveAndFlush(tweetReply);

        List<Tweet> replies = replier.getTweets();
        replies.add(tweetReply);
        replier.setTweets(replies);
        tweetReply.setInReplyTo(tweetToReplyTo);
        userRepository.saveAndFlush(replier);

        // implement method that processes mentions and hashtags

        TweetResponseDto repliedTweet = tweetMapper.entityToResponseDto(tweetReply);
        UserResponseDto tweetReplier = repliedTweet.getAuthor();
        tweetReplier.setUsername(replier.getCredentials().getUsername());
        repliedTweet.setAuthor(tweetReplier);

        return repliedTweet;
    }

    @Override
    public List<HashtagDto> getTweetHashtags(Long id) {
        Tweet tweet = checkTweet(id);
        return hashtagMapper.entitiesToDtos(tweet.getHashtags());
    }

    @Override
    public TweetResponseDto repostTweet(Long id, CredentialsDto credentialsDto) {
        Tweet tweetToRepost = checkTweet(id);
        User reposter = findUser(credentialsDto.getUsername());

        Tweet repost = new Tweet();
        repost.setAuthor(reposter);
        repost.setContent(tweetToRepost.getContent());
        repost.setRepostOf(tweetToRepost);
        tweetRepository.saveAndFlush(repost);

        TweetResponseDto repostedTweet = tweetMapper.entityToResponseDto(repost);
        UserResponseDto tweetReposter = repostedTweet.getAuthor();
        tweetReposter.setUsername(reposter.getCredentials().getUsername());
        repostedTweet.setAuthor(tweetReposter);

        return repostedTweet;
    }

    @Override
    public List<UserResponseDto> getUsersWhoLikedATweet(Long id) {
        Tweet tweet = checkTweet(id);
        List<User> likes = tweet.getLikes();
        List<UserResponseDto> users = new ArrayList<>();
        for (User user : likes) {
            if (!user.isDeleted()) {
                users.add(userMapper.entityToResponseDto(user));
            }
        }
        return users;
    }

    @Override
    public ContextDto getContextOfTweet(Long id) {
        // TODO Auto-generated method stubs
        return null;
    }

}
