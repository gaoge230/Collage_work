/**
 * 
 */
package keshe1;
import java.awt.*;

import javax.swing.*;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class client implements ActionListener{

	//���崰�����
	JButton jb1,jb2;
	JFrame jframe,jf1;
	JTextField jtf1,jtf2;
	JLabel jl1,jl2,jl3;
	JPanel jp,jp2;
	JTextArea jt;             
	JScrollPane scr;//������
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        client c=new client();
	}
	
	//���캯��
public client()
{   
	//��ʼ�������
	jframe=new JFrame();
	jp=new JPanel();
	
	//����
	jl1=new JLabel("������nֵ��mֵ���Զ��ŷָ�����");
	jl2=new JLabel("������Ҫ��ĵ�k�����룺                  ");
	jf1=new JFrame();
	jp2=new JPanel( new GridLayout(2,2));
	jtf1=new JTextField();
	jtf2=new JTextField();
    jp2.add(jl1);
    jp2.add(jtf1);
    jp2.add(jl2);
    jp2.add(jtf2);
    
    
	//�в�
	jb1=new JButton("������");
	  //��Ӽ���
	jb1.addActionListener(this);
	jb2=new JButton("��̬�滮");
	jb2.addActionListener(this);
	
	jb1.setBackground(Color.white);
	jb2.setBackground(Color.red);
	jl3=new JLabel("�����������ɫ��ť����̬�滮�������ɫ��ť��");
	//jl3.setFont();
	
	jt=new JTextArea(20, 30);
	//���ı������ó�ֻ��
	jt.setEditable(false);
	//��ʾ��ֱ����������ʾˮƽ������
	scr=new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	
	jp.add(jp2,"North");
	jp.add(jl3);
	jp.add(jb1,"West");
	jp.add(jb2,"West");
	jp.add(scr,"South");
	
	
	
	jframe.add(jp);
	jframe.setTitle("Ī��˹����");
	jframe.setLocation(380, 100);
	jframe.setSize(450, 500);
	jframe.setVisible(true);
	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//���¼���ν��д���
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	  //��ȡ�ı����е��ַ���
	  String s=jtf1.getText();
	  String m=jtf2.getText();
	  //�ԡ�,���ŷָ���
	  String ss[]= s.split(",");
	  
	  //���ַ���ǿתΪ����
	  int i = Integer.valueOf(ss[0]).intValue();
	  
	  int j = Integer.valueOf(ss[1]).intValue();
	  
	  int k= Integer.valueOf(m).intValue();
      System.out.println(i+","+j+","+k);
	 
      //�����û�����Ĳ�ͬ��ť�������¼�����
	  if(e.getSource()==jb1){
		  
		  //����û������ɫ��ť������manlifaʵ��
		   manlifa manli=new manlifa(i,j,k);
		   manli.man();
		   //�����ʾ���ı�����
		   jt.setText(manli.xian());
		  
	  }
	  if(e.getSource()==jb2){
		  
		  //����û������ɫ��ť������dongtaiguihuaʵ��
		  dongtaiguihua dtgh=new dongtaiguihua(i,j,k);
		  
		  dtgh.main();
		  //�����ʾ���ı�����
		  jt.setText("��Ҫ��ĵ�"+k+"������Ϊ��");
		  jt.append(dtgh.main());
	  }
	  
	  
}


}



