package com.example.signalbackend.domain.recommend.service;

import com.example.signalbackend.domain.admin.domain.Admin;
import com.example.signalbackend.domain.admin.exception.AdminNotFoundException;
import com.example.signalbackend.domain.admin.facade.AdminFacade;
import com.example.signalbackend.domain.recommend.domain.Category;
import com.example.signalbackend.domain.recommend.domain.Recommend;
import com.example.signalbackend.domain.recommend.domain.repository.RecommendRepository;
import com.example.signalbackend.domain.recommend.exception.RecommendNotDeleteException;
import com.example.signalbackend.domain.recommend.facade.RecommendFacade;
import com.example.signalbackend.domain.recommend.presentation.dto.request.CreateRecommendRequest;
import com.example.signalbackend.domain.recommend.presentation.dto.response.QueryRecommendDetailsResponse;
import com.example.signalbackend.domain.recommend.presentation.dto.response.QueryRecommendListResponse;
import com.example.signalbackend.domain.recommend.presentation.dto.response.RecommendListElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendService {

    private final RecommendRepository recommendRepository;
    private final AdminFacade adminFacade;
    private final RecommendFacade recommendFacade;

    @Transactional
    public void createRecommend(CreateRecommendRequest request) {
        Admin admin = adminFacade.getCurrentAdmin();

        recommendRepository.save(Recommend.builder()
                .image(request.getImage())
                .link(request.getLink())
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .admin(admin)
                .createDate(LocalDate.now())
                .build());
    }

    @Transactional
    public void deleteRecommend(UUID recommendId) {
        Admin admin = adminFacade.getCurrentAdmin();
        Recommend recommend = recommendFacade.getRecommendById(recommendId);

        boolean checkIsWriter = !(admin.getId().equals(recommend.getAdmin().getId()));
        if (checkIsWriter) {
            throw RecommendNotDeleteException.EXCEPTION;
        }

        recommendRepository.deleteById(recommend.getId());
    }

    @Transactional(readOnly = true)
    public QueryRecommendDetailsResponse queryRecommendDetail(UUID recommendId) {
        Recommend recommend = recommendFacade.getRecommendById(recommendId);
        Admin admin = adminFacade.getAdminById(recommend.getAdmin().getId())
                .orElseThrow(()-> AdminNotFoundException.EXCEPTION);

        return new QueryRecommendDetailsResponse(
                recommend.getTitle(),
                recommend.getImage(),
                recommend.getContent(),
                recommend.getLink(),
                admin.getName(),
                admin.getProfile(),
                recommend.getCreateDate()
        );
    }

    @Transactional(readOnly = true)
    public QueryRecommendListResponse queryRecommendList(Category category) {
        List<RecommendListElement> recommendList = recommendRepository.findAllByCategory(category).stream()
                .map(recommend -> {
                    return RecommendListElement.builder()
                            .id(recommend.getId())
                            .title(recommend.getTitle())
                            .content(recommend.getContent())
                            .image(recommend.getImage())
                            .link(recommend.getLink())
                            .build();
                }).collect(Collectors.toList());

        return new QueryRecommendListResponse(recommendList);
    }
}
