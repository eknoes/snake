package de.eknoes.snake;

public class Main implements Runnable {


    public static void main(String[] args) {
        Thread t = new Thread(new Main());
        t.run();
    }

    @Override
    public void run() {
        Players players = new Players(1);


        GameWindow window = new GameWindow(players, 40, 40);
        Game logic = new Game(window, players);

        window.setStaticMapPoints(logic.getStaticToDraw());
        window.repaint();
        int fps = 0;
        long start = System.currentTimeMillis();
        while (window.isVisible()) {
            fps++;
            if(System.currentTimeMillis() - start > 1000) {
                System.out.println("FPS: " + fps);
                fps = 0;
                start = System.currentTimeMillis();
            }

            window.updateMap(logic.getToDraw());

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        logic.stop();
    }
}
