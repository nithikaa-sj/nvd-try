package com.example.NVD.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Data
@Entity
@Table(name="cve")
public class Cve {
	@Id
    private String id;

    private LocalDate publishedDate; // instead of String

    private LocalDate lastModifiedDate; // for filtering modified-since

    private Double baseScore;
    
	@OneToMany(mappedBy = "cve", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CveDescription> descriptions;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}
	public LocalDate getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(LocalDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Double getBaseScore() {
		return baseScore;
	}
	public void setBaseScore(Double baseScore) {
		this.baseScore = baseScore;
	}
	public List<CveDescription> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<CveDescription> descriptions) {
		this.descriptions = descriptions;
	}
	
	
}