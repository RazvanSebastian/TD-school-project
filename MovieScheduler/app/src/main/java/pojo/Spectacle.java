package pojo;

/**
 * Created by Parautiu on 14.05.2017.
 */

public class Spectacle {

    private Long idSpectacle;

    private String name;

    private String description;

    public Long getIdSpectacle() {
        return idSpectacle;
    }

    public void setIdSpectacle(Long idSpectacle) {
        this.idSpectacle = idSpectacle;
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

    @Override
    public String toString() {
        return "Spectacle "+name+" "+description;
    }
}
