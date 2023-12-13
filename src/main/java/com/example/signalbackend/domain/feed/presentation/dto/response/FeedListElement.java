package com.example.signalbackend.domain.feed.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class FeedListElement {
    private final UUID id;
    private final String title;
    private final String image;
    private final String name;
    private final LocalDate createDate;
    private final Boolean isMine;
}
