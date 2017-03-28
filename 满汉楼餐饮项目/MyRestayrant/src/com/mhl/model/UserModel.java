/*
 * ֻ���û�������ģ�ͣ�������ɶ��û��ĸ��ֲ���
 * ������Ҫ��дС����Ҫ��ҵ�����
 */
package com.mhl.model;
import java.sql.ResultSet;

import com.mhl.db.*;
public class UserModel {
	
	
	/**
	 * 
	 * @param sql  ���û��ı��
	 * @param paras  ���û�������
	 * @return   ���û���ְλ����Ϊ������û�������
	 * 
	 */
	public String checkUser(String uId,String p){
		String zhiwei="";
		SqlHelper sh=new SqlHelper();
		try {
			//��֯sql���Ͳ����б�
			String sql="select cleZw from login,clerkInfo where login.cleId=clerkInfo.cleId and login.cleId=? and login.passwd=?";
			String paras[]={uId,p};
			ResultSet rs=sh.query(sql, paras);
			if(rs.next()){
				//��ȡ��ְλ
				zhiwei=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			sh.close();
		}
		return  zhiwei;
	}

}
