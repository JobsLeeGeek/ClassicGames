package org.jobsl.cgames.cchess.chessboard;

import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessmen.ChessColor;
import org.jobsl.cgames.cchess.chessmen.impl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JobsLee
 */
public class ChessBoard {
    // 2 x 9 x 5 -> 9 * 10
    private ChessBoardCell[][] boardCells = new ChessBoardCell[9][10];
    // row-ref
    private List<List<ChessBoardCell>> rowBoardCells = new ArrayList<>();
    // column-ref
    private List<List<ChessBoardCell>> columnBoardCells = new ArrayList<>();

    public void init() {
        // init empty cell
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                boardCells[i][j] = new ChessBoardCell();
            }
        }
        // init row ref
        for (int i = 0; i < 10; i++) {
            rowBoardCells.add(new ArrayList<ChessBoardCell>());
        }
        // init colmns ref
        for (int i = 0; i < 9; i++) {
            columnBoardCells.add(new ArrayList<ChessBoardCell>());
        }

        // init chessmen
        int midX = 4;
        // red x 0~8 y 0~4
        //
        // (0,3)       (2,3)          (4,3)          (6,3)       (8,3)
        //       (1,2)                                     (7,2)
        //
        // (0,0) (1,0) (2,0) (3,0) -> (4,0) <- (5,0) (6,0) (7,0) (8,0)
        boardCells[midX][0].setChessman(new ChessShuai(ChessColor.RED));
        boardCells[midX][3].setChessman(new ChessBing(ChessColor.RED));
        // left
        boardCells[0][0].setChessman(new ChessJu(ChessColor.RED));
        boardCells[1][0].setChessman(new ChessMa(ChessColor.RED));
        boardCells[2][0].setChessman(new ChessXiang(ChessColor.RED));
        boardCells[3][0].setChessman(new ChessShi(ChessColor.RED));
        boardCells[1][2].setChessman(new ChessPao(ChessColor.RED));
        boardCells[0][3].setChessman(new ChessBing(ChessColor.RED));
        boardCells[2][3].setChessman(new ChessBing(ChessColor.RED));
        // right
        boardCells[(midX - 0) * 2 + 0][0].setChessman(new ChessJu(ChessColor.RED));
        boardCells[(midX - 1) * 2 + 1][0].setChessman(new ChessMa(ChessColor.RED));
        boardCells[(midX - 2) * 2 + 2][0].setChessman(new ChessXiang(ChessColor.RED));
        boardCells[(midX - 3) * 2 + 3][0].setChessman(new ChessShi(ChessColor.RED));
        boardCells[(midX - 1) * 2 + 1][2].setChessman(new ChessPao(ChessColor.RED));
        boardCells[(midX - 0) * 2 + 0][3].setChessman(new ChessBing(ChessColor.RED));
        boardCells[(midX - 2) * 2 + 2][3].setChessman(new ChessBing(ChessColor.RED));
        // black x 0~8 y 5~9
        // (0,9) (1,9) (2,9) (3,9) -> (4,9) <- (5,9) (6,9) (7,9) (8,9)
        //
        //       (1,7)                                     (7,7)
        // (0,6)       (2,6)          (4,6)          (6,6)       (8,6)
        //
        boardCells[midX][9].setChessman(new ChessShuai(ChessColor.BLACK));
        boardCells[midX][6].setChessman(new ChessBing(ChessColor.BLACK));
        // left
        boardCells[0][9].setChessman(new ChessJu(ChessColor.BLACK));
        boardCells[1][9].setChessman(new ChessMa(ChessColor.BLACK));
        boardCells[2][9].setChessman(new ChessXiang(ChessColor.BLACK));
        boardCells[3][9].setChessman(new ChessShi(ChessColor.BLACK));
        boardCells[1][7].setChessman(new ChessPao(ChessColor.BLACK));
        boardCells[0][6].setChessman(new ChessBing(ChessColor.BLACK));
        boardCells[2][6].setChessman(new ChessBing(ChessColor.BLACK));
        // right
        boardCells[(midX - 0) * 2 + 0][9].setChessman(new ChessJu(ChessColor.BLACK));
        boardCells[(midX - 1) * 2 + 1][9].setChessman(new ChessMa(ChessColor.BLACK));
        boardCells[(midX - 2) * 2 + 2][9].setChessman(new ChessXiang(ChessColor.BLACK));
        boardCells[(midX - 3) * 2 + 3][9].setChessman(new ChessShi(ChessColor.BLACK));
        boardCells[(midX - 1) * 2 + 1][7].setChessman(new ChessPao(ChessColor.BLACK));
        boardCells[(midX - 0) * 2 + 0][6].setChessman(new ChessBing(ChessColor.BLACK));
        boardCells[(midX - 2) * 2 + 2][6].setChessman(new ChessBing(ChessColor.BLACK));

        // init ref
        for (int i = 0; i < 10; i++) {
            // row ref
            List<ChessBoardCell> list = rowBoardCells.get(i);
            for (int j = 0; j < 9; j++) {
                list.add(boardCells[j][i]);
            }
        }
        for (int i = 0; i < 9; i++) {
            // colmns ref
            List<ChessBoardCell> list = columnBoardCells.get(i);
            for (int j = 0; j < 10; j++) {
                list.add(boardCells[i][j]);
            }
        }
    }

    public boolean checkCellEmpty(Point point, ChessColor color) {
        if (boardCells[point.getX()][point.getY()] != null
                && boardCells[point.getX()][point.getY()].getChessman().getColor().equals(color)) {
            return false;
        }
        return true;
    }

    public int checkCellNotEmptyNum(Point currentP, Point nextP) {
        int cNum = 0;
        int p = Math.abs(currentP.getX() - nextP.getX());
        if (p > 0) {
            // move on row
            for (int i = currentP.getX() + 1; i < p; i++) {
                ChessBoardCell cell = rowBoardCells.get(currentP.getX()).get(i);
                if (cell != null) cNum++;
            }
        }
        if (nextP.getY() - currentP.getY() > 0) {
            // move on column
            for (int i = currentP.getY() + 1; i < p; i++) {
                ChessBoardCell cell = rowBoardCells.get(currentP.getY()).get(i);
                if (cell != null) cNum++;
            }
        }
        return cNum;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            List<ChessBoardCell> list = rowBoardCells.get(i);
            for (ChessBoardCell cell : list) {
                builder.append(cell.getChessman() == null ? "= " : cell.getChessman().getName() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
