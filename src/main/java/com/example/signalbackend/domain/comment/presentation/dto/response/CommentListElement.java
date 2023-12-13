package com.example.signalbackend.domain.comment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentListElement {
    private final String content;
    private final LocalDateTime createDateTime;
    private final String name;
    private final String profile;
}
