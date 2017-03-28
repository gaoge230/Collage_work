/*
 * 这是一个图书表的模型
 */
package com.BookMangement.Model;

import javax.management.Query;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.BookManagement.sql.*;
public class BookModel extends AbstractTableModel{

	//定义 
	Vector<Vector> rowdata; //存放行数据
	Vector<String> columnNames;//存放每列的属性名
	
	SqlHelper sh;
	
	//构造函数
	public BookModel() {
		rowdata=new Vector<Vector>();
		columnNames=new Vector<String>();
		
		columnNames.add("编号");
		columnNames.add("书名");
		columnNames.add("作者");
		columnNames.add("出版社");
		columnNames.add("出版日期");
		columnNames.add("价格");
		columnNames.add("库存量");
		columnNames.add("存放位置");
		
		
	}
	//复选框查询
	public void Fuquery(String a,String paras[]){
		String sql=null;
		if(a.equals("书名")){
			sql="Select *from Book where Bname like ? ";
		}
		if(a.equals("作者")){
			sql="Select *from Book where Bauctor like ? ";
		}
		if(a.equals("编号")){
			sql="Select *from Book where Bid like ? ";
		}
		
		//创建
		ResultSet rs=null;
		rowdata=new Vector<Vector>();
		columnNames=new Vector<String>();
		columnNames.add("编号");
		columnNames.add("书名");
		columnNames.add("作者");
		columnNames.add("出版社");
		columnNames.add("出版日期");
		columnNames.add("价格");
		columnNames.add("库存量");
		columnNames.add("存放位置");
		sh=new SqlHelper();
		//查询
		rs=sh.query(sql, paras);
		try {
			while(rs.next()){
				//存放行数据
				Vector hang=new Vector();
				
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8));
			
				//加入到行中
				rowdata.add(hang);
			}
			//System.out.println(((Vector)rowdata.get(0)).get(2).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh.close();
	
	}
	
	public void queryBid(String paras[]){
		//创建
		ResultSet rs=null;
		rowdata=new Vector<Vector>();
		columnNames=new Vector<String>();
		columnNames.add("编号");
		columnNames.add("书名");
		columnNames.add("作者");
		columnNames.add("出版社");
		columnNames.add("出版日期");
		columnNames.add("价格");
		columnNames.add("库存量");
		columnNames.add("存放位置");
		//组织sql语句
		String sql="Select *from Book where Bid like ? ";

		sh=new SqlHelper();
		//查询
		rs=sh.query(sql, paras);
		try {
			while(rs.next()){
				//存放行数据
				Vector hang=new Vector();
				
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8));
			
				//加入到行中
				rowdata.add(hang);
			}
			//System.out.println(((Vector)rowdata.get(0)).get(2).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh.close();
	}
	
	//普通查询操作
	public void  query(String []paras){
			//创建
			ResultSet rs=null;
			rowdata=new Vector<Vector>();
			columnNames=new Vector<String>();
			columnNames.add("编号");
			columnNames.add("书名");
			columnNames.add("作者");
			columnNames.add("出版社");
			columnNames.add("出版日期");
			columnNames.add("价格");
			columnNames.add("库存量");
			columnNames.add("存放位置");
			//组织sql语句
			String sql="Select *from Book where Bname like ? and Bauctor like ? ";
			
			if(paras==null){
				
				sql="select *from Book ";
			}
			
			sh=new SqlHelper();
			//查询
			rs=sh.query(sql, paras);
			try {
				while(rs.next()){
					//存放行数据
					Vector hang=new Vector();
					
					hang.add(rs.getString(1));
					hang.add(rs.getString(2));
					hang.add(rs.getString(3));
					hang.add(rs.getString(4));
					hang.add(rs.getString(5));
					hang.add(rs.getString(6));
					hang.add(rs.getString(7));
					hang.add(rs.getString(8));
				
					//加入到行中
					rowdata.add(hang);
				}
				System.out.println(((Vector)rowdata.get(0)).get(2).toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sh.close();

	}
	//增加书操作
	public boolean add(String []paras){
		boolean b=true;
		//组织sql语句
		String sql="insert into Book values(?,?,?,?,?,?,?,?)";
		
		//调用SqlHelper的函数
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		sh.close();
		
		return b;
	}
	//图书的删除
	public boolean delete(String []paras){
		boolean b=true;
		String sql="delete from Book where Bid=?";
		
		//调用SqlHelper的函数
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		return b;	
	}
	
	//修改书的信息
	public boolean update(String []paras){
		boolean b=true;
		String sql="update Book set Bid=? , Bname=? ,Bauctor=?,Bpublish=?,BpuTime=?,Bcost=?,Bstock=?,Baddr=? where Bid=?";
		//String sql="update Book set Bname=? ,Bauthor=? where Bid=?";
		//调用SqlHelper的函数
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		return b;
		
	}
	
	
	
	//借完书后减少库存量
	public boolean reduceStock(String []paras){
		boolean b=true;
		String sql="update Book set Bstock=Bstock-1 where Bid=?";
		//String sql="update Book set Bname=? ,Bauthor=? where Bid=?";
		//调用SqlHelper的函数
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		return b;
		
	}
	
	//归还书后增加库存量
	public boolean addStock(String []paras){
		
		boolean b=true;
		String sql="update Book set Bstock=Bstock+1 where Bid=?";
		//调用SqlHelper的函数
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		return b;
		
	}
	
	
	
	//得到每列的名字。
	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return (String)columnNames.get(arg0);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowdata.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return ((Vector)(rowdata.get(arg0))).get(arg1);
	}

}
