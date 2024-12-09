package restaurant.restaurant.v5.cooking_steps;

import restaurant.restaurant.v5.FoodPart;
import restaurant.restaurant.v5.service.DetailedMenuManager.DetailedMenuManager;

public class CookingStepsPizza implements CookingSteps {
    private final DetailedMenuManager manager;
    public CookingStepsPizza(DetailedMenuManager manager) {
        this.manager = manager;
    }
    @Override
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
