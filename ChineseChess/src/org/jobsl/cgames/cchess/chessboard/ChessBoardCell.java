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
    private Point point;
    private Chessman chessman;
    private String imageUrl;

    public ChessBoardCell(Point point, int layoutX, int layoutY) {
        setPoint(point);
        super.setLayoutX(layoutX);
        super.setLayoutY(layoutY);
        super.setPrefSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT);
        blankBackground();
    }

    public void moveFrom(ChessBoardCell lastBoardCell) {
        setChessman(lastBoardCell.getChessman());
        setImageUrl(lastBoardCell.getImageUrl());
        lastBoardCell.clear();
    }

    public void clear() {
        this.imageUrl = null;
        this.chessman = null;
        blankBackground();
    }

    public void chooseBackground() {
        drawBackground(this.imageUrl + Constants.CHESSMAN_CHOOSE_IMAGE_BACK);
    }

    public void unchooseBackground() {
        drawBackground(this.imageUrl + Constants.CHESSMAN_UNCHOOSE_IMAGE_BACK);
    }

    public void blankBackground() {
        drawBackground(Constants.CHESSMAN_BLACK_IMAGE + Constants.CHESSMAN_UNCHOOSE_IMAGE_BACK);
    }

    private void drawBackground(String imageUrl) {
        super.setBackground(new Background(new BackgroundImage(new Image(imageUrl), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT, false, false, false, false))));
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        this.imageUrl = getChessmanImageUrl(chessman);
        this.chessman = chessman;
        drawBackground(this.imageUrl + Constants.CHESSMAN_UNCHOOSE_IMAGE_BACK);
        return this;
    }

    private String getChessmanImageUrl(Chessman chessman) {
        return Constants.RESOURCE_PATH + "\\chess\\" + (ChessColor.RED.equals(chessman.getColor()) ? Constants.CHESSMAN_IMAGE_FRONT_RED : Constants.CHESSMAN_IMAGE_FRONT_BALCK) + chessman.getName().getPinyin();
    }
}
