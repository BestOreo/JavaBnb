import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by geshuaiqi on 2017/11/29.
 */
public class Music extends Thread {
    public Music(){
        this.start();
    }

    /*
    *线程运行， 播放音乐
    */
    public void run(){
        try
        {
            URL MusicURL;
            File MusicFile = new File("sound/bg.wav");
            MusicURL = MusicFile.toURL();
            AudioClip clip=(AudioClip) Applet.newAudioClip( MusicURL);
            clip.loop(); // 循环播放
        }catch(Exception mue){
            System.out.println(mue.toString());
        }
    }
}