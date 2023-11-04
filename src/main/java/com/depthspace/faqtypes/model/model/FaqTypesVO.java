package com.depthspace.faqtypes.model.model;

import java.util.Objects;

// 定義了一個FaqTypesVO類別（根據慣例，類別名稱以大寫字母開頭）
public class FaqTypesVO implements java.io.Serializable{
	private Integer faqNo;
	private String qTypes;

	// Getter 和 Setter 方法（方法名稱以小寫字母開頭，符合慣例）
	public Integer getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(Integer faqNo) {
		this.faqNo = faqNo;
	}

	public String getqTypes() {
		return qTypes;
	}

	public void setqTypes(String qTypes) {
		this.qTypes = qTypes;
	}

	// 重寫equals()方法，用於比較兩個FaqTypesVO物件是否相等
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		FaqTypesVO other = (FaqTypesVO) obj;
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
		return "FaqTypesVO{" + "faqNo=" + faqNo + ", qTypes='" + qTypes + '\'' + '}';
	}
}
