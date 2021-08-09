package MyGame.Game1_Character;

public class Character {
    public String name;//인물의 이름
    public int maxhp;//최대체력
    public int hp;//체력
    public int power;//공격력
    public int defense;//방어력
    public int money;//돈

    public Character(String name, int maxhp, int hp, int power, int defense, int money) {
        this.name = name;
        this.maxhp = maxhp;
        this.hp = hp;
        this.power = power;
        this.defense = defense;
        this.money = money;
    }


    public int attack(Character target){//공격하기
        return power-defense;
    }

}
