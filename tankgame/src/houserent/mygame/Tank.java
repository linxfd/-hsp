package houserent.mygame;

import java.util.Vector;

import static houserent.mygame.MyTankGame02.X_MAX_BOUNDARY;
import static houserent.mygame.MyTankGame02.Y_MAX_BOUNDARY;

/**
 * @version 1.0
 * 坦克的父类
 */
public class Tank {
    private int x;//坦克的横坐标
    private int y;//坦克的纵坐标
    private int direct;  //坦克的方向，默认为: 0向上 1 向右 2 向下 3 向左
    private int speed =5;  //每次移动px数
    private boolean live = true ;  //是否存活
    //所有的坦克
    private Vector<Tank> tanks = new Vector<>();

    //所有的墙
    private Vector<Wall> walls = new Vector<>();

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void moveUp(){
        if(y>0 && !isTouchEnemyTank() && !isTouchWall())
            y-=speed;
    }
    public void moveDown(){
        if(y+60<Y_MAX_BOUNDARY && !isTouchEnemyTank() && !isTouchWall())
            y+=speed;
    }
    public void moveRight(){
        if(x+60<X_MAX_BOUNDARY && !isTouchEnemyTank()&& !isTouchWall() )
            x+=speed;
    }
    public void moveLeft(){
        if(x>0 && !isTouchEnemyTank() && !isTouchWall())
            x-=speed;
    }

    public boolean isTouchEnemyTank() {

        //判断当前其他坦克(this) 方向
        switch (this.getDirect()) {
            case 0: //上
                //让当前其他坦克和其它所有的其他坦克比较
                for (int i = 0; i < tanks.size(); i++) {
                    //从vector 中取出一个其他坦克
                    Tank tank = tanks.get(i);
                    //不和自己比较
                    if (tank != this) {
                        //如果其他坦克是上/下
                        //老韩分析
                        //1. 如果其他坦克是上/下 x的范围 [tank.getX(), tank.getX() + 40]
                        //                     y的范围 [tank.getY(), tank.getY() + 60]

                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右上角的坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果其他坦克是 右/左
                        //老韩分析
                        //1. 如果其他坦克是右/左  x的范围 [tank.getX(), tank.getX() + 60]
                        //                     y的范围 [tank.getY(), tank.getY() + 40]
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右上角的坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1: //右
                //让当前坦克和其它所有的其他坦克比较
                for (int i = 0; i < tanks.size(); i++) {
                    //从vector 中取出一个坦克
                    Tank tank = tanks.get(i);
                    //不和自己比较
                    if (tank != this) {
                        //如果其他坦克是上/下
                        //老韩分析
                        //1. 如果其他坦克是上/下 x的范围 [tank.getX(), tank.getX() + 40]
                        //                     y的范围 [tank.getY(), tank.getY() + 60]

                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            //2. 当前坦克 右上角的坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 40
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果其他坦克是 右/左
                        //老韩分析
                        //1. 如果其他坦克是右/左  x的范围 [tank.getX(), tank.getX() + 60]
                        //                     y的范围 [tank.getY(), tank.getY() + 40]
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            //2. 当前坦克 右上角的坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 60
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: //下
                //让当前其他坦克和其它所有的其他坦克比较
                for (int i = 0; i < tanks.size(); i++) {
                    //从vector 中取出一个其他坦克
                    Tank tank = tanks.get(i);
                    //不和自己比较
                    if (tank != this) {
                        //如果其他坦克是上/下
                        //老韩分析
                        //1. 如果其他坦克是上/下 x的范围 [tank.getX(), tank.getX() + 40]
                        //                     y的范围 [tank.getY(), tank.getY() + 60]

                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            //2. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 40
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果其他坦克是 右/左
                        //老韩分析
                        //1. 如果其他坦克是右/左  x的范围 [tank.getX(), tank.getX() + 60]
                        //                     y的范围 [tank.getY(), tank.getY() + 40]
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            //2. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 60
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: //左
                //让当前其他坦克和其它所有的其他坦克比较
                for (int i = 0; i < tanks.size(); i++) {
                    //从vector 中取出一个其他坦克
                    Tank tank = tanks.get(i);
                    //不和自己比较
                    if (tank != this) {
                        //如果其他坦克是上/下
                        //老韩分析
                        //1. 如果其他坦克是上/下 x的范围 [tank.getX(), tank.getX() + 40]
                        //                     y的范围 [tank.getY(), tank.getY() + 60]

                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY() ]
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果其他坦克是 右/左
                        //老韩分析
                        //1. 如果其他坦克是右/左  x的范围 [tank.getX(), tank.getX() + 60]
                        //                     y的范围 [tank.getY(), tank.getY() + 40]
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY() ]
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return  false;
    }
    public boolean isTouchWall() {

        //坦克的方向，默认为: 0向上 ,1 向右 ,2 向下 ,3 向左
        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < walls.size(); i++) {
                    Wall wall = walls.get(i);
                    if (wall.isLive()) {
                        //坦克的左上角
                        if (this.getX() >= wall.getX() && this.getX() <= wall.getX() + 35
                                && this.getY() >= wall.getY() && this.getY() <= wall.getY()+35){

                            return true;
                        }

                        if (this.getX()+40 >= wall.getX() && this.getX()+40 <= wall.getX() + 35
                                && this.getY() >= wall.getY() && this.getY() <= wall.getY()+35 ) {

                            return true;
                        }
                    }
                }
                break;
            case 2:

                for (int i = 0; i < walls.size(); i++) {
                    Wall wall = walls.get(i);
                    if (wall.isLive()) {
                        //坦克的左上角
                        if (this.getX() >= wall.getX() && this.getX() <= wall.getX() + 35
                                && this.getY()+60 >= wall.getY() && this.getY()+60 <= wall.getY()+35){

                            return true;
                        }

                        if (this.getX()+40 >= wall.getX() && this.getX()+40 <= wall.getX() + 35
                                && this.getY()+60 >= wall.getY() && this.getY()+60 <= wall.getY()+35 ) {

                            return true;
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < walls.size(); i++) {
                    Wall wall = walls.get(i);
                    if (wall.isLive()) {
                        //坦克的左上角
                        if (this.getX()+60 >= wall.getX() && this.getX()+60 <= wall.getX() + 35
                                && this.getY() >= wall.getY() && this.getY() <= wall.getY()+35){

                            return true;
                        }

                        if (this.getX()+60 >= wall.getX() && this.getX()+60 <= wall.getX() + 35
                                && this.getY()+40 >= wall.getY() && this.getY()+40 <= wall.getY()+35 ) {

                            return true;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < walls.size(); i++) {
                    Wall wall = walls.get(i);
                    if (wall.isLive()) {
                        //坦克的左上角
                        if (this.getX() >= wall.getX() && this.getX() <= wall.getX()
                                && this.getY() >= wall.getY() && this.getY() <= wall.getY() + 35)
                            return true;
                        if (this.getX() >= wall.getX() && this.getX() <= wall.getX()+35
                                && this.getY()+40 >= wall.getY() && this.getY()+40 <= wall.getY() + 35)
                            return true;
                    }
                }
                break;
        }

        return false;

    }

    public void setTanks(Vector tanks) {
        this.tanks=tanks;
    }
    public void setTank(MyTank tank) {
        tanks.add(tank);
    }
    public int getDirect() {
        return direct;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setDirect(int direct) {
        this.direct = direct;
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

    public Vector<Wall> getWall() {
        return walls;
    }

    public void setWall(Vector<Wall> walls) {
        this.walls = walls;
    }
}
