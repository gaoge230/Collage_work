/*
 * 对数据库操作的类
 * 对数据库的操作，就是crud
 */
package com.mhl.db;
import java.util.*;
import java.sql.*;
public class SqlHelper {

	//定义需要的对象
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=restaurant";
	String user="sa";
	String passwd="1307064134";
	
	//构造函数 ,初始化ct
	public SqlHelper(){
		try {
			//加载驱动
			Class.forName(driver);
			//得到连接
			ct=DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public ResultSet query(String sql,String []paras){
		try {
			ps=ct.prepareStatement(sql);
			//对sql参数赋值
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
	//增加一个对数据库增  删，该的操作
	public boolean update(String sql, String []paras){
		boolean b=true;
		
		try {
			ps=ct.prepareStatement(sql);
			//对sql参数赋值
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
	//关闭资源
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
