package com.gmail.merikbest2015.commons.projection;

import com.gmail.merikbest2015.commons.enums.LinkCoverSize;
import com.gmail.merikbest2015.commons.enums.ReplyType;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

public interface TweetProjection {
    Long getId();
    String getText();
    LocalDateTime getDateTime();
    LocalDateTime getScheduledDate();
    String getAddressedUsername();
    Long getAddressedId();
    Long getAddressedTweetId();
    ReplyType getReplyType();
    String getLink();
    String getLinkTitle();
    String getLinkDescription();
    String getLinkCover();
    LinkCoverSize getLinkCoverSize();
    UserProjection getUser();
    List<ImageProjection> getImages();
    QuoteTweetProjection getQuoteTweet();
    PollProjection getPoll();
    boolean isDeleted();

    @Value("#{@tweetServiceImpl.isUserLikedTweet(target.id)}")
    boolean getIsTweetLiked();

    @Value("#{@tweetServiceImpl.isUserRetweetedTweet(target.id)}")
    boolean getIsTweetRetweeted();

    @Value("#{@tweetServiceImpl.isUserBookmarkedTweet(target.id)}")
    boolean getIsTweetBookmarked();

    @Value("#{@tweetServiceImpl.isUserFollowByOtherUser(target.user.id)}")
    boolean getIsUserFollowByOtherUser();

    @Value("#{target.retweets.size()}")
    Integer getRetweetsCount();

    @Value("#{target.likedTweets.size()}")
    Integer getLikedTweetsCount();

    @Value("#{target.replies.size()}")
    Integer getRepliesCount();

    @Value("#{target.quotes.size()}")
    Integer getQuotesCount();

    interface UserProjection {
        Long getId();
        String getEmail();
        String getFullName();
        String getUsername();
        ImageProjection getAvatar();

        @Value("#{@tweetServiceImpl.isUserMutedByMyProfile(target.id)}")
        boolean getIsUserMuted();

        @Value("#{@tweetServiceImpl.isUserBlockedByMyProfile(target.id)}")
        boolean getIsUserBlocked();

        @Value("#{@tweetServiceImpl.isMyProfileBlockedByUser(target.id)}")
        boolean getIsMyProfileBlocked();

        @Value("#{@tweetServiceImpl.isMyProfileWaitingForApprove(target.id)}")
        boolean getIsWaitingForApprove();

        @Value("#{@tweetServiceImpl.isUserFollowByOtherUser(target.id)}")
        boolean getIsFollower();
    }

    interface QuoteTweetProjection {
        @Value("#{target.isDeleted ? null : target.id}")
        Long getId();

        @Value("#{target.isDeleted ? null : target.text}")
        String getText();

        @Value("#{target.isDeleted ? null : target.dateTime}")
        LocalDateTime getDateTime();

        @Value("#{target.isDeleted ? null : target.link}")
        String getLink();

        @Value("#{target.isDeleted ? null : target.linkTitle}")
        String getLinkTitle();

        @Value("#{target.isDeleted ? null : target.linkDescription}")
        String getLinkDescription();

        @Value("#{target.isDeleted ? null : target.linkCover}")
        String getLinkCover();

        @Value("#{target.isDeleted ? null : target.linkCoverSize}")
        LinkCoverSize getLinkCoverSize();

        @Value("#{target.isDeleted ? null : target.user}")
        UserProjection getUser();
        boolean isDeleted();
    }

    interface PollProjection {
        Long getId();
        LocalDateTime getDateTime();
        List<PollChoiceProjection> getPollChoices();
    }

    interface PollChoiceProjection {
        Long getId();
        String getChoice();
        List<VotedUser> getVotedUser();
    }

    interface VotedUser {
        Long getId();
    }
}
