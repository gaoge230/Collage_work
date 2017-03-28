/**
 * 用户信息类
 */
package com.qq.common;
//序列化，对象在网路中传输
public class User implements java.io.Serializable{
	
	private String userId;
	private String passwd;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	

}
