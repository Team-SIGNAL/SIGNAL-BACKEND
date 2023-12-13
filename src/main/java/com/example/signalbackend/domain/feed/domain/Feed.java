package com.example.signalbackend.domain.feed.domain;

import com.example.signalbackend.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

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

    @Column(columnDefinition = "CHAR(200)")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(12)", nullable = false)
    private Tag tag;

    @Column(nullable = false)
    private UUID writerId;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate createDate;

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    @ColumnDefault("0")
    private boolean reportStatus;

    public void updateReportStatus(Boolean reportStatus) {
        this.reportStatus = reportStatus;
    }

    public void updateFeed(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
