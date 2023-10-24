package com.nnk.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.nnk.springboot.domain.Trade;
import java.util.Date;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeDomaineTests {

    private Trade trade;

    @BeforeEach
    public void setUp() {
        trade = new Trade("Account123", "Type456");
        trade.setTradeId(1);
        trade.setType("Type456");
        trade.setBuyQuantity(100.0);
        trade.setSellQuantity(50.0);
        trade.setBuyPrice(200.0);
        trade.setSellPrice(150.0);
        trade.setTradeDate(new Date());
        trade.setSecurity("SecurityXYZ");
        trade.setStatus("New");
        trade.setTrader("John Doe");
        trade.setBenchmark("Benchmark123");
        trade.setBook("Book456");
        trade.setCreationName("Creator");
        trade.setCreationDate(new Date());
        trade.setRevisionName("Revisor");
        trade.setRevisionDate(new Date());
        trade.setDealName("DealXYZ");
        trade.setDealType("TypeABC");
        trade.setSourceListId("List123");
        trade.setSide("Buy");
    }

    @Test
    public void testGetTradeId() {
        assertEquals(1, trade.getTradeId());
    }

    @Test
    public void testGetAccount() {
        assertEquals("Account123", trade.getAccount());
    }

    @Test
    public void testGetType() {
        assertEquals("Type456", trade.getType());
    }

    @Test
    public void testGetBuyQuantity() {
        assertEquals(100.0, trade.getBuyQuantity());
    }

    @Test
    public void testGetSellQuantity() {
        assertEquals(50.0, trade.getSellQuantity());
    }

    @Test
    public void testGetBuyPrice() {
        assertEquals(200.0, trade.getBuyPrice());
    }

    @Test
    public void testGetSellPrice() {
        assertEquals(150.0, trade.getSellPrice());
    }

    @Test
    public void testGetTradeDate() {
        Date expectedDate = new Date(); 
        Date actualDate = trade.getTradeDate();
        assertEquals(expectedDate.getTime(), actualDate.getTime());
    }


    @Test
    public void testGetSecurity() {
        assertEquals("SecurityXYZ", trade.getSecurity());
    }

    @Test
    public void testGetStatus() {
        assertEquals("New", trade.getStatus());
    }

    @Test
    public void testGetTrader() {
        assertEquals("John Doe", trade.getTrader());
    }

    @Test
    public void testGetBenchmark() {
        assertEquals("Benchmark123", trade.getBenchmark());
    }

    @Test
    public void testGetBook() {
        assertEquals("Book456", trade.getBook());
    }

    @Test
    public void testGetCreationName() {
        assertEquals("Creator", trade.getCreationName());
    }

    @Test
    public void testGetCreationDate() {
        Date expectedDate = new Date();
        long expectedTime = expectedDate.getTime();
        long actualTime = trade.getCreationDate().getTime(); 
        long tolerance = 1000; 
        assertTrue(Math.abs(expectedTime - actualTime) <= tolerance);
    }


    @Test
    public void testGetRevisionName() {
        assertEquals("Revisor", trade.getRevisionName());
    }

    @Test
    public void testGetRevisionDate() {
        Date expectedDate = new Date();
        long expectedTime = expectedDate.getTime();
        long actualTime = trade.getRevisionDate().getTime();
        long tolerance = 1000; 
        assertTrue(Math.abs(expectedTime - actualTime) <= tolerance);
    }

    @Test
    public void testGetDealName() {
        assertEquals("DealXYZ", trade.getDealName());
    }

    @Test
    public void testGetDealType() {
        assertEquals("TypeABC", trade.getDealType());
    }

    @Test
    public void testGetSourceListId() {
        assertEquals("List123", trade.getSourceListId());
    }

    @Test
    public void testGetSide() {
        assertEquals("Buy", trade.getSide());
    }
}
