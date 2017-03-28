package com.BookManagement.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;

import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.JLabel;

import com.BookManagement.tool.ImagePanel;
import com.BookMangement.Model.BorrowModel;
import com.BookMangement.Model.UserModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.awt.Color;
import java.awt.SystemColor;

public class UserClient extends JFrame {

	private JPanel contentPane;
	private String identity;//标记登陆者身份
	private String id;//标记登陆者的id
	private ReturnPanel panel_5;
	private static JTextPane textPane;//提示面板
	
    public static String getTishi() {
		return tishi;
	}
	public static void setTishi(String tishi) {
		UserClient.tishi = tishi;
	}

	public  static String tishi;
	
	public  static void checkUserDead(String id,String identity){
		String paras[]={id};
		UserModel userModel=new UserModel();
		tishi=userModel.checkDead(paras,identity);
		textPane.setText(tishi);
	}
	
	//卡片
	CardLayout cl1,cl2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserClient frame = new UserClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}
	/**
	 * Create the frame.
	 * String id,String identity
	 */
	public UserClient(String id,String identity) {
		
		this.id=id;
		this.identity=identity;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250,0, 845, 730);
		//setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel(new BorderLayout());
		splitPane.setLeftComponent(panel);
		//上面欢迎
		JLabel jl1=new JLabel(new ImageIcon("image//欢迎1.png"));
		panel.add(jl1);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOneTouchExpandable(true);
		splitPane.setRightComponent(splitPane_1);
		
		//卡片二
		JPanel panel_1 = new JPanel();
		splitPane_1.setRightComponent(panel_1);
		cl2=new CardLayout();
		panel_1.setLayout(cl2);
		
		Image im = null;
		try {
			im = ImageIO.read(new File("image//back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel jP=new ImagePanel(im);
		panel_1.add(jP, "0");
		
		//个人信息面板
		JPanel panel_2 = new UserPanel(id,identity);
		
		panel_1.add(panel_2, "name_4453015081867");
		
		//图书查询面板
		JPanel panel_3 = new SelBookPanel();
		panel_1.add(panel_3, "name_4467777257553");
		
		//借书服务面板
		JPanel panel_4 = new BorrowBookjPanel(id,identity);
		panel_1.add(panel_4, "name_4469501356922");
		
		//还书服务面板
		panel_5 = new ReturnPanel(id,identity);
		panel_1.add(panel_5, "name_4471067707545");
		
		
		//图书管理面板
		JPanel panel_6 = new BookManagePanel();
		panel_1.add(panel_6, "name_4475435855583");
		
	
		//读者管理模板
		JPanel panel_11 = new ReadManage();
		panel_1.add(panel_11, "name_7714541821421");
		//管理员管理界面
		JPanel panel_12 = new JPanel();
		panel_1.add(panel_12, "name_9560878638320");
		//办理借书卡界面
		JPanel panel_13 = new JieshukaJPanel();
		panel_1.add(panel_13, "name_926071430375");
		
		//延期功能面板
		JPanel panel_14 = new AddDeadJPanel(id);
		panel_1.add(panel_14, "name_3680024256022");
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setResizeWeight(0.4);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setLeftComponent(splitPane_2);
		
		
		
		//卡片一
		JPanel panel_7 = new JPanel();
		splitPane_2.setLeftComponent(panel_7);
		cl1=new CardLayout();
		panel_7.setLayout(cl1);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8, "name_4608381352516");
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JButton btnNewButton = new JButton("\u4E2A\u4EBA\u4FE1\u606F");
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl2.show(panel_1, "name_4453015081867");
			}
		});
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_8.add(btnNewButton);
		//图书查询按钮
		JButton btnNewButton_1 = new JButton("\u56FE\u4E66\u67E5\u8BE2");
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl2.show(panel_1, "name_4467777257553");
			}
		});
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_8.add(btnNewButton_1);
		
		//借书服务
		JButton btnNewButton_2 = new JButton("\u501F\u4E66\u670D\u52A1");
		btnNewButton_2.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl2.show(panel_1,"name_4469501356922");
			}
		});
		//图书延期功能按钮
		JButton btnNewButton_12 = new JButton("\u56FE\u4E66\u5EF6\u671F");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl2.show(panel_1,"name_3680024256022");
			}
		});
		btnNewButton_12.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_12.setFont(new Font("黑体", Font.PLAIN, 22));
		panel_8.add(btnNewButton_12);
		//判断如果是管理员就隐藏
		if(identity.trim().equals("管理员")){
			btnNewButton_12.setVisible(false);
		}
		
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_8.add(btnNewButton_2);
		//判断如果是读者就隐藏
				if(identity.trim().equals("读者")){
					btnNewButton_2.setVisible(false);
				}
		
		//还书服务
		JButton btnNewButton_3 = new JButton("\u8FD8\u4E66\u670D\u52A1");
		btnNewButton_3.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//BorrowModel bModel=new BorrowModel();
				//String paras[]={id};
				//bModel.query(paras);
				//panel_5.getJt().setModel(bModel);
				
				//方法二
				panel_5 = new ReturnPanel(id,identity);
				panel_1.add(panel_5,"name_4471067707545");
				
				cl2.show(panel_1,"name_4471067707545");
			}
		});
		panel_8.add(btnNewButton_3);
		//判断如果是读者就隐藏
		if(identity.trim().equals("读者")){
			btnNewButton_3.setVisible(false);
		}
		
		JButton btnNewButton_4 = new JButton("\u7CFB\u7EDF\u529F\u80FD");
		
		btnNewButton_4.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				cl1.show(panel_7,"name_4610864279378" );
				
			}
		});
		btnNewButton_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		//判断如果是读者就隐藏
		System.out.println("判断身份："+identity.trim().equals("读者"));
		if(identity.trim().equals("读者")){
			btnNewButton_4.setVisible(false);
		}
		
		//借书卡按钮
		JButton btnNewButton_10 = new JButton("\u529E\u501F\u4E66\u5361");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cl2.show(panel_1, "name_926071430375");
			}
		});
		btnNewButton_10.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_10.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_8.add(btnNewButton_10);
		panel_8.add(btnNewButton_4);
		//判断如果是读者就隐藏
		System.out.println("判断身份："+identity.trim().equals("读者"));
		if(identity.trim().equals("读者")){
				btnNewButton_10.setVisible(false);
		}
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9, "name_4610864279378");
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JButton btnNewButton_5 = new JButton("\u4E2A\u4EBA\u4FE1\u606F");
		btnNewButton_5.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl2.show(panel_1, "name_4453015081867");
			}
		});
		btnNewButton_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(btnNewButton_5);
		//图书查询按钮
		JButton btnNewButton_6 = new JButton("\u56FE\u4E66\u67E5\u8BE2");
		btnNewButton_6.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl2.show(panel_1, "name_4467777257553");
			}
		});
		btnNewButton_6.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("\u501F\u4E66\u670D\u52A1");
		btnNewButton_7.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl2.show(panel_1,"name_4469501356922");
			}
		});
		btnNewButton_7.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("\u8FD8\u4E66\u670D\u52A1");
		btnNewButton_8.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl2.show(panel_1,"name_4471067707545");
			}
		});
		btnNewButton_8.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("\u7CFB\u7EDF\u529F\u80FD");
		btnNewButton_9.setFont(new Font("黑体", Font.PLAIN, 22));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl1.show(panel_7,"name_4608381352516");
			}
		});
		//借书卡按钮
		JButton btnNewButton_11 = new JButton("\u529E\u501F\u4E66\u5361");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cl2.show(panel_1, "name_926071430375");
			}
		});
		btnNewButton_11.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_11.setFont(new Font("黑体", Font.PLAIN, 22));
		panel_9.add(btnNewButton_11);
		//判断如果是读者就隐藏
		System.out.println("判断身份："+identity.trim().equals("读者"));
		if(identity.trim().equals("读者")){
			btnNewButton_11.setVisible(false);
		}
		btnNewButton_9.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(btnNewButton_9);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7BA1\u7406");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl2.show(panel_1,"name_4475435855583");
			}
		});
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BFB\u8005\u7BA1\u7406");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl2.show(panel_1,"name_7714541821421");
			}
		});
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(lblNewLabel_1);
		
		//管理员管理panel
		JLabel lblNewLabel_2 = new JLabel("\u7BA1\u7406\u5458\u7BA1\u7406");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				cl2.show(panel_1,"name_9560878638320");
			}
		});
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(lblNewLabel_2);
		
		
		
		JPanel panel_10 = new JPanel();
		splitPane_2.setRightComponent(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		//这是一个提示板
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.textHighlight);
		textPane.setForeground(new Color(0, 0, 0));
		textPane.setFont(new Font("华文细黑", Font.BOLD, 23));
		textPane.setEnabled(false);
		panel_10.add(textPane);
		
		
		//设置标题图片
		Image image = null;
		try {
				image= ImageIO.read(new File("image//小图标.jpg"));
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		this.setIconImage(image);	
		this.setResizable(false);
		this.setTitle("图书管理系统");
		this.setVisible(true);
		//执行提示函数 如果还了书
		this.checkUserDead(this.id,this.identity);
		textPane.setText(tishi);
		
		
		panel_2.setOpaque(false);
		panel_1.setOpaque(false);
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
		
		
		
		panel.setOpaque(false);
	}

}
