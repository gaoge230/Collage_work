package com.demo;

import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.*;
import java.io.*;
import sun.audio.*;
import java.awt.Font;

public class Frame1
    extends JFrame {
  JPanel contentPane;
  JButton jButton1 = new JButton();
  private final Timer timer = new Timer();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel2 = new JLabel();
  public double limittime=2;
  JLabel jLabel3 = new JLabel();
  public Frame1() {
    try {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Component initialization.
   *
   * @throws java.lang.Exception
   */
  private void jbInit() throws Exception {
    contentPane = (JPanel) getContentPane();
    contentPane.setLayout(null);
    this.getContentPane().setBackground(Color.white);
    setSize(new Dimension(400, 300));
    setTitle("Frame Title");
    jButton1.setBackground(UIManager.getColor(
        "InternalFrame.activeTitleGradient"));
    jButton1.setBounds(new Rectangle(222, 191, 146, 56));
    jButton1.setFont(new java.awt.Font("幼圆", Font.BOLD, 25));
    jButton1.setForeground(SystemColor.desktop);
    jButton1.setText("开始玩吧");
    jButton1.addActionListener(new Frame1_jButton1_actionAdapter(this));
    jLabel1.setFont(new java.awt.Font("幼圆", Font.BOLD, 50));
    jLabel1.setForeground(Color.blue);
    jLabel1.setText("不要打太久哦");
    jLabel1.setBounds(new Rectangle(41, 22, 326, 72));
    jTextField1.setFont(new java.awt.Font("宋体", Font.PLAIN, 30));
    jTextField1.setBounds(new Rectangle(149, 120, 72, 39));
    jLabel2.setFont(new java.awt.Font("幼圆", Font.BOLD, 30));
    jLabel2.setText("分钟");
    jLabel2.setBounds(new Rectangle(226, 114, 91, 49));
    jLabel3.setFont(new java.awt.Font("宋体", Font.PLAIN, 40));
    jLabel3.setForeground(Color.red);
    jLabel3.setBounds(new Rectangle(15, 189, 200, 68));
    contentPane.add(jButton1);
    contentPane.add(jLabel1);
    contentPane.add(jTextField1);
    contentPane.add(jLabel2);
    contentPane.add(jLabel3);
  }

  public void start(){
    timer.schedule(new TimerTask() {
      public void run() {
        playSound();
        //timer.cancel();
      }
      private void playSound() {
        try{
          InputStream in = new FileInputStream("E://煲机//声音//短信08_铃声之家cnwav.wav");
            AudioStream as = new AudioStream(in);
            AudioPlayer.player.start(as);
            jLabel3.setText("不要玩了！");
        }catch(Exception ex){
        };
      }
    }, (int)limittime * 60 * 100,(int)limittime * 60* 100);//使用毫秒计数
  }

  public void jButton1_actionPerformed(ActionEvent e) {
    if(jTextField1.getText().length()!=0){
      limittime = Double.parseDouble(jTextField1.getText());
      jLabel3.setText("开始计时！");
      start();
    }
  }
}


class Frame1_jButton1_actionAdapter
    implements ActionListener {
  private Frame1 adaptee;
  Frame1_jButton1_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

