package com.movieapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieapi.data.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}