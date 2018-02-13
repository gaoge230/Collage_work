package org.ssh.service;

import java.util.List;

import org.ssh.dao.BaseDao;

/**
 * 用户管理业务逻辑的实现，这个是业务逻辑层，业务逻辑在这里实现，这里操作数据库是调用上一层dao层代码。
 * 
 * @author gaoge
 * @Time 2017.2.21
 * @param <T>
 */
public class UserManagerService<T> {

	private BaseDao dao;

	/**
	 * 查询所有的的设备
	 * @param uname
	 * @param clazz
	 * @return
	 */
	public List<T> queryAllUsers() {
		String queryString = "SELECT u FROM Userinfo u ";
		return dao.getObjects(queryString);
	}
	/**
	 * 增加设备
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void addUser(T user) throws Exception {
	
		dao.addObject(user);
	}
	/**
	 * 根据name 查找usercode
	 * @param queryName
	 * @return
	 */
	public List<T> queryId(String queryName) {
		if (queryName == null || "".equals(queryName))
			return null;
		String queryString = "SELECT u FROM Userinfo u WHERE u.name like '"
				+ queryName + "%'";
		return dao.getObjects(queryString);
	}

	/**
	 * 修改<br>
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void modifyUser(T user) throws Exception {
		dao.updateObject(user);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param clazz
	 * @throws Exception
	 */
	public void deleteUser(int id, Class<T> clazz) throws Exception {
		T u = dao.getObject(clazz, id);
		dao.deleteObject(u);
	}

	
	
	
	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}
}
