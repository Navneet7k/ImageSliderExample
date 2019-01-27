package navneet.com.imagesliderexample;

/**
 * Created by Navneet Krishna on 26/01/19.
 */
public class SlideItem {
    private String carName;
    private String carColour;
    private String carType;

    public SlideItem(String carName, String carColour, String carType) {
        this.carName = carName;
        this.carColour = carColour;
        this.carType = carType;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarColour() {
        return carColour;
    }

    public String getCarType() {
        return carType;
    }
}
