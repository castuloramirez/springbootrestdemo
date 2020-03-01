package at.drei.rest.dao;


import at.drei.rest.model.Location;
import at.drei.rest.model.Locations;
import at.drei.rest.model.SearchLocation;
import at.drei.rest.util.StringHelper;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static at.drei.rest.util.Type.premium;
import static at.drei.rest.util.Type.standard;


@Repository
public class LocationDAO {

    private static Locations list = new Locations();

    /**
     * Initial data
     */
    static {
        list.getLocationList().add(new Location(1, "Bratislava", "48.14", "17.10", standard.name()));
        list.getLocationList().add(new Location(2, "Fancy Place", "48.2", "15.6", premium.name()));
        list.getLocationList().add(new Location(3, "Berlin", "52.52", "13.40", standard.name()));
    }

    /**
     * By Point
     *
     * @param p1
     * @param p2
     * @return
     */
    private static List<Location> getLocationByPoint(String p1, String p2) {
        Point point1 = StringHelper.getPoint(p1);
        Point point2 = StringHelper.getPoint(p2);
        Rectangle rect = new Rectangle(point1);
        rect.add(point2);

        List<Location> listOrder = list.getLocationList();
        List<Location> newListOrder = new ArrayList<Location>();
        for (Location location : listOrder) {
            String p3 = location.getLat() + "," + location.getLng();
            Point point3 = StringHelper.getPoint(p3);
            boolean bln = rect.contains(point3);
            if (bln) {
                newListOrder.add(new Location(location.getId(), location.getName(), location.getLat(), location.getLng(), location.getType()));
            }
        }
        return newListOrder;
    }

    /**
     * Full Search, First the Premium ones.
     * Fixed the Bug.
     *
     * @return
     */
    public Locations getAllLocations() {
        List<Location> listOrder = list.getLocationList();
        listOrder.sort(Comparator.comparing(Location::getType));
        //   List<Location> limit  = listOrder.subList(0,2);
        //  list.setLocationList(limit);
        list.setLocationList(listOrder);
        return list;
    }

    /**
     * Adding a Location
     *
     * @param location
     */
    public void addLocation(Location location) {
        list.getLocationList().add(location);
    }

    /**
     * Get locations by different Criterias.
     *
     * @param sl
     * @return
     */
    public Locations getLocationsByCriterias(SearchLocation sl) {
        Locations search = new Locations();
        //Searching
        if (sl.getP1() != null && sl.getP2() != null) {//By Point
            List<Location> result = getLocationByPoint(sl.getP1(), sl.getP2());
            search.setLocationList(result);

            if (sl.getType() == null) {//Just by Points
                search.getLocationList().sort(Comparator.comparing(Location::getType));
                return search;
            }

            search = getListByType(sl, search.getLocationList()); //Points and Type
            if (sl.getLimit() != null) {//Points and Type and Limit
                int ilimit = new Integer(sl.getLimit());
                List<Location> limit = search.getLocationList().subList(0, ilimit);
                search.setLocationList(limit);
                return search;
            }

            search.getLocationList().sort(Comparator.comparing(Location::getType));
            return search;
        } else if (sl.getType() != null) {   //By Type
            return getListByType(sl, list.getLocationList());
        }
        search.getLocationList().sort(Comparator.comparing(Location::getType));
        return search;
    }

    /**
     * Search the locations by Type : premium or standard
     *
     * @param sl
     * @return
     */
    private Locations getListByType(SearchLocation sl, List<Location> listOrder) {
        Locations search = new Locations();
        List<Location> result = listOrder.stream()
                .filter(a -> Objects.equals(a.getType(), sl.getType()))
                .collect(Collectors.toList());
        search.setLocationList(result);
        return search;
    }
}