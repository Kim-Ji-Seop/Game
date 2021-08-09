package MyGame.Game1_Character;




import java.util.Random;

public class Enemy extends Character{

    public Enemy(String name, int maxhp, int hp, int power,int defense,int money){
        super(name,maxhp,hp,power,defense,money);
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






}
