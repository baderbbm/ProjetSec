package com.nnk.springboot.unit.controller;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.List;

public class TradeControllerTest {

    private TradeController tradeController;

    @Mock
    private TradeService tradeService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        tradeController = new TradeController(tradeService);
    }

    @Test
    public void testHome() {
        List<Trade> trades = List.of(Mockito.mock(Trade.class));
        when(tradeService.findAllTrades()).thenReturn(trades);
        String viewName = tradeController.home(model);
        assertEquals("trade/list", viewName);
        verify(tradeService, times(1)).findAllTrades();
        verify(model, times(1)).addAttribute("trades", trades);
    }

    @Test
    public void testAddTradeForm() {
        String viewName = tradeController.addTradeForm(model);
        assertEquals("trade/add", viewName);
    }

    @Test
    public void testValidateWithErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = tradeController.validate(Mockito.mock(Trade.class), bindingResult);
        assertEquals("trade/add", viewName);
    }

    @Test
    public void testValidateWithoutErrors() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = tradeController.validate(Mockito.mock(Trade.class), bindingResult);
        assertEquals("redirect:/trade/list", viewName);
    }

    @Test
    public void testShowUpdateForm() {
        Integer tradeId = 1;
        Trade mockTrade = Mockito.mock(Trade.class);
        when(tradeService.findTradeById(tradeId)).thenReturn(mockTrade);
        String viewName = tradeController.showUpdateForm(tradeId, model);
        assertEquals("trade/update", viewName);
        verify(tradeService, times(1)).findTradeById(tradeId);
        verify(model, times(1)).addAttribute("trade", mockTrade);
    }

    @Test
    public void testUpdateTradeWithErrors() {
        Integer tradeId = 1;
        Trade mockTrade = Mockito.mock(Trade.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = tradeController.updateTrade(tradeId, mockTrade, bindingResult);
        assertEquals("trade/update", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(tradeService, never()).updateTrade(mockTrade); // Make sure updateTrade is not called with errors
    }

    @Test
    public void testUpdateTradeWithoutErrors() {
        Integer tradeId = 1;
        Trade mockTrade = Mockito.mock(Trade.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = tradeController.updateTrade(tradeId, mockTrade, bindingResult);
        assertEquals("redirect:/trade/list", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(mockTrade, times(1)).setTradeId(tradeId);
        verify(tradeService, times(1)).updateTrade(mockTrade);
    }

    @Test
    public void testDeleteTrade() {
        Integer tradeId = 1;
        String viewName = tradeController.deleteTrade(tradeId);
        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService, times(1)).deleteTradeById(tradeId);
    }
}
