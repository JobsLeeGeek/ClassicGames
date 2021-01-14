package org.jobsl.cgames.sudoku.base;

import java.util.*;

/**
 * Github:https://github.com/a11n/sudoku
 * 解法器
 */
public class Solver {
    /**
     * 空格子数值
     */
    private static final int EMPTY = 0;

    /**
     * 1~9的随机数组
     */
    private final int[] values;

    /**
     * 构造方法 初始化随机数组
     */
    public Solver() {
        this.values = generateRandomValues();
    }

    /**
     * 数独求解
     *
     * @param grid
     */
    public void solve(Grid grid) {
        boolean solvable = solve(grid, grid.getFirstEmptyCell());
        if (!solvable) {
            throw new IllegalStateException("The provided grid is not solvable.");
        }
    }

    /**
     * 求解方法
     *
     * @param grid
     * @param cell
     * @return
     */
    private boolean solve(Grid grid, Optional<Cell> cell) {
        // 空格子 说明遍历处理完了
        if (!cell.isPresent()) {
            return true;
        }
        // 遍历随机数值 尝试填数
        for (int value : values) {
            // 校验填的数是否合理 合理的话尝试下一个空格子
            if (isValidValueForCell(cell.get(), value)) {
                cell.get().setValue(value);
                // 递归尝试下一个空格子
                if (solve(grid, grid.getNextEmptyCellOf(cell.get()))) return true;
                // 尝试失败格子的填入0 继续为当前格子尝试下一个随机值
                cell.get().setValue(EMPTY);
            }
        }
        return false;
    }

    /**
     * 获取随机数组
     *
     * @return
     */
    private int[] generateRandomValues() {
        // 初始化随机数组 此处空格子0是因为格子初始化的时候 默认给的就是0 便于判断和处理
        int[] values = {EMPTY, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();
        // 使用交换法构建随机数组
        for (int i = 0, j = random.nextInt(9), tmp = values[j];
             i < values.length;
             i++, j = random.nextInt(9), tmp = values[j]) {
            if (i == j) continue;
            values[j] = values[i];
            values[i] = tmp;
        }
        return values;
    }

    /**
     * 判断格子填入的数字是否合适
     *
     * @param cell
     * @param value
     * @return
     */
    public boolean isValidValueForCell(Cell cell, int value) {
        return isValidInRow(cell, value) && isValidInColumn(cell, value) && isValidInBox(cell, value);
    }

    /**
     * 判断数独行数字是否合规
     *
     * @param cell
     * @param value
     * @return
     */
    private boolean isValidInRow(Cell cell, int value) {
        return !getRowValuesOf(cell).contains(value);
    }

    /**
     * 判断数独列数字是否合规
     *
     * @param cell
     * @param value
     * @return
     */
    private boolean isValidInColumn(Cell cell, int value) {
        return !getColumnValuesOf(cell).contains(value);
    }

    /**
     * 判断数独子宫格数字是否合规
     *
     * @param cell
     * @param value
     * @return
     */
    private boolean isValidInBox(Cell cell, int value) {
        return !getBoxValuesOf(cell).contains(value);
    }

    /**
     * 获取行格子数值列表
     *
     * @param cell
     * @return
     */
    private Collection<Integer> getRowValuesOf(Cell cell) {
        List<Integer> rowValues = new ArrayList<>();
        for (Cell neighbor : cell.getRowNeighbors()) rowValues.add(neighbor.getValue());
        return rowValues;
    }

    /**
     * 获取列格子数值列表
     *
     * @param cell
     * @return
     */
    private Collection<Integer> getColumnValuesOf(Cell cell) {
        List<Integer> columnValues = new ArrayList<>();
        for (Cell neighbor : cell.getColumnNeighbors()) columnValues.add(neighbor.getValue());
        return columnValues;
    }

    /**
     * 获取子宫格数值列表
     *
     * @param cell
     * @return
     */
    private Collection<Integer> getBoxValuesOf(Cell cell) {
        List<Integer> boxValues = new ArrayList<>();
        for (Cell neighbor : cell.getBoxNeighbors()) boxValues.add(neighbor.getValue());
        return boxValues;
    }
}