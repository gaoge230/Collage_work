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
	
	
	//超期提示
	public String checkDead(String paras[],String identity){
		
		int deadbook=0;
		String tishi=null;
		ResultSet rs=null;
		if(identity.equals("管理员")){
			tishi="欢迎管理员进入本图书管理系统！！";
			return tishi;
		}
		//Rid =？同时 returntime 为空的
		String sql="select Ftime,Dead from Borrow where Rid= ? and Returntime is NULL";
		
		sh=new SqlHelper();
		rs=sh.query(sql, paras);
		try {
			while(rs.next()){
				//String time=rs.getDate(1);
				String nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getDate(1));
				int dead=rs.getInt(2);
				System.out.println(nowtime+"期限为："+dead);
				
						//计算两日期相差天数
				 		Date date1=rs.getDate(1);
				 		Date date2=new Date();
				        Calendar d1 = new GregorianCalendar();  
				        d1.setTime(date1);  
				        Calendar d2 = new GregorianCalendar();  
				        d2.setTime(date2);  
				        //Calendar.DAY_OF_YEAR     get 和 set 的字段数字，指示当前年中的天数。
				        int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);  
				        System.out.println("days="+days); 
				        //指示年的 get 和 set 的字段数字。
				        int y2 = d2.get(Calendar.YEAR);  
				        if (d1.get(Calendar.YEAR) != y2) {  
//				          d1 = (Calendar) d1.clone();  
				            do { 
				            //如果date1与date2不在一年就加365天
				                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);  
				                d1.add(Calendar.YEAR, 1);  
				            } while (d1.get(Calendar.YEAR) != y2);  
				        } 
				        System.out.println("days="+days);  
				        
				        //判断是否大于期限
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
			tishi="提示：您好，您有"+deadbook+"本借阅书籍超过期限，请及时归还书籍，以免引起不便！！！！";
		}else{
			tishi="提示：您好，您没有借阅书籍超期。";
		}
		System.out.println(tishi);
		return tishi;
	}
	
	//用户登录判断是什么身份
	public String login(String id,String passwd){
		ResultSet rs=null;
		sh=new SqlHelper();
		String identity="";
		//组织参数
		String paras[]={id,passwd};
		
		//组织sql语句
		String sql1="select * from Reader where Rid=? and Rpass=?";
		String sql2="select * from Administrator where Mid=? and Mpass=?";
		try {
			
			if((rs=sh.query(sql1, paras)).next()){
				
				identity="读者";
			}
				
			if((rs=sh.query(sql2, paras)).next()){
				
				identity="管理员";
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh.close();
	
		return identity;
						
	}
	
	//用户修改密码
	public boolean uppass(String paras[],String identity){
		boolean b=true;
		String sql="";
		if(identity.equals("读者")){
			sql="update Reader set Rpass= ? where Rpass=? and Rid=? ";
		}
		if(identity.equals("管理员")){
			sql="update Administrator set Bpass= ? where Bpass=? and Bid=? ";
		}
		sh=new SqlHelper();
		b=sh.update(sql, paras);
		
		sh.close();
		
		return b;
		
	}
	
	//计算还书时  //还书成功并显示是否超期，并提示超期多少天
	public int DeadTime(String  Ftime,int dead){
		int day=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		//计算两日期相差天数
 		Date date1=null;
		try {
			date1 = sdf.parse(Ftime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		Date date2=new Date();//当前时间
        Calendar d1 = new GregorianCalendar();  
        d1.setTime(date1);  
        Calendar d2 = new GregorianCalendar();  
        d2.setTime(date2);  
        //Calendar.DAY_OF_YEAR     get 和 set 的字段数字，指示当前年中的天数。
        int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);  
        System.out.println("days="+days); 
        //指示年的 get 和 set 的字段数字。
        int y2 = d2.get(Calendar.YEAR);  
        if (d1.get(Calendar.YEAR) != y2) {  
//          d1 = (Calendar) d1.clone();  
            do { 
            //如果date1与date2不在一年就加365天
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);  
                d1.add(Calendar.YEAR, 1);  
            } while (d1.get(Calendar.YEAR) != y2);  
        } 
        System.out.println("days="+days);  
        
        //判断是否大于期限
        if(days>dead){
        	day=days-dead;
        	return day;
        }else{
        	return day;
        }
		
		
		
	}
}
