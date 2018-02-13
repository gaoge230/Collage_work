package org.ssh.service;

import java.util.List;

import org.ssh.dao.BaseDao;

public class DataCodeManagerService <T>{

	private BaseDao dao;


	/**
	 * 根据设备id来查询该设备的所有的数据码
	 * @param uname
	 * @param clazz
	 * @return
	 */
	public List<T> queryAllDataCode(int id) {
		String queryString = "SELECT d FROM datacode d where d.id="+id;
		return dao.getObjects(queryString);
	}
	/**
	 * 增加设备的数据码
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Boolean addUser(T dataCode) throws Exception {
	
		dao.addObject(dataCode);
		return true;
	}
	/**
	 * 根据设备id和按键presscode 查找data数据码
	 * @param queryName
	 * @return
	 */
	public List<T> queryId(String id,String pressCode) {

		String queryString = "SELECT d FROM datacode d WHERE d.id ="
				+ id + "and d.presscode ="+pressCode;
		return dao.getObjects(queryString);
	}

	/**
	 * 修改<br>
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void modifyUser(T dataCode) throws Exception {
		dao.updateObject(dataCode);
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
