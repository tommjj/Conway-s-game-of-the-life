package theGameOfTheLife;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import utiliz.Constants.CellConstants;
import utiliz.Constants.WindowConstants;

public class CellsManager {

    private boolean cells[][];
    private int width, height;

    private int tick = 0, speed = 15;

    public CellsManager(int width, int height) {
        cells = new boolean[width][height];
        this.width = width;
        this.height = height;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = false;
            }
        }
    }
    
    public void create(int width, int hieght) {
        this.width = width;
        this.height = hieght;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = false;
            }
        }
    }
    
    public void setRandom() {
        Random rd = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = rd.nextBoolean();
            }
        }
    }
    
    public void resetAll() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = false;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    
    public int isAlive(int x, int y) {
        if (x < 0 || x >= width) {
            return 0;
        }
        if (y < 0 || y >= height) {
            return 0;
        }

        return cells[x][y] ? 1 : 0;
    }

    /*
    1. Ô đang sống mà có ít hơn hai hàng xóm đang sống sẽ chết (quá ít dân)
    2. Ô đang sống mà có nhiều hơn 3 hàng xóm đang sống sẽ chết (quá đông dân)
    3. Ô đang sống mà có 2 hoặc 3 hàng xóm đang sống sẽ tiếp tục sống (tồn tại)
    4. Ô chết mà có đúng 3 hàng xóm đang sống sẽ chuyển thành ô sống (sinh sản)   
     */
    public boolean updateCell(int x, int y) {
        int sum = 0;
        sum += isAlive(x - 1, y - 1);
        sum += isAlive(x + 1, y + 1);

        sum += isAlive(x - 1, y);
        sum += isAlive(x + 1, y);

        sum += isAlive(x, y - 1);
        sum += isAlive(x, y + 1);

        sum += isAlive(x + 1, y - 1);
        sum += isAlive(x - 1, y + 1);

        if (sum < 2 && isAlive(x, y) == 1) {
            return false;
        }
        if (sum > 3 && isAlive(x, y) == 1) {
            return false;
        }
        if (sum == 3 && isAlive(x, y) == 0) {
            return true;
        }
        return isAlive(x, y) == 1;
    }

    public void setCell(int x, int y, boolean alive) {
        if (x < 0 || x >= width) {
            return ;
        }
        if (y < 0 || y >= height) {
            return ;
        }

       cells[x][y] = alive;
    }

    public void updateAllCells() {
        boolean[][] temp = new boolean[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                temp[i][j] = updateCell(i, j);
            }
        }

        cells = temp;
    }

    public void update() {
        tick++;
        if (tick >= speed) {
            updateAllCells();
            tick = 0;
        }
    }

    public void draw(Graphics g, int offSetX, int offSetY, float scale) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowConstants.WIDTH_SIZE, WindowConstants.HEIGHT_SIZE);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cells[i][j]) {
                    g.setColor(Color.WHITE);
                    int x = (int) ((i * (scale * CellConstants.CELL_SIZE)) - (offSetX * scale));
                    int y = (int) ((j * (scale * CellConstants.CELL_SIZE)) - (offSetY * scale));
                    g.fillRect(x, y, (int) (scale * CellConstants.CELL_SIZE), (int) (scale * CellConstants.CELL_SIZE));
                } else {
                    
                }
            }
        }
    }
}
