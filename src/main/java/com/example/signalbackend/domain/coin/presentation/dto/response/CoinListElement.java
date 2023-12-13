package com.example.signalbackend.domain.coin.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CoinListElement {
    private final Long coin;
    private final String activity;
    private final LocalDate createDate;
}
