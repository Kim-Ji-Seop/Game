package MyGame.thread;


import MyGame.Game1_Character.*;
import com.sun.source.tree.SynchronizedTree;

public class HeroAttackThread extends Thread {
    public int type;
    public Hero hero;
    public Enemy enemy1;
    public Enemy enemy2;
    public Yeonbyeon easyboss;
    public JangSuKi lowboss;
    public LeeJoongKoo midboss;
    public ZhengCheng highboss;
    public boolean stop;



    public HeroAttackThread(int type,
                            Hero hero,
                            Enemy enemy1,
                            Enemy enemy2,
                            Yeonbyeon easyboss,
                            JangSuKi lowboss,
                            LeeJoongKoo midboss,
                            ZhengCheng highboss,
                            boolean stop){
        this.type = type;
        this.hero = hero;
        this.enemy1 = enemy1;
        this.enemy2 = enemy2;
        this.easyboss =easyboss;
        this.lowboss = lowboss;
        this.midboss = midboss;
        this.highboss = highboss;
        this.stop = stop;
    }


    public void attacktarget(){
        int damage;

        while(stop){//적을 공격한다
            //나의 공격력과 방어력, 적의 체력과 방어력 필요 Hero클래스에 접근을해야하고 Enemy클래스와 그의 자식클래스들에 접근해야한다.
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if(type == 1){//장수기 부하랑 싸울 때

                damage =hero.attack(enemy1);
                if(hero.hp>0 && enemy1.hp>0){//적의 체력과 영웅의체력 모두 0보다 클때의 조건에서, 실행이 되어야한다.
                    System.out.println("<"+hero.name+"의 공격!>");
                    System.out.println(enemy1.name+"는"+damage+"의 데미지를 입었다");
                    System.out.println("===================================");
                    System.out.println(enemy1.name + " hp: " + enemy1.hp + "/" + enemy1.maxhp);
                    System.out.println("===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){//영웅이 공격하는 스레드에서는 영웅의 체력을 0으로만 표기를 한다.
                        hero.hp=0;
                    }else if(enemy1.hp<=0){//적의 체력이 0보다 작으면
                        enemy1.hp=0;
                        System.out.println(enemy1.name+"는"+damage+"의 데미지를 입었다");
                        System.out.println("===================================");
                        System.out.println(enemy1.name + " hp: " + enemy1.hp + "/" + enemy1.maxhp);
                        System.out.println("===================================");
                    }
                    stop = false;
                }

            }else if(type == 2){//이중구 부하랑 싸울때
                damage =hero.attack(enemy2);
                if(hero.hp>0 && enemy2.hp>0){
                    System.out.println("<"+hero.name+"의 공격!>");
                    System.out.println(enemy2.name+"는"+damage+"의 데미지를 입었다");
                    System.out.println("===================================");
                    System.out.println(enemy2.name + " hp: " + enemy2.hp + "/" + enemy2.maxhp);
                    System.out.println("===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                    }else if(enemy2.hp<=0){
                        enemy2.hp=0;
                        System.out.println(enemy2.name+"는"+damage+"의 데미지를 입었다");
                        System.out.println("===================================");
                        System.out.println(enemy2.name + " hp: " + enemy2.hp + "/" + enemy2.maxhp);
                        System.out.println("===================================");
                    }
                    stop = false;
                }

            }else if(type == 3){//연변거지랑 싸울 때
                damage =hero.attack(easyboss);
                if(hero.hp>0 && easyboss.hp>0){
                    System.out.println("<"+hero.name+"의 공격!>");
                    System.out.println(easyboss.name+"는"+damage+"의 데미지를 입었다");
                    System.out.println("===================================");
                    System.out.println(easyboss.name + " hp: " + easyboss.hp + "/" + easyboss.maxhp);
                    System.out.println("===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                    }else if(easyboss.hp<=0){
                        easyboss.hp=0;
                        System.out.println(easyboss.name+"는"+damage+"의 데미지를 입었다");
                        System.out.println("===================================");
                        System.out.println(easyboss.name + " hp: " + easyboss.hp + "/" + easyboss.maxhp);
                        System.out.println("===================================");
                    }
                    stop = false;
                }


            }else if(type == 4){//장수기랑 싸울때
                damage =hero.attack(lowboss);
                if(hero.hp>0 && lowboss.hp>0){
                    System.out.println("<"+hero.name+"의 공격!>");
                    System.out.println(lowboss.name+"는"+damage+"의 데미지를 입었다");
                    System.out.println("===================================");
                    System.out.println(lowboss.name + " hp: " + lowboss.hp + "/" + lowboss.maxhp);
                    System.out.println("===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                    }else if(lowboss.hp<=0){
                        lowboss.hp=0;
                        System.out.println(lowboss.name+"는"+damage+"의 데미지를 입었다");
                        System.out.println("===================================");
                        System.out.println(lowboss.name + " hp: " + lowboss.hp + "/" + lowboss.maxhp);
                        System.out.println("===================================");
                    }
                    stop = false;
                }


            }else if(type == 5){//이중구랑 싸울때
                damage =hero.attack(midboss);
                if(hero.hp>0 && midboss.hp>0){
                    System.out.println("<"+hero.name+"의 공격!>");
                    System.out.println(midboss.name+"는"+damage+"의 데미지를 입었다");
                    System.out.println("===================================");
                    System.out.println(midboss.name + " hp: " + midboss.hp + "/" + midboss.maxhp);
                    System.out.println("===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                    }else if(midboss.hp<=0){
                        midboss.hp=0;
                        System.out.println(midboss.name+"는"+damage+"의 데미지를 입었다");
                        System.out.println("===================================");
                        System.out.println(midboss.name + " hp: " + midboss.hp + "/" + midboss.maxhp);
                        System.out.println("===================================");
                    }
                    stop = false;
                }

                /////////////////////////////////////////////////////////////////////////
            }else if(type == 6){//정청이랑 싸울때
                damage =hero.attack(highboss);
                if(hero.hp>0 && highboss.hp>0){
                    System.out.println("<"+hero.name+"의 공격!>");
                    System.out.println(highboss.name+"은"+damage+"의 데미지를 입었다");
                    System.out.println("===================================");
                    System.out.println(highboss.name + " hp: " + highboss.hp + "/" + highboss.maxhp);
                    System.out.println("===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                    }else if(highboss.hp<=0){
                        highboss.hp=0;
                        System.out.println(highboss.name+"은"+damage+"의 데미지를 입었다");
                        System.out.println("===================================");
                        System.out.println(highboss.name + " hp: " + highboss.hp + "/" + highboss.maxhp);
                        System.out.println("===================================");
                    }
                    stop = false;
                }

            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run(){
        try{
            attacktarget();//이 메소드가 종료되면 run은 자동종료된다.
        }catch (Exception e){

        }

    }
}
