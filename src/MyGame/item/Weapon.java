package MyGame.item;



public class Weapon extends Item {
    public int power;//추가 공격력 수치

    public Weapon(String name, int price,int power) {
        super(name, price);
        this.power=power;
    }
}