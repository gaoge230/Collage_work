/*
 * 这是一个读者表的模型
 */
package com.BookMangement.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.BookManagement.sql.SqlHelper;
public class ReadModel extends AbstractTableModel{

	//定义 
	Vector<Vector> rowdata; //存放行数据
	Vector<String> columnNames;//存放每列的属性名
	SqlHelper sh;
	public ReadModel(){
		columnNames=new Vector<String>();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("年龄");
		columnNames.add("性别");
		columnNames.add("专业");
		columnNames.add("地址");
		columnNames.add("联系方式");
		//columnNames.add("密码");

		
		rowdata=new Vector<Vector>();
		
	}
	//增加 读者的操作
	public boolean add(String paras[]){
		boolean b=true;
		String sql="insert into Reader values (?,?,?,?,?,?,?,?)";
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		sh.close();//关闭资源
		return b;
	}
	
	//查询操作
	public void query(String []paras){
		ResultSet rs=null;
		columnNames=new Vector<String>();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("年龄");
		columnNames.add("性别");
		columnNames.add("专业");
		columnNames.add("地址");
		columnNames.add("联系方式");
		//columnNames.add("密码");

		
		rowdata=new Vector<Vector>();
		String sql="select Rid,Rname,Rage,Rsex,Rpro,Raddr,Rcall from Reader where Rid= ?";
		
		if(paras==null){
			
			sql="select *from Reader where 1=1";
		}
		sh=new SqlHelper();
		rs=sh.query(sql, paras);
		try {
			while(rs.next()){
				Vector hang=new Vector();
				
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				
				rowdata.add(hang);
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh.close();
				
	}
	//删除读者
	public boolean delete(String []paras){
		boolean b=true;
		String sql="delete from Reader where Rid= ?";
		
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		sh.close();
		return b;
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

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames.get(column);
	}

}
