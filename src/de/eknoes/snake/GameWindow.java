package de.eknoes.snake;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;

/**
 * Created by soenke on 12.06.15.
 */
public class GameWindow extends JFrame {

    Listener main;
    Map map;
    int sizeX;
    int sizeY;

    public GameWindow(Players players, int maxX, int maxY) {
        super("SNAKE");
        sizeX = maxX;
        sizeY = maxY;
        this.setLayout(new GridBagLayout());
        setResizable(false);
        this.setPreferredSize(new Dimension(sizeX * 15 + 10, sizeY * 15 + 10));
        this.map = new Map(sizeX, sizeY);
        main = new Listener(players);
        this.addKeyListener(main);
        map.setPreferredSize(new Dimension(sizeX * 15 + 10, sizeY * 15 + 10));
        GridBagConstraints settings = new GridBagConstraints();
        this.add(map, settings);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public Listener getMain() {
        return main;
    }

    public void lostGame() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("You've lost the Game!");
        panel.setSize(100, 150);
        panel.add(label);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(Color.BLACK, 3, true));
        panel.setVisible(true);
        map.add(panel);
        map.validate();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void updateMap(HashMap<Point, Color> output) {
        map.setOutput(output);
        map.repaint();
    }

    public void setStaticMapPoints(HashMap<Point, Color> staticOutput) {
        map.setStaticOutput(staticOutput);
        map.repaint();
    }

    @Override
    public int getDefaultCloseOperation() {

        return super.getDefaultCloseOperation();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
