package org.jobsl.cgames.cchess.base;

import java.util.Date;

public class History {
    public History(Point fromPoint, Point toPoint) {
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.time = new Date();
    }

    private Point fromPoint;

    private Point toPoint;

    private Date time;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        // todo
        return "History";
    }
}
