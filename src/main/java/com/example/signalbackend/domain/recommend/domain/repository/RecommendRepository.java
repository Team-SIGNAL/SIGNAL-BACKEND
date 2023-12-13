package com.example.signalbackend.domain.recommend.domain.repository;

import com.example.signalbackend.domain.recommend.domain.Category;
import com.example.signalbackend.domain.recommend.domain.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecommendRepository extends JpaRepository<Recommend, UUID> {
    List<Recommend> findAllByCategory(Category category);
}
