package com.BookManagement.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.BookMangement.Model.BookModel;
import com.BookMangement.Model.BorrowModel;
import com.BookMangement.Model.UserModel;

public class ReturnPanel extends JPanel implements ActionListener{
	//����
	JTable jt;
	public BorrowModel getbModel() {
		return bModel;
	}
	public void setbModel(BorrowModel bModel) {
		this.bModel = bModel;
	}
	public JTable getJt() {
		return jt;
	}
	public void setJt(JTable jt) {
		this.jt = jt;
	}
	JTextField jtf1;
	JScrollPane jsp;
	BorrowModel bModel;
	JLabel jl1;
	JButton jb1,jb2;
	JPanel jp1;
	private String identity;//��ǵ�½�����
	private String id;//��ǵ�½�ߵ�id
	public ReturnPanel(String id,String identity){
		this.id=id;
		this.identity=identity;
		
		//��������
		jl1=new JLabel("�����б����£�");
		jl1.setFont(new Font("����", Font.PLAIN, 30));
		jl1.setAlignmentX(LEFT_ALIGNMENT);//���ô�ֱ����
		JLabel jl2=new JLabel("���߱�ţ�");
		jl2.setFont(new Font("����", Font.PLAIN, 16));
		jtf1=new JTextField(20);
		jb2=new JButton("��ѯ");
		jb2.setFont(new Font("����", Font.PLAIN, 16));
		jb2.addActionListener(this);
		JPanel jp2=new JPanel();
		JPanel jp3=new JPanel(new GridLayout(2,1));
		jp2.add(jl2);
		jp2.add(jtf1);
		jp2.add(jb2);
		jp3.add(jl1);
		jp3.add(jp2);
		
		
		//�����м�
		bModel=new BorrowModel();
		//String paras[]={id};
		//bModel.query(paras);
		jt=new JTable(bModel);
		jsp=new JScrollPane(jt);
		//��������
		jp1=new JPanel();
		jb1=new JButton("ѡ�л��飡");
		jb1.setFont(new Font("����", Font.PLAIN, 16));
		jb1.addActionListener(this);
		jb1.setAlignmentY(HEIGHT);
		jp1.add(jb1);
		
		
		
		this.setLayout(new BorderLayout());
		this.add(jp3,"North");
		this.add(jsp);
		this.add(jp1,"East");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//�������
		if(arg0.getSource()==jb1){
			if(jt.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(new JFrame(), "��ѡ��һ�У�����");
				return;
			}
			//�õ���ǰ�黹ʱ��
			//�õ������ǰϵͳʱ��
			Date date=new Date();
			String nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			System.out.println(nowtime);
			//�õ������ѧ�� �� ��������
			
			id=(String)bModel.getValueAt(jt.getSelectedRow(),0);
			String Bid=(String)bModel.getValueAt(jt.getSelectedRow(),1);
			String Ftime=(String)bModel.getValueAt(jt.getSelectedRow(),4);
			int dead=Integer.parseInt((String)bModel.getValueAt(jt.getSelectedRow(),5));
			//���ɲ���(ʱ�䣬����id����id)
			String paras[]={nowtime,id,Bid};
			bModel=new BorrowModel();
			
			//д��黹ʱ��
			if(bModel.upReturnTime(paras)){
				//���Ӹ����Stock
				BookModel bookModel=new BookModel();
				String p2[]={Bid};
				if(bookModel.addStock(p2)){
				//����ɹ�����ʾ�Ƿ��ڣ�����ʾ���ڶ�����
				UserModel uModel=new UserModel();
				String tishi="û�г��ڣ���";
				int day=uModel.DeadTime(Ftime, dead);
				if(day>0){
					 tishi="����"+day+"�죡";
				}
				JOptionPane.showMessageDialog(new JFrame(), "����ɹ���"+tishi);
				
				//����table
				bModel=new BorrowModel();
				String p[]={id};
				bModel.query(p);
				jt.setModel(bModel);

				
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "��������ʧ�ܣ���");
				}
				
			}else{
				JOptionPane.showMessageDialog(new JFrame(), "����ʧ�ܣ���");
			}
			

		}
		
		if(arg0.getSource()==jb2){
			bModel=new BorrowModel();
			String paras[]={jtf1.getText().trim()};
			bModel.query(paras);
			jt.setModel(bModel);
		}
	}
}
