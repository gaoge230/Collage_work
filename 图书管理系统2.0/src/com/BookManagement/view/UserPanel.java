package com.BookManagement.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import com.BookMangement.Model.BorrowModel;
import com.BookMangement.Model.ReadModel;
import com.BookMangement.Model.UserModel;

public class UserPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	ReadModel rModel;
	
	private String identity;//标记登陆者身份
	private String id;//标记登陆者的id

	/**
	 * Create the panel.
	 */
	public UserPanel(String id,String identity) {
		this.id=id;
		this.identity=identity;
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPane.setFont(new Font("黑体", Font.PLAIN, 18));
		add(tabbedPane);
		//个人信息面板
		JPanel panel = new JPanel();
		
		tabbedPane.addTab("\u4E2A\u4EBA\u4FE1\u606F", null, panel, null);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_15 = new JPanel();
		panel.add(panel_15);
		panel_15.setLayout(new GridLayout(7, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_15.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_3.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 16));
		textField.setEnabled(false);
		panel_3.add(textField);
		textField.setColumns(20);
		
		JPanel panel_4 = new JPanel();
		panel_15.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_4.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_1.setEnabled(false);
		panel_4.add(textField_1);
		textField_1.setColumns(20);
		
		JPanel panel_5 = new JPanel();
		panel_15.add(panel_5);
		
		JLabel lblNewLabel_3 = new JLabel("\u5E74\u9F84\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_5.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_2.setEnabled(false);
		panel_5.add(textField_2);
		textField_2.setColumns(20);
		
		JPanel panel_6 = new JPanel();
		panel_15.add(panel_6);
		
		JLabel lblNewLabel_4 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_6.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_3.setEnabled(false);
		panel_6.add(textField_3);
		textField_3.setColumns(20);
		
		JPanel panel_7 = new JPanel();
		panel_15.add(panel_7);
		
		JLabel lblNewLabel_5 = new JLabel("\u4E13\u4E1A\uFF1A");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_7.add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_4.setEnabled(false);
		panel_7.add(textField_4);
		textField_4.setColumns(20);
		
		JPanel panel_8 = new JPanel();
		panel_15.add(panel_8);
		
		JLabel lblNewLabel_6 = new JLabel("\u5730\u5740\uFF1A");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_8.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_5.setEnabled(false);
		panel_8.add(textField_5);
		textField_5.setColumns(20);
		
		JPanel panel_9 = new JPanel();
		panel_15.add(panel_9);
		
		JLabel lblNewLabel_7 = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_9.add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_6.setEnabled(false);
		panel_9.add(textField_6);
		textField_6.setColumns(20);
		//修改密码面板
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u4FEE\u6539\u5BC6\u7801", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_16 = new JPanel();
		panel_1.add(panel_16, BorderLayout.NORTH);
		panel_16.setLayout(new BoxLayout(panel_16, BoxLayout.Y_AXIS));
		
		JPanel panel_10 = new JPanel();
		panel_16.add(panel_10);
		panel_10.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11);
		
		JLabel lblNewLabel_8 = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_11.add(lblNewLabel_8);
		//原密码
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		panel_11.add(passwordField);
		
		JPanel panel_12 = new JPanel();
		panel_10.add(panel_12);
		
		JLabel lblNewLabel_9 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_12.add(lblNewLabel_9);
		//新密码
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(20);
		panel_12.add(passwordField_1);
		
		JPanel panel_13 = new JPanel();
		panel_10.add(panel_13);
		
		JLabel lblNewLabel_10 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_13.add(lblNewLabel_10);
		//新密码
		passwordField_2 = new JPasswordField();
		passwordField_2.setColumns(20);
		panel_13.add(passwordField_2);
		
		JPanel panel_14 = new JPanel();
		panel_16.add(panel_14);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//点击了修改密码确认按钮
				String oldpass=new String(passwordField.getPassword());
				String newpass1=new String(passwordField_1.getPassword());
				String newpass2=new String(passwordField_2.getPassword());
				System.out.println(oldpass.equals(newpass1));
				System.out.println(newpass1);
				if(oldpass.equals(newpass1)==true){
					
					JOptionPane.showMessageDialog(new JFrame(), "警告：不允许新密码和旧密码一样！");
				}if(newpass1.equals(newpass2)==false){
					JOptionPane.showMessageDialog(new JFrame(), "警告：两次密码不一致!!");
				}if(oldpass.equals(newpass1)==false&&newpass1.equals(newpass2)){
					//比如id 为1
					//String id="2";
					String paras[]={newpass1,oldpass,id};
					UserModel uModel=new UserModel();
					String identity="读者";
					if(uModel.uppass(paras, identity)){
						JOptionPane.showMessageDialog(new JFrame(), "修改成功！！！");
					}else{
						JOptionPane.showMessageDialog(new JFrame(), "密码错误！！！");
					}
				}
				
				passwordField.setText("");
				passwordField_1.setText("");
				passwordField_2.setText("");
				
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 16));
		panel_14.add(btnNewButton);
		
		//借书历史查询面板
		JPanel panel_2 = new JPanel(new BorderLayout());
		BorrowModel borrowModel=new BorrowModel();
		String paras[]={id};
		borrowModel.queryHistory(paras);
		JTable jTable=new JTable(borrowModel);
		JScrollPane jsp=new JScrollPane(jTable);
		panel_2.add(jsp);
		tabbedPane.addTab("\u501F\u4E66\u5386\u53F2", null, panel_2, null);
		
		JLabel lblNewLabel = new JLabel("\u4E2A\u4EBA\u7BA1\u7406");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);

		panel.setOpaque(false);
		panel_1.setOpaque(false);
		panel_2.setOpaque(false);
		panel_3.setOpaque(false);
		panel_4.setOpaque(false);
		panel_5.setOpaque(false);
		panel_6.setOpaque(false);
		panel_7.setOpaque(false);
		panel_8.setOpaque(false);
		panel_9.setOpaque(false);
		panel_10.setOpaque(false);
		panel_11.setOpaque(false);
		panel_12.setOpaque(false);
		panel_13.setOpaque(false);
		panel_14.setOpaque(false);
		panel_15.setOpaque(false);
		panel_16.setOpaque(false);
		this.setOpaque(false);
		tabbedPane.setOpaque(false);
		//初始化个人信息
		if(identity.equals("读者")){
			this.initUser();
		}
		
		
	}
	
	public void initUser(){
		rModel=new ReadModel();
		//此处参数应为从登陆用户得到的id
		String paras[]={id};
		rModel.query(paras);
		textField.setText((String)rModel.getValueAt(0, 0));
		textField_1.setText((String)rModel.getValueAt(0, 1));
		textField_2.setText((String)rModel.getValueAt(0, 2));
		textField_3.setText((String)rModel.getValueAt(0, 3));
		textField_4.setText((String)rModel.getValueAt(0, 4));
		textField_5.setText(((String)rModel.getValueAt(0, 5)).trim());
		System.out.println((String)rModel.getValueAt(0, 5));
		textField_6.setText(((String)rModel.getValueAt(0, 6)).trim());
	}

}
