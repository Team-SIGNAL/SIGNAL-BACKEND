package com.example.signalbackend.domain.recommend.presentation;

import com.example.signalbackend.domain.recommend.domain.Category;
import com.example.signalbackend.domain.recommend.presentation.dto.request.CreateRecommendRequest;
import com.example.signalbackend.domain.recommend.presentation.dto.response.QueryRecommendDetailsResponse;
import com.example.signalbackend.domain.recommend.presentation.dto.response.QueryRecommendListResponse;
import com.example.signalbackend.domain.recommend.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/recommend")
@RestController
public class RecommendController {

    private final RecommendService recommendService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createRecommend(@RequestBody CreateRecommendRequest request) {
        recommendService.createRecommend(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{recommend_id}")
    public void deleteRecommend(@PathVariable(name = "recommend_id") UUID recommendId) {
        recommendService.deleteRecommend(recommendId);
    }

    @GetMapping("/{recommend_id}")
    public QueryRecommendDetailsResponse queryRecommendDetail(@PathVariable(name = "recommend_id") UUID recommendId) {
        return recommendService.queryRecommendDetail(recommendId);
    }

    @GetMapping("/list")
    public QueryRecommendListResponse queryRecommendList(@RequestParam(name = "category") Category category) {
        return recommendService.queryRecommendList(category);
    }

}
