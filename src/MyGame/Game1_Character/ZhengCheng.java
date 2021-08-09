package MyGame.Game1_Character;



import java.util.Random;

public class ZhengCheng extends Enemy {
    public int angrygauge;//분노게이지 ==> 분노할때 이 게이지만큼 추가공격력과 체력이 있다.(0~100)
    //비리문서를 가지고있는 유일한 사람이다.

    public ZhengCheng(String name,int maxhp, int hp, int power, int defense,int money,int angrygauge){
        super(name,maxhp,hp,power,defense,money);
        this.angrygauge = angrygauge;
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

    public void angryeffect(){//정청의 분노했을 때의 효과
        if(angrygauge< 100){
            angrygauge+=10;
        }else if(angrygauge>=100){
            super.hp += angrygauge;
            super.power += 20;
            angrygauge=0;
        }
        System.out.println("===================");
        System.out.println("분노게이지 :"+angrygauge+"/100");
        System.out.println("===================");

    }

}
