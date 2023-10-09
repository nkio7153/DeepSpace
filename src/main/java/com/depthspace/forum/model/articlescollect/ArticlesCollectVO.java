package com.depthspace.forum.model.articlescollect;

import java.io.Serializable;

public class ArticlesCollectVO implements Serializable {
	private Integer articleId;
	private Integer memId;

	public ArticlesCollectVO() {

	}

	public ArticlesCollectVO(Integer articleId, Integer memId) {
		this.articleId = articleId;
		this.memId = memId;
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

}
