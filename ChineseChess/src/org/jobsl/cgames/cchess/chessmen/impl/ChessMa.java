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
public class ChessMa extends Chessman implements ChessRule {
    public ChessMa(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessName.MA;
    }

    @Override
    public boolean checkRule(Point currentP, Point nextP, ChessBoard cBoard) {
        boolean res = false;
        int xx = nextP.getX() - currentP.getX();
        int yy = nextP.getY() - currentP.getY();
        if (Math.abs(xx) == 1) {
            if (Math.abs(yy) == 2) {
                Point blockP = new Point(currentP.getX() + xx, currentP.getY() + (yy / 2));
                if (cBoard.checkCellEmpty(blockP, ChessColor.RED) && cBoard.checkCellEmpty(blockP, ChessColor.BLACK)) res = true;
            }
        }
        if (Math.abs(yy) == 1) {
            if (Math.abs(xx) == 2) {
                Point blockP = new Point(currentP.getX() + (xx / 2), currentP.getY() + yy);
                if (cBoard.checkCellEmpty(blockP, ChessColor.RED) && cBoard.checkCellEmpty(blockP, ChessColor.BLACK)) res = true;
            }
        }
        return res ? super.checkRule(currentP, nextP, cBoard) : res;
    }
}
