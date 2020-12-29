package org.jobsl.cgames.sudoku;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jobsl.cgames.sudoku.base.Cell;
import org.jobsl.cgames.sudoku.base.Generator;
import org.jobsl.cgames.sudoku.base.Grid;
import org.jobsl.cgames.sudoku.base.Solver;

import java.util.regex.Pattern;

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
    private Solver solver = null;
    private Grid grid = null;

    private CellButton chooseBtn = null;
    private static Pattern pattern = Pattern.compile("[0-9]");

    public void init() {
        solver = new Solver();
        Generator generator = new Generator(solver);
        grid = generator.generate(Constants.Level.LOW.empty);
        drawGrid(mainCanvas.getGraphicsContext2D());
    }

    @FXML
    public void keyPressed(KeyEvent event) {
        // 没有选中的格子
        if (chooseBtn == null) {
            return;
        }
        // 不可改变
        if (!chooseBtn.isFlexible()) {
            return;
        }
        // 输入的内容
        String keyText = event.getText();
        // 内容校验
        if (pattern.matcher(keyText).find()) {
            // 背景色还原
            BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#E6E6FA"), null, null);
            Background background = new Background(backgroundFill);
            chooseBtn.setBackground(background);
            if ("0".equals(keyText)) {
                // 0清空格子
                chooseBtn.setText("");
                return;
            }
            chooseBtn.setText(keyText);

            // 规则判断
            int keyValue = Integer.valueOf(keyText);
            if (solver.isValidValueForCell(chooseBtn.getCell(), keyValue)) {
                // 合规
                // 判断是否还有下一个空格
                if (!grid.getNextEmptyCellOf(chooseBtn.getCell()).isPresent()) {
                    // win胜利了
                    // todo
                    System.out.println("You Win!");
                    return;
                }
            } else {
                // 不合规
                // 方块标红
                backgroundFill = new BackgroundFill(Paint.valueOf("#FF0000"), null, null);
                background = new Background(backgroundFill);
                chooseBtn.setBackground(background);
            }
            // 填入值
            chooseBtn.getCell().setValue(keyValue);
        }
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
                Cell cell = grid.getGrid()[i][j];
                boolean isEmpty = cell.isEmpty();
                // 绘制可填数按钮
                CellButton cellButton = new CellButton();
                // set基础要素
                cellButton.setCell(cell);
                cellButton.setCbX(i);
                cellButton.setCbY(j);
                cellButton.setFlexible(isEmpty);
                cellButton.setFont(Font.font("SansSerif", isEmpty ? FontWeight.BOLD : FontWeight.LIGHT, 21));
                cellButton.setText(isEmpty ? "" : String.valueOf(cell.getValue()));
                // 确定坐标及样式
                cellButton.setPrefSize(50, 50);
                cellButton.setMinSize(50, 50);
                cellButton.setMaxSize(50, 50);
                cellButton.setLayoutX(100 + j * 55 + 2.5);
                cellButton.setLayoutY(50 + i * 55 + 2.5);
                BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf(isEmpty ? "#E6E6FA" : "#FAF8EF"), null, null);
                Background background = new Background(backgroundFill);
                cellButton.setBackground(background);
                // 添加点击事件
                // 放入本地变量 方便填数操作
                cellButton.setOnAction(event -> {
                    if (chooseBtn != null) {
                        BackgroundFill lastChooseBackgroundFill = new BackgroundFill(Paint.valueOf(isEmpty ? "#E6E6FA" : "#FAF8EF"), null, null);
                        Background lastChooseBackground = new Background(lastChooseBackgroundFill);
                        chooseBtn.setBackground(lastChooseBackground);
                    }
                    BackgroundFill chooseBackgroundFill = new BackgroundFill(Paint.valueOf("#90EE90"), null, null);
                    Background chooseBackground = new Background(chooseBackgroundFill);
                    cellButton.setBackground(chooseBackground);
                    chooseBtn = cellButton;
                });
                mainPane.getChildren().add(cellButton);
            }
        }
    }
}
