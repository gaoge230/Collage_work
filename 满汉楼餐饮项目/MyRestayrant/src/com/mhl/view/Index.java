/*
 * 用户登录的闪屏效果
 */
package com.mhl.view;
import javax.swing.*;

import java.awt.*;

public class Index extends JWindow implements Runnable{
	
	//定义进度条需要的组件
	JProgressBar jpb;
	JLabel jl1;//用来显示背景
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	Index index=new Index();
    	//创建线程
    	Thread t=new Thread(index);
    	//启动线程
    	t.start();
	}
	
	public Index(){
		
		//创建组件
		jl1=new JLabel(new ImageIcon("image/sp.jpg"));
		
		
		jpb=new JProgressBar();
		//设置进度条属性
		//jpb.setStringPainted(true);//显示当前进度值信息
		//jpb.setIndeterminate(false);//确定进度条执行完成后不来滚动
		jpb.setBorderPainted(false); //设置进度条边框不显示
		jpb.setBackground(Color.LIGHT_GRAY);//设置进度条背景颜色
		jpb.setForeground(Color.GREEN);
		this.add(jl1,"Center");
		this.add(jpb,"South");
		this.setSize(400, 240);
		//确定jwindow的初始位置
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int hight=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, hight/2-150);
		this.setVisible(true);
		
		
	}

	//重写线程的RUN方法
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int []jpbvalues={0,1,5,8,14,17,26,35,38,43,49,56,65,71,75,78,86,94,98,99,100};
		for(int i=0;i<jpbvalues.length;i++){
			//计算闪屏效果结束后，跳转到登录界面
			try {
				//休眠闪屏加载的时间，大概是10s
				//休眠1秒
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jpb.setValue(jpbvalues[i]);
		}
			
			//跳转到登录
			System.out.println("闪屏结束，转到登录界面！");
			  //创建登录对象
			new UserLogin();
			this.dispose();
			//break;//退出线程
			
		
		System.out.println("退出线程");
	}

}


//开发一个闪屏类
class ShanPing extends JPanel{
	
}