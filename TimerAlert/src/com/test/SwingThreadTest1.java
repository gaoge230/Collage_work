package com.test;

import java.awt.FlowLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JProgressBar;  
import javax.swing.JTextField;  
public class SwingThreadTest1 extends JFrame {  
    private static final long serialVersionUID = 1L;  
    private static final String STR = "Completed : ";  
    private JProgressBar progressBar = new JProgressBar();  
    private JTextField text = new JTextField(10);  
    private JButton start = new JButton("Start");  
    private JButton end = new JButton("End");  
    private boolean flag = false;  
    private int count = 0;  
    public SwingThreadTest1() {  
        this.setLayout(new FlowLayout());  
        add(progressBar);  
        text.setEditable(false);  
        add(text);  
        add(start);  
        add(end);  
        start.addActionListener(new Start());  
        end.addActionListener(new End());  
    }  
          
    private void go() {  
        while (count < 100) {  
            try {  
                Thread.sleep(100);//�������Ҫ��ɵ�ĳ����ʱ�Ĺ���  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
                         //���½������������  
            if (flag) {  
                count++;  
                progressBar.setValue(count);  
                text.setText(STR + String.valueOf(count) + "%");  
            }  
        }  
    }  
    private class Start implements ActionListener {  
        public void actionPerformed(ActionEvent e) {  
            flag = true;//���ÿ�ʼ���µı�־  
            go();//��ʼ����  
        }  
    }  
    private class End implements ActionListener {  
        public void actionPerformed(ActionEvent e) {  
            flag = false;//ֹͣ  
        }  
    }  
    public static void main(String[] args) {  
        SwingThreadTest1 fg = new SwingThreadTest1();  
        fg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        fg.setSize(300, 100);  
        fg.setVisible(true);  
    }  
}  


//�������   ;

/*
 * ���д��뷢�֣�

����1��������˿�ʼ��ť������Ϳ�ס�ˡ���ť���ܵ����������û�б����£��������Ҳû���κ���Ϣ��

ԭ�������Swing���̲߳���ȫ�ģ��ǵ��̵߳���ƣ�����ֻ�ܴ��¼��ɷ��̷߳��ʽ�Ҫ����Ļ�ϻ��Ƶ�Swing�����ActionListener��actionPerformed���������¼��ɷ��߳��е���ִ�еģ�������˿�ʼ��ť��ִ����go()��������go()���ȻҲȥִ���˸�������ķ���

progressBar.setValue(count);

text.setText(STR + String.valueOf(count) + "%");

������go()����ֱ��ѭ������������û�з��أ����Ը�������Ĳ���һֱû�б�ִ�У��������˻��濨ס������

����2������һ��ʱ��(go�������ѭ��������)�󣬻����ֿ��Բ��������ҽ����������£������Ҳ�����������뿴������Ϣ��

ԭ�������ͨ��������1�ķ��������������뵽����go()���������ˣ����������߳�(�������)���Ա��ɷ��ˣ����Ի����ϵ�����������ˡ�



*/
