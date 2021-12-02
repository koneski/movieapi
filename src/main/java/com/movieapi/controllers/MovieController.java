package com.movieapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapi.data.dto.MimMaxResultDto;
import com.movieapi.services.MovieService;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/intervalo-premios")
	public ResponseEntity<MimMaxResultDto> getIntervaloPremiosProdutor() {
		return new ResponseEntity<>(movieService.findIntervaloPremiosProdutor(), HttpStatus.OK);
	}
}