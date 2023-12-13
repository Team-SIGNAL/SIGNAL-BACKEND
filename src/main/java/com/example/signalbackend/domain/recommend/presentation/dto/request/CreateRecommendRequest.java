package com.example.signalbackend.domain.recommend.presentation.dto.request;

import com.example.signalbackend.domain.recommend.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateRecommendRequest {

    private String image;
    @NotBlank
    private String link;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private Category category;
}
