package com.example.signalbackend.domain.feed.presentation;

import com.example.signalbackend.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.example.signalbackend.domain.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
