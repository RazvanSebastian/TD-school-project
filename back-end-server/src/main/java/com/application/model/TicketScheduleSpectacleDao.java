package com.application.model;

import java.util.Date;

public class TicketScheduleSpectacleDao {
	
	private Long idTicket;
	private int price;
	private Long spectacleDate;
	private String spectacleName;
	private String spectcleDescription;
	private int seatNumber;
	

	public TicketScheduleSpectacleDao(Long idTicket, int price, Long spectacleDate, String spectacleName,
			String spectcleDescription, int seatNumber) {
		super();
		this.idTicket = idTicket;
		this.price = price;
		this.spectacleDate = spectacleDate;
		this.spectacleName = spectacleName;
		this.spectcleDescription = spectcleDescription;
		this.seatNumber = seatNumber;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Long getSpectacleDate() {
		return spectacleDate;
	}
	public void setSpectacleDate(Long spectacleDate) {
		this.spectacleDate = spectacleDate;
	}
	public String getSpectacleName() {
		return spectacleName;
	}
	public void setSpectacleName(String spectacleName) {
		this.spectacleName = spectacleName;
	}
	public String getSpectcleDescription() {
		return spectcleDescription;
	}
	public void setSpectcleDescription(String spectcleDescription) {
		this.spectcleDescription = spectcleDescription;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public Long getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}
}
