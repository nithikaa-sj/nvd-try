package com.example.NVD.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CveObject {
	private String id;
    private String sourceIdentifier;
    private String published;
    private String lastModified;
    private List<CveDescription> descriptions;
    
    
    private Metrics metrics;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceIdentifier() {
		return sourceIdentifier;
	}
	public void setSourceIdentifier(String sourceIdentifier) {
		this.sourceIdentifier = sourceIdentifier;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public List<CveDescription> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<CveDescription> descriptions) {
		this.descriptions = descriptions;
	}
	public Metrics getMetrics() {
		return metrics;
	}
	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}
    
    
}
