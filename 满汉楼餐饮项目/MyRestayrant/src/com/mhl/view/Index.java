/*
 * �û���¼������Ч��
 */
package com.mhl.view;
import javax.swing.*;

import java.awt.*;

public class Index extends JWindow implements Runnable{
	
	//�����������Ҫ�����
	JProgressBar jpb;
	JLabel jl1;//������ʾ����
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	Index index=new Index();
    	//�����߳�
    	Thread t=new Thread(index);
    	//�����߳�
    	t.start();
	}
	
	public Index(){
		
		//�������
		jl1=new JLabel(new ImageIcon("image/sp.jpg"));
		
		
		jpb=new JProgressBar();
		//���ý���������
		//jpb.setStringPainted(true);//��ʾ��ǰ����ֵ��Ϣ
		//jpb.setIndeterminate(false);//ȷ��������ִ����ɺ�������
		jpb.setBorderPainted(false); //���ý������߿���ʾ
		jpb.setBackground(Color.LIGHT_GRAY);//���ý�����������ɫ
		jpb.setForeground(Color.GREEN);
		this.add(jl1,"Center");
		this.add(jpb,"South");
		this.setSize(400, 240);
		//ȷ��jwindow�ĳ�ʼλ��
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int hight=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, hight/2-150);
		this.setVisible(true);
		
		
	}

	//��д�̵߳�RUN����
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int []jpbvalues={0,1,5,8,14,17,26,35,38,43,49,56,65,71,75,78,86,94,98,99,100};
		for(int i=0;i<jpbvalues.length;i++){
			//��������Ч����������ת����¼����
			try {
				//�����������ص�ʱ�䣬�����10s
				//����1��
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jpb.setValue(jpbvalues[i]);
		}
			
			//��ת����¼
			System.out.println("����������ת����¼���棡");
			  //������¼����
			new UserLogin();
			this.dispose();
			//break;//�˳��߳�
			
		
		System.out.println("�˳��߳�");
	}

}


//����һ��������
class ShanPing extends JPanel{
	
}