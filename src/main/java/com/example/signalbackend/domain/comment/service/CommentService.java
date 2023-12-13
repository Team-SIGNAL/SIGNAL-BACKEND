package com.example.signalbackend.domain.comment.service;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.exception.AdminNotFoundException;
import com.example.signalbackend.domain.admin.facade.AdminFacade;
import com.example.signalbackend.domain.comment.domain.Comment;
import com.example.signalbackend.domain.comment.domain.repository.CommentRepository;
import com.example.signalbackend.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.example.signalbackend.domain.comment.presentation.dto.response.CommentListElement;
import com.example.signalbackend.domain.comment.presentation.dto.response.QueryCommentListResponse;
import com.example.signalbackend.domain.feed.domain.Feed;
import com.example.signalbackend.domain.feed.facade.FeedFacade;
import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.domain.user.exception.UserNotFoundException;
import com.example.signalbackend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final FeedFacade feedFacade;
    private final CommentRepository commentRepository;
    private final UserFacade userFacade;
    private final AdminFacade adminFacade;

    @Transactional
    public void createUserComment(UUID feedId, CreateCommentRequest request) {
        User user = userFacade.getCurrentUser();
        saveFeed(feedId, user.getId(), user.getName(), request.getContent());
    }

    @Transactional
    public void createAdminComment(UUID feedId, CreateCommentRequest request) {
        Admin admin = adminFacade.getCurrentAdmin();
        saveFeed(feedId, admin.getId(), admin.getName(), request.getContent());
    }

    private void saveFeed(UUID feedId, UUID writerId, String name, String content) {
        Feed feed = feedFacade.getFeedById(feedId);

        commentRepository.save(Comment.builder()
                        .feed(feed)
                        .writerId(writerId)
                        .content(content)
                        .name(name)
                        .createDateTime(LocalDateTime.now())
                .build());
    }

    @Transactional(readOnly = true)
    public QueryCommentListResponse queryCommentList(UUID feedId) {
        Feed feed = feedFacade.getFeedById(feedId);

        List<CommentListElement> commentList = commentRepository.findAllByFeed_IdOrderByCreateDateTime(feed.getId())
                .stream()
                .map(comment -> {
                    UUID writerId = comment.getWriterId();
                    String profile = null;

                    if(!(userFacade.getUserById(writerId).isPresent())) {
                        profile = adminFacade.getAdminById(writerId)
                                .orElseThrow(()-> AdminNotFoundException.EXCEPTION).getProfile();
                    } else {
                        profile = userFacade.getUserById(writerId)
                                .orElseThrow(()-> UserNotFoundException.EXCEPTION).getProfile();
                    }

                    return CommentListElement.builder()
                            .name(comment.getName())
                            .createDateTime(comment.getCreateDateTime())
                            .content(comment.getContent())
                            .profile(profile)
                            .build();
                })
                .collect(Collectors.toList());

        return new QueryCommentListResponse(commentList);
    }
}
