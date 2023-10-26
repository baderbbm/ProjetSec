package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TradeTests {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void tradeTest() {
        Trade trade = new Trade("Trade Account", "Type");

        // Save
        trade = tradeRepository.save(trade);
        assertNotNull(trade.getTradeId());
        assertTrue(trade.getAccount().equals("Trade Account"));

        // Update
        trade.setAccount("Trade Account Update");
        trade = tradeRepository.save(trade);
        assertTrue(trade.getAccount().equals("Trade Account Update"));

        // Find
        List<Trade> listResult = tradeRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = trade.getTradeId();
        tradeRepository.delete(trade);
        Optional<Trade> tradeList = tradeRepository.findById(id);
        assertFalse(tradeList.isPresent());
    }
    
    @Test
    public void tradeInvalidTest() {
        try {
            long initialCount = tradeRepository.count();
            Trade trade = new Trade("", "", -1.0);
            trade = tradeRepository.save(trade);
            long finalCount = tradeRepository.count();
            assertEquals(initialCount, finalCount);
        } catch (Exception e) {
        }
    }
}
