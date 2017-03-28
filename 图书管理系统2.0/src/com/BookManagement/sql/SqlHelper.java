/*
 * �����ݿ���в�������
 */
package com.BookManagement.sql;
import java.sql.*;
import java.util.Collection;


public class SqlHelper {
	
	Connection ct=null;
	PreparedStatement ps=null;
	ResultSet rs=null;//�����
	String  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	String  url="jdbc:sqlserver://127.0.0.1:1433;databaseName=TSGLXT";//��ûд�������ݿ�
	String  user="sa";
	String  passwd="1307064134";
	//���캯��
	public SqlHelper(){
		try {
			//1.��������
			Class.forName(driver);
			//2.�õ�����
			ct=DriverManager.getConnection(url,user,passwd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�����ݿ��б���� ɾ �� ����
	public boolean update(String sql,String []paras){
		boolean b=true;
		try {
			//����Ԥ����
			ps=ct.prepareStatement(sql);
			
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			//ps.executeUpdate() ִ�� ������Ӱ������
			if(ps.executeUpdate()<1){
				b=false;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b=false;
		}
		return b;
	}
	
	//�����ݿ��б��    ��ѯ  ����
	public ResultSet query(String sql,String []paras){
		try {
			
			
			//����Ԥ����
			ps=ct.prepareStatement(sql);
			//��sql�е�  ��  ��ֵ
			if(paras!=null){
				for(int i=0;i<paras.length;i++){
					
				ps.setString(i+1, paras[i]);
				}
			}
			
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	//�ر���Դ
	public void close(){
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
