package com.cafe24.jblog.vo;

public class CategoryVo {

	private Long no;
	private String name;
	private String description;
	private String reg_date;
	private String id;
	private int countPost;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCountPost() {
		return countPost;
	}
	public void setCountPost(int countPost) {
		this.countPost = countPost;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", reg_date=" + reg_date
				+ ", id=" + id + ", countPost=" + countPost + "]";
	}
	


}
