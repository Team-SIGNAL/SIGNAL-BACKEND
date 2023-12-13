package com.example.signalbackend.domain.recommend.domain;

import com.example.signalbackend.domain.admin.domain.Admin;
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
@Table(name = "tbl_recommend")
@Entity
public class Recommend extends BaseIdEntity {

    @Column(columnDefinition = "CHAR(100)")
    private String image;

    @Column(columnDefinition = "VARCHAR(500)")
    private String link;

    @Column(columnDefinition = "VARCHAR(300)")
    private String title;

    @Column(columnDefinition = "VARCHAR(3000)")
    private String content;

    @Column(columnDefinition = "VARCHAR(5)")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Admin admin;

    private LocalDate createDate;
}
