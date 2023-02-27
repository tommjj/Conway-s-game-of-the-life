package main;

import inputs.MouseInputs;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    private Game game;
    private MouseInputs mouseInputs;
    
    public GamePanel(Game game) {
        this.game = game;
        
        mouseInputs = new MouseInputs(this);
        
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        addMouseWheelListener(mouseInputs);
        
        setPanelSize();
  
    }
    
    public Game getGame() {
        return game;
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 768);
        setPreferredSize(size);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.rander(g);
    }
}
