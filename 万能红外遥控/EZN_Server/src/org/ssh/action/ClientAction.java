package org.ssh.action;

import org.ssh.pojo.ClientDao;
import org.ssh.service.ClientManagerService;

import com.opensymphony.xwork2.ActionContext;

public class ClientAction {
	private ClientDao client;
	private ClientManagerService<ClientDao> clientManagerService;

	public String doLogin() {
		System.out.println("login");
		System.out.println(client.getClientName());
		System.out.println(client.getClientPassword());
		if (this.client.getClientName() == null
				|| this.client.getClientPassword() == null)
			return "INPUT";
		ClientDao user;
		try {
			user = clientManagerService.doLogin(this.client.getClientName(),
					this.client.getClientPassword());

			if (user != null) {
				// ActionContext.getContext().getSession().put("client",
				// client);
				return "SUCCESS";
			} else
				return "INPUT";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		

	}

	public ClientDao getClient() {
		return client;
	}

	public ClientManagerService<ClientDao> getClientManagerService() {
		return clientManagerService;
	}

	public void setClientManagerService(
			ClientManagerService<ClientDao> clientManagerService) {
		this.clientManagerService = clientManagerService;
	}

	public void setClient(ClientDao client) {
		this.client = client;
	}



}
