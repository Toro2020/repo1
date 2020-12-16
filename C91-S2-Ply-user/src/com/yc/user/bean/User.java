package com.yc.user.bean;

import java.sql.Timestamp;

public class User implements java.io.Serializable{

	/**
	 * 序列化版本编号
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String phone;
	private String email;
	private String pwd;
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", pwd=" + pwd
				+ ", head=" + head + ", createTime=" + createTime + "]";
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	private String head;
	private Timestamp createTime;
	
}
