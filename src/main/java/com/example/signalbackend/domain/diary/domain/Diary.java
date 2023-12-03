package com.example.signalbackend.domain.diary.domain;

import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_diary")
@Entity
public class Diary extends BaseIdEntity {

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(4000)")
    private String content;

    @Column(columnDefinition = "VARCHAR(11)")
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    private LocalDate createDate;

    @Column(columnDefinition = "CHAR(200)")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
