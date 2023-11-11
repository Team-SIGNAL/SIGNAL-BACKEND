package com.example.signalbackend.domain.feed.domain;

import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_feed")
@Entity
public class Feed extends BaseIdEntity {

    @Column(columnDefinition = "VARCHAR(60)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(3000)")
    private String content;

    @Column(columnDefinition = "CHAR(60)")
    private String image;

    @Column(columnDefinition = "VARCHAR(12)", nullable = false)
    private Tag tag;

    @Column(nullable = false)
    private Long writerId;

    private LocalDate creatDate;
}
