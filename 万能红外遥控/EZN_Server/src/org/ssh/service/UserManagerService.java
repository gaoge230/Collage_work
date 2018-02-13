package org.ssh.service;

import java.util.List;

import org.ssh.dao.BaseDao;

/**
 * �û�����ҵ���߼���ʵ�֣������ҵ���߼��㣬ҵ���߼�������ʵ�֣�����������ݿ��ǵ�����һ��dao����롣
 * 
 * @author gaoge
 * @Time 2017.2.21
 * @param <T>
 */
public class UserManagerService<T> {

	private BaseDao dao;

	/**
	 * ��ѯ���еĵ��豸
	 * @param uname
	 * @param clazz
	 * @return
	 */
	public List<T> queryAllUsers() {
		String queryString = "SELECT u FROM Userinfo u ";
		return dao.getObjects(queryString);
	}
	/**
	 * �����豸
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void addUser(T user) throws Exception {
	
		dao.addObject(user);
	}
	/**
	 * ����name ����usercode
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
	 * �޸�<br>
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void modifyUser(T user) throws Exception {
		dao.updateObject(user);
	}

	/**
	 * ɾ��
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
