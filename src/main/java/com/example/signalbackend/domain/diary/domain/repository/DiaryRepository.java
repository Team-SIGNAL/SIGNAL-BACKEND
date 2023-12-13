package com.example.signalbackend.domain.diary.domain.repository;

import com.example.signalbackend.domain.diary.domain.Diary;
import com.example.signalbackend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DiaryRepository extends JpaRepository<Diary, UUID> {
    List<Diary> findAllByUserOrderByCreateDate(User user);
    List<Diary> findAllByCreateDateAndUserOrderByCreateDate(LocalDate createDate, User user);
    List<Diary> findAllByUserAndCreateDateBetweenOrderByCreateDate(User user, LocalDate startDate, LocalDate endDate);
}
