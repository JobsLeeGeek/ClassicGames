package org.jobsl.cgames.cchess.net;

import org.jobsl.cgames.cchess.base.Commands;
import org.jobsl.cgames.cchess.base.Point;

import java.io.Serializable;

/**
 * @author JobsLee
 */
public class ChessMessage implements Serializable {
    private static final long serialVersionUID = -6620150340774328062L;

    private Commands command;

    private Point fromPoint;

    private Point toPoint;

    private Long ip;

    private Long time;

    private String sign;

    public ChessMessage(Commands command, Point fromPoint, Point toPoint) {
        this.command = command;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
    }

    public Commands getCommand() {
        return command;
    }

    public void setCommand(Commands command) {
        this.command = command;
    }

    public Point getFromPoint() {
        return fromPoint;
    }

    public void setFromPoint(Point fromPoint) {
        this.fromPoint = fromPoint;
    }

    public Point getToPoint() {
        return toPoint;
    }

    public void setToPoint(Point toPoint) {
        this.toPoint = toPoint;
    }

    public Long getIp() {
        return ip;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
