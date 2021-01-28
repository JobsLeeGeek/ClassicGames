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
public class ChessBing extends Chessman implements ChessRule {
    public ChessBing(ChessColor color) {
        super(color);
    }

    @Override
    protected void setNameAndColor(ChessColor color) {
        this.color = color;
        this.name = ChessColor.RED.equals(color) ? ChessName.BING_RED : ChessName.ZU_BLACK;
    }

    @Override
    public boolean checkRule(Point lastP, Point nextP, ChessBoard cBoard) {
        boolean res = false;
        int xx = Math.abs(nextP.getX() - lastP.getX());
        int yy = nextP.getY() - lastP.getY();
        int absyy = Math.abs(yy);
        if (ChessColor.RED.equals(color)) {
            if (yy > 0) return res;
            if ((xx == 0 && absyy == 1) || (xx == 1 && absyy == 0 && lastP.getY() <= 4)) res = true;
        }
        if (ChessColor.BLACK.equals(color)) {
            if (yy < 0) return res;
            if ((xx == 0 && absyy == 1) || (xx == 1 && absyy == 0 && lastP.getY() >= 5)) res = true;
        }
        return res ? super.checkRule(lastP, nextP, cBoard) : res;
    }
}
