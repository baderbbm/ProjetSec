package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
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
public class RatingServiceTests {

    @Mock
    private RatingRepository ratingRepository;

    private RatingService ratingService;

    @BeforeEach
    public void setUp() {
        ratingService = new RatingService(ratingRepository);
    }

    @Test
    public void shouldSaveRating() {
        Rating rating = new Rating();
        when(ratingRepository.save(rating)).thenReturn(rating);
        Rating savedRating = ratingService.saveRating(rating);
        assertEquals(rating, savedRating);
    }

    @Test
    public void shouldFindAllRatings() {
        List<Rating> expectedRatings = new ArrayList<>();
        when(ratingRepository.findAll()).thenReturn(expectedRatings);
        List<Rating> actualRatings = ratingService.findAllRatings();
        assertEquals(expectedRatings, actualRatings);
    }

    @Test
    public void shouldFindRatingById() {
        Integer ratingId = 1;
        Rating expectedRating = new Rating();
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(expectedRating));
        Rating actualRating = ratingService.findRatingById(ratingId);
        assertEquals(expectedRating, actualRating);
    }

    @Test
    public void shouldDeleteRatingById() {
        Integer ratingId = 1;
        Rating rating = new Rating();
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));
        ratingService.deleteRatingById(ratingId);
        verify(ratingRepository, times(1)).delete(rating);
    }
    @Test
    public void shouldThrowExceptionWhenRatingNotFound() {
        Integer ratingId = 1;
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            ratingService.findRatingById(ratingId);
        });
        verify(ratingRepository, times(1)).findById(ratingId);
    }

    @Test
    public void shouldFindRatingWhenRatingExists() {
        Integer ratingId = 1;
        Rating expectedRating = new Rating();
        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(expectedRating));
        assertDoesNotThrow(() -> {
            Rating actualRating = ratingService.findRatingById(ratingId);
            assertEquals(expectedRating, actualRating);
        });
        verify(ratingRepository, times(1)).findById(ratingId);
    }
}

