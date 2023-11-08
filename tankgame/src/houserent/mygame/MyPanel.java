package houserent.mygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import static houserent.mygame.MyTankGame02.X_MAX_BOUNDARY;
import static houserent.mygame.MyTankGame02.Y_MAX_BOUNDARY;


/**
 * @version 1.0
 * 坦克大战的绘图区域（面板，绘画区）
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    MyTank mytank = null;

    //定义敌人的坦克，放入Vector（线程安全的）
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义墙
    Vector<Wall> walls = new Vector<>();

    int enemyTanksSize = 6; //敌人坦克的数量
    //存放炸弹
    Vector<Bomb> bombs = new Vector<>();
    //定义三张图片，显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(char key) {

        //将MyPanel对象的 enemyTanks 设置给 Recorder 的 enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        //初始化自己的坦克
        mytank = new MyTank(100, 500);
        Recorder.setMytank(mytank);
        //先判断记录的文件是否存在
        //如果不存在，修改key为0
        File file = new File(Recorder.getRecotderfile()+"");
        if (!file.exists()) {
            System.out.println("没有存档，开始新游戏");
            key = '0';
        }
        if (key == '0') {
            //初始化敌人的坦克
            for (int i = 0; i < enemyTanksSize; i++) {
                EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                enemyTank.setDirect(2);  //默认敌人的炮筒向下
                //给该enemyTank 加入一颗子弹
                Shot shot = new Shot(enemyTank.getX() + 60, enemyTank.getY() + 20, enemyTank.getDirect());
                enemyTank.shots.add(shot); //将敌人的子弹装入子弹集合
                //敌人子弹线程
                new Thread(shot).start();
                //启动敌人坦克线程
                new Thread(enemyTank).start();
                enemyTanks.add(enemyTank); //将敌人的坦克加入Vector中
                //将所有敌人Tank都加上敌人的”引用“，不是单纯的数量，所以Tank中的tanks==enemtTanks
                enemyTank.setTanks(enemyTanks);
//            enemyTank.setTank(mytank);  //所以就不可以添加mythank
                //给敌人坦克加入墙
                enemyTank.setWall(walls);

            }
            //将所有敌人Tank都加上敌人的”引用“，不是单纯的数量，所以Tank中的tanks==enemtTanks
            mytank.setTanks(enemyTanks);
        } else if (key == '1') {
            Recorder.getNodesAndEnemyTankRec();
            Vector<Noder> noders = Recorder.getNones();
            mytank.setX(noders.get(0).getX());
            mytank.setY(noders.get(0).getY());
            for (int i = 1; i < noders.size(); i++) {
                Noder noder = noders.get(i);
                EnemyTank enemyTank = new EnemyTank(noder.getX(), noder.getY());
                enemyTank.setDirect(noder.getDirect());
                //给该enemyTank 加入一颗子弹
                Shot shot = new Shot(enemyTank.getX() + 60, enemyTank.getY() + 20, enemyTank.getDirect());
                enemyTank.shots.add(shot); //将敌人的子弹装入子弹集合
                //敌人子弹线程
                new Thread(shot).start();
                //启动敌人坦克线程
                new Thread(enemyTank).start();
                enemyTanks.add(enemyTank); //将敌人的坦克加入Vector中
                //将所有敌人Tank都加上敌人的”引用“，不是单纯的数量，所以Tank中的tanks==enemtTanks
                enemyTank.setTanks(enemyTanks);
//              enemyTank.setTank(mytank);  //所以就不可以添加mythank.
                //给敌人坦克加入墙
                enemyTank.setWall(walls);
            }
            //将所有敌人Tank都加上敌人的”引用“，不是单纯的数量，所以Tank中的tanks==enemtTanks
            mytank.setTanks(enemyTanks);
        }

        //初始化游戏地图
        GameMap.drawWall(walls);
        //给我的坦克加入墙
        mytank.setWall(walls);

        //初始化爆炸图片
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resources/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resources/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resources/bomb_3.gif"));
        //先加入一个爆炸，加载图片的导入（没有会第一个爆炸效果不显示）
//        bombs.add(new Bomb(0, 0));

        //进入游戏播放开始音乐
        new AePlayWave("src/resources/111.wav").start();
    }
    //画我的成绩
    public void showInfo(Graphics g) {
        //画玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您本局积累击毁敌人坦克", X_MAX_BOUNDARY+5, 30);
        drawTank(X_MAX_BOUNDARY+50, 60, g, 0, 1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum() + "", X_MAX_BOUNDARY+100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, X_MAX_BOUNDARY, Y_MAX_BOUNDARY);//填充矩形，默认黑色
        //如果bombs集合中有集合，就画爆炸效果
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.getLife() > 6) {
                g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.getLife() > 3) {
                g.drawImage(image2, bomb.getX(), bomb.getY(), 60, 60, this);
            } else {
                g.drawImage(image3, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            //炸弹生命周期减少
            bomb.lifeDown();
            //当bomb life为0 就从bombs集合中移除
            if (bomb.getLife() == 0) {
                bombs.remove(bomb);
            }
        }
        //画自己的坦克
        if (mytank.isLive() && mytank != null) {
            drawTank(mytank.getX(), mytank.getY(), g, mytank.getDirect(), 0);
        } else {
            System.out.println("Game over 游戏结束！");

        }
        //画自己的子弹
//        for(Shot shot : mytank.shots) {
        for (int i = 0; i < mytank.shots.size(); i++) {
            Shot shot = mytank.shots.get(i);
            if (shot != null && shot.isLive()) {
//                System.out.println("我们子弹被绘制...");
                g.setColor(Color.GREEN);
                g.fillOval(shot.getX(), shot.getY(), 4, 4);
            } else {
                //将我们子弹已死，从mytank.shots移出
                mytank.shots.remove(shot);
            }
        }
        //画敌人的坦克
        for (EnemyTank enemyTank : enemyTanks) {
            if (enemyTank.isLive()) {
                //画敌人的坦克
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画敌人的子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出敌人的子弹
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive()) {
                        g.draw3DRect(shot.getX(), shot.getY(), 2, 2, false);
                    } else {
                        //敌人子弹已死，从enemyTank.shots移出
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
        //画我的成绩
        showInfo(g);
        //画shatterableWall的可击碎的墙
        for (Wall wall : walls) {
            drawWall(wall.getX(),wall.getY(),g,wall.getType());
        }
    }
    //编写方法，画出坦克

    /**
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向 0: 向上 1 向右 2 向下 3 向左
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
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
        //direct 表示方向(0: 向上 1 向右 2 向下 3 向左 )
        switch (direct) {
            case 0:  //向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出炮筒
                break;
            case 1:  //向右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出炮筒
                break;
            case 2:  //向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3:  //向左
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//画出炮筒
                break;
            default:
                System.out.println("输入错误不是0~3");
        }
    }
    public void drawWall(int x, int y, Graphics g,int  type){
        //0是可击碎的墙
        //1是不可击碎的墙
        //2是水，坦克不可以过，但子弹可以飞过
        switch (type) {
            case 0:
                g.setColor(Color.PINK);
                break;
            case 1:
                g.setColor(Color.white);
                break;
            case 2:
                g.setColor(Color.BLUE);
                break;
        }
        g.fill3DRect(x,y,35, 35, false);
    }

    //判断是否击中了我的坦克
    public void hitMyTank() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.shots.size() != 0) {
                hitTank(enemyTank.shots, mytank);

            }
        }
    }

    //判断是否击中了敌人坦克
    public void hitEnemyTank() {
        if (mytank.shots.size() != 0) {
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(mytank.shots, enemyTank);
            }
        }
    }

    //判断判断子弹是否击中坦克Tank
    public void hitTank(Vector<Shot> shots, Tank tank) {
        Shot fa = null;
        for (int i = 0; i < shots.size(); i++) {
            Shot s = shots.get(i);
            switch (tank.getDirect()) {
                case 0:
                case 2:
                    if (s.getX() > tank.getX() && s.getX() < tank.getX() + 40
                            && s.getY() > tank.getY() && s.getY() < tank.getY() + 60) {
                        //爆炸声
                        new AePlayWave("src/resources/222.wav").start();
                        s.setLive(false);
                        tank.setLive(false);
                        //击中坦克时，创建Bomb对象，加入bombs集合
                        Bomb b = new Bomb(tank.getX(), tank.getY());
                        bombs.add(b);
                        //如果有子弹击中敌人就将该坦克从enemyTanks集合中移出
                        if (tank instanceof EnemyTank) {
                            //击毁敌人数量加一
                            Recorder.addAllEnemyTankNum();
                            enemyTanks.remove(tank);
                        }
                    }
                    break;
                case 1:
                case 3:
                    if (s.getX() > tank.getX() && s.getX() < tank.getX() + 60
                            && s.getY() > tank.getY() && s.getY() < tank.getY() + 40) {
                        //爆炸声
                        new AePlayWave("src/resources/222.wav").start();
                        s.setLive(false);
                        tank.setLive(false);
                        //击中坦克时，创建Bomb对象，加入bombs集合
                        Bomb b = new Bomb(tank.getX(), tank.getY());
                        bombs.add(b);
                        //如果有子弹击中敌人就将该坦克从enemyTanks集合中移出
                        if (tank instanceof EnemyTank) {
                            //击毁敌人数量加一
                            Recorder.addAllEnemyTankNum();
                            enemyTanks.remove(tank);
                        }
                    }
                    break;
            }
        }
    }
    public void hitMyWall(){
        if (mytank.shots.size() != 0) {
            for (int i = 0; i < walls.size(); i++) {
                Wall shatterableWall = walls.get(i);
                hitWall(mytank.shots, shatterableWall);
            }
        }
    }
    public void hitEnemyWall(){
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.shots.size() != 0) {
                for (int j = 0; j < walls.size(); j++) {
                    Wall shatterableWall = walls.get(j);
                    hitWall(enemyTank.shots, shatterableWall);
                }
            }
        }
    }
    public void hitWall(Vector<Shot> shots, Wall wall) {
        if (wall.getType() == 2){
            return;
        }
        Shot fa = null;
        for (int i = 0; i < shots.size(); i++) {
            Shot s = shots.get(i);
            if (s.getX() > wall.getX()-5 && s.getX() < wall.getX() + 35
                    && s.getY() > wall.getY()-5 && s.getY() < wall.getY() + 35) {
                //爆炸声
                new AePlayWave("src/resources/222.wav").start();
                s.setLive(false);
                if(wall.getType()!=1){
                    wall.setLive(false);
                    //击中墙时，创建Bomb对象，加入bombs集合
                    Bomb b = new Bomb(wall.getX(), wall.getY());
                    bombs.add(b);
                    //如果有子弹击中敌人就将该坦克从enemyTanks集合中移出
                    if (wall instanceof Wall) {
                        walls.remove(wall);
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //direct 表示方向(0: 向上 1 向右 2 向下 3 向左 )
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {//KeyEvent.VK_DOWN就是向下的箭头对应的code
            mytank.moveDown();
            mytank.setDirect(2);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {//向上
            mytank.moveUp();
            mytank.setDirect(0);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//向左
            mytank.moveLeft();
            mytank.setDirect(3);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//向右
            mytank.moveRight();
            mytank.setDirect(1);
        }

        //如果用户按下的是J,就发射我们的子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("用户按下了J, 开始射击.");
            mytank.shotMyTank();
//           mytank.shots.add(mytank.shotMyTank());
            System.out.println(mytank.shots.size());
        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
//        //界面开始，就绘制敌人的子弹
//        for (int i = 0; i <enemyTanksSize;i++){
//            enemyTanks.get(i).shotEnemyTank();
//        }
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException("MyPanel.run() is Exception");
            }
            //判断是否击中了敌人坦克
            hitEnemyTank();
            //判断敌人是否击中我的坦克
            hitMyTank();
            //判断我是否击中了shatterableWall可击碎的墙
            hitMyWall();
            //判断敌人是否击中了shatterableWall可击碎的墙
            hitEnemyWall();
            //让面板重绘
            this.repaint();
        }
    }
}
