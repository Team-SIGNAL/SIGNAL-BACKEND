package com.example.signalbackend.domain.feed.service;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.domain.Role;
import com.example.signalbackend.domain.admin.facade.AdminFacade;
import com.example.signalbackend.domain.comment.domain.repository.CommentRepository;
import com.example.signalbackend.domain.feed.domain.Feed;
import com.example.signalbackend.domain.feed.domain.Tag;
import com.example.signalbackend.domain.feed.domain.repository.FeedRepository;
import com.example.signalbackend.domain.feed.exception.CreateFeedNotPermissionException;
import com.example.signalbackend.domain.feed.exception.FeedNotUpdateException;
import com.example.signalbackend.domain.feed.facade.FeedFacade;
import com.example.signalbackend.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.example.signalbackend.domain.feed.presentation.dto.request.UpdateFeedRequest;
import com.example.signalbackend.domain.feed.presentation.dto.response.FeedElement;
import com.example.signalbackend.domain.feed.presentation.dto.response.FeedListElement;
import com.example.signalbackend.domain.feed.presentation.dto.response.FeedListResponse;
import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;
    private final AdminFacade adminFacade;
    private final FeedFacade feedFacade;
    private final CommentRepository commentRepository;

    @Transactional
    public void createUserFeed(CreateFeedRequest request) {
        User user = userFacade.getCurrentUser();

        feedRepository.save(Feed.builder()
                .writerId(user.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .tag(Tag.GENERAL)
                .name(user.getName())
                .createDate(LocalDate.now())
                .build());
    }

    @Transactional
    public void createAdminFeed(CreateFeedRequest request) {
        Admin admin = adminFacade.getCurrentAdmin();

        if(admin.getRole().equals(Role.NON_HOSPITAL)) throw CreateFeedNotPermissionException.EXCEPTION;

        Tag tag = Tag.HOSPITAL;
        if(admin.getRole().equals(Role.ADMIN)) tag = Tag.NOTIFICATION;

        feedRepository.save(Feed.builder()
                .writerId(admin.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .tag(tag)
                .name(admin.getName())
                .createDate(LocalDate.now())
                .build());
    }

    @Transactional(readOnly = true)
    public FeedListResponse queryUserFeedList(Tag tag, Pageable pageable) {
        List<FeedListElement> feedList = feedRepository.findAllByTagOrderByCreateDate(tag, pageable)
                .stream()
                .map(feed -> {
                    User user = userFacade.getCurrentUser();
                    boolean checkIsWriter = (user.getId().equals(feed.getWriterId()));
                    return feedListBuilder(feed, checkIsWriter);
                })
                .collect(Collectors.toList());

        long pageTotal = feedList.stream().count();

        return new FeedListResponse(feedList, pageTotal);
    }

    @Transactional(readOnly = true)
    public FeedListResponse queryAdminFeedList(Tag tag, Pageable pageable) {
        List<FeedListElement> feedList = feedRepository.findAllByTagOrderByCreateDate(tag, pageable)
                .stream()
                .map(feed -> {
                    Admin admin = adminFacade.getCurrentAdmin();
                    boolean checkIsWriter = (admin.getId().equals(feed.getWriterId()));
                    return feedListBuilder(feed, checkIsWriter);
                })
                .collect(Collectors.toList());

        long pageTotal = feedList.stream().count();

        return new FeedListResponse(feedList, pageTotal);
    }

    private FeedListElement feedListBuilder(Feed feed, Boolean checkIsWriter) {
        return FeedListElement.builder()
                .id(feed.getId())
                .title(feed.getTitle())
                .image(feed.getImage())
                .name(feed.getName())
                .createDate(feed.getCreateDate())
                .isMine(checkIsWriter).build();
    }

    @Transactional(readOnly = true)
    public FeedElement queryUserFeedDetails(UUID feedId) {
        Feed feed = feedFacade.getFeedById(feedId);

        Optional<User> user = userFacade.getUserById(feed.getWriterId());
        Optional<Admin> admin = adminFacade.getAdminById(feed.getWriterId());
        UUID userId = userFacade.getCurrentUser().getId();
        boolean checkIsWriter = (userId.equals(feed.getWriterId()));

        if(!admin.isPresent()) {
            return feedDetailsBuilder(feed, user.get().getProfile(), user.get().getName(), checkIsWriter);
        }

        return feedDetailsBuilder(feed, admin.get().getProfile(), admin.get().getName(), checkIsWriter);
    }

    @Transactional(readOnly = true)
    public FeedElement queryAdminFeedDetails(UUID feedId) {
        Feed feed = feedFacade.getFeedById(feedId);

        Optional<Admin> admin = adminFacade.getAdminById(feed.getWriterId());
        Optional<User> user = userFacade.getUserById(feed.getWriterId());
        UUID adminId = adminFacade.getCurrentAdmin().getId();
        boolean checkIsWriter = (adminId.equals(feed.getWriterId()));

        if(!admin.isPresent()) {
            return feedDetailsBuilder(feed, user.get().getProfile(), user.get().getName(), checkIsWriter);
        }

        return feedDetailsBuilder(feed, admin.get().getProfile(), admin.get().getName(), checkIsWriter);
    }

    private FeedElement feedDetailsBuilder(Feed feed, String profile, String name, Boolean checkIsWriter) {
        return FeedElement.builder()
                .id(feed.getId())
                .image(feed.getImage())
                .title(feed.getTitle())
                .createDate(feed.getCreateDate())
                .writer(feed.getName())
                .content(feed.getContent())
                .profile(profile)
                .isMine(checkIsWriter)
                .build();
    }

    @Transactional
    public void updateFeed(UUID feedId, UpdateFeedRequest request) {
        Feed feed = feedFacade.getFeedById(feedId);

        if(!checkFeedWriter(feed)) {
            throw FeedNotUpdateException.EXCEPTION;
        }
        feed.updateFeed(request.getTitle(), request.getContent(), request.getImage());
    }

    @Transactional
    public void deleteFeed(UUID feedId) {
        Feed feed = feedFacade.getFeedById(feedId);
        checkFeedWriter(feed);

        commentRepository.deleteAllByFeed_Id(feedId);
        feedRepository.deleteById(feed.getId());
    }

    private Boolean checkFeedWriter(Feed feed) {
        boolean checkIsWriter = false;

        if(feed.getTag().equals(Tag.GENERAL)) {
            userFacade.getUserById(feed.getWriterId());
            UUID userId = userFacade.getCurrentUser().getId();
            checkIsWriter = (userId.equals(feed.getWriterId()));
        } else {
            adminFacade.getAdminById(feed.getWriterId());
            UUID adminId = adminFacade.getCurrentAdmin().getId();
            checkIsWriter = (adminId.equals(feed.getWriterId()));
        }

        return checkIsWriter;
    }

    @Transactional
    public void reportFeed(UUID feedId) {
        Feed feed = feedFacade.getFeedById(feedId);
        feed.updateReportStatus(true);
    }
}
