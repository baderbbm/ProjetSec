package com.nnk.springboot.unit.controller;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
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

public class RatingControllerTest {

    private RatingController ratingController;

    @Mock
    private RatingService ratingService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ratingController = new RatingController(ratingService);
    }

    @Test
    public void testListRatings() {
        List<Rating> ratings = List.of(Mockito.mock(Rating.class));
        when(ratingService.findAllRatings()).thenReturn(ratings);
        String viewName = ratingController.listRatings(model);
        assertEquals("rating/list", viewName);
        verify(ratingService, times(1)).findAllRatings();
        verify(model, times(1)).addAttribute("ratings", ratings);
    }

    @Test
    public void testAddRatingForm() {
        String viewName = ratingController.addRatingForm(model);
        assertEquals("rating/add", viewName);
    }

    @Test
    public void testValidateWithErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = ratingController.validate(Mockito.mock(Rating.class), bindingResult);
        assertEquals("rating/add", viewName);
    }

    @Test
    public void testValidateWithoutErrors() {
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = ratingController.validate(Mockito.mock(Rating.class), bindingResult);
        assertEquals("redirect:/rating/list", viewName);
    }

    @Test
    public void testShowUpdateForm() {
        Integer ratingId = 1;
        Rating mockRating = Mockito.mock(Rating.class);
        when(ratingService.findRatingById(ratingId)).thenReturn(mockRating);
        String viewName = ratingController.showUpdateForm(ratingId, model);
        assertEquals("rating/update", viewName);
        verify(ratingService, times(1)).findRatingById(ratingId);
        verify(model, times(1)).addAttribute("rating", mockRating);
    }

    @Test
    public void testUpdateRatingWithErrors() {
        Integer ratingId = 1;
        Rating mockRating = Mockito.mock(Rating.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = ratingController.updateRating(ratingId, mockRating, bindingResult);
        assertEquals("rating/update", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(ratingService, never()).saveRating(mockRating); // Make sure saveRating is not called with errors
    }

    @Test
    public void testUpdateRatingWithoutErrors() {
        Integer ratingId = 1;
        Rating mockRating = Mockito.mock(Rating.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = ratingController.updateRating(ratingId, mockRating, bindingResult);
        assertEquals("redirect:/rating/list", viewName);
        verify(bindingResult, times(1)).hasErrors();
        verify(ratingService, times(1)).saveRating(mockRating);
    }

    @Test
    public void testDeleteRating() {
        Integer ratingId = 1;
        String viewName = ratingController.deleteRating(ratingId);
        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService, times(1)).deleteRatingById(ratingId);
    }
}
