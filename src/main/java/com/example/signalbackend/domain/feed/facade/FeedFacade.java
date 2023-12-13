package com.example.signalbackend.domain.feed.facade;

import com.example.signalbackend.domain.feed.domain.Feed;
import com.example.signalbackend.domain.feed.domain.repository.FeedRepository;
import com.example.signalbackend.domain.feed.exception.FeedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FeedFacade {
    private final FeedRepository feedRepository;

    public Feed getFeedById(UUID feedId) {
        return feedRepository.findById(feedId).orElseThrow(()-> FeedNotFoundException.EXCEPTION);
    }
}
