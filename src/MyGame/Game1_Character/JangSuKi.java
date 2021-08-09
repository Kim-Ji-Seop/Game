package MyGame.Game1_Character;


import java.util.Random;

public class JangSuKi extends Enemy {
    //지속 독데미지를 입게하는 특성을 가지게하자
    public int poison_dmg;//독데미지



    public JangSuKi(String name, int maxhp, int hp,int power, int defense, int money){
        super(name,maxhp,hp,power,defense,money);
    }


    @Override
    public int attack(Character target){//공격하기  // 타겟을 목표로하여
        Random random = new Random();
        double percent = 0.1 * (random.nextInt(4)+5);//데미지의 50%~80%정도로 설정하는 퍼센트 변수를 만들고 //퍼센트는 너무딱딱한 데미지계산을 피하려는것이 목적이였다.
        int damage =0;//데미지를 초기화하고
        if(this.power > target.defense){//내 공격력이 타겟의 방어력보다 크다면
            damage = (int) ((this.power - target.defense)*percent);//이에 내 공격력과 적 방어력의 차이에서 퍼센트를 곱해서 데미지를 환산한다.
        }
        target.hp = target.hp-damage;//그리고 타겟의 체력은 그 데미지만큼 깎인다.

        return damage;
    }



}
