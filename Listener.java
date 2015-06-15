package de.eknoes.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by soenke on 13.06.15.
 */
public class Listener implements KeyListener {

    Direction lastDir[];
    Players players;

    Listener(Players players) {
        this.players = players;
        lastDir = new Direction[players.getMaxNumber()];
    }

    public void keyPressed(KeyEvent k) {
        for (int i = 0; i < players.getNumber(); i++) {
            if(k.getKeyCode() == players.getKeyOf(i, KeyEvent.VK_W)) {
                lastDir[i] = Direction.TOP;
            } else if (k.getKeyCode() == players.getKeyOf(i, KeyEvent.VK_A)) {
                lastDir[i] = Direction.LEFT;
            } else if (k.getKeyCode() == players.getKeyOf(i, KeyEvent.VK_S)) {
                lastDir[i] = Direction.BOTTOM;
            } else if (k.getKeyCode() == players.getKeyOf(i, KeyEvent.VK_D)) {
                lastDir[i] = Direction.RIGHT;
            }
        }
        if(k.getKeyCode() == KeyEvent.VK_P) {
            players.spawn();
        }
    }

    public void keyReleased(KeyEvent k) {

    }

    public void keyTyped(KeyEvent k) {

    }

    public Direction getLastDir(int i) {
        Direction result = lastDir[i];
        lastDir[i] = null;
        return result;
    }

}
