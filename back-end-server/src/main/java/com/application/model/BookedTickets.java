package com.application.model;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="booked_tickets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookedTickets implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTransaction;
	
	@NotNull
	@Column(name="seat_number")
	private int seatNumber;

	@ManyToOne
	@JoinColumn(name="user")
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JoinColumn(name="spectacleScheduler")
	@JsonIgnore
	private SpectacleSchedule spectacleScheduler;
	
	
	
	public BookedTickets() {
		super();
	}

	public BookedTickets(int seatNumber, User user, SpectacleSchedule spectacleScheduler) {
		super();
		this.seatNumber = seatNumber;
		this.user = user;
		this.spectacleScheduler = spectacleScheduler;
	}



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
