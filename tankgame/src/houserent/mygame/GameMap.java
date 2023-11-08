package houserent.mygame;

import java.util.Vector;

/**
 * @version 1.0
 * 游戏的地图（墙）
 */
public class GameMap {
    public static final void drawWall(Vector<Wall> walls){
        //木墙
        for (int i = 0; i < 800; i+=35) {
            Wall shatterableWall = new Wall(i + 50, 80,0);
            walls.add(shatterableWall);
        }
        for (int i = 200; i < 600; i+=35) {
            Wall shatterableWall = new Wall(i + 50, 500,0);
            walls.add(shatterableWall);
        }
        for (int i = 0; i < 600; i+=35) {
            Wall shatterableWall = new Wall(i + 50, 300,0);
            walls.add(shatterableWall);
        }
        //钢墙
        for (int i = 0; i < 300; i+=35) {
            Wall shatterableWall = new Wall(i + 50, 400,1);
            walls.add(shatterableWall);
        }
        //钢墙
        for (int i = 500; i < 800; i+=35) {
            Wall shatterableWall = new Wall(i + 50, 200,1);
            walls.add(shatterableWall);
        }
        //水
        for (int i = 0; i < 100; i+=35) {
            Wall shatterableWall = new Wall(i + 50, 150,2);
            walls.add(shatterableWall);
        }
        for (int i = 300; i < 400; i+=35) {
            Wall shatterableWall = new Wall(i + 50, 150,2);
            walls.add(shatterableWall);
        }
        for (int i = 500; i < 800; i+=35) {
            Wall shatterableWall = new Wall(i + 50, 150,2);
            walls.add(shatterableWall);
        }
    }
}
