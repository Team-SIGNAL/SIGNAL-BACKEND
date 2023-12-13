package com.example.signalbackend.domain.recommend.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class RecommendListElement {
    private final UUID id;
    private final String title;
    private final String content;
    private final String image;
    private final String link;
}
