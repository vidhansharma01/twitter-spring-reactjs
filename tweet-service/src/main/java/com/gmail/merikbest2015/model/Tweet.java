package com.gmail.merikbest2015.model;

import com.gmail.merikbest2015.enums.LinkCoverSize;
import com.gmail.merikbest2015.enums.ReplyType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"poll"})
@Table(name = "tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweets_seq")
    @SequenceGenerator(name = "tweets_seq", sequenceName = "tweets_seq", initialValue = 100, allocationSize = 1)
    private Long id;

    @Column(name = "text", length = 1337, columnDefinition = "text")
    private String text;

    @Column(name = "date_time")
    private LocalDateTime dateTime = LocalDateTime.now();

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @Column(name = "addressed_username")
    private String addressedUsername;

    @Column(name = "addressed_id")
    private Long addressedId;

    @Column(name = "addressed_tweet_id")
    private Long addressedTweetId;

    @Column(name = "reply_type")
    @Enumerated(EnumType.STRING)
    private ReplyType replyType;

    @Column(name = "link")
    private String link;

    @Column(name = "link_title")
    private String linkTitle;

    @Column(name = "link_description")
    private String linkDescription;

    @Column(name = "link_cover")
    private String linkCover;

    @Column(name = "link_cover_size")
    @Enumerated(EnumType.STRING)
    private LinkCoverSize linkCoverSize;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @OneToMany
    private List<TweetImage> images;

    @OneToOne
    @JoinTable(name = "tweet_quote",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_tweet_id"))
    private Tweet quoteTweet;

    @OneToOne
    @JoinTable(name = "tweet_poll",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "poll_id"))
    private Poll poll;

    @ManyToMany
    @JoinTable(name = "replies",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "reply_id"))
    private List<Tweet> replies;

    @OneToMany
    @JoinTable(name = "quotes",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    private List<Tweet> quotes;
}