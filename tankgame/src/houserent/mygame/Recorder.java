package houserent.mygame;

import java.io.*;
import java.util.Vector;

/**
 * @version 1.0
 * 存档类
 */
public class Recorder {
    private static int allEnemyTankNum = 0;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    private static String recotderfile = "src/houserent/mygame/myRecorder.txt";
    private static Vector<EnemyTank> enemyTanks = null;
    private static MyTank mytank = null;

    private static Vector<Noder> noders = null;

    public static void keepRecord() {
        try {
            bw = new BufferedWriter(fw = new FileWriter(recotderfile));
            String str = null;
            bw.write(allEnemyTankNum + "\r\n");
            str = mytank.getX() + " " + mytank.getY() + " " + mytank.getDirect();
            bw.write(str + "\r\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive()) {
                    str = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    bw.write(str + "\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void getNodesAndEnemyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recotderfile));
            String rline = null;
            allEnemyTankNum = Integer.parseInt(br.readLine());
            noders = new Vector<>();
            while ((rline = br.readLine()) != null) {
                String sy[] = rline.split(" ");
                Noder noder = new Noder();
                noder.setX(Integer.parseInt(sy[0]));
                noder.setY(Integer.parseInt(sy[1]));
                noder.setDirect(Integer.parseInt(sy[2]));
                noders.add(noder);
            }
        } catch (Exception e) {

        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    public static void addAllEnemyTankNum() {
        allEnemyTankNum++;
    }

    public static Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static Vector<Noder> getNones() {
        return noders;
    }

    public static void setNones(Vector<Noder> noders) {
        Recorder.noders = noders;
    }

    public static MyTank getMytank() {
        return mytank;
    }

    public static void setMytank(MyTank mytank) {
        Recorder.mytank = mytank;
    }

    public static String getRecotderfile() {
        return recotderfile;
    }
}
