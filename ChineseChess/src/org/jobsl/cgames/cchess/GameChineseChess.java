package org.jobsl.cgames.cchess;

import org.jobsl.cgames.cchess.chessboard.ChessBoard;

/**
 * @author JobsLee
 */
public class GameChineseChess {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.init();
        System.out.println(chessBoard.toString());
    }
}
