package model;

import java.awt.*;
import java.util.Random;

public class GodTetromino {
    public enum Form {I, O, T, L, J, S, Z};

    private Form form;
    private int[][] blocks;
    private Point position;

    public GodTetromino() {
        this.form = Form.values()[new Random().nextInt(Form.values().length)];
        this.blocks = createFigure(form);
        this.position = new Point(4, 0);
    }

    public Form getForm() {
        return this.form;
    }

    public int[][] createFigure(Form form) {
        switch (form) {
            case I: return new int[][]{
                    {0,0,0,0},
                    {1,1,1,1},
                    {0,0,0,0},
                    {0,0,0,0}
            };
            case O: return new int[][]{
                    {1,1},
                    {1,1}
            };
            case T: return new int[][]{
                    {0,1,0},
                    {1,1,1},
                    {0,0,0}
            };
            case L: return new int[][]{
                    {1,0,0},
                    {1,1,1},
                    {0,0,0}
            };
            case J: return new int[][]{
                    {0,0,1},
                    {1,1,1},
                    {0,0,0}
            };
            case S: return new int[][]{
                    {0,1,1},
                    {1,1,0},
                    {0,0,0}
            };
            case Z: return new int[][]{
                    {1,1,0},
                    {0,1,1},
                    {0,0,0}
            };
            default: return new int[0][];
        }
    }

    private int[][] rotateFigureRight() {
        int size = blocks.length;
        int[][] rotated = new int[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                rotated[x][size - 1 - y] = this.blocks[y][x];
            }
        }
        return rotated;
    }

    public void rotateRight() {

        this.blocks = rotateFigureRight();
    }

    private int[][] rotateFigureLeft() {
        int size = blocks.length;
        int[][] rotated = new int[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                rotated[size - 1 - y][x] = this.blocks[y][x];
            }
        }
        return rotated;
    }

    public void rotateLeft() {
        this.blocks = rotateFigureLeft();
    }

    public void moveDown() {
        position.y++;
    }

    public void moveRight() {
        position.x++;
    }

    public void moveLeft() {
        position.x--;
    }

    public Point getPosition() {
        return this.position;
    }

    public int[][] getBlock() {
        return this.blocks;
    }
}
