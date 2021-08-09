package MyGame;

import MyGame.item.*;

import java.util.ArrayList;

public class Inventory {


    public void show_Weaponlist(ArrayList<Weapon> weapons){//무기 리스트를 보여준다
        for (int i = 0; i < 60; i++) {
            System.out.println();
        }
        System.out.println("==========================================================================");
        for (int i = 0; i < weapons.size(); i++) {
            Weapon weapon = weapons.get(i);
            System.out.println((i + 1) + ". " + weapon.name + "(공격력 " +  weapon.power + ")");
        }
        System.out.println("==========================================================================");


    }
    public void show_Armorlist(ArrayList<Armor> armors){//방어구 리스트를 보여준다
        System.out.println("==========================================================================");
        for (int i = 0; i < armors.size(); i++) {
            Armor armor = armors.get(i);
            System.out.println((i + 1) + ". " + armor.name + "(방어력 " + armor.defense + ")");
        }
        System.out.println("==========================================================================");
    }
    public void show_Potionlist(ArrayList<HpPotion> potions){//포션 리스트를 보여준다
        System.out.println("==========================================================================");
        System.out.println("1. 체력 포션(hp회복량: 500) : " + potions.size() + "개");
        System.out.println("==========================================================================");
    }
}
