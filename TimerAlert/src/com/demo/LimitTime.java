package com.demo;

import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Dimension;

public class LimitTime {
  boolean packFrame = false;

  /**
   * Construct and show the application.
   */
  public LimitTime() {
	  
	//关于设置界面的函数
    Frame1 frame = new Frame1();
    // Validate frames that have preset sizes
    // Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();  //调整此窗口的大小，以适合其子组件的首选大小和布局。
    }
    else {
      frame.validate(); //验证此容器及其所有子组件。
    }

    // Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//单个对象中组件的宽度和高度（精确到整数）。
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation( (screenSize.width - frameSize.width) / 2,
                      (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  /**
   * Application entry point.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          /*
           * 这是设置图形界面外观的.java的图形界面外观有3种,默认是java的金属外观,还有就是windows系统,motif系统外观.
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
这是把外观设置成你所使用的平台的外观,也就是你这个程序在哪个平台运行,显示的窗口,对话框外观将是哪个平台的外观.
           */
        }
        catch (Exception exception) {
          exception.printStackTrace();
        }

        new LimitTime();
      }
    });
  }
}