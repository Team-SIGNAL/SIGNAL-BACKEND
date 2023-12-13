package com.example.signalbackend.domain.comment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryCommentListResponse {
    private final List<CommentListElement> commentList;
}
