package com.example.signalbackend.domain.diary.service;

import com.example.signalbackend.domain.diary.domain.Diary;
import com.example.signalbackend.domain.diary.domain.repository.DiaryRepository;
import com.example.signalbackend.domain.diary.exception.DiaryNotDeleteException;
import com.example.signalbackend.domain.diary.facade.DiaryFacade;
import com.example.signalbackend.domain.diary.presentation.dto.request.CreateDiaryRequest;
import com.example.signalbackend.domain.diary.presentation.dto.response.DiaryListElement;
import com.example.signalbackend.domain.diary.presentation.dto.response.MonthDiaryListElement;
import com.example.signalbackend.domain.diary.presentation.dto.response.QueryDiaryDetailsResponse;
import com.example.signalbackend.domain.diary.presentation.dto.response.QueryDiaryListResponse;
import com.example.signalbackend.domain.diary.presentation.dto.response.QueryMonthDiaryListResponse;
import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserFacade userFacade;
    private final DiaryFacade diaryFacade;

    @Transactional
    public void createDiary(CreateDiaryRequest request) {
        User user = userFacade.getCurrentUser();

        diaryRepository.save(Diary.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .emotion(request.getEmotion())
                .createDate(request.getCreateDate())
                .image(request.getImage())
                .user(user)
                .build());
    }

    @Transactional(readOnly = true)
    public QueryDiaryDetailsResponse queryDiaryDetails(UUID diaryId) {
        Diary diary = diaryFacade.findDiaryById(diaryId);

        return new QueryDiaryDetailsResponse(
                diary.getCreateDate(),
                diary.getTitle(),
                diary.getContent(),
                diary.getEmotion(),
                diary.getImage()
        );
    }

    @Transactional(readOnly = true)
    public QueryDiaryListResponse queryAllDiaryList() {
        User user = userFacade.getCurrentUser();

        List<DiaryListElement> diaryList = diaryRepository.findAllByUserOrderByCreateDate(user).stream()
                .map(diary -> {
                    return diaryListBuilder(diary);
                }).collect(Collectors.toList());

        return new QueryDiaryListResponse(diaryList);
    }

    @Transactional(readOnly = true)
    public QueryDiaryListResponse queryDateDiaryList(LocalDate createDate) {
        User user = userFacade.getCurrentUser();

        List<DiaryListElement> diaryList = diaryRepository.findAllByCreateDateAndUserOrderByCreateDate(createDate, user).stream()
                .map(diary -> {
                    return diaryListBuilder(diary);
                }).collect(Collectors.toList());

        return new QueryDiaryListResponse(diaryList);
    }

    private DiaryListElement diaryListBuilder(Diary diary) {
        return DiaryListElement.builder()
                .id(diary.getId())
                .title(diary.getTitle())
                .content(diary.getContent())
                .image(diary.getImage())
                .emotion(diary.getEmotion())
                .build();
    }

    @Transactional(readOnly = true)
    public QueryMonthDiaryListResponse queryMonthDiaryList(LocalDate createDate) {
        User user = userFacade.getCurrentUser();

        LocalDate endDate = createDate.withDayOfMonth(createDate.lengthOfMonth());

        List<MonthDiaryListElement> monthDiaryList =
                diaryRepository.findAllByUserAndCreateDateBetweenOrderByCreateDate(user, createDate, endDate).stream()
                .map(diary -> {
                    return MonthDiaryListElement.builder()
                            .id(diary.getId())
                            .createDate(diary.getCreateDate())
                            .build();
                }).collect(Collectors.toList());

        return new QueryMonthDiaryListResponse(monthDiaryList);
    }

    @Transactional
    public void deleteDiary(UUID diaryId) {
        User user = userFacade.getCurrentUser();
        Diary diary = diaryFacade.findDiaryById(diaryId);
        boolean checkDiaryWriter = !(user.getId().equals(diary.getUser().getId()));

        if(checkDiaryWriter) {
            throw DiaryNotDeleteException.EXCEPTION;
        }

        diaryRepository.delete(diary);
    }
}
