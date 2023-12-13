package com.example.signalbackend.domain.diary.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class MonthDiaryListElement {
    private final UUID id;
    private final LocalDate createDate;
}
