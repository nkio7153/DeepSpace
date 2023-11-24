package com.depthspace.forum.model.articlescollect;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICLES_COLLECT")
@IdClass(ArticlesCollectVO.CompositeDetails.class)
public class ArticlesCollectVO implements Serializable {
	@Id
	@Column(name = "ARTICLE_ID", nullable = false)
	private Integer articleId;
	@Id
	@Column(name = "MEM_ID", nullable = false)
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

	public static class CompositeDetails implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer articleId;
		private Integer memId;

		public CompositeDetails() {
			
		}

		public CompositeDetails(Integer articleId, Integer memId) {
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

		@Override
		public int hashCode() {
			return Objects.hash(articleId, memId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeDetails other = (CompositeDetails) obj;
			return Objects.equals(articleId, other.articleId) && Objects.equals(memId, other.memId);
		}

	}

}
