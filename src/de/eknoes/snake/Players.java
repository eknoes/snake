package de.eknoes.snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Created by soenke on 14.06.15.
 */
public class Players {
    private Snake[] snake;
    private int[][] keys;
    private Color[] colors;
    private int number;
    private int maxNumber;
    private LinkedList<Snake> hasToDie;
    private String[] names;

    Players(int number) {
        hasToDie = new LinkedList<Snake>();
        this.number = number;
        this.maxNumber = 5;
        String[] names = {"A", "B", "C", "D", "E"};
        this.names = names;
        int[][] keys = {
                {KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D},
                {KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT},
                {KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6}
        };
        Color[] colors = {Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.DARK_GRAY, Color.ORANGE};
        this.colors = colors;
        this.keys = keys;
        snake = new Snake[maxNumber];

        if(number <= maxNumber) {

            for (int i = 0; i < maxNumber; i++) {
                if(i < 3) {
                    snake[i] = new Snake(i * 8 + 3, i * 8 + 3, colors[i], keys[i]);
                }
            }

            if(this.number > 3) {
                this.number = 3;
            }
        }
    }

    public boolean spawn() {
        if(number < maxNumber) {
            System.out.println("New Player #" + number + " spawned");
            number++;
            return true;
        }
        return false;
    }

    public boolean spawnKI(Structures struct, Food food, int maxX, int maxY) {
        if(number < maxNumber) {
            snake[number] = new SnakeKI(new Point(number*8+3, number*8+3), colors[number], this, struct, food, maxX, maxY);
            snake[number].setName(names[number]);
            number++;
            return true;
        }
        return false;
    }

    public Color getColor(int i) {
        return snake[i].getColor();
    }

    public Point[] getPositionsOf(int i) {
        return snake[i].getPositions();
    }

    public Point[] getPositions() {
        LinkedList<Point> temp = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < snake[i].getPositions().length; j++) {
                temp.add(snake[i].getPositions()[j]);
            }
        }
        return temp.toArray(new Point[temp.size()]);
    }

    public Point[] getClosePositions() {
        LinkedList<Point> temp = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < snake[i].getClosePositions().length; j++) {
                temp.add(snake[i].getClosePositions()[j]);
            }
        }
        return temp.toArray(new Point[temp.size()]);
    }

    public int getNumber() {
        return number;
    }

    public int getKeyOf(int i, int keycode) {
        return snake[i].getKey(keycode);
    }

    public Point move(int i, Direction dir) {
        return snake[i].move(dir);
    }

    public Point move(int i) {
        return snake[i].move();
    }

    public void eat(int j) {
        snake[j].eat();
    }

    public void die(int j) {
        System.out.println("Player " + snake[j].getName() + " died");
        hasToDie.add(snake[j]);
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void cleanDead() {
        if(hasToDie.size() > 0) {
            for (Snake s : hasToDie) {
                s.reset();
                for (int i = 0; i < number - 1; i++) {
                    if (snake[i] == s) {
                        for (int j = i; j < maxNumber - 1; j++) {
                            snake[j] = snake[j + 1];
                        }
                        snake[maxNumber - 1] = s;
                    }
                }
                number--;
            }
            hasToDie.clear();

        }

    }
}
