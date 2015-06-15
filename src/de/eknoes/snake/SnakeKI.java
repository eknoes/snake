package de.eknoes.snake;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

import static de.eknoes.snake.Functions.directionToString;
import static de.eknoes.snake.Functions.pointIn;

/**
 * Created by soenke on 14.06.15.
 */
public class SnakeKI extends Snake {
    private Structures structures;
    private Players players;
    private Food food;
    private int maxX;
    private int maxY;

    SnakeKI(Point start, Color color, Players players, Structures structures, Food food, int maxX, int maxY) {
        super(start.x, start.y, color);
        this.maxX = maxX;
        this.maxY = maxY;
        this.structures = structures;
        this.food = food;
        this.players = players;
    }

    public Point[] concatPoints(Point[] a, Point[] b) {
        Point[] result = new Point[a.length + b.length];
        for (int i = 0; i < a.length + b.length - 1; i++) {
            if(i < a.length) {
                result[i] = a[i];
            } else {
                result[i] = b[i - a.length];
            }
        }
        return  result;
    }

    private Direction calcDir(Point wantTo, Point[] dont) {
        LinkedList<Direction> possible = new LinkedList<Direction>();
        Point head = getCorpse().getFirst();
        Direction own = getDir();

        if(own == Direction.TOP) {
            possible.add(Direction.TOP);
            possible.add(Direction.LEFT);
            possible.add(Direction.RIGHT);

        } else if(own == Direction.BOTTOM) {
            possible.add(Direction.BOTTOM);
            possible.add(Direction.LEFT);
            possible.add(Direction.RIGHT);

        } else if(own == Direction.LEFT) {
            possible.add(Direction.TOP);
            possible.add(Direction.LEFT);
            possible.add(Direction.BOTTOM);

        } else if(own == Direction.RIGHT) {
            possible.add(Direction.TOP);
            possible.add(Direction.BOTTOM);
            possible.add(Direction.RIGHT);
        }

        if(head.x == maxX - 1) {
            possible.remove(Direction.RIGHT);
        }
        if(head.x == 0) {
            possible.remove(Direction.LEFT);
        }
        if(head.y == 0) {
            possible.remove(Direction.TOP);
        }
        if(head.y == maxY - 1) {
            possible.remove(Direction.BOTTOM);
        }

        Point next = new Point(0, 0);

        LinkedList<Integer> toRemove = new LinkedList<>();

        for (int i = 0; i < possible.size(); i++) {
            switch (possible.get(i)) {
                case TOP:
                    next = new Point(head.x, head.y-1);
                    break;
                case BOTTOM:
                    next = new Point(head.x, head.y+1);
                    break;
                case LEFT:
                    next = new Point(head.x-1, head.y);
                    break;
                case RIGHT:
                    next = new Point(head.x+1, head.y);
                    break;
            }
            if(pointIn(next, dont)) {
                System.out.println("(" + this.getName() + ") Do not go to " + directionToString(possible.get(i)));
                toRemove.add(i);
            }
        }

        for (int i = 0; i < toRemove.size(); i++) {
            int remIndex = toRemove.get(i);
            possible.remove(remIndex-i);
        }

        if(possible.size() == 0) {
            return Direction.BOTTOM;
        }

        Random rand = new Random();
        Direction favorite = possible.get(rand.nextInt(possible.size()));

        if(head.x < wantTo.x) {
            for (int i = 0; i < possible.size(); i++) {
                if(possible.get(i) == Direction.RIGHT) {
                    favorite = possible.get(i);
                }
            }
        }

        if(head.x > wantTo.x) {
            for (int i = 0; i < possible.size(); i++) {
                if(possible.get(i) == Direction.LEFT) {
                    favorite = possible.get(i);
                }
            }
        }

        if(head.y < wantTo.y) {
            for (int i = 0; i < possible.size(); i++) {
                if(possible.get(i) == Direction.BOTTOM) {
                    favorite = possible.get(i);
                }
            }
        }

        if(head.y > wantTo.y) {
            for (int i = 0; i < possible.size(); i++) {
                if(possible.get(i) == Direction.TOP) {
                    favorite = possible.get(i);
                }
            }
        }

        System.out.println(this.getName() + ": wants to go " + directionToString(favorite));
        return favorite;


    }

    @Override
    public Point move() {
        Point foodPos = food.getPos();
        Point[] doNotEnter = concatPoints(structures.getPositions(), players.getPositions());

        Direction moveTo = calcDir(foodPos, doNotEnter);
        setDir(moveTo);
        return super.move();
    }

    @Override
    public int getKey(int code) {
        return 0;
    }
}
