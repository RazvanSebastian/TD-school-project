package pojo;

import java.util.Date;

/**
 * Created by AmbroB on 5/16/2017.
 */

public class SpectacleSchedule {
    private Long idSpectacleSchedule;
    private String spectacleDate;
    private int seatsNumber;
    private int price;

    public Long getIdSpectacleSchedule() {
        return idSpectacleSchedule;
    }

    public void setIdSpectacleSchedule(Long idSpectacleSchedule) {
        this.idSpectacleSchedule = idSpectacleSchedule;
    }

    public String getSpectacleDate() {
        return spectacleDate;
    }

    public void setSpectacleDate(String spectacleDate) {
        this.spectacleDate = spectacleDate;
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


    public String toString() {
        return idSpectacleSchedule+", "+spectacleDate+", "+ seatsNumber+", "+price;
    }
}
