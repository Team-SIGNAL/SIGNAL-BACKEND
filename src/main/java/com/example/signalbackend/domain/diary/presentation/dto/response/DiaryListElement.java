package com.example.signalbackend.domain.diary.presentation.dto.response;

import com.example.signalbackend.domain.diary.domain.Emotion;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DiaryListElement {
    private final UUID id;
    private final String title;
    private final String content;
    private final String image;
    private final Emotion emotion;
}
