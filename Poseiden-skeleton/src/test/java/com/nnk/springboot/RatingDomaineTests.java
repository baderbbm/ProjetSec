package com.nnk.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.nnk.springboot.domain.Rating;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingDomaineTests {

    private Rating rating;

    @BeforeEach
    public void setUp() {
        rating = new Rating("Moody's", "S&P", "Fitch", 1);
        rating.setId(1);
        rating.setFitchRating("Fitch");
        rating.setMoodysRating("Moody's");
        rating.setSandPRating("S&P");
    }

    @Test
    public void testGetId() {
        assertEquals(1, rating.getId());
    }

    @Test
    public void testGetMoodysRating() {
        assertEquals("Moody's", rating.getMoodysRating());
    }

    @Test
    public void testGetSandPRating() {
        assertEquals("S&P", rating.getSandPRating());
    }

    @Test
    public void testGetFitchRating() {
        assertEquals("Fitch", rating.getFitchRating());
    }

    @Test
    public void testGetOrderNumber() {
        assertEquals(1, rating.getOrderNumber());
    }
}
