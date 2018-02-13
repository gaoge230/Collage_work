package org.ssh.pojo;

public class ClientDao {
	private String clientName;
	private String clientPassword;
	private Integer clientAutority;
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	public Integer getClientAutority() {
		return clientAutority;
	}
	public void setClientAutority(Integer clientAutority) {
		this.clientAutority = clientAutority;
	}

}
