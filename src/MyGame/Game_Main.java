package MyGame;


import java.util.ArrayList;
import java.util.Scanner;

import MyGame.Game1_Character.*;
import MyGame.item.Armor;
import MyGame.item.HpPotion;
import MyGame.item.Item;
import MyGame.item.Weapon;
import MyGame.thread.*;

public class Game_Main{
    public static void main(String[] args){
//--------------------------------------------아이템 객체생성--------------------------------------------------------------
        //포션                                  이름       가격     추가체력
        Item hp_potion = new HpPotion("체력포션",500,500); //  - Item클래스(부모)
                                                                        //       -HpPotion클래스(자식)----------- 체력포션 객체 생성
        //무기                        이름        가격      추가공격력               -  Weapon클래스(자식)-----------각목,칼,쇠파이프 객체 생성
        Item kakmok = new Weapon("각목",3000,30);      //       -   Armor클래스(자식)----------- 정장, 방탄조끼 객체 생성
        Item knife = new Weapon("칼",5000,70);
        Item pipe = new Weapon("쇠파이프",7000,110);   //다형성 - Item

        //방어구                              이름         가격      추가방어력
        Item suit = new Armor(          "정장",3000,30);
        Item bullet_proof = new Armor("방탄조끼",9000,100);


//--------------------------------------상점의 무기,방어구,포션객체들을 리스트에 추가-------------------------------------------------
        ArrayList<Weapon> weaponlist = new ArrayList<>();
        weaponlist.add((Weapon)kakmok);//각목
        weaponlist.add((Weapon)knife);//칼
        weaponlist.add((Weapon)pipe);//쇠파이프

        ArrayList<Armor> armorlist = new ArrayList<>();
        armorlist.add((Armor)suit);//정장
        armorlist.add((Armor)bullet_proof);//방탄조끼

        ArrayList<HpPotion> potionlist = new ArrayList<>();
        potionlist.add((HpPotion)hp_potion);//체력포션

        Market market = new Market(weaponlist,armorlist,potionlist);// 상점클래스에 각 아이템리스트생성 후 상점객체 생성

//--------------------------------------------캐릭터 객체생성--------------------------------------------------------------
        Hero hero = new Hero(
                "이자성",                  //영웅의 이름
                2000,                    //영웅의 공격력
                200,                    //영웅의 방어력
                10000,                  //영웅의 최대체력
                10000,                     //영웅의 체력
                10000,                   //영웅의 돈
                null,                   //무기
                null,                    //방어구
                new ArrayList<>(),             //무기리스트
                new ArrayList<>(),             //방어구리스트
                new ArrayList<>());            //포션리스트
        //////////////////////////////////////////////////////////////////////////////////////////////////
        Enemy su_ki_junior = new Enemy(
                "장수기의 부하",            //1층몹 / 1층보스: 장수기
                1000,                    //적 최대체력
                1000,                       //적 체력
                210,                     //적 공격력
                50,                     //적 방어력
                1000);                  //적이 보유한 돈
        //////////////////////////////////////////////////////////////////////////////////////////////////
        Enemy jung_ku_junior = new Enemy(
                "이중구의 부하",             //2층몹 / 2층보스:이중구
                2000,                   //적 최대체력
                2000,                      //적 체력
                230,                     //적 공격력
                50,                    //적 방어력
                4000);                   //적이 보유한 돈
        //////////////////////////////////////////////////////////////////////////////////////////////////
        Yeonbyeon easyboss = new Yeonbyeon(            //3층 몹: 연변거지 , 3층 보스: 정청
                "연변거지",                  //연변 적이름
                3000,                       //적의 최대체력
                3000,                      //적 체력
                250,                     //적 공격력
                100,                       //적 방어력
                9000,                     //적이 보유한 돈
                20);                       //연변거지만이 보유한 회피력
        //////////////////////////////////////////////////////////////////////////////////////////////////
        JangSuKi lowboss = new JangSuKi(
                "장수기",                     //장수기 적
                5000,                         //적 최대체력
                5000,                        //적 체력
                300,                     //적 공격력
                150,                        //적 방어력
                14000);                     //적이 보유한 돈
        //////////////////////////////////////////////////////////////////////////////////////////////////
        LeeJoongKoo midboss = new LeeJoongKoo(
                "이중구",                    //이중구 적
                7000,                      //적의 최대체력
                7000,                        //적의 체력
                300,                     //적의 공격력
                150,                        //적의 방어력
                19000);                    //적이 보유한 돈
        //////////////////////////////////////////////////////////////////////////////////////////////////
        ZhengCheng highboss = new ZhengCheng(
                "정 청",                    //정청 적
                20000,                       //적 공격력
                20000,                      //적 방어력
                300,                   //적 최대체력
                150,                      //적 최대체력
                24000,                    //적이 보유한 돈
                0);                    //정청이 가지고있는 분노게이지

        //////////////////////////////////////////////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////////////////////////////////////

        Stage stage1 = new Stage(5,su_ki_junior,lowboss,market);//    5층엔 장수기부하, 장수기         //각층에는 상점이있음.
        Stage stage2 = new Stage(10,jung_ku_junior,midboss,market);// 10층엔 재범파식구1,이중구
        Stage stage3 = new Stage(15,easyboss,highboss,market);//      15층엔 연변거지, 정청

        ArrayList<Stage> stages = new ArrayList<>();  //리스트에 스테이지객체를 담는다
        stages.add(stage1);//stages변수는 stage1,2,3이 모여있는 리스트 객체
        stages.add(stage2);
        stages.add(stage3);

        Inventory inventory = new Inventory();// 인벤토리 객체생성

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Scanner scan = new Scanner(System.in);                //게임에 나오는 모든 스캐너값을 받기위한 스캐너 생성자호출
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//----------------------------------------------------------주인공의 공격 스레드------------------------------------------------------
        HeroAttackThread heroAttackThread1 = new HeroAttackThread(1,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs장수기부하 5층
        HeroAttackThread heroAttackThread2 = new HeroAttackThread(2,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs이중구부하(재범파식구) 10층
        HeroAttackThread heroAttackThread3 = new HeroAttackThread(3,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs연변거지 15층
        HeroAttackThread heroAttackThread4 = new HeroAttackThread(4,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs장수기 5층
        HeroAttackThread heroAttackThread5 = new HeroAttackThread(5,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs이중구 10층
        HeroAttackThread heroAttackThread6 = new HeroAttackThread(6,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs정 청 15층
//----------------------------------------------------------적의 공격 스레드---------------------------------------------------------
        EnemyAttackThread enemyAttackThread1 = new EnemyAttackThread(1,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs장수기부하 5층
        EnemyAttackThread enemyAttackThread2 = new EnemyAttackThread(2,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs이중구부하(재범파식구) 10층
        EnemyAttackThread enemyAttackThread3 = new EnemyAttackThread(3,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs연변거지 15층
        EnemyAttackThread enemyAttackThread4 = new EnemyAttackThread(4,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs장수기 5층
        EnemyAttackThread enemyAttackThread5 = new EnemyAttackThread(5,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs이중구 10층
        EnemyAttackThread enemyAttackThread6 = new EnemyAttackThread(6,hero,su_ki_junior,jung_ku_junior,easyboss,lowboss,midboss,highboss,true);//주인공vs정 청 15층
//---------------------------------------------------------------------------------------------------------------------------------------------------------
        OpeningThread openingThread = new OpeningThread();//런어블을 implements했기 때문에 이건 쓰레드가 아니다. 따라서 쓰레드생성을 따로해줘야한다
        Thread opening = new Thread(openingThread);//---------------------------------오프닝 스레드
        MusicThread musicThread = new MusicThread("Big Sleep.mp3",true);//음악 스레드
        musicThread.start();//음악스레드 시작------------------------------------------------------------->이 음악은 배경음으로 깔고싶어서 join을 쓰지않았다.
        opening.start();//오프닝스레드 시작
        try{
            opening.join(); //메인쓰레드는 오프닝 쓰레드가 종료되기까지 기다렸다가 다음으로 넘어가게 된다.
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        for (int i = 0; i < 60; i++) {
            System.out.println();
        }
//
        LoadingThread loading = new LoadingThread("loading");
        loading.start();
        try {
            loading.join();//로딩 스레드가 종료되기까지 기다렸다가 메인스레드 실행
        } catch (Exception e) {
            e.printStackTrace();
        }
//
        try {
            Thread.sleep(2000);//로딩중........... 뜬 다음에 2초정도 기다리고 다음화면 넘어감
        } catch (Exception e) {
            e.printStackTrace();
        }


///////////////////////////////////////////////////////////시작////////////////////////////////////////////////////////////////
        System.out.println("회사 1층내부에 들어왔다..");
        System.out.println("==============================================================");
        System.out.println("게임을 시작하시겠습니까?\n[1] 예  [2] 아니오");
        System.out.println("==============================================================");
        String GameStartNumber = scan.next();



//----------------------------------------메인--------------------------------------------------------------


        loop:
        while(true){
            if(GameStartNumber.equals("1")){//게임 시작을 누를때 ==> 본격적으로 게임을 시작한다.
                //musicThread.close();//배경음 종료
                int stageFloor = 1;              //스테이지를 1로초기화를 하고, stageFloor변수는 실제 층에대한정보가아니라 리스트의 인덱스적인 측면을 접근하도록한 변수다.


                loop1:
                while(stageFloor <= stages.size()){ //스테이지가 만약에 내가 저장한 스테이지리스트의 사이즈보다 작다면 ? 실행 ==> 커지면 false값으로인해 종료된다.

                    Stage stage = stages.get(stageFloor-1);        //1~3스테이지까지있고, 인덱스는 0~2까지라서. stage라는 객체를 각 상황마다 stage1,2,3로서 초기화가 되도록해준다.

                    for (int i = 0; i < 60; i++) {//선택지화면으로 가기전에 60줄정도 띄우고
                        System.out.println();
                    }

                    System.out.println("==============================================================");
                    if(stage.floor == 5){// 각 층에 입장할때의 문구
                        System.out.println("<회사"+stage.floor+"층>");//5층에는 장수기가있음,장수기부하도..
                    }else if(stage.floor == 10){
                        System.out.println("<회사"+stage.floor+"층>");//10층에는 이중구가있음,이중구부하도..
                    }else if(stage.floor == 15){
                        System.out.println("회사"+stage.floor+"층..최종 보스 정청을 죽이고 골드문을 없애자!");//15층에는 정청이있음,연변거지도..
                    }
                    System.out.println("==============================================================");
                    try{
                        Thread.sleep(2000);//회사 몇층이라는걸 띄우고나서 2초정도 딜레이
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 60; i++) {//선택지화면으로 가기전에 60줄정도 띄우고
                        System.out.println();
                    }

                    //loop2:
                    //while(true){
                        System.out.println("<사무실에는 암호가 걸려있습니다! 휴게실에 입장하여 암호에 대한 정보를 얻으세요!>");
                        System.out.println("================================================================");
                        System.out.println("[1] 사무실 입장        [2] 휴게실 입장        [3] 인벤토리 확인        [4] 상점 입장        [5] 스탯 확인");
                        System.out.println("================================================================");
                        System.out.println("[0] 게임종료");
                        System.out.println();
                        System.out.print("취할 행동을 선택해주세요:");
                        String heroact = scan.next();

//-------------------------------------------각 층 사무실에 입장했을 때--------------------------------------------------------------------------
                        if(heroact.equals("1")){//사무실에 입장했을 때
                            for(int i=0;i<60;i++){//                                                                                        /
                                System.out.println();//
                            }
                            System.out.println("암호를 입력하세요(영문)");                                                                        //5층암호:tls(신)
                            System.out.println("================================================================");                           //10층암호:tp(세)
                            System.out.println("                                                        [0]뒤로");                             //15층암호:rP(계)
                            System.out.print("-->");
                            if(stage.floor == 5){// 5층 사무실의 암호
                                String code5 =scan.next();
                                if(code5.equals("tls")){                                           //암호는 한글로 '신'
                                    LoadingThread fightloading = new LoadingThread("startFight");//로딩중 뜨게하고
                                    fightloading.start();
                                    try {
                                        fightloading.join();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("=======================================================================");
                                    System.out.println(lowboss.name+": 자네가 내 러닝메이트인가?"); //장수기 대사
                                    System.out.println(hero.name+": 개소리 집어쳐!"); //이자성 대사
                                    System.out.println("=======================================================================");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println(lowboss.name + "와의 싸움이 시작됐습니다.");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    for(int i=0; i<60; i++) {
                                        System.out.println();
                                    }// 사무실을 선택한 이후의 화면
                                    System.out.println("[1]자동전투     [2]수동전투");
                                    System.out.println("=================================================================");
                                    System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                    System.out.println(lowboss.name + " hp: " + lowboss.hp + "/" + lowboss.maxhp);
                                    System.out.println("=================================================================");

                                    String fightchoice = scan.next();

                                    if(fightchoice.equals("1")){//////자동전투 ==>두가지 스레드흐름.


                                        try {
                                            heroAttackThread4.start();//영웅의 공격스레드(메인흐름 일시정지해야하고 /공격흐름 출발)
                                            Thread.sleep(1000);
                                            enemyAttackThread4.start();//적의 공격스레드(메인흐름 일시정지해야하고 / 공격흐름 출발)
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            heroAttackThread4.join();//영웅공격쓰레드가 실행될때는 메인스레드를 잠시 일시정지해두자--> 서브들의 런이 끝나면
                                            enemyAttackThread4.join();//적 공격쓰레드가 실행될때 또한 메인스레드를 잠시 일시정지 해두자
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if(lowboss.hp<=0){//장수기 체력이 바닥났을때
                                            System.out.println("["+lowboss.name+"를 물리쳤다!]");
                                            try{
                                                Thread.sleep(2000);//회사 몇층이라는걸 띄우고나서 2초정도 딜레이
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            LoadingThread nextstage = new LoadingThread("nextstage");//다음층으로 이동한다고 떠야한다
                                            nextstage.start();
                                            try {
                                                nextstage.join();
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            stageFloor++;
                                        }
                                        if(hero.hp<=0){
                                            hero.revive();
                                            lowboss.hp = lowboss.maxhp;
                                            continue loop1;
                                        }

                                    }else if(fightchoice.equals("2")){//////수동 전투

                                        while(true){
                                            for (int i = 0; i < 60; i++) {
                                                System.out.println();
                                            }
                                            System.out.println("[1]공격");
                                            System.out.println("=================================================================");
                                            System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                            System.out.println(lowboss.name + " hp: " + lowboss.hp + "/" + lowboss.maxhp);
                                            System.out.println("=================================================================");
                                            String heroAttack = scan.next();
                                            if(heroAttack.equals("1")){
                                                while(true){
                                                    System.out.println(hero.name+"의 공격!");
                                                    int damage = hero.attack(lowboss);//데미지라는 변수를 초기화 해주는 이유는 콘솔창에 이만큼의 데미지를 줬다라는 것을 표현하기 위함이다.
                                                    System.out.println(lowboss.name + "의 hp가 " + damage + "만큼 감소했다.");//이렇게..
                                                    try{
                                                        Thread.sleep(500);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                }
                                                if(lowboss.hp<=0){
                                                    System.out.println(lowboss.name+"를 물리쳤다!");
                                                    try{
                                                        Thread.sleep(2000);//회사 몇층이라는걸 띄우고나서 2초정도 딜레이
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    lowboss.hp =0;//내가 죽인 장수기의 체력을 0으로 만들어놓는다.
                                                    hero.money += lowboss.money;
                                                    LoadingThread nextstage = new LoadingThread("nextstage");//다음층으로 이동한다고 떠야한다
                                                    nextstage.start();
                                                    try {
                                                        nextstage.join();
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    stageFloor++;
                                                    continue loop1;
                                                    //----------------------------------------장수기 반격------------------------
                                                }else{
                                                    System.out.println(lowboss.name + "의 공격!");
                                                    int damage = lowboss.attack(hero);
                                                    System.out.println(hero.name + "의 hp가 " + damage + "만큼 감소했다.");
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    if(hero.hp <= 0){
                                                        hero.revive();//죽으면 부활한다//안에 부활로딩스레드있음
                                                        break loop1;
                                                    }
                                                }
                                            }else{
                                                System.out.println("<잘못눌렀습니다>");
                                                try{
                                                    Thread.sleep(2000);//회사 몇층이라는걸 띄우고나서 2초정도 딜레이
                                                }catch (Exception e){
                                                    e.printStackTrace();
                                                }
                                                for(int i=0;i<60;i++){
                                                    System.out.println();
                                                }
                                                continue;
                                            }

                                        }

                                    }
                                                                                                                                   //사무실 전투
//--------------------------------------------------------------------------------------------------------------------------------------------
                                                                                                                                    //뒤로가기
                                }else if(code5.equals("0")){//뒤로가기 눌렀을 때
                                    continue loop1;//뒤로가기
                                }else {
                                    for(int i=0;i<60;i++){
                                        System.out.println();
                                    }
                                    System.out.println("================================================================");
                                    System.out.println("암호가 틀렸습니다!");
                                    System.out.println("이자성 :휴게실에 뭔가 힌트가 있을 것 같은데..");
                                    System.out.println("================================================================");
                                    try{
                                        Thread.sleep(2000);//회사 몇층이라는걸 띄우고나서 2초정도 딜레이
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    for(int i=0;i<60;i++){
                                        System.out.println();
                                    }
                                    continue loop1; //다시 로비로 돌아간다.
                                }
//--------------------------------------------------------------------------------------------------------------------------------------------
                            }else if(stage.floor == 10){// 10층 사무실의 암호
                                String code10 =scan.next();
                                if(code10.equals("tp")){                                                        //암호는 한글로 '세'
                                    LoadingThread fightloading = new LoadingThread("startFight");//로딩중 뜨게하고
                                    fightloading.start();
                                    try {
                                        fightloading.join();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("=======================================================================");
                                    System.out.println(midboss.name+": 출세했네.. 이젠 내 사무실에도 막 들어오고 말이야.."); //이중구 대사
                                    System.out.println(hero.name+": ... "); //이자성 대사
                                    System.out.println("=======================================================================");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println(midboss.name + "와의 싸움이 시작됐습니다.");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    for(int i=0; i<60; i++) {
                                        System.out.println();
                                    }

                                    System.out.println("[1]자동전투     [2]수동전투");
                                    System.out.println("=================================================================");
                                    System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                    System.out.println(midboss.name + " hp: " + midboss.hp + "/" + midboss.maxhp);
                                    System.out.println("=================================================================");

                                    String fightchoice = scan.next();

                                    if(fightchoice.equals("1")){//////이중구 자동전투

                                        try {
                                            heroAttackThread5.start();//영웅의 공격스레드(메인흐름 일시정지해야하고 /공격흐름 출발)
                                            Thread.sleep(1000);
                                            enemyAttackThread5.start();//적의 공격스레드(메인흐름 일시정지해야하고 / 공격흐름 출발)
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            heroAttackThread5.join();//영웅공격쓰레드가 실행될때는 메인스레드를 잠시 일시정지해두자--> 서브들의 런이 끝나면
                                            enemyAttackThread5.join();//적 공격쓰레드가 실행될때 또한 메인스레드를 잠시 일시정지 해두자
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if(midboss.hp<=0){//이중구 체력이 바닥났을때
                                            System.out.println("["+midboss.name+"를 물리쳤다!]");
                                            midboss.hp =0;//내가 죽인 이중구의 체력을 0으로 만들어놓는다.
                                            hero.money += midboss.money;
                                            try{
                                                Thread.sleep(2000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            LoadingThread nextstage = new LoadingThread("nextstage");//다음층으로 이동한다고 떠야한다
                                            nextstage.start();
                                            try {
                                                nextstage.join();
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            stageFloor++;//다음층으로간다
                                        }
                                        if(hero.hp<=0){
                                            hero.revive();
                                            midboss.hp = midboss.maxhp;
                                            continue loop1;
                                        }

                                    }else if(fightchoice.equals("2")){//////이중구 수동 전투
                                        while(true){
                                            midboss.press(hero);//이중구 메소드(압박)
                                            try{
                                                Thread.sleep(800);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i = 0; i<60;i++){
                                                System.out.println();
                                            }
                                            System.out.println("[1]공격");
                                            System.out.println("=================================================================");
                                            System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                            System.out.println(midboss.name + " hp: " + midboss.hp + "/" + midboss.maxhp);
                                            System.out.println("=================================================================");
                                            String heroAttack = scan.next();
                                            if(heroAttack.equals("1")){
                                                while(true){
                                                    System.out.println(hero.name+"의 공격!");
                                                    int damage = hero.attack(midboss);//데미지라는 변수를 초기화 해주는 이유는 콘솔창에 이만큼의 데미지를 줬다라는 것을 표현하기 위함이다.
                                                    System.out.println(midboss.name + "의 hp가 " + damage + "만큼 감소했다.");//이렇게..
                                                    try{
                                                        Thread.sleep(500);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    break;//다시 공격을할지 도망을갈지 선택하는 곳으로 이동해야한다.
                                                }
                                                if(midboss.hp<=0){
                                                    System.out.println("["+midboss.name+"를 물리쳤다!]");
                                                    midboss.hp =0;//내가 죽인 이중구의 체력을 0으로 만들어놓는다.
                                                    hero.money += midboss.money;
                                                    try{
                                                        Thread.sleep(2000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    LoadingThread nextstage = new LoadingThread("nextstage");//다음층으로 이동한다고 떠야한다
                                                    nextstage.start();
                                                    try {
                                                        nextstage.join();
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    stageFloor++;
                                                    continue loop1;
                                                    //----------------------------------------이중구 반격------------------------
                                                }else{
                                                    System.out.println(midboss.name + "의 공격!");
                                                    int damage = midboss.attack(hero);
                                                    System.out.println(hero.name + "의 hp가 " + damage + "만큼 감소했다.");
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    if(hero.hp<=0){
                                                        hero.revive();
                                                        continue loop1;
                                                    }
                                                }
                                            }else{
                                                System.out.println("<잘못눌렀습니다>");
                                                try{
                                                    Thread.sleep(2000);//회사 몇층이라는걸 띄우고나서 2초정도 딜레이
                                                }catch (Exception e){
                                                    e.printStackTrace();
                                                }
                                                for(int i=0;i<60;i++){
                                                    System.out.println();
                                                }
                                                continue;
                                            }

                                        }

                                    }
                                                                                                                                    //사무실 전투
//--------------------------------------------------------------------------------------------------------------------------------------------
                                                                                                                                    //뒤로가기
                                }else if(code10.equals("0")){
                                    try{
                                        Thread.sleep(2000);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    for(int i=0;i<60;i++){
                                        System.out.println();
                                    }
                                    continue loop1;                                                                       //뒤로가기
                                    //=============================================================================================
                                }else {
                                    for(int i=0;i<60;i++){
                                        System.out.println();
                                    }
                                    System.out.println("================================================================");
                                    System.out.println("암호가 틀렸습니다!");
                                    System.out.println("이자성 :휴게실에 뭔가 힌트가 있을 것 같은데..");
                                    System.out.println("================================================================");
                                    try{
                                        Thread.sleep(2000);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    for(int i=0;i<60;i++){
                                        System.out.println();
                                    }
                                    continue loop1; //다시 로비로 돌아간다.
                                }
                                //==================================================================================================
                            }else if(stage.floor == 15){// 15층 사무실의 암호
                                String code15 =scan.next();
                                if(code15.equals("rP")){                                            //암호는 한글로 '계'
                                    LoadingThread fightloading = new LoadingThread("startFight");//로딩중 뜨게하고
                                    fightloading.start();
                                    try {
                                        fightloading.join();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("=======================================================================");
                                    System.out.println(highboss.name+": 그라지 말고.. 이제 고만 선택해라.."); //정청 대사
                                    System.out.println(hero.name+": 저 이제 퇴사합니다"); //이자성 대사
                                    System.out.println(highboss.name+": 너.. 나 감당할 수 있겄냐?"); //정청 대사
                                    System.out.println(hero.name+": ...!"); //이자성 대사
                                    System.out.println("=======================================================================");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println(highboss.name + "과의 싸움이 시작됐습니다.");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    for(int i=0; i<60; i++) {
                                        System.out.println();
                                    }

                                    System.out.println("[1]자동전투     [2]수동전투");
                                    System.out.println("=================================================================");
                                    System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                    System.out.println(highboss.name + " hp: " + highboss.hp + "/" + highboss.maxhp);
                                    System.out.println("=================================================================");

                                    String fightchoice = scan.next();

                                    if(fightchoice.equals("1")){//////자동전투

                                        try {
                                            heroAttackThread6.start();//영웅의 공격스레드(메인흐름 일시정지해야하고 /공격흐름 출발)
                                            Thread.sleep(1000);
                                            enemyAttackThread6.start();//적의 공격스레드(메인흐름 일시정지해야하고 / 공격흐름 출발)
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            heroAttackThread6.join();//영웅공격쓰레드가 실행될때는 메인스레드를 잠시 일시정지해두자--> 서브들의 런이 끝나면
                                            enemyAttackThread6.join();//적 공격쓰레드가 실행될때 또한 메인스레드를 잠시 일시정지 해두자
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if(highboss.hp<=0){//정청의 체력이 바닥났을때
                                            highboss.hp =0;
                                            hero.money += highboss.money;
                                            System.out.println("["+highboss.name+"을 물리쳤다!]");
                                            try{
                                                Thread.sleep(2000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }

                                            LoadingThread ending = new LoadingThread("ending");//엔딩
                                            ending.start();
                                            try{
                                                ending.join();
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            break loop;
                                        }
                                        if(hero.hp<=0){
                                            hero.revive();
                                            highboss.hp = highboss.maxhp;
                                            continue loop1;
                                        }

                                    }else if(fightchoice.equals("2")){//////수동 전투
                                        while(true){
                                            for(int i=0; i<60; i++) {
                                                System.out.println();
                                            }
                                            System.out.println("[1]공격");
                                            System.out.println("=================================================================");
                                            System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                            System.out.println(highboss.name + " hp: " + highboss.hp + "/" + highboss.maxhp);
                                            System.out.println("=================================================================");
                                            String heroAttack = scan.next();
                                            if(heroAttack.equals("1")){
                                                while(true){
                                                    System.out.println(hero.name+"의 공격!");
                                                    int damage = hero.attack(highboss);//데미지라는 변수를 초기화 해주는 이유는 콘솔창에 이만큼의 데미지를 줬다라는 것을 표현하기 위함이다.
                                                    System.out.println(highboss.name + "의 hp가 " + damage + "만큼 감소했다.");//이렇게..
                                                    System.out.println();
                                                    try{
                                                        Thread.sleep(500);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }

                                                    break;//다시 공격을할지 도망을갈지 선택하는 곳으로 이동해야한다.
                                                }
                                                if(highboss.hp<=0){
                                                    highboss.hp =0;
                                                    hero.money += highboss.money;
                                                    System.out.println("["+highboss.name+"를 물리쳤다!]");
                                                    try{
                                                        Thread.sleep(2000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    LoadingThread ending = new LoadingThread("ending");//엔딩
                                                    ending.start();
                                                    try{
                                                        ending.join();
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }

                                                    break loop;
                                                    //----------------------------------------정청 반격------------------------
                                                }else{
                                                    System.out.println(highboss.name + "의 공격!");
                                                    int damage = highboss.attack(hero);
                                                    System.out.println(hero.name + "의 hp가 " + damage + "만큼 감소했다.");
                                                    highboss.angryeffect();//내가 공격할수록 정청 분노게이지는 차오르고  정청의 분노게이지가 100이 되면 0으로 초기화되고 분노횟수는 1오르게된다.
                                                    try{
                                                        Thread.sleep(1500);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    if(hero.hp <= 0){
                                                        hero.revive();
                                                        continue loop1;
                                                    }
                                                }
                                            }else{
                                                System.out.println("잘못눌렀습니다");
                                                try{
                                                    Thread.sleep(2000);
                                                }catch (Exception e){
                                                    e.printStackTrace();
                                                }
                                                for(int i=0;i<60;i++){
                                                    System.out.println();
                                                }
                                                continue;
                                            }

                                        }
                                    }
                                                                                                                                    //사무실 전투
//--------------------------------------------------------------------------------------------------------------------------------------------
                                                                                                                                    //뒤로가기

                                }else if(code15.equals("0")){
                                    continue loop1;//뒤로가기
                                }else {
                                    for(int i=0;i<60;i++){
                                        System.out.println();
                                    }
                                    System.out.println("================================================================");
                                    System.out.println("암호가 틀렸습니다!");
                                    System.out.println("이자성 :휴게실에 뭔가 힌트가 있을 것 같은데..");
                                    System.out.println("================================================================");
                                    try{
                                        Thread.sleep(2000);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    for(int i=0;i<60;i++){
                                        System.out.println();
                                    }
                                    continue loop1; //다시 로비로 돌아간다.
                                }
                            }
/////////////////////////////////////////////////////////////////////////////////////1번선택지. 비밀번호를 입력하고 사무실을 입장했을 때의 전투 시작!


//////////////////////////////////////////////////////////////////////////////////////2번 선택지. 휴게실에 입장했을 때 바로 전투시작///////////////////////////////////////////////////////////////////////////////////////
                        }else if(heroact.equals("2")){//직원 휴게실 입장
                                if(stage.floor == 5){//----------------------------------------------------------------------------------------5층 휴게실 전투
                                    if(su_ki_junior.hp<=0){
                                        for (int i = 0; i < 60; i++) {//60줄정도 띄우고
                                            System.out.println();
                                        }
                                        System.out.println("휴게실엔 아무도 없다.. 다른곳에 가보자");
                                        try{
                                            Thread.sleep(1000);
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        continue;//다시 선택지로 돌아간다.
                                    }
                                    for (int i = 0; i < 60; i++) {//60줄정도 띄우고
                                        System.out.println();
                                    }
                                    System.out.println("[1]자동전투     [2]수동전투");
                                    System.out.println("=================================================================");
                                    System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                    System.out.println(su_ki_junior.name + " hp: " + su_ki_junior.hp + "/" + su_ki_junior.maxhp);
                                    System.out.println("=================================================================");

                                    String fightchoice = scan.next();

                                    if(fightchoice.equals("1")){//////자동전투  //패배하거나 이기면 두 스레드의while문 false되서 스레드종료되고 메인스레드흐름으로 넘어간다. 선택창뜬다.


                                        try {
                                            heroAttackThread1.start();//영웅의 공격스레드(메인흐름 일시정지해야하고 /공격흐름 출발)
                                            Thread.sleep(1000);
                                            enemyAttackThread1.start();//적의 공격스레드(메인흐름 일시정지해야하고 / 공격흐름 출발)
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            heroAttackThread1.join();//영웅공격쓰레드가 실행될때는 메인스레드를 잠시 일시정지해두자--> 서브들의 런이 끝나면

                                            enemyAttackThread1.join();//적 공격쓰레드가 실행될때 또한 메인스레드를 잠시 일시정지 해두자
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if(su_ki_junior.hp<=0){
                                            System.out.println(su_ki_junior.name+"가 굴복했다!");
                                            su_ki_junior.hp =0;
                                            hero.money += su_ki_junior.money;
                                            try{
                                                Thread.sleep(1000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i=0;i<5;i++){
                                                System.out.println();
                                            }
                                            System.out.println(hero.name+": 사무실 암호를 내놔라");
                                            try{
                                                Thread.sleep(1000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i=0;i<5;i++){
                                                System.out.println();
                                            }
                                            System.out.println(su_ki_junior.name+": tls 입니다..");
                                            try{
                                                Thread.sleep(2000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i=0;i<60;i++){
                                                System.out.println();
                                            }
                                            continue loop1;
                                        }
                                        if(hero.hp <= 0){
                                            hero.revive();
                                            continue loop1;
                                        }
                                    //---------------------------------------------------------------------------------------
                                    }else if(fightchoice.equals("2")){//////수동 전투
                                        while(true){
                                            for (int i = 0; i < 60; i++) {
                                                System.out.println();
                                            }
                                            System.out.println("[1]공격");
                                            System.out.println("=================================================================");
                                            System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                            System.out.println(su_ki_junior.name + " hp: " + su_ki_junior.hp + "/" + su_ki_junior.maxhp);
                                            System.out.println("=================================================================");
                                            String heroAttack = scan.next();
                                            if(heroAttack.equals("1")){
                                                while(true){
                                                    System.out.println(hero.name+"의 공격!");
                                                    int damage = hero.attack(su_ki_junior);//데미지라는 변수를 초기화 해주는 이유는 콘솔창에 이만큼의 데미지를 줬다라는 것을 표현하기 위함이다.
                                                    System.out.println(su_ki_junior.name + "의 hp가 " + damage + "만큼 감소했다.");//이렇게..
                                                    try{
                                                        Thread.sleep(500);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                }
                                                if(su_ki_junior.hp<=0){
                                                    System.out.println(su_ki_junior.name+"가 굴복했다!");
                                                    su_ki_junior.hp =0;
                                                    hero.money += su_ki_junior.money;
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    for(int i=0;i<5;i++){
                                                        System.out.println();
                                                    }
                                                    System.out.println(hero.name+": 사무실 암호를 내놔라");
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    for(int i=0;i<5;i++){
                                                        System.out.println();
                                                    }
                                                    System.out.println(su_ki_junior.name+": tls 입니다..");
                                                    try{
                                                        Thread.sleep(2000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    for(int i=0;i<60;i++){
                                                        System.out.println();
                                                    }
                                                    continue loop1;
                                                    //----------------------------장수기 부하의 반격-----------------------
                                                }else{
                                                    System.out.println(su_ki_junior.name + "의 공격!");
                                                    int damage = su_ki_junior.attack(hero);
                                                    System.out.println(hero.name + "의 hp가 " + damage + "만큼 감소했다.");
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    if(hero.hp <= 0){
                                                        hero.revive();
                                                        continue loop1;
                                                    }
                                                }
                                            }else{
                                                System.out.println("잘못눌렀습니다");
                                                continue;
                                            }

                                        }
                                    }


                                }else if(stage.floor == 10){//---------------------------------------------------------------------------------10층 사무실 이중구 전투
                                    if(jung_ku_junior.hp<=0){
                                        for (int i = 0; i < 60; i++) {//60줄정도 띄우고
                                            System.out.println();
                                        }
                                        System.out.println("휴게실엔 아무도 없다.. 다른곳에 가보자");
                                        try{
                                            Thread.sleep(1000);
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        continue;//다시 선택지로 돌아간다.
                                    }
                                    System.out.println();
                                    System.out.println("[1]자동전투     [2]수동전투");
                                    System.out.println("=================================================================");
                                    System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                    System.out.println(jung_ku_junior.name + " hp: " + jung_ku_junior.hp + "/" + jung_ku_junior.maxhp);
                                    System.out.println("=================================================================");
                                    String fightchoice = scan.next();

                                    if(fightchoice.equals("1")){//////자동전투

                                        try {
                                            heroAttackThread2.start();//영웅의 공격스레드(메인흐름 일시정지해야하고 /공격흐름 출발)
                                            Thread.sleep(1000);
                                            enemyAttackThread2.start();//적의 공격스레드(메인흐름 일시정지해야하고 / 공격흐름 출발)
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            heroAttackThread2.join();//영웅공격쓰레드가 실행될때는 메인스레드를 잠시 일시정지해두자--> 서브들의 런이 끝나면
                                            enemyAttackThread2.join();//적 공격쓰레드가 실행될때 또한 메인스레드를 잠시 일시정지 해두자
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if(jung_ku_junior.hp<=0){
                                            System.out.println(jung_ku_junior.name+"가 굴복했다!");
                                            jung_ku_junior.hp =0;
                                            hero.money += jung_ku_junior.money;
                                            try{
                                                Thread.sleep(1000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i=0;i<5;i++){
                                                System.out.println();
                                            }
                                            System.out.println(hero.name+": 사무실 암호를 내놔라");
                                            try{
                                                Thread.sleep(1000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i=0;i<5;i++){
                                                System.out.println();
                                            }
                                            System.out.println(jung_ku_junior.name+": tp 입니다..");
                                            try{
                                                Thread.sleep(2000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i=0;i<60;i++){
                                                System.out.println();
                                            }
                                            continue loop1;
                                        }
                                        if(hero.hp<=0){
                                            hero.revive();
                                            continue loop1;
                                        }

                                    }else if(fightchoice.equals("2")){//////수동 전투
                                        while(true){
                                            for (int i = 0; i < 60; i++) {
                                                System.out.println();
                                            }
                                            System.out.println("[1]공격");
                                            System.out.println("=================================================================");
                                            System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                            System.out.println(jung_ku_junior.name + " hp: " + jung_ku_junior.hp + "/" + jung_ku_junior.maxhp);
                                            System.out.println("=================================================================");
                                            String heroAttack = scan.next();
                                            if(heroAttack.equals("1")){
                                                while(true){
                                                    System.out.println(hero.name+"의 공격!");
                                                    int damage = hero.attack(jung_ku_junior);//데미지라는 변수를 초기화 해주는 이유는 콘솔창에 이만큼의 데미지를 줬다라는 것을 표현하기 위함이다.
                                                    System.out.println(jung_ku_junior.name + "의 hp가 " + damage + "만큼 감소했다.");//이렇게..
                                                    try{
                                                        Thread.sleep(500);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                }
                                                if(jung_ku_junior.hp<=0){//휴게실에 있는 적을 굴복시키고 암호를 얻어낸다
                                                    System.out.println(jung_ku_junior.name+"가 굴복했다!");
                                                    jung_ku_junior.hp =0;
                                                    hero.money += jung_ku_junior.money;
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    for(int i=0;i<5;i++){
                                                        System.out.println();
                                                    }
                                                    System.out.println(hero.name+": 사무실 암호를 내놔라");
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    for(int i=0;i<5;i++){
                                                        System.out.println();
                                                    }
                                                    System.out.println(jung_ku_junior.name+": tp 입니다..");
                                                    try{
                                                        Thread.sleep(2000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    for(int i=0;i<60;i++){
                                                        System.out.println();
                                                    }
                                                    continue loop1;
                                                    //----------------------------------------이중구 부하의 반격------------------------
                                                }else{
                                                    System.out.println(jung_ku_junior.name + "의 공격!");
                                                    int damage = jung_ku_junior.attack(hero);
                                                    System.out.println(hero.name + "의 hp가 " + damage + "만큼 감소했다.");
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    if(hero.hp <= 0){
                                                        hero.revive();
                                                        break loop1;
                                                    }
                                                }
                                            }else{
                                                System.out.println("잘못눌렀습니다");
                                                continue;
                                            }

                                        }
                                    }
                                }else if(stage.floor == 15){//---------------------------------------------------------------------------------15층 휴게실 전투
                                    if(easyboss.hp<=0){
                                        for (int i = 0; i < 60; i++) {//60줄정도 띄우고
                                            System.out.println();
                                        }
                                        System.out.println("휴게실엔 아무도 없다.. 다른곳에 가보자");
                                        try{
                                            Thread.sleep(1000);
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        continue;//다시 선택지로 돌아간다.
                                    }
                                    System.out.println();
                                    System.out.println("[1]자동전투     [2]수동전투");
                                    System.out.println("=================================================================");
                                    System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                    System.out.println(easyboss.name + " hp: " + easyboss.hp + "/" + easyboss.maxhp);
                                    System.out.println("=================================================================");
                                    String fightchoice = scan.next();

                                    if(fightchoice.equals("1")){//////자동전투

                                        try {
                                            heroAttackThread3.start();//영웅의 공격스레드(메인흐름 일시정지해야하고 /공격흐름 출발)
                                            Thread.sleep(1000);
                                            enemyAttackThread3.start();//적의 공격스레드(메인흐름 일시정지해야하고 / 공격흐름 출발)
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            heroAttackThread3.join();//영웅공격쓰레드가 실행될때는 메인스레드를 잠시 일시정지해두자--> 서브들의 런이 끝나면
                                            enemyAttackThread3.join();//적 공격쓰레드가 실행될때 또한 메인스레드를 잠시 일시정지 해두자
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if(easyboss.hp<=0){//연변거지 체력이 바닥났을때
                                            System.out.println(easyboss.name+"가 굴복했다!");
                                            easyboss.hp =0;
                                            hero.money += easyboss.money;
                                            try{
                                                Thread.sleep(1000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i=0;i<60;i++){
                                                System.out.println();
                                            }
                                            System.out.println(hero.name+": 사무실 암호를 내놔라");
                                            try{
                                                Thread.sleep(1000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i=0;i<5;i++){
                                                System.out.println();
                                            }
                                            System.out.println(easyboss.name+": rP..입니다");
                                            try{
                                                Thread.sleep(2000);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            for(int i=0;i<60;i++){
                                                System.out.println();
                                            }
                                            continue loop1;

                                        }
                                        if(hero.hp<=0){
                                            hero.revive();
                                            continue loop1;
                                        }

                                    }else if(fightchoice.equals("2")){//////수동 전투
                                        while(true){
                                            for (int i = 0; i < 60; i++) {
                                                System.out.println();
                                            }
                                            System.out.println("[1]공격");
                                            System.out.println("=================================================================");
                                            System.out.println(hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                                            System.out.println(easyboss.name + " hp: " + easyboss.hp + "/" + easyboss.maxhp);
                                            System.out.println("=================================================================");
                                            String heroAttack = scan.next();
                                            if(heroAttack.equals("1")){
                                                while(true){
                                                    System.out.println(hero.name+"의 공격!");
                                                    int damage = hero.attack(easyboss);//데미지라는 변수를 초기화 해주는 이유는 콘솔창에 이만큼의 데미지를 줬다라는 것을 표현하기 위함이다.
                                                    easyboss.avoid(hero,damage);//연변거지가 받은 데미지와 그를 공격하는 대상을 인풋으로 받아서 회피를 결정한다.
                                                    System.out.println(easyboss.name + "의 hp가 " + damage + "만큼 감소했다.");//이렇게..
                                                    try{
                                                        Thread.sleep(500);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    break;//다시 공격을할지 도망을갈지 선택하는 곳으로 이동해야한다.
                                                }
                                                if(easyboss.hp<=0){
                                                    System.out.println(easyboss.name+"가 굴복했다!");
                                                    easyboss.hp =0;
                                                    hero.money += easyboss.money;
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    for(int i=0;i<5;i++){
                                                        System.out.println();
                                                    }
                                                    System.out.println(hero.name+": 사무실 암호를 내놔라");
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    for(int i=0;i<5;i++){
                                                        System.out.println();
                                                    }
                                                    System.out.println(easyboss.name+": rP..입니다");
                                                    try{
                                                        Thread.sleep(2000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    for(int i=0;i<60;i++){
                                                        System.out.println();
                                                    }
                                                    continue loop1;
                                                    //----------------------------------------정청 반격------------------------
                                                }else{
                                                    System.out.println(easyboss.name + "의 공격!");
                                                    int damage = easyboss.attack(hero);
                                                    System.out.println(hero.name + "의 hp가 " + damage + "만큼 감소했다.");
                                                    try{
                                                        Thread.sleep(1000);
                                                    }catch (Exception e){
                                                        e.printStackTrace();
                                                    }
                                                    if(hero.hp <= 0){
                                                        hero.revive();
                                                        break loop1;
                                                    }
                                                }
                                            }else{
                                                System.out.println("잘못눌렀습니다");
                                                continue;
                                            }

                                        }
                                    }
                                }
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                        }else if(heroact.equals("3")){//인벤토리 확인
                            while(true){

                                System.out.println("[1] 무기 장착  [2] 방어구 장착 [3] 체력 포션 사용");
                                System.out.println("--------------------------------------------------------------------------");
                                System.out.println("[0] 뒤로");
                                String item_equip = scan.next();
                                if(item_equip.equals("1")){//무기 장착
                                    while(true){
                                        inventory.show_Weaponlist(hero.weapons);
                                        System.out.println("--------------------------------------------------------------------------");
                                        System.out.println("[0] 뒤로");
                                        System.out.println();
                                        System.out.print("장착할 무기를 선택해주세요:");
                                        String weapon_equip = scan.next();
                                        if(weapon_equip.equals("0")){//뒤로가기
                                            break;
                                        }else{
                                            int weaponchoice;
                                            try{
                                                weaponchoice = Integer.parseInt(weapon_equip)-1;//weaponchoice는 문자열이니까 정수형으로 변환해주고 인덱스값으로 나올수있게 -1을 해준다
                                            }catch (Exception e){
                                                System.out.println("<잘못 입력하셨습니다>");
                                                continue;
                                            }
                                            if(weaponchoice>=0 && weaponchoice<hero.weapons.size()){//내가 (입력한 숫자-1 = 인덱스) 가 0에서 내가 보유한 무기리스트 사이즈보다 작으면 구입가능하다
                                                hero.show_equip(hero.weapons.get(weaponchoice));//내가 선택한 무기를 장착을 완료했다는 문구를 뜨게하고
                                                hero.weapon = hero.weapons.get(weaponchoice);//무기를 장착한 후에
                                                hero.pluspower = hero.weapon.power;//내 추가공격력을 무기공격력으로 초기화해준다. 왜냐하면 무기가 바뀌니까 공격력이 바껴야한다고 생각했음.
                                                hero.weapons.remove(0);//무기구 리스트에서 하나를 삭제한다
                                            }else{
                                                System.out.println("<잘못 입력하셨습니다>");
                                            }
                                        }
                                    }
                                }else if(item_equip.equals("2")){//방어구 장착
                                    while(true){
                                        inventory.show_Armorlist(hero.armors);
                                        System.out.println("--------------------------------------------------------------------------");
                                        System.out.println("[0] 뒤로");
                                        System.out.println();
                                        System.out.print("장착할 방어구를 선택해주세요:");
                                        String armor_equip = scan.next();
                                        if(armor_equip.equals("0")){//뒤로가기
                                            break;
                                        }else{
                                            int armorchoice;
                                            try{
                                                armorchoice = Integer.parseInt(armor_equip)-1;//weaponchoice는 문자열이니까 정수형으로 변환해주고 인덱스값으로 나올수있게 -1을 해준다
                                            }catch (Exception e){
                                                System.out.println("<잘못 입력하셨습니다>");
                                                continue;
                                            }
                                            if(armorchoice>=0 && armorchoice<hero.armors.size()){//내가 (입력한 숫자-1 = 인덱스) 가 0에서 내가 보유한 방어구리스트 사이즈보다 작으면 구입가능하다
                                                hero.show_equip(hero.armors.get(armorchoice));//내가 선택한 방어구를 장착을 완료했다는 문구를 뜨게하고
                                                hero.armor = hero.armors.get(armorchoice);//방어구를 장착한 후에
                                                hero.plusdefense = hero.armor.defense;//내 추가방어력을 방어구의 방어력으로 초기화 해준다
                                                hero.armors.remove(0);//방어구 리스트에서 하나를 삭제한다
                                            }else{
                                                System.out.println("<잘못 입력하셨습니다>");
                                            }
                                        }
                                    }
                                }else if(item_equip.equals("3")){//체력 포션 사용
                                    while(true){
                                        inventory.show_Potionlist(hero.potions);
                                        System.out.println("사용할 포션을 선택해주세요");
                                        System.out.println("-----------------------------------------------------------");
                                        System.out.println("[0] 뒤로");
                                        String potionchoice = scan.next();
                                        if(potionchoice.equals("0")){//뒤로가기
                                            break;
                                        }else{//포션선택하기
                                            int potionchoiceNum;
                                            try{
                                                potionchoiceNum = Integer.parseInt(potionchoice)-1;//potionchoice는 문자열이니까 정수형으로 변환해주고 -1을 해줘서 내가 1을 선택하면 인덱스0의 포션이 선택되게끔
                                            }catch (Exception e){
                                                System.out.println("<잘못 입력하셨습니다>");
                                                continue;
                                            }
                                            if(hero.hp == hero.maxhp){//체력이 최대치일때
                                                System.out.println();
                                                System.out.println("<체력이 최대치입니다.>");
                                                System.out.println();
                                                continue;
                                            }
                                            if(hero.potions.size() == 0){//포션이 없을때
                                                System.out.println();
                                                System.out.println("<포션이 없습니다.>");
                                                System.out.println();
                                                continue;
                                            }
                                            if(potionchoiceNum == 0){
                                               if(hero.maxhp - hero.hp >  ((HpPotion) hp_potion).hp){
                                                   System.out.println("hp를 " + ((HpPotion) hp_potion).hp + "만큼 회복하였습니다.");//내가 먹을 포션의 체력량이 내가 닳은 체력보다 작다면
                                                   hero.hp += ((HpPotion) hp_potion).hp;
                                               }else{
                                                   System.out.println("hp를 " + (hero.maxhp - hero.hp) + "만큼 회복하였습니다.");//내가 먹을 포션의 체력량이 나의 닳은 체력보다 많다면
                                                   hero.hp = hero.maxhp;
                                               }
                                               hero.potions.remove(0);//포션리스트에서 하나를 삭제한다
                                            }
                                        }

                                    }
                                }else if(item_equip.equals("0")){//뒤로가기
                                    break;
                                }else{
                                    try{
                                        Thread.sleep(1500);
                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }
                            }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------상점
                        }else if(heroact.equals("4")){//상점 입장
                            System.out.println("=================================================================");
                            System.out.println("<상점에 입장하셨습니다>");
                            System.out.println("=================================================================");
                            try{
                                Thread.sleep(1500);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            loop6:
                            while(true){
                                System.out.println("[1] 아이템 사기                                            [0] 뒤로");
                                System.out.println("=================================================================");
                                String marketActChoice = scan.next();
                                if(marketActChoice.equals("1")){//-------------------------------------------------------상점 아이템 목록이 출력되도록 한다.
                                    loop7:
                                    while(true){
                                        System.out.println("===========================<무기 리스트>==================================");
                                        for (int i = 0; i < market.weapon.size(); i++) {                                                           //1.각목
                                                           //상점 무기리스트의 크기                                                                   //2.칼
                                                                                                                                                   //3.쇠파이프
                                            System.out.println("["+(i+1)+"] "+market.weapon.get(i).name+" (공격력  +"+market.weapon.get(i).power+")            <가격: "+market.weapon.get(i).price+"원>");
                                        }    //for문을 리스트의 크기만큼 돌림으로써 리스트에 저장해두었던 내 아이템들을 모두 보여줄수있는 장점이있다.
                                        System.out.println("===========================<방어구 리스트>================================");
                                        for (int i = 0; i < market.armor.size(); i++) {
                                            System.out.println("["+(i+1)+"]"+market.armor.get(i).name+" (방어력  +"+market.armor.get(i).defense+")             <가격: "+market.armor.get(i).price+"원>");
                                        }
                                        System.out.println("===========================<포션 리스트>==================================");
                                        for (int i = 0; i < market.hp_potion.size(); i++) {
                                            System.out.println("["+(i+1)+"] "+market.hp_potion.get(i).name+" (체력  +"+market.hp_potion.get(i).hp+")           <가격: "+market.hp_potion.get(i).price+"원>");
                                        }
                                        System.out.println("========================================================================");
                                        System.out.println();
                                        System.out.println();
                                        System.out.println("<어떤 아이템을 구입 하시겠습니까?>");
                                        System.out.println("[1] 무기  [2] 방어구  [3] 체력포션");
                                        System.out.println("========================================================================");
                                        System.out.println("                                                                 [0] 뒤로");
                                        String purchaseItemChoice = scan.next();//어떤 아이템을 구매할것인지 정하는 변수
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                        if(purchaseItemChoice.equals("1")){//-----------------------------------------------------------------무기 구입하기 선택
                                            while(true){
                                                System.out.println("===========================<무기 리스트>==================================");
                                                for (int i = 0; i < market.weapon.size(); i++) {
                                                    System.out.println("["+(i+1)+"] "+market.weapon.get(i).name+" (공격력  +"+market.weapon.get(i).power+")            <가격: "+market.weapon.get(i).price+"원>");
                                                }
                                                System.out.println("========================================================================");
                                                System.out.println("<선택하고자하는 무기의 번호를 입력하면 구매할수있습니다>                 [0] 뒤로");
                                                System.out.println("<보유한 돈 :"+hero.money+"원>");
                                                String choiceWeapon = scan.next();
                                                if(choiceWeapon.equals("0")){
                                                    break;
                                                }else{
                                                    int choiceWeaponIndex;    //무기리스트의 인덱스와 실제로 연관이 되도록 int형 변수를 설정하자

                                                    try{
                                                        choiceWeaponIndex = Integer.parseInt(choiceWeapon)-1;  //내가 입력하는 값은 string형 변수니까 정수형으로 형변환을 시켜주고,
                                                    }catch(Exception e){                                       //또한 입력값이 1부터시작하니까 인덱스랑 연관시키기 위해서 -1을 해준다
                                                        System.out.println("잘못 입력하셨습니다.");               //이상한거 넣으면 다시 선택지로 돌아가도록 해주자
                                                        continue;//무기 선택지로 다시 간다
                                                    }

                                                    if(choiceWeaponIndex<0 || choiceWeaponIndex >= stage.market.weapon.size()){//무기 리스트의 인덱스범위 외의 숫자를 누른경우
                                                        System.out.println("잘못 입력하셨습니다.");
                                                        continue;//무기 선택지로 다시 간다
                                                    }
                                                    if(stage.market.weapon.get(choiceWeaponIndex).price > hero.money){// choiceWeaponIndex는 그냥 무기리스트의 인덱스값으로 보면되고
                                                        System.out.println("돈이 부족합니다.");                          // 무기 가격이 내 돈보다 크면 돈이 부족하다고 문구가 뜨게 해준다
                                                        continue;//무기 선택지로 다시 간다
                                                    }
                                                    System.out.println("구입에 성공했습니다!");
                                                    hero.BuyItem(stage.market.weapon.get(choiceWeaponIndex)); // 무기라는 객체를 주인공이 사는 메소드 // 캐릭터 무기 인벤토리에 무기추가
                                                }
                                            }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                        }else if(purchaseItemChoice.equals("2")){//-----------------------------------------------------------------방어구 구입하기 선택
                                            while(true){
                                                System.out.println("===========================<방어구 리스트>================================");
                                                for (int i = 0; i < market.armor.size(); i++) {
                                                    System.out.println("["+(i+1)+"]"+market.armor.get(i).name+" (방어력  +"+market.armor.get(i).defense+")             <가격: "+market.armor.get(i).price+"원>");
                                                }
                                                System.out.println("========================================================================");
                                                System.out.println("<선택하고자하는 방어구의 번호를 입력하면 구매할수있습니다>              [0] 뒤로");
                                                System.out.println("<보유한 돈 :"+hero.money+"원>");
                                                String choiceArmor = scan.next();
                                                if(choiceArmor.equals("0")){
                                                    break;
                                                }else{
                                                    int choiceArmorIndex;    //방어구 리스트의 인덱스와 실제로 연관이 되도록 int형 변수를 설정하자

                                                    try{
                                                        choiceArmorIndex = Integer.parseInt(choiceArmor)-1;  //내가 입력하는 값은 string형 변수니까 정수형으로 형변환을 시켜주고,
                                                    }catch(Exception e){                                       //또한 입력값이 1부터시작하니까 인덱스랑 연관시키기 위해서 -1을 해준다
                                                        System.out.println("잘못 입력하셨습니다.");               //이상한거 넣으면 다시 선택지로 돌아가도록 해주자
                                                        continue;//방어구 선택지로 다시 간다
                                                    }

                                                    if(choiceArmorIndex<0 || choiceArmorIndex >= stage.market.armor.size()){//방어구 리스트의 인덱스범위 외의 숫자를 누른경우
                                                        System.out.println("잘못 입력하셨습니다.");
                                                        continue;//방어구 선택지로 다시 간다
                                                    }
                                                    if(stage.market.armor.get(choiceArmorIndex).price > hero.money){// choiceArmorIndex는 그냥 방어구리스트의 인덱스값으로 보면되고
                                                        System.out.println("돈이 부족합니다.");                          // 방어구 가격이 내 돈보다 크면 돈이 부족하다고 문구가 뜨게 해준다
                                                        continue;//방어구 선택지로 다시 간다
                                                    }
                                                    System.out.println("구입에 성공했습니다!");
                                                    hero.BuyItem(stage.market.armor.get(choiceArmorIndex)); // 방어구라는 객체를 주인공이 사는 메소드 //캐릭터 방어구 인벤토리에 방어구추가
                                                }
                                            }
                                        }else if(purchaseItemChoice.equals("3")){//-----------------------------------------------------------------체력포션 구입하기 선택
                                            while(true){
                                                System.out.println("===========================<포션 리스트>==================================");
                                                for (int i = 0; i < market.hp_potion.size(); i++) {
                                                    System.out.println("["+(i+1)+"] "+market.hp_potion.get(i).name+" (체력  +"+market.hp_potion.get(i).hp+")           <가격: "+market.hp_potion.get(i).price+"원>");
                                                }
                                                System.out.println("========================================================================");
                                                System.out.println("<선택하고자하는 체력 회복 아이템의 번호를 입력하면 구매할수있습니다>      [0] 뒤로");
                                                System.out.println("<보유한 돈 :"+hero.money+"원>");
                                                String choiceHpPotion = scan.next();
                                                if(choiceHpPotion.equals("0")){
                                                    break;
                                                }
                                                int choiceHpPotionIndex;

                                                try{
                                                    choiceHpPotionIndex = Integer.parseInt(choiceHpPotion)-1;
                                                }catch(Exception e){
                                                    System.out.println("잘못 입력하셨습니다.");               //이상한거 넣으면 다시 선택지로 돌아가도록 해주자
                                                    continue;//포션 선택지로 다시 간다
                                                }
                                                if(choiceHpPotionIndex < 0 ||choiceHpPotionIndex >= stage.market.hp_potion.size()){//포션리스트 범위내의 인덱스 값 넣으면 다시 돌아가도록
                                                    System.out.println("잘못 입력했습니다.");
                                                    continue;//포션 선택지로 다시 간다
                                                }
                                                while(true){ //--------------------------------------------체력포션 몇개 살건지 정하쟈
                                                    System.out.println("========================================================================");
                                                    System.out.println("구매하실 수량을 입력해주세요 ([0] 뒤로) :");
                                                    String number = scan.next(); //number: 갯수
                                                    if(number.equals("0")){
                                                        break;
                                                    }
                                                    int potionNumber;//구매할 갯수

                                                    try{
                                                        potionNumber = Integer.parseInt(number);//int변수로 형변환해주고
                                                    }catch (Exception e){
                                                        System.out.println("잘못 입력하셨습니다.");
                                                        continue;
                                                    }

                                                    if(potionNumber >0){
                                                        if(choiceHpPotionIndex == 0){
                                                            hero.BuyItem(stage.market.hp_potion.get(choiceHpPotionIndex),potionNumber);//체력포션에대한 정보와 구매할 갯수에대한 정보를 매개변수로 넣어준다.(오버로딩)
                                                        }
                                                    }
                                                    System.out.println("<구매 완료>");
                                                    break;
                                                }
                                            }
                                        }else if(purchaseItemChoice.equals("0")){//------------------------------------------------------------------뒤로가기 눌러서 아이템사기 선택지로 가기
                                            continue loop6;
                                        }
                                    }
                                }else if(marketActChoice.equals("0")){//-------------------------------------------------뒤로가기
                                    continue loop1;//--------------------------------------------------------------------뒤로가기를 누르면 처음 선택창이 나온다.
                                }else{
                                    System.out.println("잘못입력하셨습니다.");
                                    continue loop6;//--------------------------------------------------------------------아이템 사기 선택지로 들어간다.
                                }
                            }
//---------------------------------------------------------------------------------------------------------------------스탯확인                            
                        }else if(heroact.equals("5")){//스탯 확인
                            hero.showMyStat();//내 스탯을 확인하는 메소드
                            while(true){
                                System.out.print("-->");
                                String back = scan.next();
                                if(back.equals("0")){
                                    break;
                                }else{
                                    System.out.println("잘못 입력했습니다.");
                                }
                            }
                        }else if(heroact.equals("0")){
                            break loop;
                        }else{
                            System.out.println("잘못입력하셨습니다");
                            try{
                                Thread.sleep(1500);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    //}
                }
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            }else if(GameStartNumber.equals("2")){//게임 시작을 하지 않을때
                System.out.println("게임을 종료합니다.");
                break; //게임을 시작하겠습니까? 질문에대해, 아니오를 선택했을때 이 전체적인 while문을 탈출하고 프로젝트를 종료한다.
            }else{//2이상의 숫자를 입력했을때
                System.out.println("<잘못 입력하셨습니다. 다시 입력해주세요.>");
                System.out.println();
                continue;
            }
        }




    }
}
