package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/rating")
public class RatingController {

	private final RatingService ratingService;

	@Autowired
	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@GetMapping("/list")
	public String listRatings(Model model) {
		model.addAttribute("ratings", ratingService.findAllRatings());
		return "rating/list";
	}

	@GetMapping("/add")
	public String addRatingForm(Model model) {
		model.addAttribute("rating", new Rating());
		return "rating/add";
	}

	@PostMapping("/validate")
	public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result) {
		if (result.hasErrors()) {
			return "rating/add";
		}
		ratingService.saveRating(rating);
		return "redirect:/rating/list";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.findRatingById(id);
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") Rating rating,
			BindingResult result) {
		if (result.hasErrors()) {
			return "rating/update";
		}
		ratingService.saveRating(rating);
		return "redirect:/rating/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id) {
		ratingService.deleteRatingById(id);
		return "redirect:/rating/list";
	}
}
