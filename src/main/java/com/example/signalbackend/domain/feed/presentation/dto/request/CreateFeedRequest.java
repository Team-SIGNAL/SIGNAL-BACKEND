package com.example.signalbackend.domain.feed.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
public class CreateFeedRequest {
    @NotBlank
    @Size(min = 1, max = 60)
    private String title;
    @Size(min = 1, max = 3000)
    private String content;
    private String image;
}
