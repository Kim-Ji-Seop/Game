package MyGame.Game1_Character;


import java.util.Random;

public class Yeonbyeon extends Enemy {
    int speed;//회피율수치
    Random random = new Random();

    public Yeonbyeon(String name,int maxhp, int hp, int power, int defense, int money,int speed){
        super(name,maxhp,hp,power,defense,money);
        this.speed= speed;
    }
    @Override
    public int attack(Character target){//공격하기
        double percent = 0.1 * (random.nextInt(4)+5);//데미지의 50%~80%
        int damage =0;
        if(this.power > target.defense){
            damage = (int) ((this.power - target.defense)*percent);
        }
        target.hp = target.hp-damage;

        return damage;
    }
    public void avoid(Character hero,int damage){
        if(random.nextInt(100)<speed){//speed%의 확률로 공격회피
            damage = 0;//speed%확률로 데미지를 입지않고
            this.hp -= 0;
            System.out.println(this.name+"가 회피하여"+this.hp+"의 데미지를 입었다.");
        }else{
            damage = hero.power-this.defense;//(100-speed)%확률로 데미지를 입는다.
            this.hp -= damage;//연변거지 체력에서 데미지를 뺀다.
            System.out.println(this.name+"가"+this.hp+"의 데미지를 입었다.");
        }
    }
    

    /*public void showMyStat(){//본인의 스탯을 화면에 보여주는 메소드
        System.out.println("===================");//영웅의 능력치 보여주기
        System.out.println("이름:"+name);
        System.out.println("체력 :"+hp+"/"+ maxhp);
        System.out.println("공격력:"+power);
        System.out.println("방어력:"+defense);
        System.out.println("보유한 돈 :"+money+"원");
        System.out.println("회피율 :"+speed);
        System.out.println("===================");
    }*/
}
