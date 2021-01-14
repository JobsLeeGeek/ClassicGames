package org.jobsl.cgames.sudoku.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Github:https://github.com/a11n/sudoku
 * 数独格子 此处对象和数据的构建是精髓
 */
public class Grid {
    /**
     * 由数据格子构成的数独格子
     */
    private final Cell[][] grid;

    /**
     * 私有化构造方法
     *
     * @param grid
     */
    private Grid(Cell[][] grid) {
        this.grid = grid;
    }

    /**
     * 返回数独格子的工厂方法
     *
     * @param grid
     * @return
     */
    public static Grid of(int[][] grid) {
        // 基础校验
        verifyGrid(grid);

        // 初始化格子各维度统计List 9x9 行 列 子宫格
        Cell[][] cells = new Cell[9][9];
        List<List<Cell>> rows = new ArrayList<>();
        List<List<Cell>> columns = new ArrayList<>();
        List<List<Cell>> boxes = new ArrayList<>();
        // 初始化List 9行 9列 9子宫格
        for (int i = 0; i < 9; i++) {
            rows.add(new ArrayList<Cell>());
            columns.add(new ArrayList<Cell>());
            boxes.add(new ArrayList<Cell>());
        }

        Cell lastCell = null;
        // 逐一遍历数独格子 往各维度统计List中填数
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Cell cell = new Cell(grid[row][column]);
                cells[row][column] = cell;

                rows.get(row).add(cell);
                columns.get(column).add(cell);
                // 子宫格在List中的index计算
                boxes.get((row / 3) * 3 + column / 3).add(cell);
                // 如果有上一次遍历的格子 则当前格子为上个格子的下一格子
                if (lastCell != null) {
                    lastCell.setNextCell(cell);
                }
                // 记录上一次遍历的格子
                lastCell = cell;
            }
        }

        // 逐行 逐列 逐子宫格 遍历 处理对应模块的关联邻居List
        for (int i = 0; i < 9; i++) {
            // 逐行
            List<Cell> row = rows.get(i);
            for (Cell cell : row) {
                List<Cell> rowNeighbors = new ArrayList<>(row);
                rowNeighbors.remove(cell);
                cell.setRowNeighbors(rowNeighbors);
            }

            // 逐列
            List<Cell> column = columns.get(i);
            for (Cell cell : column) {
                List<Cell> columnNeighbors = new ArrayList<>(column);
                columnNeighbors.remove(cell);
                cell.setColumnNeighbors(columnNeighbors);
            }

            // 逐子宫格
            List<Cell> box = boxes.get(i);
            for (Cell cell : box) {
                List<Cell> boxNeighbors = new ArrayList<>(box);
                boxNeighbors.remove(cell);
                cell.setBoxNeighbors(boxNeighbors);
            }
        }

        return new Grid(cells);
    }

    /**
     * 空数独格子生成
     *
     * @return
     */
    public static Grid emptyGrid() {
        int[][] emptyGrid = new int[9][9];
        return Grid.of(emptyGrid);
    }

    /**
     * 校验数独格子基本属性
     *
     * @param grid
     */
    private static void verifyGrid(int[][] grid) {
        if (grid == null)
            throw new IllegalArgumentException("grid must not be null");
        if (grid.length != 9)
            throw new IllegalArgumentException("grid must have nine rows");
        for (int[] row : grid) {
            if (row.length != 9) {
                throw new IllegalArgumentException("grid must have nine columns");
            }
            for (int value : row) {
                if (value < 0 || value > 9) {
                    throw new IllegalArgumentException("grid must contain values from 0-9");
                }
            }
        }
    }

    /**
     * Returns the size of this Grid. This method is useful if you want to iterate over all {@link
     * Cell}s. <br><br> To access one cell use {@link #getCell(int, int)}. <br><br> Note: This is the
     * size of one dimension. This Grid contains size x size {@link Cell}s.
     *
     * @return the size of this Grid
     */
    public int getSize() {
        return grid.length;
    }

    /**
     * Returns the {@link Cell} at the given position within the Grid. <br><br> This Grid has 0 to
     * {@link #getSize()} rows and 0 to {@link #getSize()} columns.
     *
     * @param row    the row which contains the {@link Cell}
     * @param column the column which contains the {@link Cell}
     * @return the {@link Cell} at the given position
     */
    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    /**
     * 获取第一个空格子
     *
     * @return
     */
    public Optional<Cell> getFirstEmptyCell() {
        Cell firstCell = grid[0][0];
        if (firstCell.isEmpty()) {
            return Optional.of(firstCell);
        }
        return getNextEmptyCellOf(firstCell);
    }

    /**
     * 获取下一个空格子
     *
     * @param cell
     * @return
     */
    public Optional<Cell> getNextEmptyCellOf(Cell cell) {
        Cell nextEmptyCell = null;

        while ((cell = cell.getNextCell()) != null) {
            if (!cell.isEmpty()) {
                continue;
            }
            nextEmptyCell = cell;
            break;
        }

        return Optional.ofNullable(nextEmptyCell);
    }

    /**
     * 获取数独格子
     *
     * @return
     */
    public Cell[][] getGrid() {
        return this.grid;
    }

    /**
     * 转化打印数独格子
     *
     * @return
     */
    @Override
    public String toString() {
        return StringConverter.toString(this);
    }

    private static class StringConverter {
        public static String toString(Grid grid) {
            StringBuilder builder = new StringBuilder();
            int size = grid.getSize();
            for (int row = 0; row < size; row++) {
                Cell[] rc = grid.grid[row];
                int[] r = new int[9];
                for (int i = 0; i < 9; i++) {
                    r[i] = rc[i].getValue();
                }
                builder.append(Arrays.toString(r));
                builder.append("\n");
            }
            return builder.toString();
        }
    }
}