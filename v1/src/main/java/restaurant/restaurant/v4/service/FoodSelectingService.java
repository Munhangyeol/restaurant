package restaurant.restaurant.v4.service;

import restaurant.restaurant.v4.cooking_steps.CookingStepsPasta;
import restaurant.restaurant.v4.cooking_steps.CookingStepsPizza;
import restaurant.restaurant.v4.cooking_steps.CookingStepsSteak;
import restaurant.restaurant.v4.FoodPart;

import static restaurant.restaurant.v3.domain.menu.PastaMenu.MENU_DETAIED_PASTA_NOTICE;
import static restaurant.restaurant.v3.domain.menu.PastaMenu.MENU_DETAIED_PASTA_SELECTED;
import static restaurant.restaurant.v3.domain.menu.PizzaMenu.MENU_DETAIED_PIZZA_NOTICE;
import static restaurant.restaurant.v3.domain.menu.PizzaMenu.MENU_DETAIED_PIZZA_SELECTED;
import static restaurant.restaurant.v3.domain.menu.SteakMenu.MENU_DETAIED_STEAK_NOTICE;
import static restaurant.restaurant.v3.domain.menu.SteakMenu.MENU_DETAIED_STEAK_SELECTED;

public class FoodSelectingService {
    private final CookingStepsPasta cookingStepsPasta;
    private final CookingStepsPizza cookingStepsPizza;
    private final CookingStepsSteak cookingStepsSteak;

    public FoodSelectingService(CookingStepsPasta cookingStepsPasta, CookingStepsPizza cookingStepsPizza, CookingStepsSteak cookingStepsSteak){
        this.cookingStepsPasta = cookingStepsPasta;
        this.cookingStepsPizza = cookingStepsPizza;
        this.cookingStepsSteak = cookingStepsSteak;
    }
    // showDetailedMenu이면 UI에 있어야 하는거 아닌가 라고 반문할 수 있지만
    // 새로운 음식이 추가 되었을 때 변화를 생각하면 이 파트를 다른 클래스로 빼는게 매우 당연함
    // 따라서 UI클래스 내에서의 selectDetailMenu메서드에서 분기의 책임을 여리고 위임할 수 있음
    public void showDetailedMenu(int mainMenu){
        switch (mainMenu){
            case 1:{
                printSelectMenu(MENU_DETAIED_STEAK_NOTICE, MENU_DETAIED_STEAK_SELECTED);
                break;
            }
            case 2:
                printSelectMenu(MENU_DETAIED_PASTA_NOTICE, MENU_DETAIED_PASTA_SELECTED);
                break;
            case 3:
                printSelectMenu(MENU_DETAIED_PIZZA_NOTICE, MENU_DETAIED_PIZZA_SELECTED);
                break;
        }
    }
    public void cookBySelectedMenu(int mainMenu,int detailedMenu){
        switch (mainMenu){
            case 1:
                cookingStepsSteak.takeCookingSteakSteps(detailedMenu);
                break;
            case 2:
                cookingStepsPasta.takeCookingPastaStpes(detailedMenu);
                break;
            case 3:
                cookingStepsPizza.takeCookingPizzaSteps(detailedMenu);
                break;
        }

    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
