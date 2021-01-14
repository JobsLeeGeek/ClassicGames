package org.jobsl.cgames.game2048;

import com.sun.javafx.tk.FontMetrics;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Random;

import static org.jobsl.cgames.game2048.Constants.State;

/**
 * @author JobsLee
 */
public class Controller {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Canvas mainCanvas;

    private static State gameState = State.start;
    private Random rand = new Random();
    private Tile[][] tiles;
    private int side = 4;
    private static final int target = 2048;
    private static int highest = 0;
    private static int score = 0;
    private boolean checkingAvailableMoves;

    /**
     * init start canvas
     */
    public void init() {
        drawGrid(mainCanvas.getGraphicsContext2D());
    }

    @FXML
    private void mouseClick() {
        if (gameState != State.running) {
            gameState = State.running;
            tiles = new Tile[side][side];
            addRandomTile();
            addRandomTile();
            drawGrid(mainCanvas.getGraphicsContext2D());
        }
    }

    @FXML
    private void keyPress(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            default:
                break;
        }
        drawGrid(mainCanvas.getGraphicsContext2D());
    }

    // draw

    private void addRandomTile() {
        int pos = rand.nextInt(side * side);
        int row, col;
        do {
            pos = (pos + 1) % (side * side);
            row = pos / side;
            col = pos % side;
        } while (tiles[row][col] != null);

        int val = rand.nextInt(10) == 0 ? 4 : 2;
        tiles[row][col] = new Tile(val);
    }

    private void drawGrid(GraphicsContext gc) {
        gc.setFill(Constants.gridColor);
        gc.fillRoundRect(0, 0, 499, 499, 15, 15);

        switch (gameState) {
            case running:
                for (int r = 0; r < side; r++) {
                    for (int c = 0; c < side; c++) {
                        if (tiles[r][c] == null) {
                            gc.setFill(Constants.emptyColor);
                            gc.fillRoundRect(15 + c * 121, 15 + r * 121, 106, 106, 7, 7);
                        } else {
                            drawTile(gc, r, c);
                        }
                    }
                }
                break;
            case start:
            case won:
            case over:
                gc.setFill(Constants.startColor);
                gc.fillRoundRect(15, 15, 469, 469, 7, 7);
                gc.setFill(Constants.gridColor.darker());
                gc.setFont(Font.font("SansSerif", FontWeight.BOLD, 128));
                gc.fillText("2048", 110, 170);
                gc.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
                if (gameState == State.won) {
                    gc.fillText("you win!", 190, 250);
                } else if (gameState == State.over) {
                    gc.fillText("game over", 200, 250);
                }
                gc.setFill(Constants.gridColor);
                gc.fillText("click to start a new game", 130, 370);
                gc.fillText("(use arrow keys to move tiles)", 110, 430);
                break;
            default:
                break;
        }
    }

    private void drawTile(GraphicsContext gc, int r, int c) {
        int value = tiles[r][c].getValue();
        gc.setFill(Constants.colorTable[(int) (Math.log(value) / Math.log(2)) + 1]);
        gc.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
        gc.fillRoundRect(15 + c * 121, 15 + r * 121, 106, 106, 7, 7);
        String s = String.valueOf(value);
        gc.setFill(value < 128 ? Constants.colorTable[0] : Constants.colorTable[1]);
        FontMetrics fm = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(gc.getFont());
        int asc = (int) fm.getAscent();
        int dec = (int) fm.getDescent();
        int x = (int) (15 + c * 121 + (106 - fm.computeStringWidth(s)) / 2);
        int y = 15 + r * 121 + (asc + (106 - (asc + dec)) / 2);
        gc.fillText(s, x, y);
    }

    // move

    private boolean moveLeft() {
        return move(0, 0, -1);
    }

    private boolean moveRight() {
        return move(side * side - 1, 0, 1);
    }

    private boolean moveUp() {
        return move(0, -1, 0);
    }

    private boolean moveDown() {
        return move(side * side - 1, 1, 0);
    }

    private boolean move(int countDownFrom, int yIncr, int xIncr) {
        boolean moved = false;

        for (int i = 0; i < side * side; i++) {
            int j = Math.abs(countDownFrom - i);
            int r = j / side;
            int c = j % side;
            if (tiles[r][c] == null) {
                continue;
            }
            int nextR = r + yIncr;
            int nextC = c + xIncr;
            while (nextR >= 0 && nextR < side && nextC >= 0 && nextC < side) {
                printTile();
                Tile next = tiles[nextR][nextC];
                Tile curr = tiles[r][c];
                if (next == null) {
                    if (checkingAvailableMoves) {
                        return true;
                    }
                    tiles[nextR][nextC] = curr;
                    tiles[r][c] = null;
                    r = nextR;
                    c = nextC;
                    nextR += yIncr;
                    nextC += xIncr;
                    moved = true;
                } else if (next.canMergeWith(curr)) {
                    if (checkingAvailableMoves) {
                        return true;
                    }
                    int value = next.mergeWith(curr);
                    if (value > highest) {
                        highest = value;
                    }
                    score += value;
                    tiles[r][c] = null;
                    moved = true;
                    break;
                } else {
                    break;
                }
            }
        }

        if (moved) {
            if (highest < target) {
                clearMerged();
                addRandomTile();
                if (!movesAvailable()) {
                    gameState = State.over;
                }
            } else if (highest == target) {
                gameState = State.won;
            }
        }

        return moved;
    }

    private void clearMerged() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile != null) {
                    tile.setMerged(false);
                }
            }
        }
    }

    private boolean movesAvailable() {
        checkingAvailableMoves = true;
        boolean hasMoves = moveUp() || moveDown() || moveLeft() || moveRight();
        checkingAvailableMoves = false;
        return hasMoves;
    }


    // print(move-test)

    private void printTile() {
        /*for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                System.out.print("|" + tiles[i] == null ? "" : tiles[i][j] == null ? "" : tiles[i][j].getValue() + "|");
            }
            System.out.println();
        }
        System.out.println("-----");*/
    }
}
