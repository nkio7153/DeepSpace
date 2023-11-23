package com.depthspace.faq.model;

import java.util.Objects;
import com.depthspace.faqtypes.model.model.FaqTypesVO; 

//FaqVO 類別表示一個FAQ（常見問題）的對象。
public class FaqVO implements java.io.Serializable{
	private Integer serialId;
	private Integer faqNo;
	private String faqName;
	private String faqAns;
	
	private FaqTypesVO faqType; // 添加FaqTypesVO的引用
	
	public FaqTypesVO getFaqType() {
        return faqType;
    }
	
    public void setFaqType(FaqTypesVO faqType) {
        this.faqType = faqType;
    }
	

	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}

	public Integer getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(Integer faqNo) {
		this.faqNo = faqNo;
	}

	public String getFaqName() {
		return faqName;
	}

	public void setFaqName(String faqName) {
		this.faqName = faqName;
	}

	public String getFaqAns() {
		return faqAns;
	}

	public void setFaqAns(String faqAns) {
		this.faqAns = faqAns;
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
