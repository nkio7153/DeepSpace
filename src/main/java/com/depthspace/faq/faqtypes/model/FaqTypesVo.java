package com.depthspace.faq.faqtypes.model;

import java.util.Objects;

//定義了一個FaqTypesVo類別
public class FaqTypesVo {
	private final Integer faqNo;
	private final String qTypes;

	// 建構函數，用於初始化faqNo和qTypes屬性
	public FaqTypesVo(Integer faqNo, String qTypes) {
		this.faqNo = faqNo;
		this.qTypes = qTypes;
	}

	// 取得faqNo屬性的方法
	public Integer getFaqNo() {
		return faqNo;
	}

	// 取得qTypes屬性的方法
	public String getqTypes() {
		return qTypes;
	}

	// 重寫equals()方法，用於比較兩個FaqTypesVo物件是否相等
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		FaqTypesVo other = (FaqTypesVo) obj;
		return Objects.equals(faqNo, other.faqNo) && Objects.equals(qTypes, other.qTypes);
	}

	// 重寫hashCode()方法，用於計算物件的雜湊碼值
	@Override
	public int hashCode() {
		return Objects.hash(faqNo, qTypes);
	}

	// 預設的toString()方法，用於返回物件的字符串表示，方便日誌記錄等情境下使用
	@Override
	public String toString() {
		return "FaqTypesVo{" + "faqNo=" + faqNo + ", qTypes='" + qTypes + '\'' + '}';
	}
}