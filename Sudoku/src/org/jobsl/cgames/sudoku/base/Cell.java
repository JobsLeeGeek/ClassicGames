package org.jobsl.cgames.sudoku.base;

import java.util.Collection;

/**
 * From Github:https://github.com/a11n/sudoku
 * 数独小格子对象
 */
public class Cell {
    // 格子数值
    private int value;
    // 行其他格子列表
    private Collection<Cell> rowNeighbors;
    // 列其他格子列表
    private Collection<Cell> columnNeighbors;
    // 子宫格其他格子列表
    private Collection<Cell> boxNeighbors;
    // 下一个格子对象
    private Cell nextCell;

    public Cell(int value) {
        this.value = value;
    }

    /**
     * Returns the value of the Cell. <br><br> The value is a digit (1, ..., 9) or 0 if the Cell is
     * empty.
     *
     * @return the value of the Cell.
     */
    public int getValue() {
        return value;
    }

    /**
     * Indicates whether the Cell is empty or not.
     *
     * @return true if the Cell is empty, false otherwise
     */
    public boolean isEmpty() {
        return value == 0;
    }

    /**
     * Allows to change the value of the Cell.
     *
     * @param value the new value of the Cell
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns a {@link Collection} of all other Cells in the same row than this Cell.
     *
     * @return a {@link Collection} of row neighbors
     */
    public Collection<Cell> getRowNeighbors() {
        return rowNeighbors;
    }

    /**
     * Allows to set a {@link Collection} of Cells, which are interpreted to be in the same row.
     *
     * @param rowNeighbors a {@link Collection} of row neighbors
     */
    public void setRowNeighbors(Collection<Cell> rowNeighbors) {
        this.rowNeighbors = rowNeighbors;
    }

    /**
     * Returns a {@link Collection} of all other Cells in the same column than this Cell.
     *
     * @return a {@link Collection} of column neighbors
     */
    public Collection<Cell> getColumnNeighbors() {
        return columnNeighbors;
    }

    /**
     * Allows to set a {@link Collection} of Cells, which are interpreted to be in the same column.
     *
     * @param columnNeighbors a {@link Collection} of column neighbors
     */
    public void setColumnNeighbors(Collection<Cell> columnNeighbors) {
        this.columnNeighbors = columnNeighbors;
    }

    /**
     * Returns a {@link Collection} of all other Cells in the same box than this Cell.
     *
     * @return a {@link Collection} of box neighbors
     */
    public Collection<Cell> getBoxNeighbors() {
        return boxNeighbors;
    }

    /**
     * Allows to set a {@link Collection} of Cells, which are interpreted to be in the same box.
     *
     * @param boxNeighbors a {@link Collection} of box neighbors
     */
    public void setBoxNeighbors(Collection<Cell> boxNeighbors) {
        this.boxNeighbors = boxNeighbors;
    }

    /**
     * Returns the next Cell consecutive to this Cell. <br><br> This function returns the Cell to
     * the right of each Cell if the Cell is not the last Cell in a row. It returns the first Cell
     * of the next row of each Cell if the Cell is the last Cell in a row. For the very last Cell in
     * the very last row this function returns null.
     *
     * @return the next Cell consecutive to this Cell or null if it is the last Cell.
     */
    public Cell getNextCell() {
        return nextCell;
    }

    /**
     * Allows to set a Cell which is interpreted to be the next Cell consecutive to this Cell.
     *
     * @param nextCell the next Cell consecutive to this Cell.
     */
    public void setNextCell(Cell nextCell) {
        this.nextCell = nextCell;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value=" + value +
                '}';
    }
}
