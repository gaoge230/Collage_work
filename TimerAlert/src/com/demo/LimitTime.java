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
	  
	//�������ý���ĺ���
    Frame1 frame = new Frame1();
    // Validate frames that have preset sizes
    // Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();  //�����˴��ڵĴ�С�����ʺ������������ѡ��С�Ͳ��֡�
    }
    else {
      frame.validate(); //��֤���������������������
    }

    // Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//��������������Ŀ�Ⱥ͸߶ȣ���ȷ����������
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
           * ��������ͼ�ν�����۵�.java��ͼ�ν��������3��,Ĭ����java�Ľ������,���о���windowsϵͳ,motifϵͳ���.
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
���ǰ�������ó�����ʹ�õ�ƽ̨�����,Ҳ����������������ĸ�ƽ̨����,��ʾ�Ĵ���,�Ի�����۽����ĸ�ƽ̨�����.
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