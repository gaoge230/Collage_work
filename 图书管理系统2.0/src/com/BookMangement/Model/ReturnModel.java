package com.BookMangement.Model;

import java.util.Vector;

import javax.management.Query;
import javax.swing.table.AbstractTableModel;

import com.BookManagement.sql.SqlHelper;

public class ReturnModel extends AbstractTableModel{

	//���� 
	Vector<Vector> rowdata; //���������
	Vector<String> columnNames;//���ÿ�е�������
	SqlHelper sh;
	//��ѯ
	public void query(){
		
	}
	
	//�����ǲ��� ������Ϣ
	public boolean add(String paras[]){
		boolean b=true;
		String sql="insert into Return values (?,?,?,?,?,?)";
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
