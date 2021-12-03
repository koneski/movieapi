package com.movieapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.movieapi.controllers.MovieController;
import com.movieapi.data.dto.MimMaxResultDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieapiApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private MovieController controller;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	void testStatusGetIntervaloPremiosProdutor() throws Exception {
		ResponseEntity<MimMaxResultDto> response = restTemplate.getForEntity("http://localhost:" + port + "/v1/movies/intervalo-premios", MimMaxResultDto.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getMin()).isNotEmpty();
	}
}