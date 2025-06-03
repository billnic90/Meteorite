
//Bill Nicolas Senyamwuzuza
/**
 * This class represents a Geolocation object with a type and coordinates.
 * 
 */
import java.io.Serializable;
import java.util.Arrays;

public class Geolocation implements Serializable {
    private String type;
    private Double[] coordinates;

    /**
     * getter method for type
     * 
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * getter method for Coordinates
     * 
     * @return coordinates
     */
    public Double[] getCoordinates() {
        return coordinates;
    }

    @Override
    /**
     * toString method
     * 
     * @return string
     */
    public String toString() {

        return "Geolocation [type=" + getType() + ", coordinates=" + Arrays.toString(getCoordinates()) + "]";
    }

}
