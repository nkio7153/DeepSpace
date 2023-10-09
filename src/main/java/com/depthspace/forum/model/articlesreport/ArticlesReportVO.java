package com.depthspace.forum.model.articlesreport;

import java.io.Serializable;
import java.sql.Timestamp;

public class ArticlesReportVO implements Serializable {
	private Integer articleReportId;
	private Integer articleId;
	private Integer reportId;
	private Integer adminId;
	private String reportContent;
	private Timestamp reportTime;
	private Integer reportStatus;

	public ArticlesReportVO() {

	}

	public ArticlesReportVO(Integer articleReportId, Integer articleId, Integer reportId, Integer adminId,
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

}
