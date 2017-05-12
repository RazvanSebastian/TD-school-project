package com.application.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "spectacle")
public class Spectacle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idSpectacle;

	@NotNull
	@Column(name = "spectacle_name")
	private String name;

	@NotNull
	@Column(name = "spectacle_description")
	private String description;

	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "spectacle")
	 private Set<SpectacleSchedule> spectacleSchedule;

	public Spectacle() {
		super();
	}

	public Spectacle(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdSpectacle() {
		return idSpectacle;
	}

	public void setIdSpectacle(Long idSpectacle) {
		this.idSpectacle = idSpectacle;
	}

}
