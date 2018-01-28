import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Timer;

/**
 * Created by geshuaiqi on 2017/11/29.
 */
public class Map extends JFrame  {
    static MapCanvas mapcanvas=new MapCanvas();
    static int GameStart = 10;
    static int [][] BUMB_INFO = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };

    static int [][] BUMB_ROLE = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };


    static int [][] EXPLOSION_INFO = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };

    static int [][] GIFT_INFO = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };

    public Map() {
        this.setBounds(50, 50, 750, 670);
        this.setVisible(true);
        this.setTitle("泡泡堂");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.add(mapcanvas);
    }

    static void Init() {
        GameStart = 0;
        mapcanvas.Init();

        for(int i=0;i<13;i++){
            for(int j=0;j<15;j++){
                BUMB_INFO[i][j] = 0;
                BUMB_ROLE[i][j] = 0;
                EXPLOSION_INFO[i][j] = 0;
                GIFT_INFO[i][j] = 0;

            }
        }

    }

}



//class MapCanvas extends Canvas implements KeyListener{
class MapCanvas extends Canvas{
    HashMap<String,Point> hashmap=new HashMap<String,Point>();
    BufferedImage imgrule;
    BufferedImage[]imgmap=new BufferedImage[20];
    BufferedImage[] Bump = new BufferedImage[3];
    static BufferedImage[] BabyStartImg = new BufferedImage[10];

    BufferedImage VerticalBumb[] = new BufferedImage[3];
    BufferedImage HorizontalBumb[] = new BufferedImage[3];
    BufferedImage CenterBumb[] = new BufferedImage[4];

    BufferedImage HBubbleLeft[] = new BufferedImage[4];
    BufferedImage HBubbleRight[] = new BufferedImage[4];
    BufferedImage VBubbleUp[] = new BufferedImage[4];
    BufferedImage VBubbleDown[] = new BufferedImage[4];

    BufferedImage GiftImage[][] = new BufferedImage[10][3];

    BufferedImage DieImage[] = new BufferedImage[11];
    BufferedImage DieBubble[] = new BufferedImage[9];
    BufferedImage RoleAni[] = new BufferedImage[8];



    int count = 0;

    static int GiftTime = 0;

    // 宏定义
    static final int GRASS = 0;
    static final int ROAD = 1;
    static final int ROAD2 = 2;
    static final int HomeR = 3;
    static final int HomeB = 4;
    static final int HomeY = 5;
    static final int Tree = 6;
    static final int BOX = 7;
    static final int BUSH = 8;
    static final int BOXR = 9;
    static final int BOXO = 10;


    static final int HLEFT=0;
    static final int HCENTER=1;
    static final int HRIGHT = 2;
    static final int VUP=0;
    static final int VCENTER=1;
    static final int VDown = 2;

    static int BumbCount = 0;

    static Timer timer3;


    final int Role1_x = 350;
    final int Role1_y = 300;

    final int Role2_x = 350;
    final int Role2_y = 0;

    // 角色信息
    Player Role1 = new Player(1,Role1_x,Role1_y);
    Player Role2 = new Player(2,Role2_x,Role2_y);


    // 地图信息
    static int [][]MAP_INFO = {
            {GRASS,BOXO,BOXR,BOXO,BOXR,BUSH,ROAD,ROAD2,BOX,BUSH,HomeY,BOXR,HomeY,GRASS,HomeY},
            {GRASS,HomeR,BOX,HomeR,BOX,Tree,BOX,ROAD2,ROAD,Tree,BOXR,BOXO,GRASS,GRASS,GRASS},
            {GRASS,GRASS,BOXO,BOXR,BOXO,BUSH,ROAD,BOX,BOX,BUSH,HomeY,BOX,HomeY,BOX,HomeY},
            {BOX,HomeY,BOX,HomeY,BOX,Tree,BOX,ROAD2,ROAD,Tree,BOXO,BOXR,BOXO,BOXR,BOXO},
            {BOXR,BOXO,BOXR,BOXO,BOXR,BUSH,ROAD,ROAD2,BOX,BUSH,HomeY,BOX,HomeY,BOX,HomeY},
            {BOXO,HomeR,BOXO,HomeR,BOXO,HomeR,BOX,BOX,ROAD,GRASS,BOXR,BOXO,BOXR,BOXO,BOXR},
            {Tree,BUSH,Tree,BUSH,Tree,BUSH,ROAD,ROAD2,BOX,BUSH,Tree,BUSH,Tree,BUSH,Tree},
            {BOXR,BOXO,BOXR,BOXO,BOXR,GRASS,BOX,ROAD2,ROAD,Tree,BOXR,HomeR,BOXR,HomeR,BOXR},
            {HomeB,BOX,HomeB,BOX,HomeB,BUSH,ROAD,BOX,BOX,BUSH,BOXO,BOXR,BOXO,BOXR,BOXO},
            {BOXO,BOXR,BOXO,BOXR,BOXO,Tree,BOX,ROAD2,ROAD,Tree,BOX,HomeR,BOX,HomeR,BOX,HomeR,BOX},
            {HomeB,GRASS,HomeB,BOX,HomeB,BUSH,ROAD,ROAD2,BOX,BUSH,BOXR,BOXO,BOXR,BOXO,GRASS},
            {GRASS,GRASS,BOXR,BOXO,BOXR,Tree,BOX,BOX,ROAD,Tree,BOX,HomeR,BOX,HomeR,GRASS},
            {HomeB,GRASS,HomeB,BOXR,HomeB,BUSH,ROAD,ROAD2,BOX,BUSH,BOXO,BOXR,BOXO,GRASS,GRASS}
    };


    void Init(){
        final int [][]MAP_INFO_copy = {
                {GRASS,BOXO,BOXR,BOXO,BOXR,BUSH,ROAD,ROAD2,BOX,BUSH,HomeY,BOXR,HomeY,GRASS,HomeY},
                {GRASS,HomeR,BOX,HomeR,BOX,Tree,BOX,ROAD2,ROAD,Tree,BOXR,BOXO,GRASS,GRASS,GRASS},
                {GRASS,GRASS,BOXO,BOXR,BOXO,BUSH,ROAD,BOX,BOX,BUSH,HomeY,BOX,HomeY,BOX,HomeY},
                {BOX,HomeY,BOX,HomeY,BOX,Tree,BOX,ROAD2,ROAD,Tree,BOXO,BOXR,BOXO,BOXR,BOXO},
                {BOXR,BOXO,BOXR,BOXO,BOXR,BUSH,ROAD,ROAD2,BOX,BUSH,HomeY,BOX,HomeY,BOX,HomeY},
                {BOXO,HomeR,BOXO,HomeR,BOXO,HomeR,BOX,BOX,ROAD,GRASS,BOXR,BOXO,BOXR,BOXO,BOXR},
                {Tree,BUSH,Tree,BUSH,Tree,BUSH,ROAD,ROAD2,BOX,BUSH,Tree,BUSH,Tree,BUSH,Tree},
                {BOXR,BOXO,BOXR,BOXO,BOXR,GRASS,BOX,ROAD2,ROAD,Tree,BOXR,HomeR,BOXR,HomeR,BOXR},
                {HomeB,BOX,HomeB,BOX,HomeB,BUSH,ROAD,BOX,BOX,BUSH,BOXO,BOXR,BOXO,BOXR,BOXO},
                {BOXO,BOXR,BOXO,BOXR,BOXO,Tree,BOX,ROAD2,ROAD,Tree,BOX,HomeR,BOX,HomeR,BOX,HomeR,BOX},
                {HomeB,GRASS,HomeB,BOX,HomeB,BUSH,ROAD,ROAD2,BOX,BUSH,BOXR,BOXO,BOXR,BOXO,GRASS},
                {GRASS,GRASS,BOXR,BOXO,BOXR,Tree,BOX,BOX,ROAD,Tree,BOX,HomeR,BOX,HomeR,GRASS},
                {HomeB,GRASS,HomeB,BOXR,HomeB,BUSH,ROAD,ROAD2,BOX,BUSH,BOXO,BOXR,BOXO,GRASS,GRASS}
        };
        MAP_INFO = MAP_INFO_copy;
        timer3.schedule(new TimerTest.MyMusic(),100);
        Role1.Init(Role1_x,Role1_y);
        Role2.Init(Role2_x,Role2_y);
    }


    // 构造函数
    public MapCanvas() {
        this.setSize(750, 670);

        this.addKeyListener(Role1); // 监听键盘
        this.addKeyListener(Role2); // 监听键盘

        // 添加鼠标监听
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Timer().schedule(new TimerTest.MouseClick(),0); // 捡到道具的声音
            }
        });

        try{
            ReadImageFromDisk();// 读取图片
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        repaint();

//
        Timer timer = new Timer();
        timer.schedule(new TimerTest.MyTimerBump(), 0,200);

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTest.GameStartAnimation(),0);

        // 播放背景音乐
        timer3 = new Timer();
        timer3.schedule(new TimerTest.MyMusic(),100);

        Timer timer4 = new Timer();
        timer4.schedule(new TimerTest.MyTimerGift(), 0,200);

        //new Music();

    }

    public void paint(Graphics g)
    {
        drawmap(g);
    }

    public void drawmap(Graphics g) {

        // 画背景
        for(int j=0;j<13;j++) {
            for (int i = 0; i < 15; i++) {
                //g.drawImage(imgmap[GRASS], 50*i, 50*j, 50, 50, null);
                switch (MAP_INFO[j][i]) {
                    case GRASS:
                        g.drawImage(imgmap[GRASS], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case ROAD:
                        g.drawImage(imgmap[ROAD], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case ROAD2:
                        g.drawImage(imgmap[ROAD2], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case HomeR:
                        g.drawImage(imgmap[HomeR], 50 * i, 50 * j - 15, 50, 65, null);
                        break;
                    case HomeY:
                        g.drawImage(imgmap[HomeY], 50 * i, 50 * j - 15, 50, 65, null);
                        break;
                    case HomeB:
                        g.drawImage(imgmap[HomeB], 50 * i, 50 * j - 15, 50, 65, null);
                        break;
                    case Tree:
                        g.drawImage(imgmap[GRASS], 50 * i, 50 * j, 50, 50, null);
                        g.drawImage(imgmap[Tree], 50 * i, 50 * j - 20, 50, 70, null);
                        break;
                    case BOX:
                        g.drawImage(imgmap[BOX], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case BOXO:
                        g.drawImage(imgmap[BOXO], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case BOXR:
                        g.drawImage(imgmap[BOXR], 50 * i, 50 * j, 50, 50, null);
                        break;
                    case BUSH:
                        g.drawImage(imgmap[GRASS], 50 * i, 50 * j, 50, 50, null);
                        g.drawImage(imgmap[BUSH], 50 * i, 50 * j - 15, 50, 65, null);
                        break;
                }
            }
        }

        // 画炸弹
        for(int j=0;j<13;j++) {
            for (int i = 0; i < 15; i++) {


                if (Map.BUMB_INFO[j][i] >= 3) {
                    g.drawImage(Bump[Map.BUMB_INFO[j][i] % 3], i * 50, j * 50, 50, 50, null);
                }

                if(Map.EXPLOSION_INFO[j][i] > 0){
                    if(Map.EXPLOSION_INFO[j][i] == 1){
                        g.drawImage(VerticalBumb[0], i * 50, j * 50, 50, 50, null);
                        g.drawImage(VBubbleUp[count], i * 50, j * 50, 50, 50, null);
                    }
                    if(Map.EXPLOSION_INFO[j][i] == 2){
                        g.drawImage(VerticalBumb[1], i * 50, j * 50, 50, 50, null);
                    }
                    if(Map.EXPLOSION_INFO[j][i] == 3){
                        g.drawImage(VerticalBumb[2], i * 50, j * 50, 50, 50, null);
                    }
                    if(Map.EXPLOSION_INFO[j][i] == 4){
                        g.drawImage(HorizontalBumb[0], i * 50, j * 50, 50, 50, null);
                    }
                    if(Map.EXPLOSION_INFO[j][i] == 5){
                        g.drawImage(HorizontalBumb[1], i * 50, j * 50, 50, 50, null);
                    }
                    if(Map.EXPLOSION_INFO[j][i] == 6){
                        g.drawImage(HorizontalBumb[2], i * 50, j * 50, 50, 50, null);
                    }
                    if(Map.EXPLOSION_INFO[j][i] == 7){
                        System.out.println(Map.BUMB_INFO[j][i]-1);
                        g.drawImage(CenterBumb[count], i * 50, j * 50, 50, 50, null);
                    }

                }
            }
        }

        for(int j=0;j<13;j++){
            for(int i=0;i<15;i++){
                int Giftnum = Map.GIFT_INFO[j][i];
                if(Giftnum != 0){
                    g.drawImage(GiftImage[Giftnum][GiftTime], i * 50, j * 50, 50, 50, null);
                }
            }
        }



        // 画人物
        if(Role1.Pet == 0){
            if(Role1.status == 0){
                g.drawImage(Role1.outImageBuffer, Role1.x, Role1.y-15, 50, 65, null);
            }else if(Role1.status > 10){
                g.drawImage(RoleAni[Role1.status%2], Role1.x, Role1.y, 50, 50, null);
                g.drawImage(DieBubble[3], Role1.x-15, Role1.y-15, 80, 80, null);
            }else{
                g.drawImage(DieBubble[3+(10-Role1.status)/2], Role1.x, Role1.y, 50, 50, null);
                g.drawImage(DieImage[10-Role1.status], Role1.x, Role1.y, 50, 50, null);
                //timer3.cancel();
            }
        }
        else if(Role1.Pet > 0){
            g.drawImage(Role1.outPETImageBuffer, Role1.x, Role1.y+15, 50, 40, null);
            g.drawImage(Role1.outImageBuffer, Role1.x, Role1.y-25, 50, 60, null);
        }

        if(Role2.Pet == 0) {
            if (Role2.status == 0) {
                g.drawImage(Role2.outImageBuffer, Role2.x, Role2.y - 15, 50, 65, null);
            } else if (Role2.status > 10) {
                g.drawImage(RoleAni[Role2.status % 2], Role2.x, Role2.y, 50, 50, null);
                g.drawImage(DieBubble[3], Role2.x - 15, Role2.y - 15, 80, 80, null);
            } else {
                g.drawImage(DieBubble[3 + (10 - Role2.status) / 2], Role2.x, Role2.y, 50, 50, null);
                g.drawImage(DieImage[10 - Role2.status], Role2.x, Role2.y, 50, 50, null);
                //timer3.cancel();
            }
        }
        else if(Role2.Pet > 0){
            g.drawImage(Role2.outPETImageBuffer, Role2.x, Role2.y+15, 50, 40, null);
            g.drawImage(Role2.outImageBuffer, Role2.x, Role2.y-25, 50, 60, null);
        }

//        // 炸弹爆炸
//        int bx = 500;
//        int by = 300;
//        g.drawImage(CenterBumb[0], bx,by, 50, 50, null);
//        g.drawImage(VerticalBumb[VUP], bx, by-50, 50, 50, null);
//        g.drawImage(VerticalBumb[VDown], bx, by+50, 50, 50, null);
//        g.drawImage(HorizontalBumb[HLEFT], bx-50,by, 50, 50, null);
//        g.drawImage(HorizontalBumb[HRIGHT], bx+50,by, 50, 50, null);



    }


    // 读取图片
    public void ReadImageFromDisk()throws Exception {
        imgrule=ImageIO.read(new File("image/rule/rule.png"));
        imgmap[GRASS]=ImageIO.read(new File("image/map/grass.png"));
        imgmap[ROAD]= ImageIO.read(new File("image/map/road2.png"));
        imgmap[ROAD2]= ImageIO.read(new File("image/map/road.png"));
        imgmap[BOX]= ImageIO.read(new File("image/box/TownBox.png"));
        imgmap[BOXR]= ImageIO.read(new File("image/box/TownBlockRed.png"));
        imgmap[BOXO]= ImageIO.read(new File("image/box/TownBlockYellow.png"));
        imgmap[HomeB]= ImageIO.read(new File("image/map/TownHouseBlue.png"));
        imgmap[HomeR]= ImageIO.read(new File("image/map/TownHouseRed.png"));
        imgmap[HomeY]= ImageIO.read(new File("image/map/TownHouseYellow.png"));
        imgmap[Tree]= ImageIO.read(new File("image/map/TownTree.png"));
        imgmap[BUSH]= ImageIO.read(new File("image/map/TownBush.png"));

        BufferedImage Popo = ImageIO.read(new File("image/Popo.png"));
        Bump[0] = Popo.getSubimage(0*Popo.getWidth()/3, 0, Popo.getWidth()/3, Popo.getHeight());
        Bump[1] = Popo.getSubimage(1*Popo.getWidth()/3, 0, Popo.getWidth()/3, Popo.getHeight());
        Bump[2] = Popo.getSubimage(2*Popo.getWidth()/3, 0, Popo.getWidth()/3, Popo.getHeight());

        // 读取开场动画
        BufferedImage Start = ImageIO.read(new File("image/player/Role1Start.png"));
        for(int i=0;i<10;i++){
            BabyStartImg[i] = Start.getSubimage(i*Start.getWidth()/10,0,Start.getWidth()/10, Start.getHeight());
        };

        BufferedImage Wave = ImageIO.read(new File("image/bump/Explosion.png"));
        int WaveWidth = Wave.getWidth();
        int WaveHeight = Wave.getHeight();
        VerticalBumb[VUP] = Wave.getSubimage(0*WaveWidth/14, 0*WaveHeight/5, WaveWidth/14, WaveHeight/5);
        VerticalBumb[VCENTER] = Wave.getSubimage(3*WaveWidth/14, 0*WaveHeight/5, WaveWidth/14, WaveHeight/5);
        VerticalBumb[VDown] = Wave.getSubimage(0*WaveWidth/14, 1*WaveHeight/5, WaveWidth/14, WaveHeight/5);

        HorizontalBumb[HLEFT] = Wave.getSubimage(0*WaveWidth/14, 2*WaveHeight/5, WaveWidth/14, WaveHeight/5);
        HorizontalBumb[HCENTER] = Wave.getSubimage(3*WaveWidth/14, 2*WaveHeight/5, WaveWidth/14, WaveHeight/5);
        HorizontalBumb[HRIGHT] = Wave.getSubimage(0*WaveWidth/14, 3*WaveHeight/5, WaveWidth/14, WaveHeight/5);

        for(int i=0;i<4;i++){
            CenterBumb[i] = Wave.getSubimage(0*WaveWidth/14, 4*WaveHeight/5, WaveWidth/14, WaveHeight/5);
            HBubbleLeft[i] =  Wave.getSubimage((i+8)*WaveWidth/14, 2*WaveHeight/5, WaveWidth/14, WaveHeight/5);
            HBubbleRight[i] =  Wave.getSubimage((i+8)*WaveWidth/14, 3*WaveHeight/5, WaveWidth/14, WaveHeight/5);
            VBubbleDown[i] =  Wave.getSubimage((i+8)*WaveWidth/14, 1*WaveHeight/5, WaveWidth/14, WaveHeight/5);
            VBubbleUp[i] =  Wave.getSubimage((i+8)*WaveWidth/14, 0*WaveHeight/5, WaveWidth/14, WaveHeight/5);

        }

        // 读道具
        for(int i=1;i<=9;i++){
            String thisname = "image/gift/Gift"+i+".png";
            BufferedImage ThisImg = ImageIO.read(new File(thisname));
            int width = ThisImg.getWidth();
            int height = ThisImg.getHeight();
            for(int j=0;j<3;j++){
               GiftImage[i][j] = ThisImg.getSubimage(j*width/3,0,width/3,height);
            }
        }

        // 读死亡气泡图片
        BufferedImage TempDieBubble = ImageIO.read(new File("image/die/BigPopo.png"));
        for(int i=0;i<9;i++){
            int width = TempDieBubble.getWidth();
            int height = TempDieBubble.getHeight();
            DieBubble[i] = TempDieBubble.getSubimage(width/9*i,0,width/9,height);
        }

        // 读人物动作
        BufferedImage TempRoleAni = ImageIO.read(new File("image/die/RoleAni.png"));
        for(int i=0;i<8;i++){
            int width = TempRoleAni.getWidth();
            int height = TempRoleAni.getHeight();
            RoleAni[i] = TempRoleAni.getSubimage(width/8*i,0,width/8,height);
        }

        // 读爆炸照片
        BufferedImage TempDie = ImageIO.read(new File("image/die/RoleDie.png"));
        for(int i=0;i<11;i++){
            int width = TempDie.getWidth();
            int height = TempDie.getHeight();
            DieImage[i] = TempDie.getSubimage(width/11*i,0,width/11,height);
        }

    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//        //System.out.print("AA");
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        final int EAST  = 3;
//        final int SOUTH = 1;
//        final int WEST  = 2;
//        final int NORTH = 0;
//        if (e.getKeyCode() == KeyEvent. VK_D) {
//            Role1.NEXT(EAST,MAP_INFO);
//            //repaint();
//        }
//        else if (e.getKeyCode() == KeyEvent. VK_S) {
//            Role1.NEXT(SOUTH,MAP_INFO);
//            //repaint();
//        }
//        else if (e.getKeyCode() == KeyEvent. VK_A) {
//            Role1.NEXT(WEST,MAP_INFO);
//            //repaint();
//        }
//        else if (e.getKeyCode() == KeyEvent. VK_W) {
//            Role1.NEXT(NORTH,MAP_INFO);
//            //repaint();
//        }
//        else if (e.getKeyCode() == KeyEvent. VK_J) {
//            Role1.SetBump();
//            //repaint();
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        Role1.Stand();
//        repaint();
//    }



}

