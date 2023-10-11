package com.nnk.springboot.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
@Transactional
public class BidService {
    private final BidListRepository bidListRepository;

    @Autowired
    public BidService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

   
    public BidList createBid(BidList bid) {
        return bidListRepository.save(bid);
    }

    public List<BidList> findAllBids() {
        return bidListRepository.findAll();
    }

    public BidList findBidById(Integer id) {
        return bidListRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
    }

    public void updateBid(BidList bid) {
        bidListRepository.save(bid);
    }

    @Transactional
    public void deleteBidById(Integer id) {
        BidList bid = findBidById(id);
        bidListRepository.delete(bid);
    }
}
