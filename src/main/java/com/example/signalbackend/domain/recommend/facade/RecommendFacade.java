package com.example.signalbackend.domain.recommend.facade;

import com.example.signalbackend.domain.recommend.domain.Recommend;
import com.example.signalbackend.domain.recommend.domain.repository.RecommendRepository;
import com.example.signalbackend.domain.recommend.exception.RecommendNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RecommendFacade {

    private final RecommendRepository recommendRepository;

    public Recommend getRecommendById(UUID recommendId) {
        return recommendRepository.findById(recommendId)
                .orElseThrow(()-> RecommendNotFoundException.EXCEPTION);
    }
}
