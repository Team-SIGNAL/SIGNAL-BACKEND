package com.example.signalbackend.domain.comment.presentation;

import com.example.signalbackend.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.example.signalbackend.domain.comment.presentation.dto.response.QueryCommentListResponse;
import com.example.signalbackend.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/{feed_id}")
    public void creatAdminComment(
            @PathVariable(name = "feed_id") UUID feedId,
            @RequestBody CreateCommentRequest request
    ) {
        commentService.createAdminComment(feedId, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/{feed_id}")
    public void creatUserComment(
            @PathVariable(name = "feed_id") UUID feedId,
            @RequestBody CreateCommentRequest request
    ) {
        commentService.createUserComment(feedId, request);
    }

    @GetMapping("/{feed_id}")
    public QueryCommentListResponse queryCommentList(@PathVariable(name = "feed_id") UUID feedId) {
        return commentService.queryCommentList(feedId);
    }
}
