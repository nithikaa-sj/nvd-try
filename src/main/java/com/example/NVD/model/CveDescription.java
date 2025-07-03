package com.example.NVD.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "cve_description")
public class CveDescription {
	@Id
    @GeneratedValue
    private Long id;

	private String lang;
	@Column(length = 5000)
	private String value;


	
	@ManyToOne
    @JoinColumn(name = "cve_id")
    private Cve cve;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cve getCve() {
		return cve;
	}
	public void setCve(Cve cve) {
		this.cve = cve;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
