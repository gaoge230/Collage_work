/*
 * 对数据库进行操作的类
 */
package com.BookManagement.sql;
import java.sql.*;
import java.util.Collection;


public class SqlHelper {
	
	Connection ct=null;
	PreparedStatement ps=null;
	ResultSet rs=null;//结果集
	String  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	String  url="jdbc:sqlserver://127.0.0.1:1433;databaseName=TSGLXT";//还没写具体数据库
	String  user="sa";
	String  passwd="1307064134";
	//构造函数
	public SqlHelper(){
		try {
			//1.加载驱动
			Class.forName(driver);
			//2.得到连接
			ct=DriverManager.getConnection(url,user,passwd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//对数据库中表的增 删 改 操作
	public boolean update(String sql,String []paras){
		boolean b=true;
		try {
			//创建预编译
			ps=ct.prepareStatement(sql);
			
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			//ps.executeUpdate() 执行 返回受影响行数
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
	
	//对数据库中表的    查询  操作
	public ResultSet query(String sql,String []paras){
		try {
			
			
			//创建预编译
			ps=ct.prepareStatement(sql);
			//给sql中的  ？  赋值
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
	
	
	//关闭资源
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
