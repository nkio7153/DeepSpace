package com.depthspace.forum.model.articleslike;

import java.io.Serializable;

public class ArticlesLikeVO implements Serializable {
	private Integer articleId;
	private Integer memId;

	public ArticlesLikeVO() {
	}

	public ArticlesLikeVO(Integer articleId, Integer memId) {
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
