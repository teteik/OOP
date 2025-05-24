package model;

import java.awt.*;

public class GameModel {
    private final int WIDTH = 10;
    private final int HEIGHT = 20;
    private int[][] field;
    private GodTetromino currentFigure;

    public GameModel() {
        field = new int[HEIGHT][WIDTH];
        makeFigure();
    }

    public void makeFigure() {
        currentFigure = new GodTetromino();
    }

    public int getWIDTH() { return WIDTH; }

    public int getHEIGHT() { return HEIGHT; }

    public int[][] getField() { return field; }

    public GodTetromino getCurrentFigure() { return currentFigure; }

    public boolean canMove(int shiftX, int shiftY) {
        for (int y = 0; y < currentFigure.getBlock().length; y++) {
            for (int x = 0; x < currentFigure.getBlock()[0].length; x++) {
                if (currentFigure.getBlock()[y][x] != 0) {
                    int newX = currentFigure.getPosition().x + x + shiftX;
                    int newY = currentFigure.getPosition().y + y + shiftY;
                    if (newX < 0 || newX >= WIDTH || newY < 0 || newY >= HEIGHT) return false;
                    if (newY < HEIGHT && newX < WIDTH && field[newY][newX] != 0) return false;
                }
            }
        }
        return true;
    }

    public void placePiece() {
        int[][] block = currentFigure.getBlock();
        Point pos = currentFigure.getPosition();
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                if (block[y][x] != 0) {
                    field[pos.y + y][pos.x + x] = 1;
                }
            }
        }
        clearLines();
        makeFigure();
    }

    public void clearLines() {
        for (int y = HEIGHT - 1; y >= 0; y--) {
            boolean fullLine = true;
            for (int x = 0; x < WIDTH; x++) {
                if (field[y][x] == 0) {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                for (int i = y; i > 0; i--) {
                    field[i] = field[i - 1].clone();
                }
                field[0] = new int[WIDTH];
                y++;
            }
        }
    }

    public boolean isGameOver() {
        for (int x = 0; x < 4; x++) {
            if (field[0][3 + x] != 0) return true;
        }
        return false;
    }
}