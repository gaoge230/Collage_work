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

	//定义窗口组件
	JButton jb1,jb2;
	JFrame jframe,jf1;
	JTextField jtf1,jtf2;
	JLabel jl1,jl2,jl3;
	JPanel jp,jp2;
	JTextArea jt;             
	JScrollPane scr;//滚动条
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        client c=new client();
	}
	
	//构造函数
public client()
{   
	//初始化各组件
	jframe=new JFrame();
	jp=new JPanel();
	
	//北部
	jl1=new JLabel("请输入n值与m值（以逗号分隔）：");
	jl2=new JLabel("请输入要求的第k个密码：                  ");
	jf1=new JFrame();
	jp2=new JPanel( new GridLayout(2,2));
	jtf1=new JTextField();
	jtf2=new JTextField();
    jp2.add(jl1);
    jp2.add(jtf1);
    jp2.add(jl2);
    jp2.add(jtf2);
    
    
	//中部
	jb1=new JButton("蛮力法");
	  //添加监听
	jb1.addActionListener(this);
	jb2=new JButton("动态规划");
	jb2.addActionListener(this);
	
	jb1.setBackground(Color.white);
	jb2.setBackground(Color.red);
	jl3=new JLabel("蛮力法点击白色按钮，动态规划法点击红色按钮：");
	//jl3.setFont();
	
	jt=new JTextArea(20, 30);
	//把文本域设置成只读
	jt.setEditable(false);
	//显示垂直滚动条，显示水平滚动条
	scr=new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	
	jp.add(jp2,"North");
	jp.add(jl3);
	jp.add(jb1,"West");
	jp.add(jb2,"West");
	jp.add(scr,"South");
	
	
	
	jframe.add(jp);
	jframe.setTitle("莫尔斯电码");
	jframe.setLocation(380, 100);
	jframe.setSize(450, 500);
	jframe.setVisible(true);
	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//对事件如何进行处理
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	  //提取文本框中的字符串
	  String s=jtf1.getText();
	  String m=jtf2.getText();
	  //以“,”号分隔开
	  String ss[]= s.split(",");
	  
	  //把字符串强转为整型
	  int i = Integer.valueOf(ss[0]).intValue();
	  
	  int j = Integer.valueOf(ss[1]).intValue();
	  
	  int k= Integer.valueOf(m).intValue();
      System.out.println(i+","+j+","+k);
	 
      //根据用户点击的不同按钮来进行事件处理
	  if(e.getSource()==jb1){
		  
		  //如果用户点击白色按钮，创建manlifa实例
		   manlifa manli=new manlifa(i,j,k);
		   manli.man();
		   //结果显示到文本域中
		   jt.setText(manli.xian());
		  
	  }
	  if(e.getSource()==jb2){
		  
		  //如果用户点击红色按钮，创建dongtaiguihua实例
		  dongtaiguihua dtgh=new dongtaiguihua(i,j,k);
		  
		  dtgh.main();
		  //结果显示到文本域中
		  jt.setText("您要求的第"+k+"个编码为：");
		  jt.append(dtgh.main());
	  }
	  
	  
}


}



