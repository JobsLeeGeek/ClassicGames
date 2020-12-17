package org.jobsl.cgames.sudoku;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jobsl.cgames.sudoku.base.Generator;
import org.jobsl.cgames.sudoku.base.Grid;

/**
 * @author JobsLee
 */
public class Controller {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Canvas mainCanvas;

    private int xSide = 9;
    private int ySide = 9;
    private Grid.Cell[][] numGrid = null;

    public void init() {
        Generator generator = new Generator();
        Grid grid = generator.generate(Constants.Level.LOW.empty);
        numGrid = grid.getGrid();
        drawGrid(mainCanvas.getGraphicsContext2D());
    }

    @FXML
    private void mouseClick(MouseEvent event) {
        // 获取焦点坐标 canvas内坐标
        double eX = event.getX();
        double eY = event.getY();
        // 计算点击的格子
        int row = (int) (eX / 55);
        int column = (int) (eY / 55);
        // todo
    }

    /**
     * 绘制数独格子 9x9
     *
     * @param gc
     */
    private void drawGrid(GraphicsContext gc) {
        gc.setFill(Constants.emptyColor);
        // 画框
        gc.setLineWidth(3);
        gc.strokeRect(0, 0, 495, 495);
        // 画行
        for (int i = 1; i < xSide; i++) {
            gc.setLineWidth(1);
            if (i % 3 == 0) {
                gc.setLineWidth(3);
            }
            gc.strokeLine(55 * i, 0, 55 * i, 495);
        }
        // 画列
        for (int i = 1; i < ySide; i++) {
            gc.setLineWidth(1);
            if (i % 3 == 0) {
                gc.setLineWidth(3);
            }
            gc.strokeLine(0, 55 * i, 495, 55 * i);
        }
        // 填数
        for (int i = 0; i < ySide; i++) {
            for (int j = 0; j < xSide; j++) {
                gc.setFill(Constants.textColor);
                gc.setFont(Font.font("SansSerif", FontWeight.LIGHT, 21));
                gc.fillText(String.valueOf(numGrid[i][j].isEmpty() ? "" : numGrid[i][j].getValue()), j * 55 + 17 + 5.25, (i + 1) * 55 - 17);
            }
        }
    }
}
