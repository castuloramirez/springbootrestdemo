package at.drei.rest.dao;


import at.drei.rest.model.Location;
import at.drei.rest.model.Locations;
import at.drei.rest.model.SearchLocation;
import at.drei.rest.util.StringHelper;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.*;
import java.util.List;
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
     * Full Search
     *
     * @return
     */
    public Locations getAllLocations() {
        List<Location> listOrder = list.getLocationList();
        listOrder.sort(Comparator.comparing(Location::getId));
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
     * Get locations by different criterias.
     * @param sl
     * @return
     */
    public Locations getLocationsByCriterias(SearchLocation sl) {
        List<Location> listOrder = list.getLocationList();
        Locations search = new Locations();
        //By Type
        if (sl.getP1() == null || sl.getP2() == null) {
            List<Location> result = listOrder.stream()
                    .filter(a -> Objects.equals(a.getType(), sl.getType()))
                    .collect(Collectors.toList());
            search.setLocationList(result);
            return search;

        } else if (sl.getP1() != null && sl.getP2() != null) {
            List<Location> result = getLocationByPoint(sl.getP1(),sl.getP2());
            search.setLocationList(result);
            if(sl.getType()!=null){
                List<Location> result2 = listOrder.stream()
                        .filter(a -> Objects.equals(a.getType(), sl.getType()))
                        .collect(Collectors.toList());
               // search.getLocationList().addAll(result2);
               // search.getLocationList().sort(Comparator.comparing(Location::getType));

                List<Location> newList = new ArrayList<Location>(result);
                newList.addAll(result2);
                search.setLocationList(newList);

                if(sl.getLimit()!=null){
                    int limit = Integer.valueOf(sl.getLimit());
                    List<Location> listOrderLimit = newList;//search.getLocationList();
                    List<Location> newListOrder = new ArrayList<Location>();
                    int i =0;
                    for (Location location : listOrderLimit) {
                        if(i < limit) {
                            newListOrder.add(new Location(location.getId(), location.getName(), location.getLat(), location.getLng(), location.getType()));
                            i++;
                        }
                    }
                    if(i>0){
                        search.setLocationList(newListOrder);
                        search.getLocationList().sort(Comparator.comparing(Location::getType));
                        return search;
                    }
                }
            }
            search.getLocationList().sort(Comparator.comparing(Location::getType));
            return search;
        }

        listOrder.sort(Comparator.comparing(Location::getType));
        search.setLocationList(listOrder);
        return search;
    }

    /**
     * By Point
     * @param p1
     * @param p2
     * @return
     */
    private static List<Location> getLocationByPoint(String p1, String p2) {
        Point point1 = StringHelper.getPoint(p1);
        Point point2 = StringHelper.getPoint(p2);
        Rectangle rect= new Rectangle(point1);
        rect.add(point2);

        List<Location> listOrder = list.getLocationList();
        List<Location> newListOrder = new ArrayList<Location>();
        for (Location location : listOrder) {
            String p3 = location.getLat() + "," +location.getLng();
            Point point3 = StringHelper.getPoint(p3);
            boolean bln = rect.contains(point3);
            if(bln){
                newListOrder.add(new Location(location.getId(), location.getName(), location.getLat(), location.getLng(), location.getType()));
            }
        }
        return newListOrder;
    }
}