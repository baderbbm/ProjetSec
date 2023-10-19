package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TradeServiceTests {

    @Mock
    private TradeRepository tradeRepository;

    private TradeService tradeService;

    @BeforeEach
    public void setUp() {
        tradeService = new TradeService(tradeRepository);
    }

    @Test
    public void shouldCreateTrade() {
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);
        Trade createdTrade = tradeService.createTrade(trade);
        assertEquals(trade, createdTrade);
    }

    @Test
    public void shouldFindAllTrades() {
        List<Trade> expectedTrades = new ArrayList<>();
        when(tradeRepository.findAll()).thenReturn(expectedTrades);
        List<Trade> actualTrades = tradeService.findAllTrades();
        assertEquals(expectedTrades, actualTrades);
    }

    @Test
    public void shouldFindTradeById() {
        Integer tradeId = 1;
        Trade expectedTrade = new Trade();
        when(tradeRepository.findById(tradeId)).thenReturn(Optional.of(expectedTrade));
        Trade actualTrade = tradeService.findTradeById(tradeId);
        assertEquals(expectedTrade, actualTrade);
    }

    @Test
    public void shouldUpdateTrade() {
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);
        tradeService.updateTrade(trade);
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    public void shouldDeleteTradeById() {
        Integer tradeId = 1;
        Trade trade = new Trade();
        when(tradeRepository.findById(tradeId)).thenReturn(Optional.of(trade));
        tradeService.deleteTradeById(tradeId);
        verify(tradeRepository, times(1)).delete(trade);
    }
    
    @Test
    public void testThrowExceptionWhenTradeNotFound() {
        Integer tradeId = 1;
        when(tradeRepository.findById(tradeId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            tradeService.findTradeById(tradeId);
        });
        verify(tradeRepository, times(1)).findById(tradeId);
    }

    @Test
    public void testdFindTradeWhenTradeExists() {
        Integer tradeId = 1;
        Trade expectedTrade = new Trade();
        when(tradeRepository.findById(tradeId)).thenReturn(Optional.of(expectedTrade));
        assertDoesNotThrow(() -> {
            Trade actualTrade = tradeService.findTradeById(tradeId);
            assertEquals(expectedTrade, actualTrade);
        });
        verify(tradeRepository, times(1)).findById(tradeId);
    }
    

}
