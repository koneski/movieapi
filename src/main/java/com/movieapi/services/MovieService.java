package com.movieapi.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.movieapi.data.model.Movie;
import com.movieapi.repositories.MovieRepository;

@Component
public class MovieService {

	private final MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public List<Movie> findAll(){
		return movieRepository.findAll();
	}
}