package de.eknoes.snake;

import java.awt.*;
import java.util.Random;

import static de.eknoes.snake.Functions.pointIn;

/**
 * Created by soenke on 13.06.15.
 */
public class Food {
    private Point pos;
    private Random rand;
    private Color color;
    private int maxX;
    private int maxY;

    Food(Point[] notIn, Point[] notIn2, int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        rand = new Random();
        color = Color.RED;

        eat(notIn, notIn2);
    }

    public void eat(Point[] notIn, Point[] notIn2) {
        pos = new Point(rand.nextInt(maxX), rand.nextInt(maxY));
        if(pointIn(pos, notIn) || pointIn(pos, notIn2)) {
            eat(notIn, notIn2);
        }
    }

    public Color getColor() {
        return color;
    }

    public Point getPos() {
        return pos;
    }
}
