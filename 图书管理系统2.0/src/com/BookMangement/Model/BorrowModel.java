/*
 * ���ı��ģ��
 */
package com.BookMangement.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.management.Query;
import javax.swing.table.AbstractTableModel;

import com.BookManagement.sql.SqlHelper;

public class BorrowModel extends AbstractTableModel{

	//���� 
	Vector<Vector> rowdata; //���������
	Vector<String> columnNames;//���ÿ�е�������
	SqlHelper sh;
	
	public BorrowModel(){
		columnNames=new Vector<String>();
		columnNames.add("ѧ��");
		columnNames.add("����");//��һ�ű��е�
		columnNames.add("����");
		columnNames.add("����"); //��һ�ű��е�
		columnNames.add("���ʱ��");
		columnNames.add("����");
		columnNames.add("�������");
		
		rowdata=new Vector<Vector>();
		
	}
	
	//�����鼮 ���൱�����ӽ�����Ϣ��
	//?????��һ��������� ��ͬʱ��  ͬһ�˽�ͬһ����
	public boolean add(String paras[]){
		boolean b=true;
		//����������Ϣ
		String sql="insert into Borrow values(?,?,?,?,?,?)";
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		sh.close();
		return b;	
	}
	
	//�����൱����������Ϣ������뻹��ʱ��, ͬʱbook�Ŀ�������� 
	public boolean upReturnTime(String paras[]){
		boolean b=true;
		String sql="update Borrow set Returntime = ? where Rid=? and Bid= ?";
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		sh.close();
		return b;
	}
	
	//��������Ĳ�ѯ,����ʱ��Ϊ�ա�����Ϊ ѧ��
	public void query(String paras[]){
		ResultSet rs=null;
		columnNames=new Vector<String>();
		columnNames.add("ѧ��");
		columnNames.add("����");//��һ�ű��
		columnNames.add("����");
		columnNames.add("����"); //��һ�ű��е�
		columnNames.add("���ʱ��");
		columnNames.add("����");
		columnNames.add("�������");
		
		rowdata=new Vector<Vector>();
		//�˴�Ӧ�ò�ѯû�й黹�Ľ������     Returntime=null;
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
	
	//������ʷ��ѯ�����н�����飩
	public void queryHistory(String paras[]){
		ResultSet rs=null;
		columnNames=new Vector<String>();
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("����"); //��һ�ű��е�
		columnNames.add("���ʱ��");
		columnNames.add("����");
		columnNames.add("����ʱ��");
		columnNames.add("�������");
		
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
	//���ڹ��ܣ��൱���������ޣ�
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
