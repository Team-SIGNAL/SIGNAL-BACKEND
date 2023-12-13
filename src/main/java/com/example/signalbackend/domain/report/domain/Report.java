package com.example.signalbackend.domain.report.domain;

import com.example.signalbackend.global.entity.BaseIdEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_report")
@Entity
public class Report extends BaseIdEntity {

    @Column(columnDefinition = "CHAR(200)")
    private String image;

    @Column(columnDefinition = "VARCHAR(1000)", nullable = false)
    private String content;
}
