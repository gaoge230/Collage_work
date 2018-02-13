package org.ssh.service;

import java.util.List;

import org.ssh.dao.BaseDao;

public class DataCodeManagerService <T>{

	private BaseDao dao;


	/**
	 * �����豸id����ѯ���豸�����е�������
	 * @param uname
	 * @param clazz
	 * @return
	 */
	public List<T> queryAllDataCode(int id) {
		String queryString = "SELECT d FROM datacode d where d.id="+id;
		return dao.getObjects(queryString);
	}
	/**
	 * �����豸��������
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Boolean addUser(T dataCode) throws Exception {
	
		dao.addObject(dataCode);
		return true;
	}
	/**
	 * �����豸id�Ͱ���presscode ����data������
	 * @param queryName
	 * @return
	 */
	public List<T> queryId(String id,String pressCode) {

		String queryString = "SELECT d FROM datacode d WHERE d.id ="
				+ id + "and d.presscode ="+pressCode;
		return dao.getObjects(queryString);
	}

	/**
	 * �޸�<br>
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void modifyUser(T dataCode) throws Exception {
		dao.updateObject(dataCode);
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
