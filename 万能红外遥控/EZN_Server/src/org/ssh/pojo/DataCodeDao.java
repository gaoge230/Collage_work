package org.ssh.pojo;

/**
 * 电器按键的数据码
 * 
 * @author gaoge
 * @Time 2017.2.21
 */
public class DataCodeDao {
	// 电器设备id
	private Integer id;
	//按键的编号
	private Integer pressCode;
	//数据码
	private String dataCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPressCode() {
		return pressCode;
	}
	public void setPressCode(Integer pressCode) {
		this.pressCode = pressCode;
	}
	public String getDataCode() {
		return dataCode;
	}
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
}
