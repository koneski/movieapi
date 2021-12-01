package com.movieapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapi.data.model.Movie;
import com.movieapi.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Movie>> getAll() {
		return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
	}
}