package pojo;

/**
 * Created by Parautiu on 11.05.2017.
 */

public class ModelTest {

    private long id;
    private String name;

    public ModelTest() {
    }

    public ModelTest(long id,String name) {
        this.name = name;
        this.id=id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
