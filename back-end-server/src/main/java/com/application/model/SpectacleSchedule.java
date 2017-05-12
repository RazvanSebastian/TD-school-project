package com.application.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "spectacle_schedule")
public class SpectacleSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idSpectacleSchedule;

	@NotNull
	@Column(name = "spectacle_date")
	private Date spectacleDate;

	@NotNull
	@Column(name = "number_of_seats")
	private int seatsNumber;

	@NotNull
	@Column(name = "price")
	private int price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="spectacle")
	private Spectacle spectacle ;
	
	@OneToMany(mappedBy="spectacleScheduler",fetch=FetchType.LAZY)
	private Set<BookedTickets> spectacleTickets;

	
	public SpectacleSchedule(Date spectacleDate, int seatsNumber, int price) {
		super();
		this.spectacleDate = spectacleDate;
		this.seatsNumber = seatsNumber;
		this.price = price;
	}

	public SpectacleSchedule() {
		super();
	}

	public int getSeatsNumber() {
		return seatsNumber;
	}

	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Long getIdSpectacleSchedule() {
		return idSpectacleSchedule;
	}

	public void setIdSpectacleSchedule(Long idSpectacleSchedule) {
		this.idSpectacleSchedule = idSpectacleSchedule;
	}

	public Date getSpectacleDate() {
		return spectacleDate;
	}

	public void setSpectacleDate(Date spectacleDate) {
		this.spectacleDate = spectacleDate;
	}

	public Spectacle getSpectacle() {
		return spectacle;
	}

	public void setSpectacle(Spectacle spectacle) {
		this.spectacle = spectacle;
	}

	public Set<BookedTickets> getSpectacleTickets() {
		return spectacleTickets;
	}

	public void setSpectacleTickets(Set<BookedTickets> spectacleTickets) {
		this.spectacleTickets = spectacleTickets;
	}

	



	

}
