package org.ssh.pojo;

/**
 * �����豸��������
 * @author gaoge
 * @Time 2017.2.21
 */
public class Userinfo implements java.io.Serializable {
	//�����豸id
	private Integer id;
	//�����豸����
	private String name;
	private String userCode;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getId() {
		return id;
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

}