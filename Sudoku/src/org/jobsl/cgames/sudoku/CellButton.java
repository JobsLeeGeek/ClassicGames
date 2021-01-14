package org.jobsl.cgames.sudoku;

import javafx.scene.control.Button;
import org.jobsl.cgames.sudoku.base.Cell;

/**
 * @author JobsLee
 */
public class CellButton extends Button {
    /**
     * 数独格子数值对象
     */
    private Cell cell;

    /**
     * 空按钮数独x坐标 0~8
     */
    private int cbX;

    /**
     * 空按钮数独y坐标 0~8
     */
    private int cbY;

    /**
     * 是否可更改
     */
    private boolean isFlexible;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getCbX() {
        return cbX;
    }

    public void setCbX(int cbX) {
        this.cbX = cbX;
    }

    public int getCbY() {
        return cbY;
    }

    public void setCbY(int cbY) {
        this.cbY = cbY;
    }

    public boolean isFlexible() {
        return isFlexible;
    }

    public void setFlexible(boolean flexible) {
        isFlexible = flexible;
    }
}
