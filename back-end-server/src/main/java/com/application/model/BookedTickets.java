package com.application.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="booked_tickets")
public class BookedTickets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTransaction;
	
	@NotNull
	@Column(name="seat_number")
	private int seatNumber;

	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="spectacleScheduler")
	private SpectacleSchedule spectacleScheduler;
	
	public Long getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SpectacleSchedule getSpectacleScheduler() {
		return spectacleScheduler;
	}

	public void setSpectacleScheduler(SpectacleSchedule spectacleScheduler) {
		this.spectacleScheduler = spectacleScheduler;
	}
	
	
	
}
