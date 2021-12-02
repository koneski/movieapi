package com.movieapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieapi.data.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Integer>{

}
