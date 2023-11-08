package houserent.mygame;

import java.util.Vector;

/**
 * @version 1.0
 * 我的坦克
 */
public class MyTank extends Tank {
    //定义一个Shot对象, 表示一个射击(线程)
    Shot shot = null;
    //使用Vector保存多个子弹
    Vector<Shot> shots= new Vector<>();
    public MyTank(int x, int y) {
        super(x, y);
    }
    //创建 (子弹)Shot 对象, 根据当前Hero对象的位置和方向来创建Shot
    public void shotMyTank(){

        switch(getDirect()){//得到Hero对象方向
            case 0:
                shot = new Shot(getX()+20,getY(),getDirect());
                break;
            case 1:
                shot = new Shot(getX()+60,getY()+20,getDirect());
                break;
            case 2:
                shot = new Shot(getX()+20,getY()+60,getDirect());
                break;
            case 3:
                shot = new Shot(getX(),getY()+20,getDirect());
                break;
        }
        shots.add(shot);
        //启动敌人的Shot线程
        new Thread(shot).start();
    }
}
