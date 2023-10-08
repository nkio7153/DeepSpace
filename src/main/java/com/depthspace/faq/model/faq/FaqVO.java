package com.depthspace.faq.model.faq;

import java.util.Objects;

//FaqVO 類別表示一個FAQ（常見問題）的對象。
public class FaqVO {
	private final Integer serialId;
	private final Integer faqNo;
	private final String faqName;
	private final String faqAns;

	// 建構函數，用於初始化這個FAQ對象。
	public FaqVO(Integer serialId, Integer faqNo, String faqName, String faqAns) {
		this.serialId = serialId;
		this.faqNo = faqNo;
		this.faqName = faqName;
		this.faqAns = faqAns;
	}

	// 取得FAQ的唯一流水號。
	public Integer getSerialId() {
		return serialId;
	}

	// 取得FAQ的編號。
	public Integer getFaqNo() {
		return faqNo;
	}

	// 取得FAQ的名稱。
	public String getFaqName() {
		return faqName;
	}

	// 取得FAQ的答案。
	public String getFaqAns() {
		return faqAns;
	}

	// 覆寫equals方法，用於對比兩個FaqVO對象的相等性。
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		FaqVO faqVO = (FaqVO) obj;
		return Objects.equals(serialId, faqVO.serialId) && Objects.equals(faqNo, faqVO.faqNo)
				&& Objects.equals(faqName, faqVO.faqName) && Objects.equals(faqAns, faqVO.faqAns);
	}

	// 覆寫hashCode方法，返回FaqVO對象的雜湊碼。
	@Override
	public int hashCode() {
		return Objects.hash(serialId, faqNo, faqName, faqAns);
	}

	// 覆寫toString方法，返回FaqVO對象的字符串表示。
	@Override
	public String toString() {
		return "FaqVO{" + "serialId=" + serialId + ", faqNo=" + faqNo + ", faqName='" + faqName + '\'' + ", faqAns='"
				+ faqAns + '\'' + '}';
	}
}
