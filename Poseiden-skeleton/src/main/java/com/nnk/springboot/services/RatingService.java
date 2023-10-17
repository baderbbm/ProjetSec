package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.List;

@Service
@Transactional
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating findRatingById(Integer id) {
        return ratingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id: " + id));
    }

    public void deleteRatingById(Integer id) {
        Rating rating = findRatingById(id);
        ratingRepository.delete(rating);
    }
}
