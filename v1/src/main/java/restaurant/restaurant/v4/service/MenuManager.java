package restaurant.restaurant.v4.service;

import restaurant.restaurant.v4.cooking_steps.CookingSteps;
import restaurant.restaurant.v4.cooking_steps.CookingStepsPasta;
import restaurant.restaurant.v4.cooking_steps.CookingStepsPizza;
import restaurant.restaurant.v4.cooking_steps.CookingStepsSteak;




public class MenuManager {
    private CookingSteps cookingSteps;

    public MenuManager(){
    }
    // showDetailedMenu이면 UI에 있어야 하는거 아닌가 라고 반문할 수 있지만
    // 새로운 음식이 추가 되었을 때 변화를 생각하면 이 파트를 다른 클래스로 빼는게 매우 당연함
    // 따라서 UI클래스 내에서의 selectDetailMenu메서드에서 분기의 책임을 여리고 위임할 수 있음
    public void selectMainMenu(int mainMenu){
        switch (mainMenu){
            case 1:{
                cookingSteps=new CookingStepsSteak(new SteakManager());
                break;
            }
            case 2:
                cookingSteps=new CookingStepsPasta(new PastaManager());
                break;
            case 3:
                cookingSteps = new CookingStepsPizza(new PizzaManager());
                break;
        }
    }
    public void printDetailedMenu(){
        cookingSteps.printDetailedMenu();
    }
    public void cookBySelectedMenu(int detailedMenu){
        cookingSteps.takeCookingSteps(detailedMenu);
    }

}
