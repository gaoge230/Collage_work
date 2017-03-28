/*
 * 借阅表的模型
 */
package com.BookMangement.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.management.Query;
import javax.swing.table.AbstractTableModel;

import com.BookManagement.sql.SqlHelper;

public class BorrowModel extends AbstractTableModel{

	//定义 
	Vector<Vector> rowdata; //存放行数据
	Vector<String> columnNames;//存放每列的属性名
	SqlHelper sh;
	
	public BorrowModel(){
		columnNames=new Vector<String>();
		columnNames.add("学号");
		columnNames.add("书编号");//另一张表中的
		columnNames.add("姓名");
		columnNames.add("书名"); //另一张表中的
		columnNames.add("借出时间");
		columnNames.add("期限");
		columnNames.add("借出数量");
		
		rowdata=new Vector<Vector>();
		
	}
	
	//借阅书籍 （相当于增加结余信息）
	//?????有一种情况就是 不同时期  同一人借同一本书
	public boolean add(String paras[]){
		boolean b=true;
		//插入结余表信息
		String sql="insert into Borrow values(?,?,?,?,?,?)";
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		sh.close();
		return b;	
	}
	
	//还书相当于往借阅信息里面加入还书时间, 同时book的库存量增加 
	public boolean upReturnTime(String paras[]){
		boolean b=true;
		String sql="update Borrow set Returntime = ? where Rid=? and Bid= ?";
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		sh.close();
		return b;
	}
	
	//借书情况的查询,还书时间为空、参数为 学号
	public void query(String paras[]){
		ResultSet rs=null;
		columnNames=new Vector<String>();
		columnNames.add("学号");
		columnNames.add("书编号");//另一张表的
		columnNames.add("姓名");
		columnNames.add("书名"); //另一张表中的
		columnNames.add("借出时间");
		columnNames.add("期限");
		columnNames.add("借出数量");
		
		rowdata=new Vector<Vector>();
		//此处应该查询没有归还的借阅情况     Returntime=null;
		String sql="select Borrow.Rid,Borrow.Bid,Book.Bname,Reader.Rname,Ftime,Dead,Num from Book,Borrow,Reader where Borrow.Bid=Book.Bid and Borrow.Rid=Reader.Rid and Returntime is NULL and Borrow.Rid=? ";
		
		sh=new SqlHelper();
		rs=sh.query(sql, paras);
		try {
			while(rs.next()){
				Vector hang=new Vector<>();
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
	
	//还书历史查询（所有借过的书）
	public void queryHistory(String paras[]){
		ResultSet rs=null;
		columnNames=new Vector<String>();
		columnNames.add("学号");
		columnNames.add("书编号");
		columnNames.add("姓名");
		columnNames.add("书名"); //另一张表中的
		columnNames.add("借出时间");
		columnNames.add("期限");
		columnNames.add("还书时间");
		columnNames.add("借出数量");
		
		rowdata=new Vector<Vector>();
		String  sql="select Borrow.Rid,Borrow.Bid,Book.Bname,Reader.Rname,Ftime,Dead,Returntime,Num from Book,Borrow,Reader "
				+ "where Borrow.Bid=Book.Bid and Borrow.Rid=Reader.Rid and Borrow.Rid=? ";
		
		sh=new SqlHelper();
		rs=sh.query(sql, paras);
		try {
			while(rs.next()){
				Vector hang=new Vector();
				hang.add(rs.getInt(1));
				hang.add(rs.getInt(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8));
				
				rowdata.add(hang);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh.close();
		
		
	}
	//延期功能（相当于增加期限）
	public boolean addDead(String paras[]){
		boolean b=true;
		String sql="update Borrow set Dead=Dead+15 where Rid= ? and Bid= ?";
		sh=new SqlHelper();
		sh.update(sql, paras);
		
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector)(rowdata.get(rowIndex))).get(columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.columnNames.get(column);
	}

}
