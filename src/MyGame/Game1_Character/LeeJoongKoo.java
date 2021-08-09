package MyGame.Game1_Character;

import java.util.Random;

public class LeeJoongKoo extends Enemy {
    //위압감이 있고 그것때문에 내 능력치중 랜덤으로 한가지 능력치가 내려간다

    int pressure;//위압감

    public LeeJoongKoo(String name,int maxhp, int hp ,int power, int defense, int money){
        super(name,maxhp,hp,power,defense,money);
    }


    public void press(Hero hero){//이중구의 위압감(포스)
        Random random = new Random();
        this.pressure = random.nextInt(3)+1;
        System.out.println("=====================================================");////////////////////////////////
        System.out.println("[이중구의 위압감으로 "+hero.name+"의 능력치가 감소합니다]");//          수동전투를 할 때, 나와 전투를 하면서 공격력이나 방어력 중에서
//                                                                                           차감이 될 능력치를 랜덤으로 정하고
        int pressnumber = random.nextInt(2)+1;//                                       공격력이나 방어력수치 또한 랜덤으로 감소한다
        if(pressnumber == 1){//
            System.out.println("<"+hero.name+"의 공격력이"+this.pressure+"만큼 감소합니다>");//   그러니까.. 공격력or방어력 중에서 하나가 랜덤으로 선택이 되고
            hero.power -= this.pressure;//                                                    그리고나서 차감이 될 수치값(1~3)까지가 랜덤으로 선택이 된다.
        }else{//
            System.out.println("<"+hero.name+"의 방어력이"+this.pressure+"만큼 감소합니다>");//   위압감은 영화에서 이중구가 카리스마있게 보여서 넣은 내 생각.
            hero.defense -= this.pressure;
        }
        System.out.println("=====================================================");

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
