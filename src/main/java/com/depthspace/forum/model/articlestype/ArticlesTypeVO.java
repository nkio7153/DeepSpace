package com.depthspace.forum.model.articlestype;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "ARTICLES_TYPE")
public class ArticlesTypeVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTI_TYPE_ID")
	private Integer artiTypeId;
	@Column(name = "ARTI_TYPE_TEXT")
	private String artiTypeText;

	public ArticlesTypeVO() {

	}

	public ArticlesTypeVO(Integer artiTypeId, String artiTypeText) {
		this.artiTypeId = artiTypeId;
		this.artiTypeText = artiTypeText;
	}

	public Integer getArtiTypeId() {
		return artiTypeId;
	}

	public void setArtiTypeId(Integer artiTypeId) {
		this.artiTypeId = artiTypeId;
	}

	public String getArtiTypeText() {
		return artiTypeText;
	}

	public void setArtiTypeText(String artiTypeText) {
		this.artiTypeText = artiTypeText;
	}

	@Override
	public String toString() {
		return "ArticlesTypeVO [artiTypeId=" + artiTypeId + ", artiTypeText=" + artiTypeText + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(artiTypeId, artiTypeText);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticlesTypeVO other = (ArticlesTypeVO) obj;
		return Objects.equals(artiTypeId, other.artiTypeId) && Objects.equals(artiTypeText, other.artiTypeText);
	}

}
