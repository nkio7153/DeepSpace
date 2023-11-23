package com.depthspace.forum.model.forumarticles;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "FORUM_ARTICLES")
public class ForumArticlesVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_ID")
	private Integer articleId;
	@Column(name = "MEM_ID")
	private Integer memId;
	@Column(name = "ARTI_TYPE_ID")
	private Integer artiTypeId;
	@Column(name = "ARTI_TITLE")
	private String artiTitle;
	@Column(name = "ARTI_TIME")
	private Timestamp artiTime;
	@Column(name = "ARTI_TEXT")
	private String artiText;
	@Column(name = "ARTI_LK")
	private Integer artiLk;
	@Column(name = "ARTI_IMG", columnDefinition = "MEDIUMBLOB")
	private byte[] artiImg;

	@Transient
	private String base64Str;

	public ForumArticlesVO() {

	}

	public ForumArticlesVO(Integer articleId, Integer memId, Integer artiTypeId, String artiTitle,
			Timestamp artiTime, String artiText, Integer artiLk, byte[] artiImg) {
		super();
		this.articleId = articleId;
		this.memId = memId;
		this.artiTypeId = artiTypeId;
		this.artiTitle = artiTitle;
		this.artiTime = artiTime;
		this.artiText = artiText;
		this.artiLk = artiLk;
		this.artiImg = artiImg;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getArtiTypeId() {
		return artiTypeId;
	}

	public void setArtiTypeId(Integer artiTypeId) {
		this.artiTypeId = artiTypeId;
	}

	public String getArtiTitle() {
		return artiTitle;
	}

	public void setArtiTitle(String artiTitle) {
		this.artiTitle = artiTitle;
	}

	public Timestamp getArtiTime() {
		return artiTime;
	}

	public void setArtiTime(Timestamp artiTime) {
		this.artiTime = artiTime;
	}

	public String getArtiText() {
		return artiText;
	}

	public void setArtiText(String artiText) {
		this.artiText = artiText;
	}

	public Integer getArtiLk() {
		return artiLk;
	}

	public void setArtiLk(Integer artiLk) {
		this.artiLk = artiLk;
	}

	public byte[] getArtiImg() {
		return artiImg;
	}

	public void setArtiImg(byte[] artiImg) {
		this.artiImg = artiImg;
	}

	public String getBase64Str() {
		return base64Str;
	}

	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}
	
	// 增加按讚數
	public void incrementLikes() {
	    this.artiLk++; // 由於已經確定初始值為0，直接增加即可
	}

	// 減少按讚數
	public void decrementLikes() {
	    if (this.artiLk > 0) {
	        this.artiLk--; // 只有當目前讚數大於0時才減少
	    }
	}

	@Override
	public String toString() {
		return "ForumArticlesVO [articleId=" + articleId + ", memId=" + memId + ", artiTypeId="
				+ artiTypeId + ", artiTitle=" + artiTitle + ", artiTime=" + artiTime + ", artiText=" + artiText
				+ ", artiLk=" + artiLk + ", artiImg=" + Arrays.toString(artiImg) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(artiImg);
		result = prime * result
				+ Objects.hash(artiLk, artiText, artiTime, artiTitle, artiTypeId, articleId, memId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForumArticlesVO other = (ForumArticlesVO) obj;
		return Arrays.equals(artiImg, other.artiImg) && Objects.equals(artiLk, other.artiLk)
				&& Objects.equals(artiText, other.artiText) && Objects.equals(artiTime, other.artiTime)
				&& Objects.equals(artiTitle, other.artiTitle) && Objects.equals(artiTypeId, other.artiTypeId)
				&& Objects.equals(articleId, other.articleId) && Objects.equals(memId, other.memId);
	}

}
