package houserent.tankgame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * @version 1.1
 * 游戏的绘图区域（游戏窗口）
 */
public  class MyTankGame02 extends JFrame {
    MyPanel mp = null;
    public static final int X_MAX_BOUNDARY = 1000; //设置面板的x和y的最大值
    public static final int Y_MAX_BOUNDARY = 600; //设置面板的x和y的最大值
    public static  char key = '0';

    public static void main(String[] args) {
        new MyTankGame02();
    }
    //准备游戏的界面
    public MyTankGame02() {
        //加入背景图片
        ImageIcon bg=new ImageIcon("out/production/untitled/beijin.png");
        JLabel label=new JLabel(bg);
        label.setSize(bg.getIconWidth(),bg.getIconHeight());
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        //2.把窗口面板设为内容面板并设为透明、流动布局。
        JPanel pan=(JPanel)this.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(new FlowLayout());
        JButton button1 = new JButton("新游戏");
        JButton button2 = new JButton("继续游戏");
        this.setTitle("坦克大战");
        this.setBounds(200,200,bg.getIconWidth(),bg.getIconHeight());
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.add(button1);
        this.add(button2);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGame();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                key='1';
                setGame();
            }
        });
    }
    //游戏中的界面
    public void setGame(){
        closeThis();
        JFrame frame = new JFrame();
        //设置在屏幕的位置
        mp = new MyPanel(key);
        new Thread(mp).start();
        frame.setTitle("坦克大战");
        frame.add(mp);//把面板(就是游戏的绘图区域)加入窗口
        frame.setSize(X_MAX_BOUNDARY + 15 + 300, Y_MAX_BOUNDARY + 35);
        frame.addKeyListener(mp);  //在窗口中加入监听者
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //按×时，程序关闭
        frame.setVisible(true);  //窗口可见
        //在JFrame 中增加相应关闭窗口的处理
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //关闭窗口时，保存文件
                Recorder.keepRecord();
                System.exit(0);
            }
        });

    }
    public void closeThis() {
        this.dispose();
    }
}
