package com.movieapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movieapi.data.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Query(value = "SELECT p.name_producer as produtor, MIN(CAST(m.year AS INT)) as menor, "
			+ "MAX(CAST(m.year AS INT)) as ultimo, "
			+ "(MAX(CAST(m.year AS INT)) - MIN(CAST(m.year AS INT))) as intervalo "
			+ "FROM movie m "
			+ "inner JOIN movie_producer  mp ON m.ID = mp.movie_id "
			+ "inner JOIN producer p ON mp.producer_id = p.id "
			+ "where lower(m.winner) like '%yes%' "
			+ "group by p.name_producer "
			+ "having count (p.name_producer) > 1 "
			+ "order by intervalo", 
			nativeQuery = true)
	List<Object[]> findIntervalMinMaxWinners();
}