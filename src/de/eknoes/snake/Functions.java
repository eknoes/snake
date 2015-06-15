package de.eknoes.snake;

import java.awt.*;

/**
 * Created by soenke on 13.06.15.
 */
public class Functions {

    public static boolean pointIn(Point point, Point[] list) {
        for (int i = 0; i < list.length; i++) {
            if(list[i] != null) {
                if(point.x == list[i].x && point.y == list[i].y) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String directionToString(Direction d) {
        switch (d) {
            case TOP:
                return "Top";
            case BOTTOM:
                return "Bottom";
            case LEFT:
                return "Left";
            case RIGHT:
                return "Right";
        }
        return "Invalid Direction";
    }

    public static boolean pointIn(Point point, Point single) {
        if(point.x == single.x && point.y == single.y) {
            return true;
        }
        return false;
    }
}
