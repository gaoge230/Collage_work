package org.ssh.service;

import java.util.List;

import org.ssh.dao.BaseDao;

public class ClientManagerService <T>{
	private BaseDao dao;
	/**
	 * 客户登录
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public  T doLogin(String name, String password) throws Exception {
		if (name == null || password == null)
			return null;
		System.out.println("AAAA");
		String queryString = "SELECT c FROM ClientDao c WHERE c.clientName like '"
				+ name + "' AND c.clientPassword like '" + password+"'";
		List<T> users = dao.getObjects(queryString);
		System.out.println(users.get(0));
		return users.get(0);
	}
	/**
	 * 查询所有client
	 * @param clazz
	 * @return
	 */
	public List<T> queryAll(Class<T> clazz){
		List<T> allObjects = dao.getAllObjects(clazz);
		return allObjects;
	}
	
	
	
	/**
	 * 添加客户
	 * @param client
	 */
	public void addClient(T client){
		dao.addObject(client);
	}
	/**
	 * 删除客户
	 * @param id
	 */
	public void deleteClient(int id,Class<T> clazz){
		//先获取指定id的对象，然后删除；
		T client = dao.getObject(clazz, id);
		dao.deleteObject(client);
	}
	/**
	 * 修改客户
	 * @param client
	 */
	public void modifyClient(T client){
		dao.updateObject(client);
	}
	
	
	
	
	
	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}
	
}
