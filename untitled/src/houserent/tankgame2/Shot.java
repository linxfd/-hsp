package houserent.tankgame2;

import static houserent.tankgame2.MyTankGame02.X_MAX_BOUNDARY;
import static houserent.tankgame2.MyTankGame02.Y_MAX_BOUNDARY;

/**
 * @version 1.0
 * 我们的子弹
 */
public class Shot implements Runnable {
    private int x;
    private int y;
    private int direct=0;//子弹方向,0: 向上 1 向右 2 向下 3 向左
    private int speed=5;//子弹的速度
    private boolean isLive=true;  //子弹是否还存活

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    public boolean isShotTouchWall(){

        return false;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch(direct){
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }
//            System.out.println("x="+x+" y="+y);

            if(!(x>0&&x<X_MAX_BOUNDARY && y>0&&y<Y_MAX_BOUNDARY )){
                isLive=false;
//                System.out.println("子弹结束");
                break;
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }



}
