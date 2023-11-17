package com.nnk.springboot.unit.domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.nnk.springboot.domain.BidList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BidListDomaineTests {

    private BidList bidList;

    @BeforeEach
    public void setUp() {
        bidList = new BidList("Account123", "Type456", 100.0);
        bidList.setAccount("Account123");
        bidList.setType("Type456");
        bidList.setId(1);
        bidList.setAskQuantity(50.0);
        bidList.setBid(200.0);
        bidList.setAsk(150.0);
        bidList.setBenchmark("Benchmark123");
        bidList.setBidListDate(new Date());
        bidList.setCommentary("Sample Commentary");
        bidList.setSecurity("SecurityXYZ");
        bidList.setStatus("New");
        bidList.setTrader("John Doe");
        bidList.setBook("Book456");
        bidList.setCreationName("Creator");
        bidList.setCreationDate(new Date());
        bidList.setRevisionName("Revisor");
        bidList.setRevisionDate(new Date());
        bidList.setDealName("DealXYZ");
        bidList.setDealType("TypeABC");
        bidList.setSourceListId("List123");
        bidList.setSide("Buy");
    }

    @Test
    public void testGetId() {
        assertEquals(1, bidList.getId());
    }

    @Test
    public void testGetAccount() {
        assertEquals("Account123", bidList.getAccount());
    }

    @Test
    public void testGetType() {
        assertEquals("Type456", bidList.getType());
    }

    @Test
    public void testGetBidQuantity() {
        assertEquals(100.0, bidList.getBidQuantity());
    }

    @Test
    public void testGetAskQuantity() {
        assertEquals(50.0, bidList.getAskQuantity());
    }

    @Test
    public void testGetBid() {
        assertEquals(200.0, bidList.getBid());
    }

    @Test
    public void testGetAsk() {
        assertEquals(150.0, bidList.getAsk());
    }

    @Test
    public void testGetBenchmark() {
        assertEquals("Benchmark123", bidList.getBenchmark());
    }

    @Test
    public void testGetBidListDate() {
        Date expectedDate = new Date();
        long expectedTime = expectedDate.getTime();
        long actualTime = bidList.getBidListDate().getTime();
        long tolerance = 1000; 
        assertTrue(Math.abs(expectedTime - actualTime) <= tolerance);
    }



    @Test
    public void testGetCommentary() {
        assertEquals("Sample Commentary", bidList.getCommentary());
    }

    @Test
    public void testGetSecurity() {
        assertEquals("SecurityXYZ", bidList.getSecurity());
    }

    @Test
    public void testGetStatus() {
        assertEquals("New", bidList.getStatus());
    }

    @Test
    public void testGetTrader() {
        assertEquals("John Doe", bidList.getTrader());
    }

    @Test
    public void testGetBook() {
        assertEquals("Book456", bidList.getBook());
    }

    @Test
    public void testGetCreationName() {
        assertEquals("Creator", bidList.getCreationName());
    }

    @Test
    public void testGetRevisionName() {
        assertEquals("Revisor", bidList.getRevisionName());
    }

    
    @Test
    public void testGetCreationDate() {
        LocalDate expectedDate = LocalDate.now();
        LocalDate actualDate = bidList.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(expectedDate, actualDate);
    }


    @Test
    public void testGetDealName() {
        assertEquals("DealXYZ", bidList.getDealName());
    }

    @Test
    public void testGetDealType() {
        assertEquals("TypeABC", bidList.getDealType());
    }

    @Test
    public void testGetSourceListId() {
        assertEquals("List123", bidList.getSourceListId());
    }

    @Test
    public void testGetSide() {
        assertEquals("Buy", bidList.getSide());
    }
}

