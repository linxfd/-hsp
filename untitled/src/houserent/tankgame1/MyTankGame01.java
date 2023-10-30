package houserent.tankgame1;

import javax.swing.*;

/**
 * @version 1.0
 */
public class MyTankGame01 extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        new MyTankGame01();
    }
    public MyTankGame01(){
        mp = new MyPanel();
        this.add(mp);//把面板(就是游戏的绘图区域)
        this.setSize(1000, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
