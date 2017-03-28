/*
 * �����б�������������İ���ˣ�
 */
package com.qq.client.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import com.qq.client.tools.*;

public class QqFriendlist extends JFrame implements ActionListener,MouseListener{
	
	    //�����һ�ſ�Ƭ�����ѣ�
	    JPanel jphy1,jphy2,jphy3;
	    JButton jphy_jb1,jphy_jb2,jphy_jb3;
	    JScrollPane jsp1;
	    
	    //����ڶ��ſ�Ƭ��İ���ˣ�
	    JPanel jpmsr1,jpmsr2,jpmsr3;
	    JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	    JScrollPane jsp2;
	    
	    //����JFrameΪ��Ƭ����CardLayout
	    CardLayout cl;
	    //����ȫ��
	    String owner;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqFriendlist qqfriendlist=new QqFriendlist();

	}
	//�ڱ�������ʾ��ǰ�û���id
	public QqFriendlist(String ownerid)
	{   this.owner=ownerid;
		//�����һ�ſ�Ƭ���߽粼�֣�
		jphy_jb1=new JButton("�ҵĺ���");
		jphy_jb2=new JButton("İ����");
		//������ť
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("������");
		
		jphy1=new JPanel(new BorderLayout());
		//�м���JScrollPane(���Թ���) �ϼ�һ��JPane
		jphy2=new JPanel(new GridLayout(50,1,4,4));//����50���˼��4
		//��jphy2��ʼ��50����
		JLabel []jbls=new JLabel[50];
		
		for(int i=0;i<50;i++)
		{
			jbls[i]=new JLabel(i+1+"",new ImageIcon("image/tx.gif"),JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
			
		}
		jphy3=new JPanel(new GridLayout(2,1));
		//�Ѱ�ť�ݵ�jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		
		jsp1=new JScrollPane(jphy2);
		
		//��ʼ����һ�ſ�Ƭ
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		//����ڶ��ſ�Ƭ���߽粼�֣�
		jpmsr_jb1=new JButton("�ҵĺ���");
		//����jpmsr_jb1
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("İ����");
		jpmsr_jb3=new JButton("������");
				
		jpmsr1=new JPanel(new BorderLayout());
		//�м���JScrollPane(���Թ���) �ϼ�һ��JPane
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));//����50���˼��4
		//��jpmsr2��ʼ��20����
		JLabel []jbls2=new JLabel[20];
				
		for(int i=0;i<jbls2.length;i++)
		{
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("image/tx.gif"),JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
					
		}
		jpmsr3=new JPanel(new GridLayout(2,1));
		//�Ѱ�ť�ݵ�jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
				
				
		jsp2=new JScrollPane(jpmsr2);
				
		//��ʼ���ڶ��ſ�Ƭ
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
        cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		//���������ʾid
		this.setTitle(ownerid);
		this.setSize(160,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//����û������İ���˾���ʾ�ڶ��ſ�Ƭ
		if(arg0.getSource()==jphy_jb2)
		{
			cl.show(this.getContentPane(), "2");
		}else if(arg0.getSource()==jpmsr_jb1)
		{
			cl.show(this.getContentPane(), "1");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//��Ӧ�û�˫�����¼������õ����ѵı��
		if(e.getClickCount()==2)
		{
			//�õ����ѵı��
			String friendNo=((JLabel)e.getSource()).getText();
			System.out.println("��ϣ����"+friendNo+"����");
			Qqchat qqchat=new Qqchat(owner,friendNo);
			
			//�����������뵽��������
			ManangeQqchat.addQqchat(this.owner+" "+friendNo, qqchat);
			
			
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//�û����֪���Ǿͱ�ɺ�ɫ
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.red);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.BLACK);
		
	}

}
