package org.jobsl.cgames.cchess;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jobsl.cgames.cchess.base.Commands;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessboard.ChessBoardCell;
import org.jobsl.cgames.cchess.chessmen.ChessColor;
import org.jobsl.cgames.cchess.chessmen.Chessman;
import org.jobsl.cgames.cchess.net.ChessMessage;
import org.jobsl.cgames.cchess.net.ChessNetBattle;
import org.jobsl.cgames.net.msg.MessageCode;

import java.util.concurrent.TimeUnit;

/**
 * @author JobsLee
 */
public class Controller {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView mainBoard;

    private ChessBoard chessBoard;
    private ChessBoardCell chooseCell;
    private ChessColor chessColorShould = ChessColor.RED;

    private ChessColor myChessColor = ChessColor.RED;
    private boolean onlineSwitch = false;
    ChessNetBattle netBattle = null;

    public void init() {
        // online mode
        if (onlineSwitch) {
            // msg handler init
            netBattle = new ChessNetBattle(this, "127.0.0.1", 9191);
            netBattle.connect();
        }
        // init chessBoard and chessmen data
        chessBoard = new ChessBoard();
        chessBoard.init(myChessColor);
        // init background display
        mainBoard.setImage(new Image(Constants.BOARD_BACKGROUND_IMAGE));
        // init chessmen display
        ChessBoardCell[][] boardCells = chessBoard.getBoardCells();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                ChessBoardCell cell = boardCells[j][i];
                cell.reDraw();
                cell.setOnAction(event -> {
                    // red first and black second ... one by one
                    boolean colorShouldChoose = (cell.getChessman() != null && chessColorShould.equals(cell.getChessman().getColor()));
                    // choose new one
                    if (chooseCell == null) {
                        if (!colorShouldChoose) return;
                        cell.setChoose(true);
                        cell.reDraw();
                        chooseCell = cell;
                    }
                    // choose the same one
                    if (chooseCell == cell) return;
                    // choose or move or override
                    Chessman lastC = chooseCell.getChessman();
                    Chessman nextC = cell.getChessman();
                    if (lastC == null) return;
                    if (lastC != null) {
                        if (nextC != null) {
                            if (lastC.getColor().equals(nextC.getColor())) {
                                if (!colorShouldChoose) return;
                                // the same color choose new one
                                cell.setChoose(true);
                                cell.reDraw();
                                chooseCell.setChoose(false);
                                chooseCell.reDraw();
                                chooseCell = cell;
                                return;
                            }
                            // override
//                            move(cell);
                        }
                        /*if (nextC == null) {
                            // move
//                            move(cell);
                        }*/
                        move(cell);
                    }
                });
                mainPane.getChildren().add(cell);
            }
        }
    }

    public void move(Point fromP, Point toP) {
        ChessBoardCell fromCell = chessBoard.getBoardCells()[fromP.getX()][fromP.getY()];
        ChessBoardCell toCell = chessBoard.getBoardCells()[toP.getX()][toP.getY()];
        fromCell.fire();
        try {
            TimeUnit.MILLISECONDS.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        toCell.fire();
    }

    private void move(ChessBoardCell cell) {
        if (chooseCell.getChessman().checkRule(chooseCell.getPoint(), cell.getPoint(), chessBoard)) {
            // online mode send msg
            if (onlineSwitch) {
                netBattle.sendMessage(MessageCode.SUCCESS, new ChessMessage(Commands.MOVE, chooseCell.getPoint(), cell.getPoint()));
            }
            cell.setChessman(chooseCell.getChessman());
            // release choose
            cell.setChoose(false);
            cell.reDraw();
            // release choose
            chooseCell.setChoose(false);
            chooseCell.setChessman(null);
            chooseCell.reDraw();
            // choose null next re-choose
            chooseCell = null;
            // change color should
            setOpposedChessColorShould();
        }
    }

    private void setOpposedChessColorShould() {
        chessColorShould = chessColorShould.getOpposed(chessColorShould);
    }
}
