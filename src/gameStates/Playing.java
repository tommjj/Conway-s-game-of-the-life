package gameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import main.Game;
import theGameOfTheLife.CellsManager;
import utiliz.Constants.CellConstants;
import utiliz.Constants.WindowConstants;

public class Playing extends State implements Statemethods {

    private CellsManager cellsManager;

    private float offsetX = 0;
    private float offsetY = 0;
    private float scale = 1.0f;

    private boolean left = false, right = false, up = false, down = false;

    private boolean updateCheck = true;

    public Playing(Game game) {
        super(game);
        cellsManager = new CellsManager(1280 * 2, 768 * 2);
        cellsManager.setRandom();
    }

    private void zoomIn() {
        float value = scale > 1 ? scale / 10 : 0.1f;
        changeScale(value);
    }

    private void zoomOut() {
        float value = scale > 1 ? scale / 10 : 0.1f;
        changeScale(-value);
    }

    @Override
    public void update() {
        if (!left && right) {
            changeOffsetX((int) (10));
        }
        if (left && !right) {
            changeOffsetX((int) (-10));
        }
        if (!up && down) {
            changeOffsetY((int) (10));
        }
        if (up && !down) {
            changeOffsetY((int) (-10));
        }
        if (updateCheck) {
            cellsManager.update();
        }
    }

    public void changeScale(float value) {
        if ((scale + value) <= 0.1f) {
            scale = 0.1f;
            return;
        }
        if ((scale + value) > 16) {
            scale = 16;
            return;
        }
        scale += value;
    }

    public void updateOffset() {
        changeOffsetX(0);
        changeOffsetY(0);
    }

    public void changeOffsetX(float value) {
        if (offsetX + value < 0) {
            offsetX = 0;
            return;
        }
        if (offsetX + value >= (cellsManager.getWidth() * CellConstants.CELL_SIZE) - ((WindowConstants.WIDTH_SIZE / (CellConstants.CELL_SIZE * scale)) * CellConstants.CELL_SIZE)) {
            offsetX = (int) ((cellsManager.getWidth() * CellConstants.CELL_SIZE) - ((WindowConstants.WIDTH_SIZE / (CellConstants.CELL_SIZE * scale)) * CellConstants.CELL_SIZE));
            return;
        }
        offsetX += value;
    }

    public void changeOffsetY(float value) {
        if (offsetY + value < 0) {
            offsetY = 0;
            return;
        }
        if (offsetY + value >= (cellsManager.getHeight() * CellConstants.CELL_SIZE) - ((WindowConstants.HEIGHT_SIZE / (CellConstants.CELL_SIZE * scale)) * CellConstants.CELL_SIZE)) {
            offsetY = (int) ((cellsManager.getHeight() * CellConstants.CELL_SIZE) - ((WindowConstants.HEIGHT_SIZE / (CellConstants.CELL_SIZE * scale)) * CellConstants.CELL_SIZE));
            return;
        }
        offsetY += value;
    }

    @Override
    public void draw(Graphics g) {
        cellsManager.draw(g, (int) offsetX, (int) offsetY, scale);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        float x = e.getX() / (CellConstants.CELL_SIZE * scale) + offsetX / CellConstants.CELL_SIZE;

        float y = e.getY() / (CellConstants.CELL_SIZE * scale) + offsetY / CellConstants.CELL_SIZE;
        System.out.println(x + "  " + y);

        cellsManager.setCell((int) x, (int) y, cellsManager.isAlive((int) x, (int) y) != 1);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_SPACE:
                updateCheck = !updateCheck;
                break;
            default:

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            default:

        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() > 0) {
            float sx = (float) e.getX() / WindowConstants.WIDTH_SIZE;
            float sy = (float) e.getY() / WindowConstants.HEIGHT_SIZE;

            float lastScale = scale;
            zoomOut();

            changeOffsetX(((((WindowConstants.WIDTH_SIZE / (CellConstants.CELL_SIZE * lastScale)) * CellConstants.CELL_SIZE)) - (((WindowConstants.WIDTH_SIZE / (CellConstants.CELL_SIZE * scale)) * CellConstants.CELL_SIZE))) * sx);
            changeOffsetY(((((WindowConstants.HEIGHT_SIZE / (CellConstants.CELL_SIZE * lastScale)) * CellConstants.CELL_SIZE)) - (((WindowConstants.HEIGHT_SIZE / (CellConstants.CELL_SIZE * scale)) * CellConstants.CELL_SIZE))) * sy);
        } else {
            float sx = (float) e.getX() / WindowConstants.WIDTH_SIZE;
            float sy = (float) e.getY() / WindowConstants.HEIGHT_SIZE;

            float lastScale = scale;
            zoomIn();

            changeOffsetX(((((WindowConstants.WIDTH_SIZE / (CellConstants.CELL_SIZE * lastScale)) * CellConstants.CELL_SIZE)) - (((WindowConstants.WIDTH_SIZE / (CellConstants.CELL_SIZE * scale)) * CellConstants.CELL_SIZE))) * sx);
            changeOffsetY(((((WindowConstants.HEIGHT_SIZE / (CellConstants.CELL_SIZE * lastScale)) * CellConstants.CELL_SIZE)) - (((WindowConstants.HEIGHT_SIZE / (CellConstants.CELL_SIZE * scale)) * CellConstants.CELL_SIZE))) * sy);
        }

    }

    public boolean isUpdateCheck() {
        return updateCheck;
    }

    public void setUpdateCheck(boolean updateCheck) {
        this.updateCheck = updateCheck;
    }

    public void resetBool() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
