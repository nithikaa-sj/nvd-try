package com.example.NVD.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CvssMetricV2 {
    private CvssData cvssData;

	public CvssData getCvssData() {
		return cvssData;
	}

	public void setCvssData(CvssData cvssData) {
		this.cvssData = cvssData;
	}
    
}