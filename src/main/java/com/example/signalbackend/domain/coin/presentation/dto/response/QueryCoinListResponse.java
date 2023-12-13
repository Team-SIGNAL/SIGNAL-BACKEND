package com.example.signalbackend.domain.coin.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryCoinListResponse {
    private final List<CoinListElement> coinList;
}
