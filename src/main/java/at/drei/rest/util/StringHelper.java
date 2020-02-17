package at.drei.rest.util;

import java.awt.*;

public class StringHelper {

    /**
     * @param sPoint
     * @return
     */
    public static Point getPoint(String sPoint) {
        String[] tokens = sPoint.split(",");
        Point point = new Point();
        point.setLocation(Double.valueOf(tokens[0].trim()), Double.valueOf(tokens[1].trim()));
        return point;
    }
}
