package MyGame.thread;

import MyGame.Game1_Character.*;

public class EnemyAttackThread extends Thread {
    public int type;
    public Hero hero;
    public Enemy enemy1;
    public Enemy enemy2;
    public Yeonbyeon easyboss;
    public JangSuKi lowboss;
    public LeeJoongKoo midboss;
    public ZhengCheng highboss;
    public boolean stop;


    public EnemyAttackThread(int type,
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
    public void Enemy_attack(){
        int damage;

        while(stop){//적을 공격한다
            //나의 공격력과 방어력, 적의 체력과 방어력 필요 Hero클래스에 접근을해야하고 Enemy클래스와 그의 자식클래스들에 접근해야한다.
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if(type == 1){////////////////////////////////////////////////////////////장수기 부하

                damage =enemy1.attack(hero);
                if(hero.hp>0 && enemy1.hp>0){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<"+enemy1.name+"의 공격!>");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은(는)"+damage+"의 데미지를 입었다");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은(는)"+damage+"의 데미지를 입었다");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println();
                    }else if(enemy1.hp<=0){
                        enemy1.hp=0;
                    }
                    stop = false;
                }

            }else if(type == 2){/////////////////////////////////////////////////////재범파식구 (이중구부하)
                damage =enemy2.attack(hero);
                if(hero.hp>0 &&enemy2.hp>0){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<"+enemy2.name+"의 공격!>");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println();
                    }else if(enemy2.hp<=0){
                        enemy2.hp=0;
                    }
                    stop = false;
                }

            }else if(type == 3){//////////////////////////////////////////////////////연변거지
                damage =easyboss.attack(hero);
                if(hero.hp>0 &&easyboss.hp>0){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<"+easyboss.name+"의 공격!>");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println();
                    }else if(easyboss.hp<=0){
                        easyboss.hp=0;
                    }
                    stop = false;
                }


            }else if(type == 4){///////////////////////////////////////////////////////장수기
                damage =lowboss.attack(hero);
                if(hero.hp>0 &&lowboss.hp>0){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<"+lowboss.name+"의 공격!>");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println();
                    }else if(lowboss.hp<=0){
                        lowboss.hp=0;
                    }
                    stop = false;
                }


            }else if(type == 5){/////////////////////////////////////////////////////이중구
                damage =midboss.attack(hero);
                if(hero.hp>0 &&midboss.hp>0){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<"+midboss.name+"의 공격!>");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println();
                    }else if(midboss.hp<=0){
                        midboss.hp=0;
                    }
                    stop = false;
                }


            }else if(type == 6){//////////////////////////////////////////////////////////정청
                damage =highboss.attack(hero);
                if(hero.hp>0 &&highboss.hp>0){
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<"+highboss.name+"의 공격!>");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                    System.out.println();
                }else{
                    if(hero.hp<=0){
                        hero.hp=0;
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name+"은"+damage+"의 데미지를 입었다");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hero.name + " hp: " + hero.hp + "/" + hero.maxhp);
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t===================================");
                        System.out.println();
                    }else if(highboss.hp<=0){
                        highboss.hp=0;
                    }
                    stop = false;
                }


            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void run(){
        try{
            Enemy_attack();
        }catch (Exception e){

        }


    }

}
