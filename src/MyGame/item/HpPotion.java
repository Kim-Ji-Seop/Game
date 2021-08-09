package MyGame.item;



public class HpPotion extends Item {
    public int hp;//추가체력수치


    public HpPotion(String name, int price, int hp) {
        super(name, price);
        this.hp = hp;
    }
}