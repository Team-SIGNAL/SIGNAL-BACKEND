package com.example.signalbackend.domain.feed.domain.repository;

import com.example.signalbackend.domain.feed.domain.Feed;
import com.example.signalbackend.domain.feed.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedRepository extends JpaRepository<Feed, UUID> {
    Page<Feed> findAllByTagOrderByCreateDate(Tag tag, Pageable pageable);
}