package org.jobsl.cgames.sudoku;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.jobsl.cgames.sudoku.Constants.Level;
import org.jobsl.cgames.sudoku.Constants.State;
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
    @FXML
    private Text mainTitle;
    @FXML
    private Text tipText;
    @FXML
    private ChoiceBox<Level> levelChoiceBox;
    @FXML
    private Button startButton;

    private static State gameState = State.start;
    private static int errorCount = 0;

    private int xSide = 9;
    private int ySide = 9;
    private Solver solver = null;
    private Grid grid = null;

    private CellButton chooseBtn = null;
    private static Pattern pattern = Pattern.compile("[0-9]");

    /**
     * 初始化方法
     */
    public void init() {
        // 初始化choiceBox组件
        levelChoiceBox.setItems(FXCollections.observableArrayList(Level.values()));
        levelChoiceBox.setValue(Level.LOW);
        start();
    }

    /**
     * 游戏开始
     */
    private void start() {
        gameState = Constants.State.running;
        tipText.setVisible(false);
        solver = new Solver();
        Generator generator = new Generator(solver);
        grid = generator.generate(levelChoiceBox.getValue().empty);
        drawGrid(mainCanvas.getGraphicsContext2D());
    }

    @FXML
    public void startGame() {
        start();
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
            setBtnBackgroupColor(chooseBtn, Constants.buttonDefaultHex);
            if ("0".equals(keyText)) {
                // 0清空格子
                chooseBtn.setText("");
                return;
            }
            chooseBtn.setText(keyText);

            // 规则判断
            int keyValue = Integer.valueOf(keyText);
            // 数格填入值
            chooseBtn.getCell().setValue(keyValue);
            if (solver.isValidValueForCell(chooseBtn.getCell(), keyValue)) {
                // 合规
                // 判断是否还有下一个空格
                if (!grid.getNextEmptyCellOf(chooseBtn.getCell()).isPresent()) {
                    // win胜利了
                    gameState = State.won;
                    drawGrid(mainCanvas.getGraphicsContext2D());
                    tipText.setText("You Win!");
                    tipText.setVisible(true);
                    return;
                }
            } else {
                // 不合规
                // 方块标红
                setBtnBackgroupColor(chooseBtn, Constants.buttonRedHex);
                // 错误次数+1
                errorCount++;
                if (errorCount >= 3) {
                    // 游戏失败了
                    gameState = State.over;
                    // 自动求解
                    solver.solve(grid);
                    drawGrid(mainCanvas.getGraphicsContext2D());
                    tipText.setText("Game Over");
                    tipText.setVisible(true);
                }
            }
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
                setBtnBackgroupColor(cellButton, isEmpty ? Constants.buttonEmptyHex : Constants.buttonNotEmptyHex);
                // 添加点击事件
                // 放入本地变量 方便填数操作
                cellButton.setOnAction(event -> {
                    if (chooseBtn != null) {
                        setBtnBackgroupColor(chooseBtn, isEmpty ? Constants.buttonEmptyHex : Constants.buttonNotEmptyHex);
                    }
                    setBtnBackgroupColor(cellButton, Constants.buttonChooseHex);
                    chooseBtn = cellButton;
                });
                mainPane.getChildren().add(cellButton);
            }
        }
    }

    /**
     * 按钮背景色处理
     *
     * @param button
     * @param color
     */
    private void setBtnBackgroupColor(Button button, String color) {
        BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf(color), null, null);
        Background background = new Background(backgroundFill);
        button.setBackground(background);
    }
}
