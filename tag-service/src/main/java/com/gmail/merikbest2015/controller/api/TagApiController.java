package com.gmail.merikbest2015.controller.api;

import com.gmail.merikbest2015.models.Tag;
import com.gmail.merikbest2015.service.TagClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagApiController {

    private final TagClientService tagClientService;

    @GetMapping("/{tweetId}")
    public List<Tag> getTagsByTweetId(@PathVariable Long tweetId) {
        return tagClientService.getTagsByTweetId(tweetId);
    }

    @GetMapping("/search")
    public Tag getTagByTagName(@RequestParam String tagName) {
        return tagClientService.getTagByTagName(tagName);
    }

    @PostMapping("/save")
    public Tag saveTag(@RequestBody Tag tag) {
        return tagClientService.saveTag(tag);
    }

    @PostMapping("/delete")
    public void deleteTag(@RequestBody Tag tag) {
        tagClientService.deleteTag(tag);
    }
}