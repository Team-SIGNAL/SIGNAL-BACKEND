package com.example.signalbackend.domain.recommend.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class QueryRecommendDetailsResponse {

    private final String title;
    private final String image;
    private final String content;
    private final String link;
    private final String name;
    private final String profile;
    private final LocalDate createDate;
}
