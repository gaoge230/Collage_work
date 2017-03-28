package com.BookManagement.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.BookMangement.Model.*;

public class BorrowBookjPanel extends JPanel implements ActionListener{

	//�������
	JPanel jp1,jp1_0,jp1_1,jp1_2,jp1_3;
	JLabel jl1,jl2;
	JTextField jtf1,jtf2;
	JButton jb1;
	JTable jt;
	JScrollPane jsp;
	JPanel jp2;
	JButton jb2;
	//bookģ��
	BookModel bModel;
	
	private String identity;//��ǵ�½�����
	private String id;//��ǵ�½�ߵ�id
	
	public BorrowBookjPanel(String id,String identity){
		this.id=id;
		this.identity=identity;
		
		//�����Ǳ߽粼�� ����������
		jp1=new JPanel(new GridLayout(2,2));
		
		jp1_1=new JPanel();
		jp1_2=new JPanel();
		jp1_3=new JPanel();
		jl1=new JLabel("����ѧ�ţ�");
		jl1.setFont(new Font("����", Font.PLAIN, 16));
		jtf1=new JTextField(20);
		
		jl2=new JLabel("ͼ���ţ�");
		jl2.setFont(new Font("����", Font.PLAIN, 16));
		jtf2=new JTextField(20);
		
		jp1_1.add(jl1);
		jp1_1.add(jtf1);
		jp1_2.add(jl2);
		jp1_2.add(jtf2);
		
		jb1=new JButton("��ѯ");
		jb1.setFont(new Font("����", Font.PLAIN, 16));
		jb1.addActionListener(this);
		jp1_3.add(jb1);
		jp1_0=new JPanel();
		jp1.add(jp1_1);
		jp1.add(jp1_0);
		jp1.add(jp1_2);
		jp1.add(jp1_3);
		//�в�
		bModel=new BookModel();
		jt=new JTable(bModel);
		jsp=new JScrollPane(jt);
		
		//����
		jp2=new JPanel();
		jb2=new JButton("ѡ�н��ģ�");
		jb2.addActionListener(this);
		jb2.setFont(new Font("����", Font.PLAIN, 16));
		jb2.setBounds(0, 100, 50, 20);
		jp2.add(jb2);
		
		
		this.setLayout(new BorderLayout());
		this.add(jp1,"North");
		this.add(jsp);
		this.add(jp2,"East");

		this.setLocation(200, 50);
		this.setSize(1000, 600);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//����˲�ѯ�鼮�İ�ť
		if(arg0.getSource()==jb1){
			System.out.println("��ѯ�鼮��");
			//����ı����е����� ��������ͬʱΪ��
			
			if(jtf1.getText().trim().equals("")&&jtf2.getText().equals("")){
				System.out.println("��");
				JOptionPane.showMessageDialog(this, "������Ϊ��");
				return;
			}
			//�õ���ѯ��������Ҫ��ѯ���鼮�ı�ţ� ģ����ѯ
			String paras[]={"%"+jtf2.getText().trim()+"%"};
			
			for (String string : paras) {
				System.out.println(string);
			}
			
			//����table����ʾ��ѯ���
			bModel=new BookModel();
			bModel.queryBid(paras);
			jt.setModel(bModel);	
			
		}
		
		//����˽����鼮�İ�ť
		if(arg0.getSource()==jb2){
			//�ж��û��ǲ���ѡ����һ��
			if(jt.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(new JFrame(), "��ѡ��һ�У�����");
				return;
			}
			System.out.println("����");
			//�õ�ѡ���еĵ�һ�� ��Ҳ�����鼮�ı�ţ�
			String Bid=(String)bModel.getValueAt(jt.getSelectedRow(), 0);
			//�õ�����Ŀ�������Թ������жϣ�
			int Bstock=Integer.parseInt((String)bModel.getValueAt(jt.getSelectedRow(), 6));
			
			System.out.println(Bid);
			System.out.println(Bstock);
			
			//�õ���ǰ����ʱ��
			Date date=new Date();
			String nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			java.sql.Timestamp goodsTimestamp=java.sql.Timestamp.valueOf(nowtime);
			System.out.println(nowtime);
			System.out.println(goodsTimestamp);
			//�жϸ���Ŀ�����Ƿ����
			if(Bstock<1){	
				JOptionPane.showMessageDialog(new JFrame(), "�������������");
				
			}else{
				
				BorrowModel bModel=new BorrowModel();
				//���ò������˴��黹ʱ����ΪNULL����ʾ����û�й黹
				String paras[]={this.jtf1.getText().trim(),Bid,nowtime,"30",null,"1"};
				//�ж��Ƿ���ĳɹ����൱�ڸ���Ŀ������һ��
				if(bModel.add(paras)){
					
					//�޸��������������
					String p[]={Bid};
					BookModel bookModel=new BookModel();
					if(bookModel.reduceStock(p)){
						JOptionPane.showMessageDialog(new JFrame(), "���ĳɹ�������");
			
						
					}
					else{
						JOptionPane.showMessageDialog(new JFrame(), "�����޸�ʧ�ܣ�����");
					}
					
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "����ʧ�ܣ�����");
				}
						
				
			}
				
		}
		
	}

}
