package model;

import java.awt.*;

public class GameModel {
    private final int WIDTH = 10;
    private final int HEIGHT = 20;
    private int[][] field;
    private GodTetromino currentFigure;
    private GodTetromino nextFigure;
    private int score = 0;
    private boolean paused = false;

    public GameModel() {
        field = new int[HEIGHT][WIDTH];
        makeNextFigure();
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void makeNextFigure() {
        currentFigure = nextFigure != null ? nextFigure : new GodTetromino();
        nextFigure = new GodTetromino();
        currentFigure.getPosition().x = WIDTH / 2 - currentFigure.getBlock()[0].length / 2;
        currentFigure.getPosition().y = 0;
    }

    public GodTetromino getCurrentFigure() { return currentFigure; }
    public GodTetromino getNextFigure() { return nextFigure; }

    public int getWIDTH() { return WIDTH; }
    public int getHEIGHT() { return HEIGHT; }
    public int[][] getField() { return field; }
    public int getScore() { return score; }

    public boolean canMove(int shiftX, int shiftY) {
        int[][] block = currentFigure.getBlock();
        Point pos = currentFigure.getPosition();

        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                if (block[y][x] != 0) {
                    int newX = pos.x + x + shiftX;
                    int newY = pos.y + y + shiftY;

                    if (newX < 0 || newX >= WIDTH || newY < 0 || newY >= HEIGHT)
                        return false;

                    if (newY < HEIGHT && field[newY][newX] != 0)
                        return false;
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
    }

    public void clearLines() {
        int linesCleared = 0;
        for (int y = HEIGHT - 1; y >= 0; y--) {
            boolean fullLine = true;
            for (int x = 0; x < WIDTH; x++) {
                if (field[y][x] == 0) {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                linesCleared++;
                for (int i = y; i > 0; i--) {
                    field[i] = field[i - 1].clone();
                }
                field[0] = new int[WIDTH];
                y++;
            }
        }

        if (linesCleared > 0) {
            addScore(linesCleared);
        }
    }

    private void addScore(int linesCleared) {
        switch (linesCleared) {
            case 1 -> score += 100;
            case 2 -> score += 300;
            case 3 -> score += 500;
            case 4 -> score += 800;
        }
    }

    public boolean isGameOver() {
        for (int x = 0; x < 4; x++) {
            if (field[0][3 + x] != 0) {
                return true;
            }
        }
        return false;
    }
}