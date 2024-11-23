package com.gmail.merikbest2015.broker.consumer;

import com.gmail.merikbest2015.commons.constants.HeaderConstants;
import com.gmail.merikbest2015.commons.constants.KafkaTopicConstants;
import com.gmail.merikbest2015.commons.event.UpdateTweetCountEvent;
import com.gmail.merikbest2015.service.UserUpdateTweetCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateTweetCountConsumer {

    private final UserUpdateTweetCountService userUpdateTweetCountService;

    @KafkaListener(topics = KafkaTopicConstants.UPDATE_USER_TWEETS_COUNT_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void updateTweetCountListener(UpdateTweetCountEvent tweetCountEvent, @Header(HeaderConstants.AUTH_USER_ID_HEADER) String authId) {
        userUpdateTweetCountService.handleUpdateTweetCount(tweetCountEvent, authId);
    }

    @KafkaListener(topics = KafkaTopicConstants.UPDATE_USER_LIKES_COUNT_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void updateLikeTweetCountListener(UpdateTweetCountEvent tweetCountEvent, @Header(HeaderConstants.AUTH_USER_ID_HEADER) String authId) {
        userUpdateTweetCountService.handleUpdateLikeTweetCount(tweetCountEvent, authId);
    }

    @KafkaListener(topics = KafkaTopicConstants.UPDATE_USER_MEDIA_COUNT_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void updateMediaTweetCountListener(UpdateTweetCountEvent tweetCountEvent, @Header(HeaderConstants.AUTH_USER_ID_HEADER) String authId) {
        userUpdateTweetCountService.handleUpdateMediaTweetCount(tweetCountEvent, authId);
    }
}
