package at.drei.rest.controller;

import at.drei.rest.dao.LocationDAO;
import at.drei.rest.model.Location;
import at.drei.rest.model.Locations;
import at.drei.rest.model.SearchLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/locations")
public class LocationController {
    @Autowired
    private LocationDAO locationDAO;

    @GetMapping(path = "/", produces = "application/json")
    public Locations getLocations() {
        return locationDAO.getAllLocations();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Location addLocation(
            @RequestBody Location location)
            throws Exception {
        //Generate resource id
        Integer id = locationDAO.getAllLocations().getLocationList().size() + 1;
        location.setId(id);

        //add resource
        locationDAO.addLocation(location);
        //Send location in response
        return location;
    }

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    public Locations getLocationsByParameters(@RequestBody SearchLocation searchLocation) {
        System.out.println("+++++++++++++++++" + searchLocation.toString());
        return locationDAO.getLocationsByCriterias(searchLocation);
    }
}