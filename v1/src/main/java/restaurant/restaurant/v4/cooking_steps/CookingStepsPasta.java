package restaurant.restaurant.v4.cooking_steps;

import restaurant.restaurant.v4.FoodPart;
import restaurant.restaurant.v4.domain.pasta.CreamPastaPart;
import restaurant.restaurant.v4.domain.pasta.OilPastaPart;
import restaurant.restaurant.v4.domain.pasta.PastaPart;
import restaurant.restaurant.v4.domain.pasta.TomatoPastaPart;
import restaurant.restaurant.v4.service.PastaManager;

import static restaurant.restaurant.v4.domain.menu.PastaMenuConsol.MENU_DETAIED_PASTA_NOTICE;
import static restaurant.restaurant.v4.domain.menu.PastaMenuConsol.MENU_DETAIED_PASTA_SELECTED;

// 디테일한 메뉴 수정은 여기서함
// 따라서 각 디테일한 메뉴의 출력도 여기서 하면 좋을듯
public class CookingStepsPasta implements CookingSteps{
    private final PastaManager pastaManager;
    public CookingStepsPasta(PastaManager pastaManager) {
        this.pastaManager=pastaManager;

    }
    public void takeCookingSteps(int detailedMenu) {
        cookEntire(pastaManager.selectPasta(detailedMenu));
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
    public void printDetailedMenu(){
        pastaManager.printDetailedMenu();
    }

}
