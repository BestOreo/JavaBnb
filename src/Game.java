import javax.swing.*;
import java.util.Timer;

/**
 * Created by geshuaiqi on 2017/12/14.
 */
public class Game extends JFrame{
    Game(){
        new Timer().schedule(new TimerTest.StartMusic(), 0);
        Map map=new Map();
    }
}
