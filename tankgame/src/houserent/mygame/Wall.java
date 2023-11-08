package houserent.mygame;

/**
 * @version 1.0
 */
public class Wall {
    private int x;//墙的横坐标
    private int y;//墙的纵坐标
    private boolean live = true ;  //是否存活
    private int type= 0 ;
    public Wall(int x, int y,int  type) {
        this.x = x;
        this.y = y;
        this.type=type;
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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
