package com.movieapi.data.dto;

import java.util.List;

public class MimMaxResultDto {

	private List<ProducerRangeDto> min;
	private List<ProducerRangeDto> max;

	public MimMaxResultDto() {
		
	}
	
	public MimMaxResultDto(List<ProducerRangeDto> min, List<ProducerRangeDto> max) {
		this.min = min;
		this.max = max;
	}
	
	public List<ProducerRangeDto> getMin() {
		return min;
	}
	public void setMin(List<ProducerRangeDto> min) {
		this.min = min;
	}
	
	public List<ProducerRangeDto> getMax() {
		return max;
	}
	public void setMax(List<ProducerRangeDto> max) {
		this.max = max;
	}
}