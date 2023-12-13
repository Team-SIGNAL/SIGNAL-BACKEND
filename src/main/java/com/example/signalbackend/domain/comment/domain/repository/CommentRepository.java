package com.example.signalbackend.domain.comment.domain.repository;

import com.example.signalbackend.domain.comment.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {
    List<Comment> findAllByFeed_IdOrderByCreateDateTime(UUID feedId);
    void deleteAllByFeed_Id(UUID feedId);
}
