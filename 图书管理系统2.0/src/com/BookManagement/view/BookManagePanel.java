package com.BookManagement.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.BookMangement.Model.BookModel;

public class BookManagePanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the panel.
	 */
	public BookManagePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("黑体", Font.PLAIN, 18));
		add(tabbedPane, BorderLayout.CENTER);
		//添加面板
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u6DFB\u52A0", null, panel_2, null);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("\u4E66\u7C4D\u7F16\u53F7\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_6.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_6.add(textField);
		textField.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("  \u4E66\u540D\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_7.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		panel_7.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("  \u4F5C\u8005\u540D\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_8.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		panel_8.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_5.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_4 = new JLabel("\u51FA\u7248\u793E\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_10.add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		panel_10.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		panel_5.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_5 = new JLabel("\u51FA\u7248\u65F6\u95F4\uFF1A");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_11.add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		panel_11.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_5.add(panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_6 = new JLabel("  \u4EF7\u683C\uFF1A");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_12.add(lblNewLabel_6);
		
		textField_5 = new JTextField();
		panel_12.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panel_5.add(panel_13);
		panel_13.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_7 = new JLabel("    \u6570\u91CF\uFF1A");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_13.add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		panel_13.add(textField_6);
		textField_6.setColumns(10);
		
		JPanel panel_14 = new JPanel();
		panel_5.add(panel_14);
		panel_14.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_8 = new JLabel("  \u4F4D\u7F6E\uFF1A");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_14.add(lblNewLabel_8);
		
		textField_7 = new JTextField();
		panel_14.add(textField_7);
		textField_7.setColumns(10);
		panel_2.add(panel_5);
		
		//添加按钮
		JButton btnNewButton = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//点击了添加书籍按钮
				//获得参数
				if(textField.getText().trim().equals("")&&textField_1.getText().equals("")&&
						textField_2.getText().equals("")&&textField_3.getText().equals("")&&
						textField_4.getText().equals("")&&textField_5.getText().equals("")&&
						textField_6.getText().equals("")&&textField_7.getText().equals("")){
					JOptionPane.showMessageDialog(new JFrame(), "不能为空！！！");
					return;
					
				}
				String paras[]={textField.getText().trim(),textField_1.getText().trim(),textField_2.getText().trim(),
						textField_3.getText().trim(),textField_4.getText().trim(),textField_5.getText().trim(),textField_6.getText().trim()
						,textField_7.getText().trim()};
				BookModel bModel=new BookModel();
				if(bModel.add(paras)){
					JOptionPane.showMessageDialog(new JFrame(), "添加成功！！");
					//如果添加成功就置空
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					textField_6.setText("");
					textField_7.setText("");
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "添加失败！！");
				}
			}
		});
		
		
		panel_2.add(btnNewButton);
		//修改，删除按钮面板
		JPanel panel_1 = new SelBookManagePanel();
		tabbedPane.addTab("\u4FEE\u6539\u53CA\u5220\u9664", null, panel_1, null);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u4FE1\u606F\u7BA1\u7406");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 35));
		panel.add(lblNewLabel);

	}

}
