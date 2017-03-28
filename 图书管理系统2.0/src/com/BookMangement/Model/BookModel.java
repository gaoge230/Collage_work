/*
 * ����һ��ͼ����ģ��
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

	//���� 
	Vector<Vector> rowdata; //���������
	Vector<String> columnNames;//���ÿ�е�������
	
	SqlHelper sh;
	
	//���캯��
	public BookModel() {
		rowdata=new Vector<Vector>();
		columnNames=new Vector<String>();
		
		columnNames.add("���");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("������");
		columnNames.add("��������");
		columnNames.add("�۸�");
		columnNames.add("�����");
		columnNames.add("���λ��");
		
		
	}
	//��ѡ���ѯ
	public void Fuquery(String a,String paras[]){
		String sql=null;
		if(a.equals("����")){
			sql="Select *from Book where Bname like ? ";
		}
		if(a.equals("����")){
			sql="Select *from Book where Bauctor like ? ";
		}
		if(a.equals("���")){
			sql="Select *from Book where Bid like ? ";
		}
		
		//����
		ResultSet rs=null;
		rowdata=new Vector<Vector>();
		columnNames=new Vector<String>();
		columnNames.add("���");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("������");
		columnNames.add("��������");
		columnNames.add("�۸�");
		columnNames.add("�����");
		columnNames.add("���λ��");
		sh=new SqlHelper();
		//��ѯ
		rs=sh.query(sql, paras);
		try {
			while(rs.next()){
				//���������
				Vector hang=new Vector();
				
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8));
			
				//���뵽����
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
		//����
		ResultSet rs=null;
		rowdata=new Vector<Vector>();
		columnNames=new Vector<String>();
		columnNames.add("���");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("������");
		columnNames.add("��������");
		columnNames.add("�۸�");
		columnNames.add("�����");
		columnNames.add("���λ��");
		//��֯sql���
		String sql="Select *from Book where Bid like ? ";

		sh=new SqlHelper();
		//��ѯ
		rs=sh.query(sql, paras);
		try {
			while(rs.next()){
				//���������
				Vector hang=new Vector();
				
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8));
			
				//���뵽����
				rowdata.add(hang);
			}
			//System.out.println(((Vector)rowdata.get(0)).get(2).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh.close();
	}
	
	//��ͨ��ѯ����
	public void  query(String []paras){
			//����
			ResultSet rs=null;
			rowdata=new Vector<Vector>();
			columnNames=new Vector<String>();
			columnNames.add("���");
			columnNames.add("����");
			columnNames.add("����");
			columnNames.add("������");
			columnNames.add("��������");
			columnNames.add("�۸�");
			columnNames.add("�����");
			columnNames.add("���λ��");
			//��֯sql���
			String sql="Select *from Book where Bname like ? and Bauctor like ? ";
			
			if(paras==null){
				
				sql="select *from Book ";
			}
			
			sh=new SqlHelper();
			//��ѯ
			rs=sh.query(sql, paras);
			try {
				while(rs.next()){
					//���������
					Vector hang=new Vector();
					
					hang.add(rs.getString(1));
					hang.add(rs.getString(2));
					hang.add(rs.getString(3));
					hang.add(rs.getString(4));
					hang.add(rs.getString(5));
					hang.add(rs.getString(6));
					hang.add(rs.getString(7));
					hang.add(rs.getString(8));
				
					//���뵽����
					rowdata.add(hang);
				}
				System.out.println(((Vector)rowdata.get(0)).get(2).toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sh.close();

	}
	//���������
	public boolean add(String []paras){
		boolean b=true;
		//��֯sql���
		String sql="insert into Book values(?,?,?,?,?,?,?,?)";
		
		//����SqlHelper�ĺ���
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		sh.close();
		
		return b;
	}
	//ͼ���ɾ��
	public boolean delete(String []paras){
		boolean b=true;
		String sql="delete from Book where Bid=?";
		
		//����SqlHelper�ĺ���
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		return b;	
	}
	
	//�޸������Ϣ
	public boolean update(String []paras){
		boolean b=true;
		String sql="update Book set Bid=? , Bname=? ,Bauctor=?,Bpublish=?,BpuTime=?,Bcost=?,Bstock=?,Baddr=? where Bid=?";
		//String sql="update Book set Bname=? ,Bauthor=? where Bid=?";
		//����SqlHelper�ĺ���
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		return b;
		
	}
	
	
	
	//���������ٿ����
	public boolean reduceStock(String []paras){
		boolean b=true;
		String sql="update Book set Bstock=Bstock-1 where Bid=?";
		//String sql="update Book set Bname=? ,Bauthor=? where Bid=?";
		//����SqlHelper�ĺ���
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		return b;
		
	}
	
	//�黹������ӿ����
	public boolean addStock(String []paras){
		
		boolean b=true;
		String sql="update Book set Bstock=Bstock+1 where Bid=?";
		//����SqlHelper�ĺ���
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		return b;
		
	}
	
	
	
	//�õ�ÿ�е����֡�
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
