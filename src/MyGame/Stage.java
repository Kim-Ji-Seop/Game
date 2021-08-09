package MyGame;


import MyGame.Game1_Character.*;


public class Stage {//층수,보스급,잡몹,상점(무기와 포션),
    int floor;//층 수
    Enemy enemy1;//장수기 부하
    Enemy enemy2;// 이중구 부하
    Yeonbyeon easyboss;// 연변거지
    JangSuKi lowboss;// 장수기
    LeeJoongKoo midboss;// 이중구
    ZhengCheng highboss;// 정청
    Market market;//상점

    Stage(int floor, Enemy enemy1, JangSuKi lowboss, Market market){// 5층-> 장수기, 장수기부하, 상점
        this.floor = floor;
        this.enemy1 = enemy1;
        this.lowboss = lowboss;
        this.market = market;
    }
    Stage(int floor, Enemy enemy2, LeeJoongKoo midboss, Market market){//10층-> 이중구, 이중구부하, 상점
        this.floor = floor;
        this.enemy2 = enemy2;
        this.midboss = midboss;
        this.market = market;
    }
    Stage(int floor, Yeonbyeon easyboss, ZhengCheng highboss, Market market){//15층-> 정청, 연변거지, 상점
        this.floor = floor;
        this.easyboss = easyboss;
        this.highboss =highboss;
        this.market = market;
    }


////////////////////////////////////////////스테이지에 대한 정보를 어떻게 표현할까?//////////////////////////////

////////////////////////////////////////////버프는 나중에생각을 해보자/////////////////////////////////////////

}
