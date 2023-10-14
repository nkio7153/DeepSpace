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
import javax.persistence.Table;
@Entity
@Table(name = "FORUM_ARTICLES")
public class ForumArticlesVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_ID")
	private Integer articleId;
	@Column(name = "MEM_ID")
	private Integer memId;
	@Column(name = "MSG_ID")
	private Integer msgId;
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
	@Column(name = "ARTI_STATUS")
	private Integer artiStatus;
	@Column(name = "ARTI_IMG" , columnDefinition = "BLOB")
	private byte[] artiImg;

	public ForumArticlesVO() {

	}

	public ForumArticlesVO(Integer articleId, Integer memId, Integer msgId, Integer artiTypeId, String artiTitle,
			Timestamp artiTime, String artiText, Integer artiLk, Integer artiStatus, byte[] artiImg) {
		super();
		this.articleId = articleId;
		this.memId = memId;
		this.msgId = msgId;
		this.artiTypeId = artiTypeId;
		this.artiTitle = artiTitle;
		this.artiTime = artiTime;
		this.artiText = artiText;
		this.artiLk = artiLk;
		this.artiStatus = artiStatus;
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

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
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

	public Integer getArtiStatus() {
		return artiStatus;
	}

	public void setArtiStatus(Integer artiStatus) {
		this.artiStatus = artiStatus;
	}

	public byte[] getAtriImg() {
		return artiImg;
	}

	public void setAtriImg(byte[] atriImg) {
		this.artiImg = atriImg;
	}

	@Override
	public String toString() {
		return "ForumArticlesVO [articleId=" + articleId + ", memId=" + memId + ", msgId=" + msgId + ", artiTypeId="
				+ artiTypeId + ", artiTitle=" + artiTitle + ", artiTime=" + artiTime + ", artiText=" + artiText
				+ ", artiLk=" + artiLk + ", artiStatus=" + artiStatus + ", artiImg=" + Arrays.toString(artiImg) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(artiImg);
		result = prime * result
				+ Objects.hash(artiLk, artiStatus, artiText, artiTime, artiTitle, artiTypeId, articleId, memId, msgId);
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
				&& Objects.equals(artiStatus, other.artiStatus) && Objects.equals(artiText, other.artiText)
				&& Objects.equals(artiTime, other.artiTime) && Objects.equals(artiTitle, other.artiTitle)
				&& Objects.equals(artiTypeId, other.artiTypeId) && Objects.equals(articleId, other.articleId)
				&& Objects.equals(memId, other.memId) && Objects.equals(msgId, other.msgId);
	}

	
}
