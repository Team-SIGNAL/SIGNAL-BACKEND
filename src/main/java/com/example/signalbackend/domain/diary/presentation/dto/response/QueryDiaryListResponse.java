package com.example.signalbackend.domain.diary.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryDiaryListResponse {
    private final List<DiaryListElement> diaryList;
}
