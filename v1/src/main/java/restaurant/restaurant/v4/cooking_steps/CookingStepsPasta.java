package restaurant.restaurant.v4.cooking_steps;

import restaurant.restaurant.v4.FoodPart;
import restaurant.restaurant.v4.service.DetailedMenuManager.DetailedMenuManager;
import restaurant.restaurant.v4.service.DetailedMenuManager.PastaManager;

// 디테일한 메뉴 수정은 여기서함
// 따라서 각 디테일한 메뉴의 출력도 여기서 하면 좋을듯
public class CookingStepsPasta implements CookingSteps{
    private final DetailedMenuManager manager;
    public CookingStepsPasta( DetailedMenuManager manager) {
        this.manager=manager;

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
