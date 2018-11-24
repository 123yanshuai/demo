package com.yc.bean;

import java.sql.Timestamp;

public class Article {
	private Long id;
	private String title;
	private String content;
	private String keyWords;
	private String description;
	private Long columnId;
	private String lable;
	private String titleImgs;
	private String status;
	private String type;
	private Timestamp releaseTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getColumnId() {
		return columnId;
	}
	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getTitleImgs() {
		return titleImgs;
	}
	public void setTitleImgs(String titleImgs) {
		this.titleImgs = titleImgs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Timestamp releaseTime) {
		this.releaseTime = releaseTime;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", keyWords=" + keyWords
				+ ", description=" + description + ", columnId=" + columnId + ", lable=" + lable + ", titleImgs="
				+ titleImgs + ", status=" + status + ", type=" + type + ", releaseTime=" + releaseTime + "]";
	}
	
}
