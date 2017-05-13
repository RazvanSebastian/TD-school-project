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

@Entity
@Table(name = "booked_tickets")
public class BookedTickets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idTransaction;

	@NotNull
	@Column(name = "seat_number")
	private int seatNumber;

	@ManyToOne
	@JoinColumn(name = "userField")
	private User userField;

	@ManyToOne
	@JoinColumn(name = "spectacleScheduler")
	private SpectacleSchedule spectacleScheduler;

	public BookedTickets() {
		super();
	}

	public BookedTickets(int seatNumber, User userField, SpectacleSchedule spectacleScheduler) {
		super();
		this.seatNumber = seatNumber;
		this.userField = userField;
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



	public SpectacleSchedule getSpectacleScheduler() {
		return spectacleScheduler;
	}

	public void setSpectacleScheduler(SpectacleSchedule spectacleScheduler) {
		this.spectacleScheduler = spectacleScheduler;
	}

	public User getUserField() {
		return userField;
	}

	public void setUserField(User userField) {
		this.userField = userField;
	}

}
