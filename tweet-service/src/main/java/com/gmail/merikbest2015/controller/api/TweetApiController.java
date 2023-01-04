package com.gmail.merikbest2015.controller.api;

import com.gmail.merikbest2015.client.tweet.TweetPageableRequest;
import com.gmail.merikbest2015.client.tweet.TweetUserIdsRequest;
import com.gmail.merikbest2015.commons.dto.TweetResponse;
import com.gmail.merikbest2015.commons.models.Tweet;
import com.gmail.merikbest2015.commons.projection.TweetImageProjection;
import com.gmail.merikbest2015.commons.projection.TweetProjection;
import com.gmail.merikbest2015.commons.projection.TweetsUserProjection;
import com.gmail.merikbest2015.mapper.TweetClientMapper;
import com.gmail.merikbest2015.service.TweetClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tweets")
public class TweetApiController {

    private final TweetClientService tweetClientService;
    private final TweetClientMapper tweetClientMapper;

    @GetMapping("/{userId}")
    public Optional<Tweet> getTweetById(@PathVariable("userId") Long userId) {
        return tweetClientService.getTweetById(userId);
    }

    @GetMapping("/user/{userId}")
    public List<TweetsUserProjection> getTweetsByUserId(@PathVariable("userId") Long userId) {
        return tweetClientService.getTweetsByUserId(userId);
    }

    @GetMapping("/pinned/{userId}")
    public Optional<TweetsUserProjection> getPinnedTweetByUserId(@PathVariable("userId") Long userId) {
        return tweetClientService.getPinnedTweetByUserId(userId);
    }

    @PostMapping("/user/media")
    public Page<TweetProjection> getAllUserMediaTweets(@RequestBody TweetPageableRequest request) {
        return tweetClientService.getAllUserMediaTweets(request);
    }

    @PostMapping("/user/mentions")
    public Page<TweetProjection> getUserMentions(@RequestBody TweetPageableRequest request) {
        return tweetClientService.getUserMentions(request);
    }

    @PostMapping("/user/images")
    public List<TweetImageProjection> getUserTweetImages(@RequestBody TweetPageableRequest request) {
        return tweetClientService.getUserTweetImages(request);
    }

    @GetMapping("/replies/{userId}")
    public List<TweetsUserProjection> getRepliesByUserId(@PathVariable("userId") Long userId) {
        return tweetClientService.getRepliesByUserId(userId);
    }

    @GetMapping("/notification/{userId}")
    public List<TweetProjection> getNotificationsFromTweetAuthors(@PathVariable("userId") Long userId) {
        return tweetClientService.getNotificationsFromTweetAuthors(userId);
    }

    @GetMapping("/tag")
    public List<TweetResponse> getTweetsByTagName(@RequestParam("tagName") String tagName) {
        return tweetClientMapper.getTweetsByTagName(tagName);
    }

    @GetMapping("/user/ids")
    public Page<TweetProjection> getTweetsByUserIds(@RequestBody TweetUserIdsRequest request) {
        return tweetClientService.getTweetsByUserIds(request);
    }
}
