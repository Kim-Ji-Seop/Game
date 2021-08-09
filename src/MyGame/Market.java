package MyGame;

import MyGame.item.*;

import java.util.ArrayList;

public class Market {//상점
    public ArrayList<Weapon> weapon;//무기 리스트 참조형 변수
    public ArrayList<Armor> armor;//방어구 리스트 참조형 변수
    public ArrayList<HpPotion> hp_potion;//포션 리스트 참조형 변수


    Market(ArrayList<Weapon> weapon,ArrayList<Armor> armor,ArrayList<HpPotion> hp_potion){
        this.weapon = weapon;
        this.armor = armor;
        this.hp_potion = hp_potion;
    }

}
