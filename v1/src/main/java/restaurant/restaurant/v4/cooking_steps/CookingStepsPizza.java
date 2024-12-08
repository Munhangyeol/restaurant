package restaurant.restaurant.v4.cooking_steps;

import restaurant.restaurant.v4.FoodPart;
import restaurant.restaurant.v4.domain.pizza.CheezePizzaPart;
import restaurant.restaurant.v4.domain.pizza.GorgonzolaPizzaPart;
import restaurant.restaurant.v4.domain.pizza.PeperoniPizzaPart;
import restaurant.restaurant.v4.domain.pizza.PizzaPart;

public class CookingStepsPizza {

    public CookingStepsPizza() {

    }

    public  void takeCookingPizzaSteps(int detailedMenu) {
        PizzaPart pizzaPart = null;
        switch (detailedMenu){
            case 1:
                pizzaPart = new PeperoniPizzaPart("Peperoni",new String[]{"Peperoni","Bread"});
                break;
            case 2:
                pizzaPart = new CheezePizzaPart("Cheeze", new String[]{"Cheeze", "Bread"});
                break;
            case 3:
                pizzaPart = new GorgonzolaPizzaPart("Gorgonzola",new String[]{"Gorgonzola","Bread"});
        }
        assert pizzaPart != null;
        cookEntire(pizzaPart);
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
}
