
//Bill Senyamwuzuza
/**
 * This class represents a Meteorite object.
 * It contains fields for the meteorite's
 * name, id, nametype, recclass,
 * mass, fall, year, reclat,
 * reclong, and geolocation.
 * 
 */
import java.io.Serializable;

public class Meteorite implements Serializable {
    private String name;
    private String id;
    private String nametype;
    private String recclass;
    private double mass;
    private String fall;
    private String year;
    private double reclat;
    private double reclong;
    private Geolocation geolocation;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getNametype() {
        return nametype;
    }

    public String getRecclass() {
        return recclass;
    }

    public double getMass() {
        return mass;
    }

    public String getFall() {
        return fall;
    }

    public String getYear() {
        if (year == null) {
            return "0";
        } else if (year.length() >= 4) {
            return year.substring(0, 4);
        }
        return year;
    }

    public double getReclat() {
        return reclat;
    }

    public double getReclong() {
        return reclong;
    }

    @Override
    /**
     * To String method
     * 
     * @return string
     */
    public String toString() {

        return "[name=" + getName() + ", id=" + getId() + ", nametype=" + getNametype() + ", recclass=" + getRecclass()
                + ", mass=" + getMass() + ", fall=" + getFall() + ", year=" + getYear() + ", reclat=" + getReclat()
                + ", reclong=" + getReclong() + ", geolocation=" + (geolocation != null ? geolocation.toString()
                        : "N/A")
                + "]";
    }

    /**
     * This method displays name, id,
     * recclass, mass, and year
     */
    public String display() {
        return "name: " + getName() +
                ", id: " + getId() +
                ", recclass: " + getRecclass() +
                ", mass: " + getMass() +
                ", year: " + getYear();
    }

}
