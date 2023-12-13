package com.example.signalbackend.domain.coin.presentation.dto.request;

import com.example.signalbackend.domain.coin.domain.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateCoinRequest {
    @NotNull
    private Long coin;
    @NotNull
    private Type type;
}
