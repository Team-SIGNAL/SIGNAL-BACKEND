package com.example.signalbackend.domain.diary.facade;

import com.example.signalbackend.domain.diary.domain.Diary;
import com.example.signalbackend.domain.diary.domain.repository.DiaryRepository;
import com.example.signalbackend.domain.diary.exception.DiaryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DiaryFacade {

    private final DiaryRepository diaryRepository;

    public Diary findDiaryById(UUID id) {
        return diaryRepository.findById(id).orElseThrow(() -> DiaryNotFoundException.EXCEPTION);
    }
}
