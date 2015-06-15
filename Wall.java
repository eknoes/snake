package de.eknoes.snake;

import java.awt.*;
import java.util.Random;
import static de.eknoes.snake.Functions.*;
/**
 * Created by soenke on 13.06.15.
 */
public class Wall {
    private Point[] pos;
    private Random rand;
    private int maxSize;
    private int size;
    private int maxX;
    private int maxY;


    Wall(Point[] notIn, int size, int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        rand = new Random();
        pos = new Point[size];
        maxSize = size;
        size = 0;
        createStructure(notIn);
    }

    private void createStructure(Point[] notIn) {
        if(size < maxSize) {
            Point neighbour = new Point(rand.nextInt(maxX-maxSize), rand.nextInt(maxY-maxSize));
            if (pointIn(neighbour, notIn)) {
                createStructure(notIn);
            }
            pos[0] = neighbour;
            size = 1;
            addNeighbour(notIn);
        }
    }

    private void addNeighbour(Point[] notIn) {
        Point next;
        if(size < maxSize) {
            if(rand.nextBoolean()) {
                next = new Point(pos[size-1].x + 1, pos[size-1].y);
            } else {
                next = new Point(pos[size-1].x, pos[size-1].y + 1);
            }
            if(!pointIn(next, notIn)) {
                pos[size] = next;
                size++;
                addNeighbour(notIn);
            } else {
                addNeighbour(notIn);
            }
        }
    }

    public Point[] getPositions() {
        return pos;
    }

}
