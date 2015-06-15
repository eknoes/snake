package de.eknoes.snake;

import java.awt.*;
import java.util.HashMap;

import static de.eknoes.snake.Functions.pointIn;

/**
 * Created by soenke on 13.06.15.
 */
public class Game implements Runnable {
    private Players players;
    private GameWindow window;
    private Food food;
    private Structures structures;
    private Thread thread;
    private HashMap<Point, Color> toDraw;
    private HashMap<Point, Color> staticToDraw;
    private boolean isStopped = false;


    Game(GameWindow window, Players players) {
        this.players = players;
        this.window = window;

        this.structures = new Structures(players.getClosePositions(), window.getSizeX(), window.getSizeY());
        this.food = new Food(players.getPositions(), structures.getPositions(), window.sizeX, window.sizeY);


        toDraw = new HashMap<>();
        staticToDraw = new HashMap<>();
        for (Point point : structures.getPositions()) {
            staticToDraw.put(point, Color.BLACK);
        }

        this.players.spawnKI(this.structures, food, window.getSizeX(), window.getSizeY());
        this.players.spawnKI(this.structures, food, window.getSizeX(), window.getSizeY());
        this.players.spawnKI(this.structures, food, window.getSizeX(), window.getSizeY());
        this.players.spawnKI(this.structures, food, window.getSizeX(), window.getSizeY());
        this.players.spawnKI(this.structures, food, window.getSizeX(), window.getSizeY());

        this.thread = new Thread(this);
        this.thread.start();



    }



    public void run() {
        Direction newDir;
        Point[] newPoint;
        Point[][] oldPositions;
        boolean collision;
        Listener keys = window.getMain();
        int i = 0;
        long time;
        i = 0;
        newPoint = new Point[players.getMaxNumber()];
        oldPositions = new Point[players.getMaxNumber()][];
        boolean gameLost = false;
        toDraw = new HashMap<>();

        while(!gameLost && !isStopped) {
            for (int j = 0; j < players.getNumber(); j++) {
                oldPositions[j] = players.getPositionsOf(j);

                //Key Input + Change Position
                newDir = keys.getLastDir(j);
                if (newDir != null) {
                    newPoint[j] = players.move(j, newDir);
                } else {
                    newPoint[j] = players.move(j);
                }

                //Food
                if (newPoint[j].x == food.getPos().x && newPoint[j].y == food.getPos().y) {
                    food.eat(players.getPositions(), structures.getPositions());
                    players.eat(j);
                }
            }

            for (int j = 0; j < players.getNumber(); j++) {
                //Collision Check
                collision = false;
                if(pointIn(newPoint[j], structures.getPositions())) {
                    collision = true;
                }

                if(pointIn(newPoint[j], oldPositions[j])) {
                    collision = true;
                }

                if(j > 0 && pointIn(newPoint[j], players.getPositionsOf(j-1))) {
                    collision = true;
                }
                if(j > 1 && pointIn(newPoint[j], players.getPositionsOf(j-2))) {
                    collision = true;
                }

                if (newPoint[j].x > window.getSizeX() || newPoint[j].x < 0 || newPoint[j].y < 0 || newPoint[j].y > window.getSizeY()) {
                    collision = true;
                }

                if (collision) {
                    System.out.println("Die, Player #" + j);
                    players.die(j);
                }

            }
            players.cleanDead();
            if(players.getNumber() == 0) {
                window.lostGame();
                gameLost = true;
            }

            toDraw.clear();
            for (int j = 0; j < players.getNumber(); j++) {
                for(Point point : players.getPositionsOf(j)) {
                    toDraw.put(point, players.getColor(j));
                }
            }
            toDraw.put(food.getPos(), Color.RED);




            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<Point, Color> getToDraw() {
        return toDraw;
    }

    public void stop() {
        isStopped = true;
    }

    public HashMap<Point, Color> getStaticToDraw() {
        return staticToDraw;
    }
}
