package com.example.signalbackend.domain.coin.service;

import com.example.signalbackend.domain.coin.domain.Coin;
import com.example.signalbackend.domain.coin.domain.repository.CoinRepository;
import com.example.signalbackend.domain.coin.presentation.dto.request.CreateCoinRequest;
import com.example.signalbackend.domain.coin.presentation.dto.response.CoinCountResponse;
import com.example.signalbackend.domain.coin.presentation.dto.response.CoinListElement;
import com.example.signalbackend.domain.coin.presentation.dto.response.QueryCoinListResponse;
import com.example.signalbackend.domain.user.domain.User;
import com.example.signalbackend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CoinService {

    private final CoinRepository coinRepository;
    private final UserFacade userFacade;

    @Transactional
    public CoinCountResponse createCoin(CreateCoinRequest request) {
        User user = userFacade.getCurrentUser();
        user.addUserCoinCount(request.getCoin());

        coinRepository.save(Coin.builder()
                .coin(request.getCoin())
                .type(request.getType())
                .createDate(LocalDate.now())
                .user(user)
                .build());

        return new CoinCountResponse(user.getCoinCount());
    }

    @Transactional(readOnly = true)
    public QueryCoinListResponse queryCoinList() {
        User user = userFacade.getCurrentUser();

        List<CoinListElement> coinList = coinRepository.findAllByUserOrderByCreateDate(user).stream()
                .map(coin -> {
                    return CoinListElement.builder()
                            .coin(coin.getCoin())
                            .activity(coin.getType().getActivity())
                            .createDate(coin.getCreateDate())
                            .build();
                }).collect(Collectors.toList());

        return new QueryCoinListResponse(coinList);
    }
}
