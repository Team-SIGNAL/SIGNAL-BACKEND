package com.example.signalbackend.domain.feed.presentation;

import com.example.signalbackend.domain.feed.domain.Tag;
import com.example.signalbackend.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.example.signalbackend.domain.feed.presentation.dto.request.UpdateFeedRequest;
import com.example.signalbackend.domain.feed.presentation.dto.response.FeedElement;
import com.example.signalbackend.domain.feed.presentation.dto.response.FeedListResponse;
import com.example.signalbackend.domain.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/feed")
@RestController
public class FeedController {
    private final FeedService feedService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public void createUserFeed(@RequestBody @Valid CreateFeedRequest request) {
        feedService.createUserFeed(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin")
    public void createAdminFeed(@RequestBody @Valid CreateFeedRequest request) {
        feedService.createAdminFeed(request);
    }

    @GetMapping("/user/list")
    public FeedListResponse queryUserFeedList(@RequestParam(name = "tag")Tag tag, Pageable pageable) {
        return feedService.queryUserFeedList(tag, pageable);
    }

    @GetMapping("/admin/list")
    public FeedListResponse queryAdminFeedList(@RequestParam(name = "tag")Tag tag, Pageable pageable) {
        return feedService.queryAdminFeedList(tag, pageable);
    }

    @GetMapping("/user/{feed_id}")
    public FeedElement queryUserFeedDetails(@PathVariable(name = "feed_id") UUID feedId) {
        return feedService.queryUserFeedDetails(feedId);
    }

    @GetMapping("/admin/{feed_id}")
    public FeedElement queryAdminFeedDetails(@PathVariable(name = "feed_id") UUID feedId) {
        return feedService.queryAdminFeedDetails(feedId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{feed_id}")
    public void updateFeed(
            @PathVariable(name = "feed_id") UUID feedId,
            @RequestBody UpdateFeedRequest request
    ) {
        feedService.updateFeed(feedId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed_id}")
    public void deleteFeed(@PathVariable(name = "feed_id") UUID feedId) {
        feedService.deleteFeed(feedId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/report/{feed_id}")
    public void reportFeed(@PathVariable(name = "feed_id") UUID feedId) {
        feedService.reportFeed(feedId);
    }
}

