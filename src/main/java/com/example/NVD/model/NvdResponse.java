package com.example.NVD.model;
import lombok.Data;
import java.util.List;


@Data

public class NvdResponse {
	

	private int resultsPerPage;
	private int startIndex;
	private int totalResults;
	private List<Vulnerability> vulnerabilities;
	public int getResultsPerPage() {
		return resultsPerPage;
	}
	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	public List<Vulnerability> getVulnerabilities() {
		return vulnerabilities;
	}
	public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}

}
