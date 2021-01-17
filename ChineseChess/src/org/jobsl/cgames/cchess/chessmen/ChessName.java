package org.jobsl.cgames.cchess.chessmen;

/**
 * @author JobsLee
 */

public enum ChessName {
    JU("車", "JU"), MA("馬", "MA"), PAO("炮", "PAO"), BING_RED("兵", "BING"), ZU_BLACK("卒", "ZU"), SHI_RED("仕", "SHI"), SHI_BLACK("士", "SHI"), SHUAI_RED("帥", "SHUAI"), JIANG_BLACK("將", "JIANG"), XIANG_RED("相", "XIANG"), XIANG_BLACK("象", "XIANG");

    String chinese;
    String pinyin;

    ChessName(String chinese, String pinyin) {
        this.chinese = chinese;
        this.pinyin = pinyin;
    }

    public String getChinese() {
        return chinese;
    }

    public String getPinyin() {
        return pinyin;
    }
}
