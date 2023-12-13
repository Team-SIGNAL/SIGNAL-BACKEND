package com.example.signalbackend.domain.feed.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeedListResponse {
    private final List<FeedListElement> feedList;
    private final long pageTotal;
}


