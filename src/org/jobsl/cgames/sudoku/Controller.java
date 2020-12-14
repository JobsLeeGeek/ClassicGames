package org.jobsl.cgames.sudoku;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jobsl.cgames.game2048.Constants;

/**
 * @author JobsLee
 */
public class Controller {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Canvas mainCanvas;

    int xSide = 9;
    int ySide = 9;
    int[][] blocks = new int[xSide][ySide];

    public void init() {
        int count = 1;
        for (int i = 0; i < xSide; i++) {
            for (int j = 0; j < ySide; j++) {
                blocks[i][j] = count++;
                if (count > 9) {
                    count = 1;
                }
            }
        }
        drawGrid(mainCanvas.getGraphicsContext2D());
    }

    private void drawGrid(GraphicsContext gc) {
        gc.setFill(Constants.emptyColor);
        gc.setLineWidth(3);
        gc.strokeRect(0, 0, 495, 495);
        for (int i = 1; i < xSide; i++) {
            gc.setLineWidth(1);
            if (i % 3 == 0) {
                gc.setLineWidth(3);
            }
            gc.strokeLine(55 * i, 0, 55 * i, 495);
        }
        for (int i = 1; i < ySide; i++) {
            gc.setLineWidth(1);
            if (i % 3 == 0) {
                gc.setLineWidth(3);
            }
            gc.strokeLine(0, 55 * i, 495, 55 * i);
        }
        for (int i = 0; i < ySide; i++) {
            for (int j = 0; j < xSide; j++) {
                gc.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
                gc.fillText(String.valueOf(blocks[i][j]), j * 55 + 17 + 5.25, (i + 1) * 55 - 17);
            }
        }
    }
}
