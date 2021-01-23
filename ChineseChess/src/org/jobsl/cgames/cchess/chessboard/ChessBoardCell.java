package org.jobsl.cgames.cchess.chessboard;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessmen.ChessColor;
import org.jobsl.cgames.cchess.chessmen.Chessman;

/**
 * @author JobsLee
 */
public class ChessBoardCell extends Button {
    private boolean isChoose;
    private Point point;
    private Chessman chessman;
    private String imageUrl;

    public ChessBoardCell(Point point, int layoutX, int layoutY) {
        this.setPoint(point);
        this.imageUrl = getChessmanImageUrl(null);
        this.reDraw();
        super.setLayoutX(layoutX);
        super.setLayoutY(layoutY);
        super.setPrefSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT);
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Chessman getChessman() {
        return chessman;
    }

    public ChessBoardCell setChessman(Chessman chessman) {
        this.chessman = chessman;
        this.imageUrl = getChessmanImageUrl(this.chessman);
        this.reDraw();
        return this;
    }

    public void reDraw() {
        String imageFullPath = this.imageUrl + (isChoose ? Constants.CHESSMAN_CHOOSE_IMAGE_BACK : Constants.CHESSMAN_UNCHOOSE_IMAGE_BACK);
        super.setBackground(new Background(new BackgroundImage(new Image(imageFullPath), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT, false, false, false, false))));
    }

    private String getChessmanImageUrl(Chessman chessman) {
        String imageFullPath = "";
        if (chessman != null) {
            imageFullPath = Constants.RESOURCE_CHESS_PATH + (ChessColor.RED.equals(chessman.getColor()) ? Constants.CHESSMAN_IMAGE_FRONT_RED : Constants.CHESSMAN_IMAGE_FRONT_BALCK) + chessman.getName().getPinyin();
        } else {
            imageFullPath = Constants.CHESSMAN_BLANK_IMAGE;
        }
        return imageFullPath;
    }
}
