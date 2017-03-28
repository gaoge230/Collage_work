package com.BookMangement.Model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.BookManagement.sql.*;
public class UserModel {

	SqlHelper sh;
	
	
	//������ʾ
	public String checkDead(String paras[],String identity){
		
		int deadbook=0;
		String tishi=null;
		ResultSet rs=null;
		if(identity.equals("����Ա")){
			tishi="��ӭ����Ա���뱾ͼ�����ϵͳ����";
			return tishi;
		}
		//Rid =��ͬʱ returntime Ϊ�յ�
		String sql="select Ftime,Dead from Borrow where Rid= ? and Returntime is NULL";
		
		sh=new SqlHelper();
		rs=sh.query(sql, paras);
		try {
			while(rs.next()){
				//String time=rs.getDate(1);
				String nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getDate(1));
				int dead=rs.getInt(2);
				System.out.println(nowtime+"����Ϊ��"+dead);
				
						//�����������������
				 		Date date1=rs.getDate(1);
				 		Date date2=new Date();
				        Calendar d1 = new GregorianCalendar();  
				        d1.setTime(date1);  
				        Calendar d2 = new GregorianCalendar();  
				        d2.setTime(date2);  
				        //Calendar.DAY_OF_YEAR     get �� set ���ֶ����֣�ָʾ��ǰ���е�������
				        int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);  
				        System.out.println("days="+days); 
				        //ָʾ��� get �� set ���ֶ����֡�
				        int y2 = d2.get(Calendar.YEAR);  
				        if (d1.get(Calendar.YEAR) != y2) {  
//				          d1 = (Calendar) d1.clone();  
				            do { 
				            //���date1��date2����һ��ͼ�365��
				                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);  
				                d1.add(Calendar.YEAR, 1);  
				            } while (d1.get(Calendar.YEAR) != y2);  
				        } 
				        System.out.println("days="+days);  
				        
				        //�ж��Ƿ��������
				        if(days>dead){
				        	deadbook++;
				        }
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh.close();
		if(deadbook>0){
			tishi="��ʾ�����ã�����"+deadbook+"�������鼮�������ޣ��뼰ʱ�黹�鼮���������𲻱㣡������";
		}else{
			tishi="��ʾ�����ã���û�н����鼮���ڡ�";
		}
		System.out.println(tishi);
		return tishi;
	}
	
	//�û���¼�ж���ʲô���
	public String login(String id,String passwd){
		ResultSet rs=null;
		sh=new SqlHelper();
		String identity="";
		//��֯����
		String paras[]={id,passwd};
		
		//��֯sql���
		String sql1="select * from Reader where Rid=? and Rpass=?";
		String sql2="select * from Administrator where Mid=? and Mpass=?";
		try {
			
			if((rs=sh.query(sql1, paras)).next()){
				
				identity="����";
			}
				
			if((rs=sh.query(sql2, paras)).next()){
				
				identity="����Ա";
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh.close();
	
		return identity;
						
	}
	
	//�û��޸�����
	public boolean uppass(String paras[],String identity){
		boolean b=true;
		String sql="";
		if(identity.equals("����")){
			sql="update Reader set Rpass= ? where Rpass=? and Rid=? ";
		}
		if(identity.equals("����Ա")){
			sql="update Administrator set Bpass= ? where Bpass=? and Bid=? ";
		}
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		sh.close();
		
		return b;
		
	}
	
	//���㻹��ʱ  //����ɹ�����ʾ�Ƿ��ڣ�����ʾ���ڶ�����
	public int DeadTime(String  Ftime,int dead){
		int day=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		//�����������������
 		Date date1=null;
		try {
			date1 = sdf.parse(Ftime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		Date date2=new Date();//��ǰʱ��
        Calendar d1 = new GregorianCalendar();  
        d1.setTime(date1);  
        Calendar d2 = new GregorianCalendar();  
        d2.setTime(date2);  
        //Calendar.DAY_OF_YEAR     get �� set ���ֶ����֣�ָʾ��ǰ���е�������
        int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);  
        System.out.println("days="+days); 
        //ָʾ��� get �� set ���ֶ����֡�
        int y2 = d2.get(Calendar.YEAR);  
        if (d1.get(Calendar.YEAR) != y2) {  
//          d1 = (Calendar) d1.clone();  
            do { 
            //���date1��date2����һ��ͼ�365��
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);  
                d1.add(Calendar.YEAR, 1);  
            } while (d1.get(Calendar.YEAR) != y2);  
        } 
        System.out.println("days="+days);  
        
        //�ж��Ƿ��������
        if(days>dead){
        	day=days-dead;
        	return day;
        }else{
        	return day;
        }
		
		
		
	}
}
