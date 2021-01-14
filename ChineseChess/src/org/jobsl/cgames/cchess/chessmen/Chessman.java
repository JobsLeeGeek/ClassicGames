package org.jobsl.cgames.cchess.chessmen;

import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;

/**
 * @author JobsLee
 */
public abstract class Chessman implements ChessRule {
    protected Point point;
    protected ChessColor color;
    protected String name;

    public Chessman(ChessColor color) {
        setNameAndColor(color);
    }

    protected abstract void setNameAndColor(ChessColor color);

    @Override
    public boolean checkRule(Point nextP, ChessBoard cBoard) {
        if (nextP.getX() >= 0 && nextP.getX() < 9
                && nextP.getY() >= 0 && nextP.getY() < 10) {
            if (!cBoard.checkCellEmpty(nextP, color)) return false;
            return true;
        } else {
            return false;
        }
    }

    public ChessColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
