package tech.nicecraftz.common.location;

import java.util.ArrayList;
import java.util.List;

public record BoundingBox(Point min, Point max) {

    /*
        returns the entire area of the current BoundingBox
     */
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();

        for (int x = min.x(); x <= max.x(); x++) {
            for (int y = min.y(); y <= max.y(); y++) {
                for (int z = min.z(); z <= max.z(); z++) {
                    points.add(new Point(x, y, z));
                }
            }
        }

        return points;
    }

    /*
        checks if a point is inside or not the BoundingBox
     */
    public boolean containsPoint(Point point) {
        return point.x() >= min.x() && point.x() <= max.x()
                && point.y() >= min.y() && point.y() <= max.y()
                && point.z() >= min.z() && point.z() <= max.z();
    }
}
