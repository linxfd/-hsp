package houserent.mygame;

import java.util.Vector;

/**
 * @version 1.0
 * 敌人的坦克
 */
public class EnemyTank extends Tank implements Runnable{
    Shot shot = null;
    //使用Vector保存多个子弹
    Vector<Shot> shots= new Vector<>();

    public EnemyTank(int x, int y){
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            //判断
            if (isLive() && shots.size()<2) {
                //判断坦克的方向，创建对应的子弹
                switch (getDirect()) {
                    case 0:
                        shot= new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        shot = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2: //向下
                        shot = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3://向左
                        shot = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(shot);
                //启动
                new Thread(shot).start();
            }
            for(int i=0; i<5; i++) {  //每次移动5次同方向的才考虑换方向
                switch (getDirect()) {
                    case 0:
                        moveUp();

                        break;
                    case 1:
                        moveRight();
                        break;
                    case 2:
                        moveDown();

                        break;
                    case 3:
                        moveLeft();

                        break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //停1秒，再移动

            //通过设置坦克的方向来移动坦克
            setDirect((int) (Math.random() * 4));
            if(!isLive()){
                break;
            }
//            if(shot!=null && !shot.isLive()){
//                shot = new Shot(getX() + 20, getY() + 60, getDirect());
//                new Thread(shot).start();  //自动子弹线程
//            }
        }
    }
}
