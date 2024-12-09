package restaurant.restaurant.v5.service.DetailedMenuManager;

import restaurant.restaurant.v5.FoodPart;
import restaurant.restaurant.v5.domain.pasta.CreamPastaPart;
import restaurant.restaurant.v5.domain.pasta.OilPastaPart;
import restaurant.restaurant.v5.domain.pasta.TomatoPastaPart;

import static restaurant.restaurant.v4.domain.menu.PastaMenuConsol.MENU_DETAIED_PASTA_NOTICE;
import static restaurant.restaurant.v4.domain.menu.PastaMenuConsol.MENU_DETAIED_PASTA_SELECTED;

public class PastaManager implements DetailedMenuManager {
    public PastaManager(){

    }
    public FoodPart selectDetailedMenu(int detailedMenu){
        return switch (detailedMenu) {
            case 1 -> new TomatoPastaPart("Tomato", new String[]{"Tomato", "Noodle"});
            case 2 -> new CreamPastaPart("Cream", new String[]{"Cream", "Noodle"});
            case 3 -> new OilPastaPart("Oil", new String[]{"Oil", "Noodle"});
            default -> throw new RuntimeException("This Menu was not in my Restaurant");
        };
    }
    public void printDetailedMenu(){
        printSelectMenu(MENU_DETAIED_PASTA_NOTICE, MENU_DETAIED_PASTA_SELECTED);

    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
