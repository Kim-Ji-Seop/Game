package MyGame.item;



public class Armor extends Item {
    public int defense;//추가 방어력수치


    public Armor(String name, int price,int defense) {
        super(name, price);
        this.defense = defense;
    }
}
