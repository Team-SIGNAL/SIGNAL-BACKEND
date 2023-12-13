package com.example.signalbackend.domain.diary.presentation.dto.response;

import com.example.signalbackend.domain.diary.domain.Emotion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class QueryDiaryDetailsResponse {
    private final LocalDate createDate;
    private final String title;
    private final String content;
    private final Emotion emotion;
    private final String image;
}
