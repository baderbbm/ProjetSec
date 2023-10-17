package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TradeService {
    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Trade createTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public List<Trade> findAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade findTradeById(Integer id) {
        return tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
    }

    public void updateTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    @Transactional
    public void deleteTradeById(Integer id) {
        Trade trade = findTradeById(id);
        tradeRepository.delete(trade);
    }
}
