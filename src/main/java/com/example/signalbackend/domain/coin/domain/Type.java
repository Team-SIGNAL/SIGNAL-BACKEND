package com.example.signalbackend.domain.coin.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    COMMENT(1, "댓글 작성을 완료했어요 🐾"),
    FEED(2, "게시글 작성을 완료했어요 🌱"),
    DIARY(3, "일기 작성을 완료했어요 🦖"),
    RESERVATION(4, "진료 예약을 완료했어요 🪴");

    private final int num;
    private final String activity;
}
