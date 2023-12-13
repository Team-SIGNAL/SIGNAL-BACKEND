package com.example.signalbackend.domain.feed.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateFeedRequest {
    private String title;
    private String content;
    private String image;
}
