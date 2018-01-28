### JAVA泡泡堂
**姓名：葛帅琦**
**学号：3150102193**
**指导老师：楼学庆**


#### 一、简介——童年记忆
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/1.png)
《泡泡堂》是由韩国游戏公司Nexon开发的一款休闲游戏（Casual Game），于2003年在中国大陆上线，由盛大网络运营。游戏讲述了在哈巴森林的一个村落的村民们利用神奇的水泡来打猎和采集宝石，故事由为拯救村民和夺回被海盗抢去的宝石而展开。
该游戏设有8位基本角色、2位隐藏角色和在基本角色上进阶的新角色。卡通的人物形象、多种道具、饰品和搞怪表情，是一款适合任何年龄的休闲类网游。
借JAVA课程大作业的契机，我决定用JAVA来做一个简易版的泡泡堂！










#### 二、程序设计原理、目的，算法说明

##### 1. 场景布局
泡泡堂采用乐高积木的风格，每个单元布局一个道具或者场景元素，因而布局比较规整，用代码实现也比较方便。
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/2.png)
用大小13*15数组的方式存放地图元素信息,每个单元格需要放置什么样元素，repaint回调函数依据大小13*15数组信息进行场景的绘制。
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/3.png)
数组实现方式：
```
 // 地图信息
static int [][]MAP_INFO = {        {GRASS,BOXO,BOXR,BOXO,BOXR,BUSH,ROAD,ROAD2,BOX,BUSH,HomeY,BOXR,HomeY,GRASS,HomeY},
{GRASS,HomeR,BOX,HomeR,BOX,Tree,BOX,ROAD2,ROAD,Tree,BOXR,BOXO,GRASS,GRASS,GRASS},
.......
}
// 图片读取
imgrule=ImageIO.read(new File("image/rule/rule.png"));
imgmap[GRASS]=ImageIO.read(new File("image/map/grass.png"));
......
```
**实现结果**






























![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/4.png)


























##### 2. 人物
人物的动作需要多帧来完成，多帧的图片也用二维数组来存储。例如
```
BabyMove  = ImageIO.read(new File("image/player/Role1.png"));
int imgWidth  = BabyMove.getWidth();
int imgHeight = BabyMove.getHeight();
// 读入移动的24帧图像
for(int i=0;i<4;i++){
       for(int j=0;j<6;j++){
			ImageBuffer[i][j] = BabyMove.getSubimage(j*imgWidth/6, i*imgHeight/4, imgWidth/6, imgHeight/4);
                }
            }
```         
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/5.png)







通过扫描数组，实现动画帧的播放，从而获得更加真实的效果。

##### 3. 炸弹
泡泡角色通过释放炸弹摧毁乐高积木来获取道具以及杀死对手。炸弹释放后不会立即爆炸，而是在释放若干时间后自行爆炸。炸弹的爆炸有连锁反应。
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/6.png)
炸弹的定时效果由线程定时器控制，定时器会在后面介绍。
炸弹的水波也用贴图动画的形式实现，为了获得的爆炸效果，炸弹的贴图动态程度更高一些。如果仔细看的话，不同点的爆炸纹理是不同的。

















![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/7.png)
>**炸弹的算法**在这里需要一提。
炸弹的水波蔓延用**宽度搜索**的方式进行实现，在宽度搜索的过程中，如果当前距离小于水波长度，依次执行以下内容:
1. 判定当前区域，如果是房子或者树不可蔓延，并且停止继续蔓延；如果是盒子则进行炸毁，如果是人物则杀死人物。
2. 如果是炮弹的话，都刷新炮弹的时间。从而实现连环爆炸。
3. 根据当前位置进行贴图。
4. 继续蔓延直至超出炸弹能力。

##### 4. 道具
玩家通过拾取不同的道具，从而获得不同能力的增长。例如拾取跑鞋可以加速，拾取炸弹可以增加释放炸弹的数量，拾取药水可以增加炸弹压力等等。拾取宠物可以获得坐骑，坐骑可以抵一条命。
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/8.png)

如果捡到坐骑的话，人物动画切换坐骑动画。在实现过程中，就是repaint函数画人物时，检测Role类成员变量pet的值，根据pet的情况进行绘图。
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/9.png)


##### 5. 被炸弹炸死
当人物被水波炸到时，会被水波困住，然后爆炸死亡，游戏结束。
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/10.png)
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/11.png)
爆炸死亡时会有动画，为获得更好的游戏效果。动画同样用播放图片数组来实现帧的效果。


















![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/11.png)

##### 6. 线程定时器
在本工程中有专门的一个TimerTest的类，内含大量的静态函数继承Timertask的函数，供游戏中需要定时器的功能，例如人物移动动画，开场动画，炸弹定时器，音乐定时器等等，有着广泛的应用。以下是炸弹定时器的一个例子。
```
// 炸弹定时器
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
```

##### 7. 音乐播放

背景音乐与特效音乐（鼠标点击、炸弹爆炸）, 都由线程来负责。
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/12.png)
音乐分两种，一种是循环播放（背景音乐），另一种是只播放一次（例如炸弹）。代码只是一个参数的差别
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/13.png)
 












#### 二、程序流程框图、调用函数关系、文件列表 
##### 1. 程序流程框图
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/14.png)






















##### 2. 重要函数与类介绍
Game类：启动游戏
Map类：存储地图信息
>//炸弹信息
>static int [][] BUMB_INFO
>//炸弹归属信息
>static int [][] BUMB_ROLE
>//爆炸水波信息
>static int [][] EXPLOSION_INFO
>//道具信息
>static int [][] GIFT_INFO
>//初始化
>static void Init()
>//构造函数
>public Map() 

MapCanvas： 画布类，图片读取，根据信息画布绘制
> // 角色信息
    Player Role1 = new Player(1,Role1_x,Role1_y);
    Player Role2 = new Player(2,Role2_x,Role2_y);
> // 地图信息
    static int [][]MAP_INFO
>// 初始化
>void Init()    
> // 构造函数
    public MapCanvas()
> //回调绘制函数（最重要）
> public void paint(Graphics g)
    {
        drawmap(g);
    }    
> // 绘制函数
> public void drawmap(Graphics g)
> 

Music类：音乐测试
Player类：玩家信息，玩家基本动作，键盘监听
> // 人物输出的图形
>BufferedImage outImageBuffer;
> // 坐骑图像
> BufferedImage outPETImageBuffer; 
>  // 获得炮弹长度信息
> int getWaveLength()
> //  初始化
> void Init(int Rx, int Ry)
> // 下一帧动画
    public void NEXT(int DIR, int [][]MAP_INFO)
 >// 放炮
 >void SetBump()   
 >// 静止动画
    public void Stand()
>// 键盘监听
    public void keyPressed(KeyEvent e)    

Timer类：后台线程计时器，包括动画计时，炮弹刷新，音乐播放
>//背景音乐计时器
>static class StartMusic extends TimerTask 
>// 线程播放炸弹声音
>    static class BumbSound extends TimerTask 
>     // 线程播放点击鼠标的声音
    static class MouseClick extends TimerTask
>// 线程播死亡的声音
    static class RoleDieMusic extends TimerTask
>// 线程播放获得道具的声音
    static class GetGiftSound extends TimerTask
>// 游戏开始动画
    static class GameStartAnimation extends TimerTask
> /*炸弹定时器*/
static class MyTimerBump extends TimerTask
> // 炸毁箱子随机出现道具，箱子分两种
        int DestroyBox(int boxi,int boxj)
 >// 水平方向障碍物检测
        boolean HCheck(int Bumbi,int Bumbj, int nowi, int nowj)
  >// 竖直方向障碍物检测
        boolean VCheck(int Bumbi,int Bumbj, int nowi, int nowj)
  >// 检测人物是否被水波炸到
        void CheckRole(int wavei, int wavej)
>// 刷新游戏时钟，总计时器
>timertest中public void run()

##### 3. 文件列表
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/15.png)





























#### 三、项目所用到的模式：MVC
MVC全名是Model View Controller，是模型(model)－视图(view)－控制器(controller)的缩写，一种软件设计典范，用一种业务逻辑、数据、界面显示分离的方法组织代码，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。MVC被独特的发展起来用于映射传统的输入、处理和输出功能在一个逻辑的图形化用户界面的结构中。
* Model（模型）表示应用程序核心（比如数据库记录列表）。
* View（视图）显示数据（数据库记录）。
* Controller（控制器）处理输入（写入数据库记录）。

在本工程中，Model模块写于Msp模块中存储数据信息；View模块写于MapCanvas的两个大类，通过回调的方式进行绘制；Control模块写于Player中，用于控制逻辑。

#### 四、难点、要点、得意点
#####1. 难点：
本工程同时运行的线程比较多，时钟刷新，键盘监听，图像绘制，动画绘制（每一次移动都是多帧播放实现动画）。并行性写的非常高，虽然并行程序都是次要的，但是这些并行程序（动画，爆炸音乐）大大提高了游戏的可玩性。

同时，动画的绘制是另一个难点。如果人物不会进行微小的动作，只会让玩家觉得这是没有生命的刚体，游戏性大打折扣。所以事先游戏人物动画是非常有必要的。事先动画是利用人类的视觉延迟，在较短的时间内播放多帧静态图片，从而化静为动。

炸弹的算法。其实这个游戏中能用数据结构的地方非常少，炸弹水波的蔓延采取了宽度搜索的计算，按每一次宽度搜索进行逻辑判断，从而判定是停止蔓延还是炸毁箱子还是炸死人还是绘制结束水纹等等。

##### 2. 要点：
充分利用java的一些优势，尽量做到封装。同时充分利用静态static，简化访问增强数据一致性。充分利用线程，并行计算。尽量关注细节，提高游戏还原度。


#####3. 得意点：
为了还原真实的泡泡堂场景，所有图像、音乐、道具等等均为原版资源，地图布局为原版泡泡堂，具有非常高的还原度。泡泡堂作为十五年前盛极一时的游戏，由韩国游戏公司Nexon开发。我用了2000余行java代码实现了原泡泡堂50%的基础操作。还是很有成就感的。
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/16.png)
**原版泡泡堂**

与先前做过的一些课程游戏坦克大战，赛车相比，这次的动画，音乐细节做得更为细致。如果有机会，我还想用opengl来实现一下3d版本的泡泡堂。

游戏中应用了大量的后台线程，充分利用了多核计算机的性能。并行程序对于游戏来说极为重要，大大提高了游戏的真实性和可玩性。游戏中的音乐，操作，监听，动作，动画均由线程来完成。

结合计算机数据空间局部性和时间局部性，以及操作系统内存管理等知识，我将游戏场景布局切割成小元素模块而非整张大图的读写，这样局部数据可以反复读取，近期操纵的数据在接下来也会反复调用，提高了游戏运行效率，降低了开销。

算法上利用了最基本但是行之有效的宽度搜索来进行炸弹爆炸的计算，不仅完成了障碍物的判定，人物的识别，还实现了炸弹的连锁爆炸，增加游戏难度和趣味性。

#### 五、程序使用说明：
打开MyPPT文件夹中的jar包即可运行程序。**提醒：不可移动jar包的位置**

操作指南：
玩家一：WSAD控制上下左右，J放炮
玩家二：↑↓←→控制上下左右，L放炮
按P键初始化程序。
![Alt text](https://github.com/BestOreo/Pic-for-README.md/blob/master/bnb/17.png)
























#### 结论与展望
在实现这次Java泡泡堂的过程中，运用了很多本学期学到的java知识，并且在编写程序的时候考虑了一些硬件上实现的东西，例如线程，内存管理，局部性等等。在实现的过程中遇到了很多bug，花了点时间调了下bug，收获也很多。个人觉得写得这个游戏还是挺漂亮的。不过也有一些不足之处。比如代码尽管大部分用了面向对象的知识，但是不少部分个人觉得函数功能还不够明确，导致看起来像面向过程编程。

这个迷你泡泡堂和原版泡泡堂其实还有不小的差距，原版游戏中道具的功能更加多样，不同的地图也非常多。如果要进一步拓展的话，可以让这个游戏更加复杂。

还有一点挺遗憾的是，没有时间继续写连网的机制，其中一个原因是时间不够，另一个则是还没有上过计算机网络这门课，之前也没实现过联网多玩家游戏怎么来写。争取寒假的时候做一下尝试。

这学期跟着楼老师学java收获良多，楼老师有点我非常喜欢就是上课会打代码，不像别的老师只会念PPT，一点也不讲工程上的东西。给老师打个call。




