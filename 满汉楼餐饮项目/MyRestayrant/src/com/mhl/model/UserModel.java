/*
 * 只是用户表数据模型，用它完成对用户的各种操作
 * 这里主要编写小牧需要的业务操作
 */
package com.mhl.model;
import java.sql.ResultSet;

import com.mhl.db.*;
public class UserModel {
	
	
	/**
	 * 
	 * @param sql  该用户的编号
	 * @param paras  该用户的密码
	 * @return   该用户的职位，若为空则该用户不存在
	 * 
	 */
	public String checkUser(String uId,String p){
		String zhiwei="";
		SqlHelper sh=new SqlHelper();
		try {
			//组织sql，和参数列表
			String sql="select cleZw from login,clerkInfo where login.cleId=clerkInfo.cleId and login.cleId=? and login.passwd=?";
			String paras[]={uId,p};
			ResultSet rs=sh.query(sql, paras);
			if(rs.next()){
				//则取出职位
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
