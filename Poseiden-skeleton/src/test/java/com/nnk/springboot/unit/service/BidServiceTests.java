package com.nnk.springboot.unit.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidService;
import org.junit.jupiter.api.AfterEach;
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
public class BidServiceTests {

    @Mock
    private BidListRepository bidListRepository;

    private BidService bidService;

    @BeforeEach
    public void setUp() {
        bidService = new BidService(bidListRepository);
    }

    @Test
    public void shouldCreateBid() {
        BidList bid = new BidList();
        when(bidListRepository.save(bid)).thenReturn(bid);
        BidList createdBid = bidService.createBid(bid);
        assertEquals(bid, createdBid);
    }

    @Test
    public void shouldFindAllBids() {
        List<BidList> expectedBids = new ArrayList<>();
        when(bidListRepository.findAll()).thenReturn(expectedBids);
        List<BidList> actualBids = bidService.findAllBids();
        assertEquals(expectedBids, actualBids);
    }

    @Test
    public void shouldFindBidById() {
        Integer bidId = 1;
        BidList expectedBid = new BidList();
        when(bidListRepository.findById(bidId)).thenReturn(Optional.of(expectedBid));
        BidList actualBid = bidService.findBidById(bidId);
        assertEquals(expectedBid, actualBid);
    }

    @Test
    public void shouldUpdateBid() {
        BidList bid = new BidList();
        when(bidListRepository.save(bid)).thenReturn(bid);
        bidService.updateBid(bid);
        verify(bidListRepository, times(1)).save(bid);
    }

    @Test
    public void shouldDeleteBidById() {
        Integer bidId = 1;
        BidList bid = new BidList();
        when(bidListRepository.findById(bidId)).thenReturn(Optional.of(bid));
        bidService.deleteBidById(bidId);
        verify(bidListRepository, times(1)).delete(bid);
    }
    
    @Test
    public void shouldThrowExceptionWhenBidNotFound() {
        Integer bidId = 1;
        when(bidListRepository.findById(bidId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            bidService.findBidById(bidId);
        });
        verify(bidListRepository, times(1)).findById(bidId);
    }

    @Test
    public void shouldFindBidWhenBidExists() {
        Integer bidId = 1;
        BidList expectedBid = new BidList();
        when(bidListRepository.findById(bidId)).thenReturn(Optional.of(expectedBid));
        assertDoesNotThrow(() -> {
            BidList actualBid = bidService.findBidById(bidId);
            assertEquals(expectedBid, actualBid);
        });
        verify(bidListRepository, times(1)).findById(bidId);
    }
}
