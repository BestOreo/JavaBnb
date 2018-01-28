import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Timer;

/**
 * Created by geshuaiqi on 2017/11/29.
 */
class Player implements KeyListener {
    int id;
    final int EAST  = 3;
    final int SOUTH = 1;
    final int WEST  = 2;
    final int NORTH = 0;
    int x = 0;
    int y = 0;
    int NowGridi = 0;
    int NowGridj = 0;
    int direction = SOUTH;
    int speed = 8;
    int WaveLength = 1;
    int status = 0;
    int bumbAbility = 1;

    int Pet = 0;


    int ANI_COUNT = 0;
    Boolean InBush = false;

    final int DROP_BUMPSetTime = 16;


    BufferedImage outImageBuffer;
    BufferedImage outPETImageBuffer;

    static BufferedImage [][] ImageBuffer = new BufferedImage[4][6];

    BufferedImage BaByRide[] = new BufferedImage[4];
    BufferedImage UFO[][] = new BufferedImage[4][2];
    BufferedImage Turtle[][] = new BufferedImage[4][2];
    BufferedImage Owl[][] = new BufferedImage[4][2];



    int getWaveLength(){
        return WaveLength;
    }

    void Init(int Rx, int Ry){
        x = Rx;
        y = Ry;
        status = 0;
        Pet = 0;
        direction = SOUTH;
        speed = 8;
        WaveLength = 1;
        bumbAbility = 1;
    }


    // 构造函数
    public Player(int init_id, int init_x, int init_y){
        id = init_id;
        //this.addKeyListener();

        x = init_x;
        y = init_y;
        status = 0;

        direction = SOUTH;
        NowGridi = (x+25)/50;
        NowGridj = (y+25)/50;


        try{
            BufferedImage BabyMove;
            BufferedImage BabyStart;
            BabyStart = ImageIO.read(new File("image/player/Role1Start.png"));


            BabyMove  = ImageIO.read(new File("image/player/Role1.png"));
            int imgWidth  = BabyMove.getWidth();
            int imgHeight = BabyMove.getHeight();

            // 读入移动的24帧图像
            for(int i=0;i<4;i++){
                for(int j=0;j<6;j++){
                    ImageBuffer[i][j] = BabyMove.getSubimage(j*imgWidth/6, i*imgHeight/4, imgWidth/6, imgHeight/4);
                }
            }

            BufferedImage rideImg =  ImageIO.read(new File("image/pet/Role1Ride.png"));
            imgHeight = rideImg.getHeight();
            imgWidth = rideImg.getWidth();
            for(int i=0;i<4;i++){
                BaByRide[i] = rideImg.getSubimage(i*imgWidth/4,0,imgWidth/4,imgHeight);
            }

            BufferedImage TurtleImg =  ImageIO.read(new File("image/pet/Turtle.png"));
            imgHeight = TurtleImg.getHeight();
            imgWidth = TurtleImg.getWidth();
            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    Turtle[i][j] = TurtleImg.getSubimage(j*imgWidth/2,i*imgHeight/4,imgWidth/2,imgHeight/4);
                }
            }

            BufferedImage OwlImg =  ImageIO.read(new File("image/pet/Owl.png"));
            imgHeight = OwlImg.getHeight();
            imgWidth = OwlImg.getWidth();
            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    Owl[i][j] = OwlImg.getSubimage(j*imgWidth/2,i*imgHeight/4,imgWidth/2,imgHeight/4);
                }
            }

            BufferedImage UFOImg =  ImageIO.read(new File("image/pet/FastUFO.png"));
            imgHeight = UFOImg.getHeight();
            imgWidth = UFOImg.getWidth();
            for(int i=0;i<4;i++){
                for(int j=0;j<2;j++){
                    UFO[i][j] = UFOImg.getSubimage(j*imgWidth/2,i*imgHeight/4,imgWidth/2,imgHeight/4);
                }
            }


        }
        catch (Exception e){
            System.out.toString();
        }
        // 播放开场动画
        //outImageBuffer = BabyStartImg[2];
        outImageBuffer = ImageBuffer[SOUTH][0];
    }

    // 下一帧动画
    public void NEXT(int DIR, int [][]MAP_INFO){
        if(direction != DIR){
            direction = DIR;
        }
        ANI_COUNT = (ANI_COUNT)%5+1; // 当前帧数

        if(Pet == 0){
            outImageBuffer = ImageBuffer[DIR][ANI_COUNT];
        }else if(Pet == 1){
            outImageBuffer = BaByRide[DIR];
            outPETImageBuffer = UFO[DIR][ANI_COUNT%2];
        }
        else if(Pet == 2){
            outImageBuffer = BaByRide[DIR];
            outPETImageBuffer = Turtle[DIR][ANI_COUNT%2];
        }
        else if(Pet == 3){
            outImageBuffer = BaByRide[DIR];
            outPETImageBuffer = Owl[DIR][ANI_COUNT%2];
        }


        int Left_x = x;
        int Left_y = y;
        int Right_x = x+50;
        int Right_y = y+50;

        int C_x = x+25;
        int C_y = y+25;


        // 移动判定

        if(DIR == EAST){
            if(Pet != 1 && Right_x + speed < 750 && MAP_INFO[C_y/50][(Right_x+speed)/50] <= 2){
                //x+=speed;
                for(int i=0;i<speed;i++){
                    x++;
                    Map.mapcanvas.repaint();
                }
            }
            else if(Pet != 1){
                int t = speed;
                while(t>0){
                    t--;
                    if(Right_x + t < 750 && MAP_INFO[C_y/50][(Right_x+t)/50] <= 2){
                        for(int i=0;i<t;i++){
                            x++;
                            Map.mapcanvas.repaint();
                        }
                        break;
                    }
                }
            }
            else if(Pet == 1){
                for(int i=0;i<speed;i++){
                    x++;
                    Map.mapcanvas.repaint();
                }
            }

        }
        else if(DIR == SOUTH){
            if(Pet != 1 && Right_y + speed < 670 && MAP_INFO[(Right_y+speed)/50][C_x/50] <= 2){
                //y+=speed;
                for(int i=0;i<speed;i++){
                    y++;
                    Map.mapcanvas.repaint();
                }
            }
            else if(Pet != 1){
                int t = speed;
                while(t>0){
                    t--;
                    if(Right_y + t < 670 && MAP_INFO[(Right_y+t)/50][C_x/50] <= 2){
                        for(int i=0;i<t;i++){
                            y++;
                            Map.mapcanvas.repaint();
                        }
                        break;
                    }
                }
            }
            else if(Pet == 1){
                for(int i=0;i<speed;i++){
                    y++;
                    Map.mapcanvas.repaint();
                }
            }

        }
        else if(DIR == WEST) {
            if(Pet != 1 && Left_x - speed >= 0 && MAP_INFO[C_y/50][(Left_x-speed)/50] <= 2) {
                //x -= speed;
                for(int i=0;i<speed;i++){
                    x--;
                    Map.mapcanvas.repaint();
                }
            }
            else if(Pet != 1){
                int t = speed;
                while(t>0){
                    t--;
                    if(Left_x - t >=0 && MAP_INFO[C_y/50][(Left_x-t)/50] <= 2){
                        for(int i=0;i<t;i++){
                            x--;
                            Map.mapcanvas.repaint();
                        }
                        break;
                    }
                }
            }
            else if(Pet == 1){
                for(int i=0;i<speed;i++){
                    x--;
                    Map.mapcanvas.repaint();
                }
            }

        }
        else if(DIR == NORTH){
            if(Pet != 1 && Left_y - speed >= 0 && MAP_INFO[(Left_y-speed)/50][C_x/50] <= 2){
                //y-=speed;
                for(int i=0;i<speed;i++){
                    y--;
                    Map.mapcanvas.repaint();
                }
            }
            else if(Pet != 1){
                int t = speed;
                while(t>0){
                    t--;
                    if(Left_y - t >= 0 && MAP_INFO[(Left_y-t)/50][C_x/50] <= 2){
                        for(int i=0;i<t;i++){
                            y--;
                            Map.mapcanvas.repaint();
                        }
                        break;
                    }
                }
            }
            else if(Pet == 1){
                for(int i=0;i<speed;i++){
                    y--;
                    Map.mapcanvas.repaint();
                }
            }
        }

        // 捡道具
        int nowi = (x+25) / 50;
        int nowj = (y+25) / 50;
        NowGridi = nowi;
        NowGridi = nowj;
        if(Map.GIFT_INFO[nowj][nowi] > 0){
            switch (Map.GIFT_INFO[nowj][nowi]){
                case 1: bumbAbility++;break; // 炸弹
                case 2: if(speed<20) speed += 5; break; // 跑鞋
                case 3: WaveLength++; break; // 药水
                case 4: WaveLength++; break; // 大药水
                case 5: if(speed<20) speed += 5; break;// 跑鞋
                case 6: if(speed<20) speed += 8;break; // 红鬼
                case 7: Pet = 3;break;  // 猫头鹰
                case 8: Pet = 2;break;  // 乌龟
                case 9: Pet = 1;break;  // 飞碟
            }
            new Timer().schedule(new TimerTest.GetGiftSound(),0); // 捡到道具的声音
            Map.GIFT_INFO[nowj][nowi] = 0;
        }

    }

    void SetBump(){
        int Bump_x = (x+25)/50;
        int Bump_y = (y+25)/50;
        Map.BUMB_INFO[Bump_y][Bump_x] = DROP_BUMPSetTime;
        Map.BUMB_ROLE[Bump_y][Bump_x] = id;
    }

    // 静止动画
    public void Stand(){
        if(Pet == 0)
            outImageBuffer = ImageBuffer[direction][0];
        else
        {   // 有坐骑的站立状态
            if(Pet == 0){
                outImageBuffer = ImageBuffer[direction][0];
            }else if(Pet == 1){
                outImageBuffer = BaByRide[direction];
                outPETImageBuffer = UFO[direction][0];
            }
            else if(Pet == 2){
                outImageBuffer = BaByRide[direction];
                outPETImageBuffer = Turtle[direction][0];
            }
            else if(Pet == 3){
                outImageBuffer = BaByRide[direction];
                outPETImageBuffer = Owl[direction][0];
            }
        }

        ANI_COUNT = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.print("AA");

    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int EAST  = 3;
        final int SOUTH = 1;
        final int WEST  = 2;
        final int NORTH = 0;
        if (e.getKeyCode() == KeyEvent. VK_D && id == 1) {
            NEXT(EAST,Map.mapcanvas.MAP_INFO);
            //repaint();
        }
        else if (e.getKeyCode() == KeyEvent. VK_S && id == 1 ) {
            NEXT(SOUTH,Map.mapcanvas.MAP_INFO);
            //repaint();
        }
        else if (e.getKeyCode() == KeyEvent. VK_A && id == 1 ) {
            NEXT(WEST,Map.mapcanvas.MAP_INFO);
            //repaint();
        }
        else if (e.getKeyCode() == KeyEvent. VK_W && id == 1) {
            NEXT(NORTH,Map.mapcanvas.MAP_INFO);
            //repaint();
        }
        else if (e.getKeyCode() == KeyEvent. VK_J && id == 1) {
            if(bumbAbility > 0) {
                SetBump();
                bumbAbility--;
            }
            //repaint();
        }
        else if (e.getKeyCode() == KeyEvent. VK_UP&& id == 2) {
            NEXT(NORTH,Map.mapcanvas.MAP_INFO);
            System.out.println("UP");
            //repaint();
        }
        else if (e.getKeyCode() == KeyEvent. VK_DOWN&& id == 2) {
            NEXT(SOUTH,Map.mapcanvas.MAP_INFO);
            System.out.println("DOWN");
            //repaint();
        }
        else if (e.getKeyCode() == KeyEvent. VK_LEFT&& id == 2) {
            NEXT(WEST,Map.mapcanvas.MAP_INFO);
            System.out.println("LEFT");
            //repaint();
        }
        else if (e.getKeyCode() == KeyEvent. VK_RIGHT&& id == 2) {
            NEXT(EAST,Map.mapcanvas.MAP_INFO);
            System.out.println("RIGHT");
            //repaint();
        }
        else if (e.getKeyCode() == KeyEvent. VK_L && id == 2) {
            if(bumbAbility > 0) {
                SetBump();
                bumbAbility--;
            }
            //repaint();
        }
        // 初始化
        else if (e.getKeyCode() == KeyEvent. VK_P) {
            System.out.println("Restart Game");
            Map.Init();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Stand();
        Map.mapcanvas.repaint();
    }



}
