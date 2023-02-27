package main;

import java.awt.Graphics;

public class Game implements Runnable{
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    
    private Thread gameThread;
    private final int FPS_SET = 1, UPS_SET = 1;
    
    public Game() {
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);

        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startGameLoop();
    }
    
    public void update() {
        
    }

    public void rander(Graphics g) {

    }
    
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFreme = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;
        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFreme;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
