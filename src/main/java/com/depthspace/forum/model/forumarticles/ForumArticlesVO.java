package com.depthspace.forum.model.forumarticles;

import java.io.Serializable;
import java.sql.Timestamp;

public class ForumArticlesVO implements Serializable {
	private Integer articleId;
	private Integer memId;
	private Integer msgId;
	private Integer artiTypeId;
	private String artiTitle;
	private Timestamp artiTime;
	private String artiText;
	private Integer artiLk;
	private Integer artiStatus;
	private byte[] atriImg;

	public ForumArticlesVO() {

	}

	public ForumArticlesVO(Integer articleId, Integer memId, Integer msgId, Integer artiTypeId, String artiTitle,
			Timestamp artiTime, String artiText, Integer artiLk, Integer artiStatus, byte[] atriImg) {
		this.articleId = articleId;
		this.memId = memId;
		this.msgId = msgId;
		this.artiTypeId = artiTypeId;
		this.artiTitle = artiTitle;
		this.artiTime = artiTime;
		this.artiText = artiText;
		this.artiLk = artiLk;
		this.artiStatus = artiStatus;
		this.atriImg = atriImg;
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
		return atriImg;
	}

	public void setAtriImg(byte[] atriImg) {
		this.atriImg = atriImg;
	}

}
