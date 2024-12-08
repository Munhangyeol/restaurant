package restaurant.restaurant.v4.cooking_steps;

import restaurant.restaurant.v4.FoodPart;
import restaurant.restaurant.v4.domain.pizza.CheezePizzaPart;
import restaurant.restaurant.v4.domain.pizza.GorgonzolaPizzaPart;
import restaurant.restaurant.v4.domain.pizza.PeperoniPizzaPart;
import restaurant.restaurant.v4.domain.pizza.PizzaPart;
import restaurant.restaurant.v4.service.PizzaManager;

import static restaurant.restaurant.v4.domain.menu.PizzaMenuConsol.MENU_DETAIED_PIZZA_NOTICE;
import static restaurant.restaurant.v4.domain.menu.PizzaMenuConsol.MENU_DETAIED_PIZZA_SELECTED;

public class CookingStepsPizza implements CookingSteps{
    private final PizzaManager pizzaManager;
    public CookingStepsPizza(PizzaManager pizzaManager) {
        this.pizzaManager = pizzaManager;
    }
    @Override
    public void takeCookingSteps(int detailedMenu) {
        cookEntire(pizzaManager.selectPizza(detailedMenu));
    }

    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
    public void printDetailedMenu(){
        pizzaManager.printDetailedMenu();
    }



}
