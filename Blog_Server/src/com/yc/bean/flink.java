package com.yc.bean;

import java.sql.Timestamp;

public class flink {
	private Long id;
	private String name;
	private String webUrl;
	private String lingImg;
	private String description;
	private String status;
	private String openWays;
	private Long sort;
	private String createBy;
	private Timestamp createDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getLingImg() {
		return lingImg;
	}
	public void setLingImg(String lingImg) {
		this.lingImg = lingImg;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOpenWays() {
		return openWays;
	}
	public void setOpenWays(String openWays) {
		this.openWays = openWays;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "flink [id=" + id + ", name=" + name + ", webUrl=" + webUrl + ", lingImg=" + lingImg + ", description="
				+ description + ", status=" + status + ", openWays=" + openWays + ", sort=" + sort + ", createBy="
				+ createBy + "]";
	}
	
}
