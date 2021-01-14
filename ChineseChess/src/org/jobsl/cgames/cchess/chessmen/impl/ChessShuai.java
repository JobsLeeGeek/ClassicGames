package org.jobsl.cgames.cchess.chessmen.impl;

import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.ChessColor;
import org.jobsl.cgames.cchess.chessmen.ChessName;
import org.jobsl.cgames.cchess.chessmen.ChessRule;
import org.jobsl.cgames.cchess.chessmen.Chessman;

/**
 * @author JobsLee
 */
public class ChessShuai extends Chessman implements ChessRule {
    public ChessShuai(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessColor.RED.equals(color) ? ChessName.SHUAI_RED.getName() : ChessName.JIANG_BLACK.getName();
    }

    @Override
    public boolean checkRule(Point nextP, ChessBoard cBoard) {
        // x 3~5 y 0~2
        if (ChessColor.RED.equals(color)) {
            if (nextP.getX() < 3 || nextP.getX() > 5) return false;
            if (nextP.getY() < 0 || nextP.getY() > 2) return false;
        }
        // x 3~5 y 7~9
        if (ChessColor.BLACK.equals(color)) {
            if (nextP.getX() < 3 || nextP.getX() > 5) return false;
            if (nextP.getY() < 7 || nextP.getY() > 9) return false;
        }
        return super.checkRule(nextP, cBoard);
    }
}
