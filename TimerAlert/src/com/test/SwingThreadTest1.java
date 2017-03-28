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
                Thread.sleep(100);//这里比作要完成的某个耗时的工作  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
                         //更新进度条和输入框  
            if (flag) {  
                count++;  
                progressBar.setValue(count);  
                text.setText(STR + String.valueOf(count) + "%");  
            }  
        }  
    }  
    private class Start implements ActionListener {  
        public void actionPerformed(ActionEvent e) {  
            flag = true;//设置开始更新的标志  
            go();//开始工作  
        }  
    }  
    private class End implements ActionListener {  
        public void actionPerformed(ActionEvent e) {  
            flag = false;//停止  
        }  
    }  
    public static void main(String[] args) {  
        SwingThreadTest1 fg = new SwingThreadTest1();  
        fg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        fg.setSize(300, 100);  
        fg.setVisible(true);  
    }  
}  


//错误分析   ;

/*
 * 运行代码发现，

现象1：当点击了开始按钮，画面就卡住了。按钮不能点击，进度条没有被更新，输入框上也没有任何信息。

原因分析：Swing是线程不安全的，是单线程的设计，所以只能从事件派发线程访问将要在屏幕上绘制的Swing组件。ActionListener的actionPerformed方法是在事件派发线程中调用执行的，而点击了开始按钮后，执行了go()方法，在go()里，虽然也去执行了更新组件的方法

progressBar.setValue(count);

text.setText(STR + String.valueOf(count) + "%");

但由于go()方法直到循环结束，它并没有返回，所以更新组件的操作一直没有被执行，这就造成了画面卡住的现象。

现象2：过了一段时间(go方法里的循环结束了)后，画面又可以操作，并且进度条被更新，输入框也出现了我们想看到的信息。

原因分析：通过在现象1的分析，很容易联想到，当go()方法返回了，则其他的线程(更新组件)可以被派发了，所以画面上的组件被更新了。



*/
