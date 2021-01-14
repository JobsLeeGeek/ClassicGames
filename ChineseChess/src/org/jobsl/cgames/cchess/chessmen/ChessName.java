package org.jobsl.cgames.cchess.chessmen;

/**
 * @author JobsLee
 */

public enum ChessName {
    JU("車"), MA("馬"), PAO("炮"), BING_RED("兵"), ZU_BLACK("卒"), SHI_RED("仕"), SHI_BLACK("士"), SHUAI_RED("帥"), JIANG_BLACK("將"), XIANG_RED("相"), XIANG_BLACK("象");

    String name;

    ChessName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
