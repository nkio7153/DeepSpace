package com.depthspace.forum.model.articlereport;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "ARTICLE_REPORT")
public class ArticleReportVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTICLE_REPORT_ID")
	private Integer articleReportId;
	@Column(name = "ARTICLE_ID")
	private Integer articleId;
	@Column(name = "REPORT_ID")
	private Integer reportId;
	@Column(name = "ADMIN_ID")
	private Integer adminId;
	@Column(name = "REPORT_CONTENT")
	private String reportContent;
	@Column(name = "REPORT_TIME")
	private Timestamp reportTime;
	@Column(name = "REPORT_STATUS")
	private Integer reportStatus;

	public ArticleReportVO() {

	}

	public ArticleReportVO(Integer articleReportId, Integer articleId, Integer reportId, Integer adminId,
			String reportContent, Timestamp reportTime, Integer reportStatus) {

		this.articleReportId = articleReportId;
		this.articleId = articleId;
		this.reportId = reportId;
		this.adminId = adminId;
		this.reportContent = reportContent;
		this.reportTime = reportTime;
		this.reportStatus = reportStatus;
	}

	public Integer getArticleReportId() {
		return articleReportId;
	}

	public void setArticleReportId(Integer articleReportId) {
		this.articleReportId = articleReportId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public Timestamp getReportTime() {
		return reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	@Override
	public String toString() {
		return "ArticlesReportVO [articleReportId=" + articleReportId + ", articleId=" + articleId + ", reportId="
				+ reportId + ", adminId=" + adminId + ", reportContent=" + reportContent + ", reportTime=" + reportTime
				+ ", reportStatus=" + reportStatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminId, articleId, articleReportId, reportContent, reportId, reportStatus, reportTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleReportVO other = (ArticleReportVO) obj;
		return Objects.equals(adminId, other.adminId) && Objects.equals(articleId, other.articleId)
				&& Objects.equals(articleReportId, other.articleReportId)
				&& Objects.equals(reportContent, other.reportContent) && Objects.equals(reportId, other.reportId)
				&& Objects.equals(reportStatus, other.reportStatus) && Objects.equals(reportTime, other.reportTime);
	}

}
