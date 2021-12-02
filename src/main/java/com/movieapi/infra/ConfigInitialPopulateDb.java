package com.movieapi.infra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.movieapi.data.model.Movie;
import com.movieapi.data.model.Producer;
import com.movieapi.repositories.MovieRepository;
import com.movieapi.repositories.ProducerRepository;

@Component
public class ConfigInitialPopulateDb implements ApplicationListener<ApplicationReadyEvent> {
	
	private static final String REGEX = "(,|\\s* and \\s*)";
	private static final Logger LOG = LoggerFactory.getLogger(ConfigInitialPopulateDb .class);
	
	private final MovieRepository movieRepository;
	private final ProducerRepository producerRepository;
	
	public ConfigInitialPopulateDb(MovieRepository movieRepository, ProducerRepository producerRepository) {
		this.movieRepository = movieRepository;
		this.producerRepository = producerRepository;
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		LOG.info("Inicializando carga do banco de dados...");
		createDataBaseInitial();
		LOG.info("Base de dados populada!");
	}
	
	private void createDataBaseInitial() {
		Set<Producer> producerSet = new HashSet<>();
		List<Movie> movies = movieRepository.findAll();
		if(!movies.isEmpty()) {
			List<Producer> producers = populateProducer(movies);
			if(!producers.isEmpty()) {
				for(Movie movie : movies) {
					producerSet.clear();
					for(Producer producer : producers) {
						if(movie.getProducers().contains(producer.getNameProducer())) {
							producerSet.add(producer);
						}
					}
					movie.setMovieProducer(producerSet);
					movieRepository.save(movie);
				}
			}
		}
	}
	
	private List<Producer> populateProducer(List<Movie> movies) {
		LOG.info("Populando tabela Producer...");
		List<Producer> producerLst = new ArrayList<>();
		for(Movie movie : movies) {
			String[] producerArray = movie.getProducers().split(REGEX);
			for(String producer : producerArray) {
				if(producer.length() > 1) {
					Producer producerObj = new Producer(producer.stripLeading());
					producerLst.add(producerObj);	
				}
			}
		}
		producerLst = producerLst.stream().distinct().collect(Collectors.toList());
		producerLst.sort((p1,p2) -> p1.getNameProducer().compareTo(p2.getNameProducer()));
		return producerRepository.saveAllAndFlush(producerLst);
	}
}