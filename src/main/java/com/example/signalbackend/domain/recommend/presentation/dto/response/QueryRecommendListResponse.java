package com.example.signalbackend.domain.recommend.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryRecommendListResponse {
    private final List<RecommendListElement> recommendList;
}
