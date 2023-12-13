package com.example.signalbackend.domain.diary.presentation.dto.request;

import com.example.signalbackend.domain.diary.domain.Emotion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateDiaryRequest {
    private String title;
    private String content;
    private Emotion emotion;
    private LocalDate createDate;
    private String image;
}
