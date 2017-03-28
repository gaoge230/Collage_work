/*
f * ��¼����
 */
package com.BookManagement.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.tools.Tool;

import com.BookMangement.Model.*;
public class Userlogin extends JDialog implements ActionListener,KeyListener{
	//�������
	JPanel jp1;
	JLabel jl1,jl2;
	JTextField jtf;
	JPasswordField jpf;
	JButton jb1,jb2;
	
	MainWindow mainWindow;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new Userlogin();
	}
	
	public Userlogin(MainWindow mainWindow) {
		// TODO Auto-generated constructor stub
		this.mainWindow=mainWindow;
		
		//�������
		//����һ��BackImage����
		BackImage bi=new BackImage();
		bi.setLayout(null);
		//bi.setOpaque(false);
		//��λ��ȷ��
		bi.setBounds(0, 0, 560, 330);
		jp1=new JPanel();
		jl1=new JLabel();
		jl2=new JLabel();
		jtf=new JTextField(20);
		jtf.setBounds(273, 175, 150, 27);
		jpf=new JPasswordField(20);
		jpf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					jb1.doClick();
				    
				}
			}
			
		});
		
		jpf.setBounds(273, 212, 150, 27);
		
		
		jb1=new JButton(new ImageIcon("image//1.jpg"));
		//jb1.setOpaque(true);
		//jb1.setBorder(BorderFactory.createLoweredBevelBorder());
		jb1.setBounds(259,257,63,25);
		jb1.addActionListener(this);
		
		jb2=new JButton(new ImageIcon("image//2.jpg"));
		//jb2.setOpaque(true);
		jb2.setBounds(340,257,63,25);
		jb2.addActionListener(this);
		
		
		
		bi.add(jtf);
		bi.add(jpf);
		bi.add(jb1);
		bi.add(jb2);
		this.add(bi);
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int hight=Toolkit.getDefaultToolkit().getScreenSize().height;
		
		//���ô˴��������д���ǰ��
		
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setLocation(width/2-300,hight/2-200);
		this.setSize(560,330);
		this.setVisible(true);
		
	}
	//����һ���ڲ���
		class BackImage extends JPanel{
			
			Image im;
			//���캯��
			public BackImage(){
				try {
					im=ImageIO.read(new File("image//��¼����.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
			public void paintComponent(Graphics g){
				g.drawImage(im, 0, 0, 560, 330, this);
			}
		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1){
			if(jtf.getText().trim().equals("")||jpf.getPassword().toString().trim().equals("")){
				JOptionPane.showMessageDialog(new JFrame(), "�˺ţ����벻��Ϊ�գ�����");
				return;
			}
			System.out.println("��¼");
			//��õ�¼�� id  ��   ���롣
			String id=jtf.getText().toString().trim();
			String  passwd=new String(jpf.getPassword());
			System.out.println("ID:"+id +"����:"+passwd);
			
			//��ѯ��¼��ݼ��Ƿ���ȷ ������ݻ��
			UserModel um=new  UserModel();
			String identity=um.login(id,passwd);
			
			System.out.println(identity.trim().equals("����"));
			
			if(identity.trim().equals("����")){
				System.out.println(identity);
				//�Զ�����ݽ������
				mainWindow.dispose();
				this.dispose();
				new UserClient(id,identity);
				

			}else if (identity.trim().equals("����Ա")){
				System.out.println(identity);
				//�Թ���Ա��ݽ������
				mainWindow.dispose();
				this.dispose();
				new UserClient(id,identity);
				
			}else {
				JOptionPane.showMessageDialog(this, "�˺������������󣡣���");
			}
			jtf.setText("");
			jpf.setText("");
			
		}
		if(arg0.getSource()==jb2){
			System.out.println("�ر�");
			//�رգ��ͷ�
			this.dispose();
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP){
			//jb1.doClick();
		    System.out.println("DGGDFSGDGDFG");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
