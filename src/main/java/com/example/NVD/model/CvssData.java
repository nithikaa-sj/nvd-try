package com.example.NVD.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CvssData {
    private Double baseScore;

	public Double getBaseScore() {
		return baseScore;
	}

	public void setBaseScore(Double baseScore) {
		this.baseScore = baseScore;
	}
    
}