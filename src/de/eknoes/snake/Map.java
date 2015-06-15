package de.eknoes.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;


/**
 * Created by soenke on 12.06.15.
 */
public class Map extends JPanel {

    private int sizeX;
    private int sizeY;
    private BufferedImage buffImg;
    private BufferedImage screenImg;
    private HashMap<Point, Color> output;
    private HashMap<Point, Color> staticOutput;

    Map() {
        this(30, 30);
    }

    Map(int x, int y) {
        super();
        sizeX = x;
        sizeY = y;
        this.setBackground(new Color(255, 255, 255));
        this.staticOutput = new HashMap<>();
        this.output = new HashMap<>();
        this.screenImg = new BufferedImage(sizeX * 15, sizeY * 15, BufferedImage.TYPE_INT_ARGB);
    }

    public void setOutput(HashMap<Point, Color> output) {
        this.output = output;
    }

    private void initBufferImg() {
        this.buffImg = new BufferedImage(sizeX * 15, sizeY * 15, BufferedImage.TYPE_INT_ARGB);
        Graphics imgPainter = buffImg.getGraphics();

        imgPainter.setColor(new Color(255, 255, 255, 255));
        imgPainter.fillRect(0, 0, getWidth(), getHeight());
        imgPainter.setColor(new Color(0, 0, 0, 255));

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if(staticOutput.containsKey(new Point(i, j))) {
                    imgPainter.setColor(staticOutput.get(new Point(i, j)));
                    imgPainter.fillRect(i * 15 + 1, j * 15 + 1, 10, 10);
                    imgPainter.setColor(new Color(0, 0, 0, 255));
                } else {
                    imgPainter.drawRect(i * 15 + 1, j * 15 + 1, 10, 10);
                }

            }
        }
    }

    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics screenImgPainter = screenImg.getGraphics();
        screenImgPainter.setColor(new Color(255, 255, 255, 255));
        screenImgPainter.fillRect(0, 0, getWidth(), getHeight());
        screenImgPainter.drawImage(buffImg, 0, 0, this);

        HashMap<Point, Color> tempIt = new HashMap<>(output);

        for (java.util.Map.Entry<Point, Color> entry : tempIt.entrySet()) {
            screenImgPainter.setColor(entry.getValue());
            screenImgPainter.fillRect(entry.getKey().x * 15 + 1, entry.getKey().y * 15 + 1, 10, 10);
        }
        screenImgPainter.setColor(Color.BLACK);


        graphic.drawImage(screenImg, 0, 0, this);
    }

    public void setStaticOutput(HashMap<Point, Color> staticOutput) {
        this.staticOutput = staticOutput;
        initBufferImg();
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
}
