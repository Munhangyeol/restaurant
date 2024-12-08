package restaurant.restaurant.v4.cooking_steps;

import restaurant.restaurant.v4.FoodPart;
import restaurant.restaurant.v4.domain.pasta.CreamPastaPart;
import restaurant.restaurant.v4.domain.pasta.OilPastaPart;
import restaurant.restaurant.v4.domain.pasta.PastaPart;
import restaurant.restaurant.v4.domain.pasta.TomatoPastaPart;

public class CookingStepsPasta {

    public CookingStepsPasta() {

    }

    public void takeCookingPastaStpes(int detailedMenu) {
        PastaPart pastaPart = null;
        switch (detailedMenu){
            case 1:
                pastaPart = new TomatoPastaPart("Tomato",new String[]{"Tomato","Noodle"});
                break;
            case 2:
                pastaPart = new CreamPastaPart("Cream", new String[]{"Cream", "Noodle"});
                break;
            case 3:
                pastaPart = new OilPastaPart("Oil",new String[]{"Oil","Noodle"});
                break;
        }
        cookEntire(pastaPart);
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
}
