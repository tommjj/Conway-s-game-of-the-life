package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener, MouseWheelListener{
    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.isControlDown()) {
            if(e.getWheelRotation() > 0) {
                System.out.println("Up");
            } else {
                System.out.println("Donw");
            }
        } 
    } 
}
