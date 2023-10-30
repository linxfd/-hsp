package houserent.tankgame2;

/**
 * @version 1.0
 */
public class Noder {
    private int x;//坦克的横坐标
    private int y;//坦克的纵坐标
    private int direct;  //坦克的方向，默认为0: 向上 1 向右 2 向下 3 向左

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
}
