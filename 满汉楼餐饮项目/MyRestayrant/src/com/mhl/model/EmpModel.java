package com.mhl.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.mhl.db.*;
public class EmpModel extends AbstractTableModel{

	Vector<String> colums;
	Vector<Vector> rows;
	
	//ɾ��ְԱ����(����ְԱ���)
	public boolean deletEmp(String id){
		boolean b=true;
		//��ʼ��sql ������paras
		String sql="delete from clerkInfo where cleId=?";
		String []paras={id};
		//���ò������ݿ�ĺ���
		SqlHelper sh=new SqlHelper();
		try {
			b=sh.update(sql, paras);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			//�ر���Դ
			sh.close();
		}
		
		return b;
		
		
	}
	//��ѯ��Ҫ��ʾ����Ҫ��ѯ����Ϣ query��ͨ��
	//��������޸ģ��������и��õ�ͨ����
	public void query(String select){
		//��ʼ��
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
		
		//��rs�����п��Եõ�һ��resultsetmetadata ����֪�����ض����к���
		
		
		try {
			
			ResultSetMetaData rsmt=rs.getMetaData();
			for(int i=0;i<rsmt.getColumnCount();i++){
				this.colums.add(rsmt.getColumnName(i+1));
			}
				
			//rs���������rows��
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
	
	//�������
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

	//�õ�ÿ��ֵ
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return ((Vector)this.rows.get(arg0)).get(arg1);
	}

}
