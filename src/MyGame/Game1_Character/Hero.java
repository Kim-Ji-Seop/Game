package MyGame.Game1_Character;

import MyGame.thread.LoadingThread;
import MyGame.item.*;


import java.util.ArrayList;
import java.util.Random;


public class Hero extends Character {//주인공 클래스==> Charecter클래스의 자식클래스 상속. 체력,공격력,방어력 기본적으로 탑재
    public int plusdefense;//추가방어력 방어구장착했을때 추가되는 방어력임
    public int alldefense;//총방어력
    public int pluspower;//추가공격력  무기를 장착했을 때 추가되는 공격력을 나타내기 위함.
    public int allpower;//총공격력
    public Weapon weapon;//무기 객체
    public Armor armor;//방어구 객체
    public ArrayList<Weapon> weapons;//무기 객체 리스트
    public ArrayList<Armor> armors;//방어구 객체 리스트
    public ArrayList<HpPotion> potions;//체력포션 객체 리스트



    public Hero(String name,//생성자
                int power,
                int defense,
                int maxhp,
                int hp,
                int money,
                Weapon weapon,
                Armor armor,
                ArrayList<Weapon> weapons,
                ArrayList<Armor> armors,
                ArrayList<HpPotion> potions){
        super(name, maxhp, hp, power, defense, money);
        this.weapon = weapon;
        this.armor = armor;
        this.weapons = weapons;
        this.armors = armors;
        this.potions = potions;
    }



    @Override
    public int attack(Character target){//공격하기
        Random random = new Random();
        double percent = 0.1 * (random.nextInt(4)+5);//데미지의 50%~80%
        int damage =0;
        if(this.power > target.defense){
            damage = (int) ((this.power - target.defense)*percent);
        }
        target.hp = target.hp-damage;

        return damage;
    }

    public void revive(){//최대체력의 반으로 부활하기
        for(int i=0; i<60; i++) {
            System.out.println();
        }
        System.out.println(this.name+"이 패배했습니다.");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LoadingThread reviveLoading = new LoadingThread("revive");//부활한다라는 문구가 뜨게..
        reviveLoading.start();
        try {
            reviveLoading.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hp = (int)(maxhp*0.5);//영웅의 체력을 최대체력의 반으로만듦
        System.out.println("이자성이 체력"+hp+"로 부활했습니다.");
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<60;i++){
            System.out.println();
        }
    }

    public void BuyItem(Item item){//아이템을 상점에서 사면; 산다는 행동과 인벤토리와 상점의 연관성을 잘 파악해야한다.
        if(item instanceof Weapon){//내가 구매한 물건이 Weapon클래스의 객체인 물건이라면
            Weapon weapon = (Weapon)item;//전역변수 weapon말고 내가 산 물건을 추가해야하니까 Weapon형 item을 추가해야한다
            weapons.add(weapon);//무기를 추가
            this.money -= weapon.price;//내돈에서 구매한 무기의 가격을 뺌
        }
        if(item instanceof Armor){//내가 구매한 물건이 Armor클래스의 객체인 물건이라면
            Armor armor = (Armor)item;//전역변수 armor말고 내가 산 물건을 추가해야하니까 Armor형 item을 추가해야한다
            armors.add(armor);//방어구 추가
            this.money -= armor.price;//내돈에서 구매한 방어구의 가격을 뺀다
        }
    }

    public void BuyItem(Item item,int number){
        if(item instanceof HpPotion){//내가 구매한 물건이 HpPotion의 객체인 물건이라면
            HpPotion hpPotion =(HpPotion) item;// 내가 산 물건이 아이템이라는 객체에서 hp포션이라는 물건임을 정확히 알려준다

            for(int i=0; i<number;i++){
                this.potions.add(hpPotion);//포션을 추가한다.
                this.money -= hpPotion.price;//내가 산 수량만큼 가격을 가감한다.
            }
        }
    }
    public void show_equip(Item item){
        if(item instanceof Weapon || item instanceof Armor){
            System.out.println("-----------------------------------------------------------");
            System.out.println(item.name + "을(를) 장착했다!");
            System.out.println("-----------------------------------------------------------");
            try{
                Thread.sleep(1500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }




    public void showMyStat(){//본인의 스탯을 화면에 보여주는 메소드
        allpower = power+pluspower;//기존 공격력+추가공격력 = 총공격력
        alldefense = defense+plusdefense;
        System.out.println("=========================================");//영웅의 능력치 보여주기
        System.out.println("이름:"+name);
        System.out.println("체력 :"+hp+"/"+ maxhp);
        System.out.println("공격력:"+allpower+"("+power+"+"+pluspower+")");
        System.out.println("방어력:"+alldefense+"("+defense+"+"+plusdefense+")");
        System.out.println("무기: " + ((weapon != null) ? weapon.name : "장착x"));
        System.out.println("방어구: " + ((armor != null) ? armor.name: "장착x"));
        System.out.println("보유한 돈 :"+money+"원");
        System.out.println("=========================================");
        System.out.println("[0] 뒤로");
    }


}