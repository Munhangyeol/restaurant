package restaurant.restaurant.v4.service.DetailedMenuManager;

import restaurant.restaurant.v4.FoodPart;
import restaurant.restaurant.v4.domain.pizza.CheezePizzaPart;
import restaurant.restaurant.v4.domain.pizza.GorgonzolaPizzaPart;
import restaurant.restaurant.v4.domain.pizza.PeperoniPizzaPart;
import restaurant.restaurant.v4.service.DetailedMenuManager.DetailedMenuManager;

import static restaurant.restaurant.v4.domain.menu.PizzaMenuConsol.MENU_DETAIED_PIZZA_NOTICE;
import static restaurant.restaurant.v4.domain.menu.PizzaMenuConsol.MENU_DETAIED_PIZZA_SELECTED;

public class PizzaManager implements DetailedMenuManager {
    public PizzaManager(){

    }
    public FoodPart selectDetailedMenu(int detailedMenu){
        return switch (detailedMenu) {
            case 1 -> new PeperoniPizzaPart("Peperoni", new String[]{"Peperoni", "Bread"});
            case 2 -> new CheezePizzaPart("Cheeze", new String[]{"Cheeze", "Bread"});
            case 3 -> new GorgonzolaPizzaPart("Gorgonzola", new String[]{"Gorgonzola", "Bread"});
            default -> throw new RuntimeException("This Menu was not in my Restaurant");
        };
    }
    public void printDetailedMenu(){
        printSelectMenu(MENU_DETAIED_PIZZA_NOTICE, MENU_DETAIED_PIZZA_SELECTED);

    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
