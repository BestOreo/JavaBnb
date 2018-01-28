/**
 * Created by geshuaiqi on 2017/11/30.
 */
import sun.misc.resources.Messages_pt_BR;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class TimerTest {
    static int gamestatus = 0;
    static class MyTimerTask1 extends TimerTask {
        public void run() {
            new Timer().schedule(new MyTimerTask2(), 2000);
        }
    }
    static class MyTimerTask2 extends TimerTask {
        public void run() {
            new Timer().schedule(new MyTimerTask2(), 2000);
        }
    }

    static class StartMusic extends TimerTask {
        public void run() {
            try
            {
                System.out.println("Start Music");
                URL MusicURL;
                File MusicFile = new File("sound/start.wav");
                MusicURL = MusicFile.toURL();
                AudioClip clip=(AudioClip) Applet.newAudioClip( MusicURL);
                clip.play();
            }catch(Exception mue){
                System.out.println(mue.toString());
            }
        }
    }

    // 线程播放炸弹声音
    static class BumbSound extends TimerTask {
        public void run() {
            try
            {
                URL MusicURL;
                File MusicFile = new File("sound/explode.wav");
                MusicURL = MusicFile.toURL();
                AudioClip clip=(AudioClip) Applet.newAudioClip( MusicURL);
                clip.play();
            }catch(Exception mue){
                System.out.println(mue.toString());
            }
        }
    }

    // 线程播放点击鼠标的声音
    static class MouseClick extends TimerTask {
        public void run() {
            try
            {
                URL MusicURL;
                File MusicFile = new File("sound/appear.wav");
                MusicURL = MusicFile.toURL();
                AudioClip clip=(AudioClip) Applet.newAudioClip( MusicURL);
                clip.play();
            }catch(Exception mue){
                System.out.println(mue.toString());
            }
        }
    }


    // 线程播死亡的声音
    static class RoleDieMusic extends TimerTask {
        public void run() {
            try
            {
                URL MusicURL;
                File MusicFile = new File("sound/die.wav");
                MusicURL = MusicFile.toURL();
                AudioClip clip=(AudioClip) Applet.newAudioClip( MusicURL);
                clip.play();
            }catch(Exception mue){
                System.out.println(mue.toString());
            }
        }
    }

    // 线程播放获得道具的声音
    static class GetGiftSound extends TimerTask {
        public void run() {
            try
            {
                URL MusicURL;
                File MusicFile = new File("sound/get.wav");
                MusicURL = MusicFile.toURL();
                AudioClip clip=(AudioClip) Applet.newAudioClip( MusicURL);
                clip.play();
            }catch(Exception mue){
                System.out.println(mue.toString());
            }
        }
    }


    static AudioClip BackgroundMusicClip; // 背景音乐

    // 播放背景音乐
    static class MyMusic extends TimerTask {
        public void run() {
            try
            {
                URL MusicURL;
                File MusicFile = new File("sound/bg.wav");
                MusicURL = MusicFile.toURL();
                BackgroundMusicClip=(AudioClip) Applet.newAudioClip( MusicURL);
                BackgroundMusicClip.loop(); // 循环播放
                //Thread.sleep(20000);
            }catch(Exception mue){
                System.out.println(mue.toString());
            }
            System.out.println("End Music");

            //new Timer().schedule(new TimerTest.MyMusic(),0);
        }
    }

    // 游戏开始动画
    static class GameStartAnimation extends TimerTask {
        public void run() {
            while (Map.GameStart > 0) {
                try {
                    System.out.println("游戏开始:Map.GameStart");
                    // 开场动画
                    Map.mapcanvas.Role1.outImageBuffer = Map.mapcanvas.BabyStartImg[Map.GameStart - 1];
                    Map.mapcanvas.Role2.outImageBuffer = Map.mapcanvas.BabyStartImg[Map.GameStart - 1];
                    Map.GameStart--;
                    Timer next = new Timer();
                    Thread.sleep(100);
                    if (Map.GameStart == 0) {
                        Map.mapcanvas.Role1.outImageBuffer = Player.ImageBuffer[1][0];
                        Map.mapcanvas.Role2.outImageBuffer = Player.ImageBuffer[1][0];
                        System.out.println("开始动画结束");
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    static class MyTimerGift extends TimerTask {
        public void run() {
            Map.mapcanvas.GiftTime = (Map.mapcanvas.GiftTime +1)%3;
        }
    }




    /*
    炸弹定时器
     */

    static class MyTimerBump extends TimerTask {

        void SetInterLinkBump(int ci,int cj, int init_i, int init_j){
            if(cj > init_j)
                Map.BUMB_INFO[cj][ci] = 4;
            else if(cj == init_j && ci >= init_i){
                Map.BUMB_INFO[cj][ci] = 4;
            }
            else
                Map.BUMB_INFO[cj][ci] = 3;

            int wavelength = Map.mapcanvas.Role1.getWaveLength();
            for(int i=1;i<=wavelength;i++){
                if(ci+i<15 && Map.BUMB_INFO[cj][ci+i]>4){
                    SetInterLinkBump(ci+i,cj,init_i,init_j);
                }
                if(ci-i>=0 && Map.BUMB_INFO[cj][ci-i]>4){
                    SetInterLinkBump(ci-i,cj,init_i,init_j);
                }
                if(cj-i>=0 && Map.BUMB_INFO[cj-i][ci]>4){
                    SetInterLinkBump(ci,cj-i,init_i,init_j);
                }
                if(cj+i<13 && Map.BUMB_INFO[cj+i][ci]>4){
                    SetInterLinkBump(ci,cj+i,init_i,init_j);
                }
            }
            return;
        }

        // 炸毁箱子随机出现道具，箱子分两种
        int DestroyBox(int boxi,int boxj){
            Random random = new Random();
            int gift = 0;

            if(random.nextInt(2) == 0){
                int nextkind = random.nextInt(12);
                if( nextkind < 7){
                    gift = random.nextInt(3)+1;
                }
                else if( nextkind >=7 && nextkind<9){
                    gift = random.nextInt(4)+3;
                }
                else if(nextkind>=9 && nextkind<12){
                    gift = random.nextInt(4)+6;
                }
            }



            if(Map.mapcanvas.MAP_INFO[boxj][boxi] == 7){
                if(boxi >= 6 && boxi <= 8)
                    Map.mapcanvas.MAP_INFO[boxj][boxi] = 1 + ((boxi == 7)?1:0);
                else
                    Map.mapcanvas.MAP_INFO[boxj][boxi] = 0;

                Map.GIFT_INFO[boxj][boxi] = gift;
                return 1;
            }
            if(Map.mapcanvas.MAP_INFO[boxj][boxi] >= 8 && Map.mapcanvas.MAP_INFO[boxj][boxi] <= 10){
                Map.mapcanvas.MAP_INFO[boxj][boxi] = 0;
                Map.GIFT_INFO[boxj][boxi] = gift;
                return 1;
            }
            return 0;
        }

        // 水平方向障碍物检测
        boolean HCheck(int Bumbi,int Bumbj, int nowi, int nowj){
            if(nowi > Bumbi){
                for(int i=Bumbi+1; i<nowi;i++){
                    if(Map.mapcanvas.MAP_INFO[nowj][i] >=3 && Map.mapcanvas.MAP_INFO[nowj][i] <= 6){
                        return false;
                    }
                    if(i >= Bumbi+1 && Map.mapcanvas.MAP_INFO[nowj][i] >=7 && Map.mapcanvas.MAP_INFO[nowj][i] <= 10){
                        return false;
                    }
                }
                return true;
            }
            else
            {
                for(int i=Bumbi-1; i>nowi;i--){
                    if(Map.mapcanvas.MAP_INFO[nowj][i] >=3 && Map.mapcanvas.MAP_INFO[nowj][i] <= 6){
                        return false;
                    }
                    if(i <= Bumbi-1 && Map.mapcanvas.MAP_INFO[nowj][i] >=7 && Map.mapcanvas.MAP_INFO[nowj][i] <= 10){
                        return false;
                    }
                }
                return true;
            }
        }

        // 竖直方向障碍物检测
        boolean VCheck(int Bumbi,int Bumbj, int nowi, int nowj){
            if(nowj > Bumbj){
                for(int j=Bumbj+1; j<nowj;j++){
                    if(Map.mapcanvas.MAP_INFO[j][nowi] >=3 && Map.mapcanvas.MAP_INFO[j][nowi] <= 6){
                        return false;
                    }
                    if(j >= Bumbj+1 && Map.mapcanvas.MAP_INFO[j][nowi] >=7 && Map.mapcanvas.MAP_INFO[j][nowi] <= 10){
                        return false;
                    }
                }
                return true;
            }
            else
            {
                for(int j=Bumbj-1; j>nowj;j--){
                    if(Map.mapcanvas.MAP_INFO[j][nowi] >=3 && Map.mapcanvas.MAP_INFO[j][nowi] <= 6){
                        return false;
                    }
                    if(j <= Bumbj-1 && Map.mapcanvas.MAP_INFO[j][nowi] >=7 && Map.mapcanvas.MAP_INFO[j][nowi] <= 10){
                        return false;
                    }
                }
                return true;
            }
        }

        // 检测人物是否被水波炸到
        void CheckRole(int wavei, int wavej){
            int leftx = wavei*50 + 5;
            int lefty = wavej*50 + 5;
            int rightx = leftx+40;
            int righty = lefty+40;

            int role1x = Map.mapcanvas.Role1.x + 25;
            int role1y = Map.mapcanvas.Role1.y + 25;

            int role2x = Map.mapcanvas.Role2.x + 25;
            int role2y = Map.mapcanvas.Role2.y + 25;
            // 稍微放宽点要求
            if ( (role1x>=leftx && role1x<=rightx && role1y>=lefty && role1y<=righty) ){
                if(Map.mapcanvas.Role1.Pet == 0)    // 宠物可以抵一条命
                    Map.mapcanvas.Role1.status = 25;
                else
                    Map.mapcanvas.Role1.Pet = 0;
                System.out.println("Dead1");
            }
            if ( (role2x>=leftx && role2x<=rightx && role2y>=lefty && role2y<=righty) ){
                if(Map.mapcanvas.Role2.Pet == 0)    // 宠物可以抵一条命
                    Map.mapcanvas.Role2.status = 25;
                else
                    Map.mapcanvas.Role2.Pet = 0;
                System.out.println("Dead2");
            }

        }



        public void run() {
            for(int j=0;j<13;j++) {
                for (int i = 0; i < 15; i++) {

                    int wavelength = Map.mapcanvas.Role1.getWaveLength();
                    if(Map.BUMB_INFO[j][i] > 0)
                    {
                        int NowBumbInfo = Map.BUMB_INFO[j][i];

                        if(Map.BUMB_INFO[j][i] == 4){
                            //连环爆炸预备
                            SetInterLinkBump(i, j,i,j);
                        }

                        if(Map.BUMB_INFO[j][i] == 3){
                            Map.mapcanvas.count = 3;
                            new Timer().schedule(new TimerTest.BumbSound(),0); // 爆炸声音
                            if(Map.BUMB_ROLE[j][i] == 1){
                                Map.mapcanvas.Role1.bumbAbility++; // 恢复炸弹能力
                                Map.BUMB_ROLE[j][i] = 0;
                            }
                            if(Map.BUMB_ROLE[j][i] == 2) {
                                Map.mapcanvas.Role2.bumbAbility++; // 恢复炸弹能力
                                Map.BUMB_ROLE[j][i] = 0;
                            }
                        }
                        if(Map.BUMB_INFO[j][i] <= 3) {
                            if(Map.mapcanvas.count > 0)
                                Map.mapcanvas.count--;
                            //System.out.println("爆炸");

                            Map.EXPLOSION_INFO[j][i] = 7;

                            if(Map.BUMB_INFO[j][i] == 1) // 检测是否炸到
                                CheckRole(i,j);
                           // Map.mapcanvas.repaint();
                            int temp = 0;


                            // 当水波很长时，不允许穿透爆炸
                            int checkOnlyDestroyOneBoxLeft = 0;
                            int checkOnlyDestroyOneBoxRight = 0;
                            int checkOnlyDestroyOneBoxUp = 0;
                            int checkOnlyDestroyOneBoxDown = 0;
                            // 画爆炸水波，水波边缘另外处理，水波中央另外处理,数字123竖直方向图片，456水平方向图片，7中央图片
                            while(wavelength > 0){
                                temp++;
                                if( i-temp>=0 && ( Map.mapcanvas.MAP_INFO[j][i-temp] > 6 ||  Map.mapcanvas.MAP_INFO[j][i-temp] < 3 ) )
                                {
                                    if(temp == 1 || HCheck(i,j,i-temp,j)) {
                                        if (wavelength == 1 || i - temp == 0) {
                                            Map.EXPLOSION_INFO[j][i - temp] = 4;
                                        } else {
                                            Map.EXPLOSION_INFO[j][i - temp] = 5;
                                        }
                                        if(Map.BUMB_INFO[j][i] == 1 && checkOnlyDestroyOneBoxLeft == 0) {
                                            checkOnlyDestroyOneBoxLeft = DestroyBox(i - temp, j);
                                            CheckRole(i-temp,j); // 检测是否炸到人物
                                        }
                                    }
                                }
                                if( i+temp<15 && ( Map.mapcanvas.MAP_INFO[j][i+temp] > 6 ||  Map.mapcanvas.MAP_INFO[j][i+temp] < 3 ) ){
                                    if(temp == 1 || HCheck(i,j,i+temp,j)) {
                                        if (wavelength == 1 || i + temp == 14) {
                                            Map.EXPLOSION_INFO[j][i + temp] = 6;
                                        } else {
                                            Map.EXPLOSION_INFO[j][i + temp] = 5;
                                        }
                                        if(Map.BUMB_INFO[j][i] == 1 && checkOnlyDestroyOneBoxRight == 0) {
                                            checkOnlyDestroyOneBoxRight = DestroyBox(i + temp, j);
                                            CheckRole(i+temp,j);// 检测是否炸到人物
                                        }
                                    }

                                }
                                if( j-temp>=0 && ( Map.mapcanvas.MAP_INFO[j-temp][i] > 6 ||  Map.mapcanvas.MAP_INFO[j-temp][i] < 3 )){
                                    if(temp == 1 || VCheck(i,j,i,j-temp)) {
                                        if (wavelength == 1 || j - temp == 0) {
                                            Map.EXPLOSION_INFO[j - temp][i] = 1;
                                        } else {
                                            Map.EXPLOSION_INFO[j - temp][i] = 2;
                                        }
                                        if(Map.BUMB_INFO[j][i] == 1 && checkOnlyDestroyOneBoxUp == 0) {
                                            checkOnlyDestroyOneBoxUp = DestroyBox(i, j - temp);
                                            CheckRole(i,j-temp);// 检测是否炸到人物
                                        }
                                    }

                                }
                                if( j+temp<13 && ( Map.mapcanvas.MAP_INFO[j+temp][i] > 6 ||  Map.mapcanvas.MAP_INFO[j+temp][i] < 3 )){
                                    if(temp == 1 || VCheck(i,j,i,j+temp)) {
                                        if (wavelength == 1 || j + temp == 12) {
                                            Map.EXPLOSION_INFO[j + temp][i] = 3;
                                        } else {
                                            Map.EXPLOSION_INFO[j + temp][i] = 2;
                                        }
                                        if(Map.BUMB_INFO[j][i] == 1 && checkOnlyDestroyOneBoxDown == 0) {
                                            checkOnlyDestroyOneBoxDown = DestroyBox(i, j + temp);
                                            CheckRole(i,j+temp);
                                        }
                                    }
                                }
                                wavelength--;
                            }

                        }
                        Map.BUMB_INFO[j][i]--;
                        wavelength = Map.mapcanvas.Role1.getWaveLength();
                        // 清除图形中的水波
                        if(Map.BUMB_INFO[j][i] == 0) {
                            Map.EXPLOSION_INFO[j][i] = 0;
                            int temp = 0;
                            while (wavelength > 0) {
                                temp++;
                                if(j+temp < 13)
                                    Map.EXPLOSION_INFO[j+temp][i] = 0;
                                if(j-temp >= 0)
                                    Map.EXPLOSION_INFO[j-temp][i] = 0;
                                if(i+temp < 15)
                                    Map.EXPLOSION_INFO[j][i+temp] = 0;
                                if(i-temp >= 0)
                                    Map.EXPLOSION_INFO[j][i-temp] = 0;
                                wavelength--;
                            }
                        }
                    }
                }
                //Map.mapcanvas.repaint();
            }

            // 被泡泡困住计时
            if(Map.mapcanvas.Role1.status > 0){
                if(Map.mapcanvas.Role1.status == 5){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            new Timer().schedule(new TimerTest.RoleDieMusic(),0); // 死亡，游戏结束的声音
                            BackgroundMusicClip.stop(); // 背景音乐停止
                        }
                    }).start();
                }

                if(Map.mapcanvas.Role1.status > 1){
                    Map.mapcanvas.Role1.status--;
                    Map.mapcanvas.repaint();
                    //System.out.println("游戏结束");
                }
            }

            if(Map.mapcanvas.Role2.status > 0){
                if(Map.mapcanvas.Role2.status == 5){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            new Timer().schedule(new TimerTest.RoleDieMusic(),0); // 死亡，游戏结束的声音
                            BackgroundMusicClip.stop(); // 背景音乐停止
                        }
                    }).start();
                }

                if(Map.mapcanvas.Role2.status > 1){
                    Map.mapcanvas.Role2.status--;
                    Map.mapcanvas.repaint();
                    //System.out.println("游戏结束");
                }
            }


            Map.mapcanvas.repaint();
            //new Timer().schedule(new MyTimerBump(), 250);
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask2(), 2000);
        while(true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
