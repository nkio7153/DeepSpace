package com.depthspace.faq.model.keywordqa;

import java.util.Objects;

public class KeywordQaVO {
    private final Integer serialId;  
    private final String kwTypes;   
    private final String kwAns;
   
    public KeywordQaVO(Integer serialId, String kwTypes, String kwAns) {
        this.serialId = serialId;
        this.kwTypes = kwTypes;
        this.kwAns = kwAns;
    }

    // 取得關鍵字答案的唯一識別號
    public Integer getSerialId() {
        return serialId;
    }

    // 取得關鍵字的類型
    public String getKwTypes() {
        return kwTypes;
    }

    // 取得關鍵字的答案
    public String getKwAns() {
        return kwAns;
    }

    // 重寫equals()方法，用於比較兩個KeyWordQaVO物件是否相等
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        KeywordQaVO other = (KeywordQaVO) obj;
        return Objects.equals(serialId, other.serialId) && Objects.equals(kwTypes, other.kwTypes) && Objects.equals(kwAns, other.kwAns);
    }

    // 重寫hashCode()方法，用於計算物件的雜湊碼值
    @Override
    public int hashCode() {
        return Objects.hash(serialId, kwTypes, kwAns);
    }

    // 預設的toString()方法，用於返回物件的字符串表示，方便日誌記錄等情境下使用
    @Override
    public String toString() {
        return "KeyWordQaVO{" + "serialId=" + serialId + ", kwTypes='" + kwTypes + '\'' + ", kwAns='" + kwAns + '\'' + '}';
    }
}