package de.eknoes.snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Created by soenke on 13.06.15.
 */



public class Snake {
    private int length;
    private Direction dir;
    private LinkedList<Point> corpse = new LinkedList<>();
    private Color color;
    private int[] keys;
    private Point start;
    private String name;

    //Just for KI
    Snake(int startX, int startY, Color color) {
        this(startX, startY, color, null);
    }

    Snake(int startX, int startY, Color color, int[] keys) {
        length = 3; //Tail length
        dir = Direction.RIGHT;
        start = new Point(startX, startY);
        corpse.add(new Point(startX, startY));
        corpse.add(new Point(startX - 1, startY));
        corpse.add(new Point(startX - 2, startY));
        this.color = color;
        this.keys = keys;

    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public LinkedList<Point> getCorpse() {
        return corpse;
    }

    public void reset() {
        length = 3;
        corpse.clear();
        dir = Direction.RIGHT;
        corpse.add(new Point(start.x, start.y));
        corpse.add(new Point(start.x - 1, start.y));
        corpse.add(new Point(start.x - 2, start.y));

    }

    public Color getColor() {
        return color;
    }

    public Point[] getPositions() {
        return corpse.toArray(new Point[corpse.size()]);
    }

    public Point[] getClosePositions() {
        LinkedList<Point> result;
        Point next;
        result = new LinkedList<>(corpse);

        next = result.getFirst();

        for (int i = 0; i < 3; i++) {
            switch (dir) {
                case TOP:
                    next = new Point(next.x, next.y - 1);
                    break;
                case BOTTOM:
                    next = new Point(next.x, next.y + 1);
                    break;
                case LEFT:
                    next = new Point(next.x - 1, next.y);
                    break;
                case RIGHT:
                    next = new Point(next.x + 1, next.y);
                    break;

            }
            result.addFirst(next);

        }

        return result.toArray(new Point[result.size()]);



    }

    /**
     * Moves the snake 1 iteration
     * @return new position of the head
     */
    public Point move() {
        Point current;
        Point next;
        current = corpse.getFirst();
        next = current;
        switch (dir) {
            case TOP:
                next = new Point(current.x, current.y - 1);
                break;
            case BOTTOM:
                next = new Point(current.x, current.y + 1);
                break;
            case LEFT:
                next = new Point(current.x - 1, current.y);
                break;
            case RIGHT:
                next = new Point(current.x + 1, current.y);
                break;
        }
        corpse.addFirst(next);
        if(length + 1 == corpse.size()) {
            corpse.removeLast();
        }

        return next;
    }

    public Point move(Direction newDir) {
        dir = newDir;
        return this.move();
    }

    public void eat() {
        length++;
    }

    public int getLength() {
        return length;
    }

    public int getKey(int keycode) {
        switch (keycode) {
            case KeyEvent.VK_W:
                return keys[0];
            case KeyEvent.VK_A:
                return keys[1];
            case KeyEvent.VK_S:
                return keys[2];
            case KeyEvent.VK_D:
                return keys[3];
            default:
                return 0;

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
