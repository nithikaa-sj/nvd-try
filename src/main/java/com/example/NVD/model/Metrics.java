package com.example.NVD.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metrics {
	private List<CvssMetricV2> cvssMetricV2;
    private List<CvssMetricV3> cvssMetricV3;
    
    
	public List<CvssMetricV2> getCvssMetricV2() {
		return cvssMetricV2;
	}
	public void setCvssMetricV2(List<CvssMetricV2> cvssMetricV2) {
		this.cvssMetricV2 = cvssMetricV2;
	}
	public List<CvssMetricV3> getCvssMetricV3() {
		return cvssMetricV3;
	}
	public void setCvssMetricV3(List<CvssMetricV3> cvssMetricV3) {
		this.cvssMetricV3 = cvssMetricV3;
	}
    
}
