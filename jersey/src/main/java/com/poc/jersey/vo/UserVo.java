package com.poc.jersey.vo;

import java.io.Serializable;

public class UserVo implements Serializable {

	private static final long serialVersionUID = -1568224787481869048L;

	private int userId;

	private String name;

	private String pwd;

	private String email;

	private String mobile;

	private String lstUpdtTs;

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the lstUpdtTs
	 */
	public String getLstUpdtTs() {
		return lstUpdtTs;
	}

	/**
	 * @param lstUpdtTs
	 *            the lstUpdtTs to set
	 */
	public void setLstUpdtTs(String lstUpdtTs) {
		this.lstUpdtTs = lstUpdtTs;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 *            the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVo [userId=").append(userId).append(", name=").append(name).append(", pwd=").append(pwd)
				.append(", email=").append(email).append(", mobile=").append(mobile).append(", lstUpdtTs=")
				.append(lstUpdtTs).append("]");
		return builder.toString();
	}
}
