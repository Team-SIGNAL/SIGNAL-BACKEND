package com.example.signalbackend.domain.comment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
    @NotNull
    private String content;
}
