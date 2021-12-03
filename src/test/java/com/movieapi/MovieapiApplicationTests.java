package com.movieapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.movieapi.controllers.MovieController;
import com.movieapi.data.dto.MimMaxResultDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieapiApplicationTests {

	@LocalServerPort
	private int port;

	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	MovieController controller;

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	void testStatusGetIntervaloPremiosProdutor() throws Exception {
		ResponseEntity<MimMaxResultDto> response = restTemplate
				.getForEntity(createURLWithPort("/v1/movies/intervalo-premios"), MimMaxResultDto.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getMin()).isNotEmpty();
	}

	@Test
	void testContainsStringBodyMin() {
		assertThat(this.restTemplate.getForObject(createURLWithPort("/v1/movies/intervalo-premios"),
				String.class)).contains("Joel Silver");
	}

	@Test
	void testContainsStringBodyMax() {
		assertThat(this.restTemplate.getForObject(createURLWithPort("/v1/movies/intervalo-premios"),
				String.class)).contains("Matthew Vaughn");
	}

	@Test
	void testRetrieveTagJson() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/v1/movies/intervalo-premios"),
				HttpMethod.GET, entity, String.class);
		String expected = "{\n"
				+ "    \"min\": [\n"
				+ "        {\n"
				+ "            \"producer\": \"Joel Silver\",\n"
				+ "            \"interval\": 1990,\n"
				+ "            \"previousWin\": 1991,\n"
				+ "            \"followingWin\": 1\n"
				+ "        }\n"
				+ "    ],\n"
				+ "    \"max\": [\n"
				+ "        {\n"
				+ "            \"producer\": \"Matthew Vaughn\",\n"
				+ "            \"interval\": 2002,\n"
				+ "            \"previousWin\": 2015,\n"
				+ "            \"followingWin\": 13\n"
				+ "        }\n"
				+ "    ]\n"
				+ "}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}