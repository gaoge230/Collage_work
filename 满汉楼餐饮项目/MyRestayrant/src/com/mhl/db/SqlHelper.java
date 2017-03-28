/*
 * �����ݿ��������
 * �����ݿ�Ĳ���������crud
 */
package com.mhl.db;
import java.util.*;
import java.sql.*;
public class SqlHelper {

	//������Ҫ�Ķ���
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=restaurant";
	String user="sa";
	String passwd="1307064134";
	
	//���캯�� ,��ʼ��ct
	public SqlHelper(){
		try {
			//��������
			Class.forName(driver);
			//�õ�����
			ct=DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public ResultSet query(String sql,String []paras){
		try {
			ps=ct.prepareStatement(sql);
			//��sql������ֵ
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	//����һ�������ݿ���  ɾ���õĲ���
	public boolean update(String sql, String []paras){
		boolean b=true;
		
		try {
			ps=ct.prepareStatement(sql);
			//��sql������ֵ
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			
			ps.executeUpdate();
		} catch (Exception e) {
			b=false;
			// TODO: handle exception
			e.printStackTrace();
		}
		return b;
		
	}
	//�ر���Դ
	public void close(){
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(!ct.isClosed()) ct.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
