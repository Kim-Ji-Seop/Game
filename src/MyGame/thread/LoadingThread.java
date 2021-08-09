package MyGame.thread;

public class LoadingThread extends Thread {
    public String type;//로딩스레드 실행 시 어떤 로딩 메소드를 쓸지 지정하기 위해 존재하는 변수

    public LoadingThread(String type){//객체를 생성할 때 입력하는 타입에 따라 실행되는 로딩 메소드가 달라지게끔 설정==>switch문 쓰자
        this.type = type;
    }

    public void Loading(){
        for(int i=0; i<60; i++) {
            System.out.println();
        }
        System.out.print("로딩중");
        for(int i=0; i<20; i++ ) {
            System.out.print('.');

            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0; i<60; i++) {
            System.out.println();
        }
    }
    public void StartFight() {
        for(int i=0; i<60; i++) {
            System.out.println();
        }
        System.out.print("로딩중");
        for(int i=0; i<10; i++ ) {
            System.out.print('.');

            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0; i<60; i++) {
            System.out.println();
        }
    }
    public void Revive() {

        for(int i=0; i<30; i++) {
            System.out.println();
        }
        System.out.print("해당 층에서 부활합니다");
        for(int i=0; i<20; i++ ) {
            System.out.print('.');

            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0; i<60; i++) {
            System.out.println();
        }
    }
    public void NextStage() {
        for(int i=0; i<60; i++) {
            System.out.println();
        }
        System.out.print("다음 층으로 이동합니다");
        for(int i=0; i<20; i++ ) {
            System.out.print('.');

            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0; i<60; i++) {
            System.out.println();
        }
    }
    public void Ending() {
        for(int i=0; i<60; i++) {
            System.out.println();
        }
        String[] bossScript = {"정","청","을 ","죽","이","고 ", "골","드","문","을 ", "접","수","한 ", "이","자","성","은 "
                ,"훗","날 ", "골","드","문 ","그","룹","의 " ,"재","산","을 " ,"사","회","에 ", "환","원","하","고 ","경","찰","로","서 ","살","아","가","게","된","다","."};
        for(int i=0; i<bossScript.length; i++) {
            System.out.print(bossScript[i]);
            try{
                Thread.sleep(150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        for(int i=0; i<60; i++) {
            System.out.println();
        }
        System.out.print("게임을 종료합니다");
        for(int i=0; i<20; i++ ) {
            System.out.print('.');

            try {
                Thread.sleep(140);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0; i<60; i++) {
            System.out.println();
        }
    }

    public void run(){
        switch (type){
            case "loading"://로딩중 실행
                Loading();
                break;
            case "startFight":
                StartFight();
                break;
            case "revive":
                Revive();
                break;
            case "nextstage":
                NextStage();
                break;
            case "ending":
                Ending();
                break;
        }
    }
}
