package com.example.signalbackend.domain.feed.service;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.domain.Role;
import com.example.signalbackend.domain.admin.facade.AdminFacade;
import com.example.signalbackend.domain.feed.domain.Feed;
import com.example.signalbackend.domain.feed.domain.Tag;
import com.example.signalbackend.domain.feed.domain.repository.FeedRepository;
import com.example.signalbackend.domain.feed.exception.CreateFeedNotPermissionException;
import com.example.signalbackend.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;
    private final AdminFacade adminFacade;

    @Transactional
    public void createUserFeed(CreateFeedRequest request) {
        User user = userFacade.getCurrentUser();

        feedRepository.save(Feed.builder()
                .writerId(user.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .tag(Tag.GENERAL)
                        .creatDate(LocalDate.now())
                .build());
    }

    @Transactional
    public void createAdminFeed(CreateFeedRequest request) {
        Admin admin = adminFacade.getCurrentAdmin();

        if(admin.getRole() == Role.NON_HOSPITAL) throw CreateFeedNotPermissionException.EXCEPTION;

        Tag tag = Tag.HOSPITAL;
        if(admin.getRole() == Role.ADMIN) tag = Tag.NOTIFICATION;

        feedRepository.save(Feed.builder()
                .writerId(admin.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .tag(tag)
                .creatDate(LocalDate.now())
                .build());
    }

}
