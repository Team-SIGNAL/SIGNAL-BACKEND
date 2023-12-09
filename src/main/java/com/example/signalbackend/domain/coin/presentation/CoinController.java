package com.example.signalbackend.domain.coin.presentation;

import com.example.signalbackend.domain.coin.presentation.dto.request.CreateCoinRequest;
import com.example.signalbackend.domain.coin.presentation.dto.response.CoinCountResponse;
import com.example.signalbackend.domain.coin.presentation.dto.response.QueryCoinListResponse;
import com.example.signalbackend.domain.coin.service.CoinService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/coin")
@RestController
public class CoinController {

    private final CoinService coinService;

    @PostMapping
    public CoinCountResponse createCoin(@RequestBody CreateCoinRequest request) {
        return coinService.createCoin(request);
    }

    @GetMapping("/list")
    public QueryCoinListResponse queryCoinList() {
        return coinService.queryCoinList();
    }
}
