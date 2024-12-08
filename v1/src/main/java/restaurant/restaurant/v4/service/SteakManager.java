package restaurant.restaurant.v4.service;

import restaurant.restaurant.v4.FoodPart;
import restaurant.restaurant.v4.domain.pasta.CreamPastaPart;
import restaurant.restaurant.v4.domain.pasta.OilPastaPart;
import restaurant.restaurant.v4.domain.pasta.TomatoPastaPart;
import restaurant.restaurant.v4.domain.steak.RibeyeSteak;
import restaurant.restaurant.v4.domain.steak.SirloinSteakPart;
import restaurant.restaurant.v4.domain.steak.TBornSteakPart;

import static restaurant.restaurant.v4.domain.menu.SteakMenuConsol.MENU_DETAIED_STEAK_NOTICE;
import static restaurant.restaurant.v4.domain.menu.SteakMenuConsol.MENU_DETAIED_STEAK_SELECTED;

public class SteakManager {
    public SteakManager(){

    }
    public FoodPart selectSteak(int detailedMenu){

        return switch (detailedMenu) {
            case 1 -> new TBornSteakPart("T-Born", new String[]{"T-Born meat", "Butter"});
            case 2 -> new SirloinSteakPart("Sir-loin", new String[]{"Sir-loin meat", "Butter"});
            case 3 -> new RibeyeSteak("Rib-eye", new String[]{"Rib-eye meat", "Butter"});
            default -> throw new RuntimeException("This Menu was not in my Restaurant");
        };
    }
    public void printDetailedMenu(){
        printSelectMenu(MENU_DETAIED_STEAK_NOTICE, MENU_DETAIED_STEAK_SELECTED);
    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
