package MyGame.item;

public class Item {//아이템 ==> 상점클래스와 인벤토리에 관련이있음.
    public String name; //아이템의 이름
    public int price; //아이템의 가격

    public Item(String name, int price){
        this.name = name;
        this.price = price;
    }
}