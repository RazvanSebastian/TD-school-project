package pojo;

import java.util.Date;

/**
 * Created by Parautiu on 19.05.2017.
 */

public class TicketScheduleSpectacle {
    private int idTicket;
    private int price;
    private Long spectacleDate;
    private String spectacleName;
    private String spectcleDescription;
    private int seatNumber;

    public TicketScheduleSpectacle(int idTicket, int price, Long spectacleDate, String spectacleName, String spectcleDescription, int seatNumber) {
        this.idTicket = idTicket;
        this.price = price;
        this.spectacleDate = spectacleDate;
        this.spectacleName = spectacleName;
        this.spectcleDescription = spectcleDescription;
        this.seatNumber = seatNumber;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
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

    @Override
    public String toString() {
        return  "\n\n\n\n\n\n\n"+
                "Spectacle name :'" + spectacleName + '\n' +
                ""+'\n'+
                "Seat number : " + seatNumber+'\n'+
                ""+'\n'+
                "Price : "+price+'\n'+
                ""+'\n'+
                "Date : " + new Date(spectacleDate).toString() +'\n'+
                ""+'\n'+
                "Storyline : " + spectcleDescription;

    }
}
