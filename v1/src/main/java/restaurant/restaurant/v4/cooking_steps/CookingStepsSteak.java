package restaurant.restaurant.v4.cooking_steps;

import restaurant.restaurant.v4.FoodPart;
import restaurant.restaurant.v4.domain.steak.RibeyeSteak;
import restaurant.restaurant.v4.domain.steak.SirloinSteakPart;
import restaurant.restaurant.v4.domain.steak.SteakPart;
import restaurant.restaurant.v4.domain.steak.TBornSteakPart;
import restaurant.restaurant.v4.service.PizzaManager;
import restaurant.restaurant.v4.service.SteakManager;

import static restaurant.restaurant.v4.domain.menu.SteakMenuConsol.MENU_DETAIED_STEAK_NOTICE;
import static restaurant.restaurant.v4.domain.menu.SteakMenuConsol.MENU_DETAIED_STEAK_SELECTED;

public class CookingStepsSteak implements CookingSteps{
    private final SteakManager steakManager;

    public CookingStepsSteak(SteakManager steakManager) {
        this.steakManager = steakManager;
    }

    public void takeCookingSteps(int detailedMenu) {
        cookEntire(steakManager.selectSteak(detailedMenu));
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
    public void printDetailedMenu(){
        steakManager.printDetailedMenu();
    }


}
