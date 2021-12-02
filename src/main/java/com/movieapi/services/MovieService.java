package com.movieapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.movieapi.data.dto.MimMaxResultDto;
import com.movieapi.data.dto.ProducerRangeDto;
import com.movieapi.repositories.MovieRepository;

@Component
public class MovieService {

	private final MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public MimMaxResultDto findIntervaloPremiosProdutor() {
		MimMaxResultDto mimMaxResultDto = new MimMaxResultDto();
		
		List<ProducerRangeDto> producerRangeDtoLstMin = new ArrayList<>();
		List<ProducerRangeDto> producerRangeDtoLstMax = new ArrayList<>();
		
		List<Object[]> objLst = movieRepository.findIntervalMinMaxWinners();
		if (objLst != null && !objLst.isEmpty()) {
			List<ProducerRangeDto> producerRangeDtoLst = new ArrayList<>();
			objLst.forEach(v -> producerRangeDtoLst.add(new ProducerRangeDto((String) v[0], (int) v[1], (int) v[2],(int)v[3])));
			for (int i=0; i < producerRangeDtoLst.size(); i++) {
				if(i<2) {
					producerRangeDtoLstMin.add(producerRangeDtoLst.get(i));
				}else {
					producerRangeDtoLstMax.add(producerRangeDtoLst.get(i));
				}
			}
		}
		
		mimMaxResultDto.setMin(producerRangeDtoLstMin);
		mimMaxResultDto.setMax(producerRangeDtoLstMax);
		
		return mimMaxResultDto;
	}
}