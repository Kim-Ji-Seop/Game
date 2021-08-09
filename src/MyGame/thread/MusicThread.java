package MyGame.thread;


import MyGame.Game_Main;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class MusicThread extends Thread{

    public Player player;
    public boolean isLoop;
    public File file;
    public FileInputStream fis;
    public BufferedInputStream bis;

    public MusicThread(String name, boolean isLoop){//곡의 이름과//해당곡이 무한반복인지를 판별하는 boolean변수
        try{
            //try문안에서 예외가 발생하면 catch문으로 넘어간다
            this.isLoop = isLoop;
            file = new File(Game_Main.class.getResource("../music/"+name).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public int getTime(){//내가 실행시킨 이 음악이 현재 어떤위치에 실행되고 있는지를 알려줌
        if(player==null){
            return 0;
        }
        return player.getPosition();
    }

    public void close(){//음악이 언제실행되고있던간에 항상 종료할수있도록 해주는 함수
        isLoop = false;
        player.close();
        this.interrupt();
    }

    @Override
    public void run(){
        try {
            do{
                player.play();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            }while(isLoop);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
