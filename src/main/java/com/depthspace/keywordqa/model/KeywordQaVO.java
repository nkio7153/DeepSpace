package com.depthspace.keywordqa.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "KEYWORD_QA")
public class KeywordQaVO implements Serializable {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SERIAL_ID")
	private Integer serialId;
	@Expose
	@Column(name = "KW_TYPES")
	private String kwTypes;
	@Expose
	@Column(name = "KW_ANS")
	private String kwAns;

	public KeywordQaVO(Integer serialId, String kwTypes, String kwAns) {
		this.serialId = serialId;
		this.kwTypes = kwTypes;
		this.kwAns = kwAns;
	}
	
	public KeywordQaVO() {
        super();
    }
	
	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}

	public String getKwTypes() {
		return kwTypes;
	}

	public void setKwTypes(String kwTypes) {
		this.kwTypes = kwTypes;
	}

	public String getKwAns() {
		return kwAns;
	}

	public void setKwAns(String kwAns) {
		this.kwAns = kwAns;
	}

	// 重寫equals()方法，用於比較兩個KeyWordQaVO物件是否相等
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		KeywordQaVO other = (KeywordQaVO) obj;
		return Objects.equals(serialId, other.serialId) && Objects.equals(kwTypes, other.kwTypes)
				&& Objects.equals(kwAns, other.kwAns);
	}

	// 重寫hashCode()方法，用於計算物件的雜湊碼值
	@Override
	public int hashCode() {
		return Objects.hash(serialId, kwTypes, kwAns);
	}

	// 預設的toString()方法，用於返回物件的字符串表示，方便日誌記錄等情境下使用
	@Override
	public String toString() {
		return "KeywordQaVO{" + "serialId=" + serialId + ", kwTypes='" + kwTypes + '\'' + ", kwAns='" + kwAns + '\''
				+ '}';
	}
}