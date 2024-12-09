package restaurant.restaurant.v5.cooking_steps;

import restaurant.restaurant.v5.FoodPart;
import restaurant.restaurant.v5.service.DetailedMenuManager.DetailedMenuManager;

public class CookingStepsSteak implements CookingSteps {
    private final DetailedMenuManager manager;

    public CookingStepsSteak(DetailedMenuManager manager) {
        this.manager = manager;
    }

    public void takeCookingSteps(int detailedMenu) {
        cookEntire(manager.selectDetailedMenu(detailedMenu));
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
    public void printDetailedMenu(){
        manager.printDetailedMenu();
    }


}
