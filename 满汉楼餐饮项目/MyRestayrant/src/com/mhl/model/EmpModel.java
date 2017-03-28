package com.mhl.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.mhl.db.*;
public class EmpModel extends AbstractTableModel{

	Vector<String> colums;
	Vector<Vector> rows;
	
	//删除职员操作(根据职员编号)
	public boolean deletEmp(String id){
		boolean b=true;
		//初始化sql 及参数paras
		String sql="delete from clerkInfo where cleId=?";
		String []paras={id};
		//调用操作数据库的函数
		SqlHelper sh=new SqlHelper();
		try {
			b=sh.update(sql, paras);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			//关闭资源
			sh.close();
		}
		
		return b;
		
		
	}
	//查询需要显示的所要查询的信息 query不通用
	//因此做个修改，让它具有更好的通用性
	public void query(String select){
		//初始化
		colums=new Vector<String>();
//		colums.add("1");
//		colums.add("2");
//		colums.add("3");
//		colums.add("4");

		rows=new Vector<Vector>();
		ResultSet rs=null;
		SqlHelper sh=new SqlHelper();
		if(!select.equals("1")){
			String sql="select cleId,cleName,cleSex,cleZw,cleSex from clerkInfo where cleId=? or cleName=?";
			String paras[]={select,select};
			rs=sh.query(sql, paras);
		}else {
			String sql="select cleId,cleName,cleSex,cleZw,cleSex from clerkInfo where 1=?";
			String paras[]={"1"};
			rs=sh.query(sql, paras);
			
		}
		
		//从rs对象中可以得到一个resultsetmetadata 可以知道返回多少行和列
		
		
		try {
			
			ResultSetMetaData rsmt=rs.getMetaData();
			for(int i=0;i<rsmt.getColumnCount();i++){
				this.colums.add(rsmt.getColumnName(i+1));
			}
				
			//rs结果集加入rows中
			while(rs.next()){
				Vector read=new Vector();
				for(int i=0;i<rsmt.getColumnCount();i++){
					read.add(rs.getString(i+1));
				}
				rows.add(read);
		}
		
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			sh.close();
		}
		
		
	}
	
	//获得列名
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.colums.get(column).toString();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colums.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	//得到每个值
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return ((Vector)this.rows.get(arg0)).get(arg1);
	}

}
