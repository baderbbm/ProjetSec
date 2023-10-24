package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RatingTests {

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void ratingTest() {
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

        // Save
        rating = ratingRepository.save(rating);
        assertNotNull(rating.getId());
        assertEquals(10, rating.getOrderNumber());

        // Update
        rating.setOrderNumber(20);
        rating = ratingRepository.save(rating);
        assertEquals(20, rating.getOrderNumber());

        // Find
        List<Rating> listResult = ratingRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rating.getId();
        ratingRepository.delete(rating);
        Optional<Rating> ratingList = ratingRepository.findById(id);
        assertFalse(ratingList.isPresent());
    }
    @Test
    public void ratingInvalidTest() {
        Rating rating = new Rating("", "", "", -1);
        if (isValidRating(rating)) {
            rating = ratingRepository.save(rating);
            assertNotNull(rating.getId());
            assertEquals("ValidRating", rating.getMoodysRating());
        } else {
            System.out.println("Données non valides : la sauvegarde a échoué");
        }
    }

    private boolean isValidRating(Rating rating) {
        return !rating.getMoodysRating().isEmpty() &&
               !rating.getSandPRating().isEmpty() &&
               !rating.getFitchRating().isEmpty() &&
               rating.getOrderNumber() > 0;
    }

}
