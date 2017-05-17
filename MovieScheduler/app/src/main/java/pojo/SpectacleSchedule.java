package pojo;

import java.util.Date;

/**
 * Created by AmbroB on 5/16/2017.
 */

public class SpectacleSchedule {
    private Long idSpectacleSchedule;
    private Date spectacleDate;
    private Integer seatsNumber;
    private Integer price;
    private Spectacle spectacle ;
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

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Spectacle getSpectacle() {
        return spectacle;
    }

    public void setSpectacle(Spectacle spectacle) {
        this.spectacle = spectacle;
    }


    public String toString() {
        return idSpectacleSchedule+", "+spectacleDate+", "+ seatsNumber+", "+price+", "+spectacle.toString();
    }
}
