package restaurant.restaurant.v5.service.DetailedMenuManager;

import restaurant.restaurant.v5.FoodPart;
import restaurant.restaurant.v5.domain.steak.RibeyeSteak;
import restaurant.restaurant.v5.domain.steak.SirloinSteakPart;
import restaurant.restaurant.v5.domain.steak.TBornSteakPart;

import static restaurant.restaurant.v4.domain.menu.SteakMenuConsol.MENU_DETAIED_STEAK_NOTICE;
import static restaurant.restaurant.v4.domain.menu.SteakMenuConsol.MENU_DETAIED_STEAK_SELECTED;

public class SteakManager implements DetailedMenuManager {
    public SteakManager(){

    }
    public FoodPart selectDetailedMenu(int detailedMenu){

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
