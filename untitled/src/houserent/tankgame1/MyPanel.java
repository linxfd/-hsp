package houserent.tankgame1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @version 1.0
 * 坦克大战的绘图区域
 */
public class MyPanel extends JPanel implements KeyListener {
    MyTank mytank = null;
    public MyPanel(){
        mytank=new MyTank(100,100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,1000);//填充矩形，默认黑色
        drawTank(mytank.getX(), mytank.getY(),g,0,0);
    }
    //编写方法，画出坦克
    /**
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向（上下左右）
     * @param type   坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch(type){
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        /**
         * fill3DRect 画3D矩形
         * fillOval 画圆形
         * drawLine 画线段
         */
        switch(direct){
            case 0:
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出炮筒
            default:
                System.out.println("....");
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {//KeyEvent.VK_DOWN就是向下的箭头对应的code
            mytank.setY(mytank.getY() + 10);
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {//向上
            mytank.setY(mytank.getY() - 10);
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {//向左
            mytank.setX(mytank.getX() - 10);
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {//向右
            mytank.setX(mytank.getX() + 10);
        }
        //让面板重绘
        this.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
