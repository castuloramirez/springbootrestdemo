package at.drei.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Locations {

    private List<Location> locationList;

    public List<Location> getLocationList() {
        if (locationList == null) {
            locationList = new ArrayList<>();
        }
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
}