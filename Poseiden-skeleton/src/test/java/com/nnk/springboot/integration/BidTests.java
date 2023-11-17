package com.nnk.springboot.integration;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BidTests {

	@Autowired
	private BidListRepository bidListRepository;

	        
	@Test
	public void bidListTest() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		bid = bidListRepository.save(bid);
		assertNotNull(bid.getId());
		assertEquals(10d, bid.getBidQuantity(), 0.01);

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		assertEquals(20d, bid.getBidQuantity(), 0.01);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertFalse(bidList.isPresent());
	}

	
	@Test
	public void testNoSaveWhenDataIsInvalid() {
	    try {
	        long initialCount = bidListRepository.count();	       
	        BidList bid = new BidList("", "", -1);
	        bid = bidListRepository.save(bid);
	        long finalCount = bidListRepository.count();
	        assertEquals(initialCount, finalCount);
	    } catch (Exception e) {
	    }
	}
}
