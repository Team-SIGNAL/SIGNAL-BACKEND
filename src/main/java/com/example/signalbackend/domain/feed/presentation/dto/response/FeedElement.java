package com.example.signalbackend.domain.feed.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class FeedElement {
    private final UUID id;
    private final String image;
    private final String title;
    private final LocalDate createDate;
    private final String writer;
    private final String content;
    private final String profile;
    private final boolean isMine;
}
