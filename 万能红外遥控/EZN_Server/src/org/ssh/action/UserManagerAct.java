package org.ssh.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.ssh.pojo.Userinfo;
import org.ssh.service.UserManagerService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 这是一个action，充当视图控制器的角色，也就是mvc中的c。直接与外部打交道，可以供客户端调用，也可以和jsp等调用，
 * 这一层通过调用业务逻辑层来完成功能。不直接操作数据库，业务逻辑也不在这里实现。一些界面控制可以在这里实现，
 * 
 * @author gaoge
 * @Time 2017.2.21
 * 
 */
public class UserManagerAct extends ActionSupport {
	private Userinfo user = new Userinfo();
	private UserManagerService<Userinfo> userManagerService;
	private List<Userinfo> users;
	private String searchName;
	// json
	private Map<String, Object> jdata;

	/*
	 * public String doLogin(){ if(this.user.getUname() == null ||
	 * this.user.getPassword() == null) return INPUT; try { Userinfo user =
	 * userService.doLogin(this.user.getUname(), this.user.getPassword());
	 * if(user != null){ ActionContext.getContext().getSession().put("userinfo",
	 * user); return doQuery(); }else return INPUT; } catch (Exception e) {
	 * return ERROR; } }
	 */
	/*
	 * public String doQuery(){ searchText = getParam("queryText"); users =
	 * userService.queryUsers(searchText,Userinfo.class); return SUCCESS; }
	 */
	public String doQueryIdAndUserCode() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String searchName = request.getParameter("queryNameJson");

		System.out.println("A   " + searchName);
		users = userManagerService.queryId(searchName);
		Iterator<Userinfo> it = users.iterator();
		Userinfo next = null;
		if (it.hasNext()) {
			next = it.next();
			System.out.println("query id：" + next.getId());
			System.out.println("query userCode：" + next.getUserCode());

		}
		jdata = new HashMap<String, Object>();
		jdata.put("queryUserCodeJson", next.getUserCode());
		return SUCCESS;
	}

	public String doAddDevice() {
		return null;
	}

	public String doDeleteDevice() {
		return null;
	}

	public String doQueryDevice() {
		return null;
	}

	public String doAddUser() {
		String result = "";
		try {
			String param = getParam("param");
			if (Integer.parseInt(param) > 0) {
				user.setId(0);
				userManagerService.addUser(user);
				result = "success";
			} else
				result = "addUser";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// public String doEdit(){
	// try {
	// Integer param = Integer.parseInt(getParam("param"));
	// if(param == 0){
	// Integer id = Integer.parseInt(getParam("id"));
	// user = userService.getUser(Userinfo.class, id);
	// return "editUser";
	// }else if(param == 1){
	// userService.modifyUser(user);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return doQuery();
	// }

	/*
	 * public String doDelete(){ try { Integer param =
	 * Integer.parseInt(getParam("id"));
	 * userService.deleteUser(param,Userinfo.class); } catch (Exception e) {
	 * e.printStackTrace(); } return doQuery(); }
	 */

	protected String getParam(String key) {
		return ServletActionContext.getRequest().getParameter(key);
	}

	public UserManagerService<Userinfo> getUserService() {
		return userManagerService;
	}

	public void setUserService(UserManagerService<Userinfo> userService) {
		this.userManagerService = userService;
	}

	public List<Userinfo> getUsers() {
		return users;
	}

	public Userinfo getUser() {
		return user;
	}

	public void setUser(Userinfo user) {
		this.user = user;
	}

	public Map<String, Object> getJdata() {
		return jdata;
	}

	public void setJdata(Map<String, Object> jdata) {
		this.jdata = jdata;
	}

	public List<Userinfo> getId() {
		return users;
	}

	public void setId(List<Userinfo> id) {
		this.users = id;
	}

	public String getSearchText() {
		return searchName;
	}

	public void setSearchText(String searchText) {
		this.searchName = searchText;
	}
}
