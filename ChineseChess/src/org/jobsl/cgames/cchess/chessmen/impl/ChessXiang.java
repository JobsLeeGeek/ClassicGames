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
public class ChessXiang extends Chessman implements ChessRule {
    public ChessXiang(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessColor.RED.equals(color) ? ChessName.XIANG_RED.getName() : ChessName.XIANG_BLACK.getName();
    }

    @Override
    public boolean checkRule(Point nextP, ChessBoard cBoard) {
        if (ChessColor.RED.equals(color)) {
            // (2,0) (0,2) (2,4) (4,2) (6,0) (6,4) (8,2)
            if (nextP.getX() != 0 && nextP.getX() != 2 && nextP.getX() != 4 && nextP.getX() != 6 && nextP.getX() != 8)
                return false;
            if (nextP.getY() != 0 && nextP.getY() != 2 && nextP.getY() != 4) return false;
        }
        if (ChessColor.BLACK.equals(color)) {
            // (2,9) (0,5) (2,5) (4,7) (6,9) (6,5) (8,7)
            if (nextP.getX() != 0 && nextP.getX() != 2 && nextP.getX() != 4 && nextP.getX() != 6 && nextP.getX() != 8)
                return false;
            if (nextP.getY() != 5 && nextP.getY() != 7 && nextP.getY() != 9) return false;
        }
        return super.checkRule(nextP, cBoard);
    }
}
