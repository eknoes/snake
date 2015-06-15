package de.eknoes.snake;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by soenke on 13.06.15.
 */
public class Structures {
    Wall[] walls;
    private Color color;
    private int number;
    int sizeX;
    int sizeY;

    Structures(Point[] notIn, int maxX, int maxY) {
        sizeX = maxX;
        sizeY = maxY;
        number = 20;
        Random rand = new Random();
        color = Color.BLACK;
        walls = new Wall[number];
        for (int i = 0; i < number; i++) {
            walls[i] = new Wall(notIn, rand.nextInt(10), sizeX, sizeY);
        }
    }

    public Point[] getPositions() {
        LinkedList<Point> temp;
        temp = new LinkedList<Point>();
        for (int i = 0; i < number; i++) {
            Point[] currPoints = walls[i].getPositions();
            for (int j = 0; j < currPoints.length; j++) {
                temp.add(currPoints[j]);
            }
        }

        Point[] result = new Point[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }
        return result;
    }

    public Color getColor() {
        return color;
    }

}
