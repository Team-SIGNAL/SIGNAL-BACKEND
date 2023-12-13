package com.example.signalbackend.domain.diary.presentation;

import com.example.signalbackend.domain.diary.presentation.dto.request.CreateDiaryRequest;
import com.example.signalbackend.domain.diary.presentation.dto.response.QueryDiaryDetailsResponse;
import com.example.signalbackend.domain.diary.presentation.dto.response.QueryDiaryListResponse;
import com.example.signalbackend.domain.diary.presentation.dto.response.QueryMonthDiaryListResponse;
import com.example.signalbackend.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/diary")
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createDiary(@RequestBody CreateDiaryRequest request) {
        diaryService.createDiary(request);
    }

    @GetMapping("/details/{diary_id}")
    public QueryDiaryDetailsResponse queryDiaryDetails(@PathVariable(name = "diary_id") UUID diaryId) {
        return diaryService.queryDiaryDetails(diaryId);
    }

    @GetMapping("/list")
    public QueryDiaryListResponse queryDiaryList() {
        return diaryService.queryAllDiaryList();
    }

    @GetMapping
    public QueryDiaryListResponse queryDateDiaryList(@RequestParam("create_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createDate) {
        return diaryService.queryDateDiaryList(createDate);
    }

    @GetMapping("/month")
    public QueryMonthDiaryListResponse queryMonthDiaryList(@RequestParam("create_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createDate) {
        return diaryService.queryMonthDiaryList(createDate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{diary_id}")
    public void deleteDiary(@PathVariable(name = "diary_id") UUID diaryId) {
        diaryService.deleteDiary(diaryId);
    }
}
