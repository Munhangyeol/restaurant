package restaurant.restaurant.v3.cooking_steps;

import restaurant.restaurant.v3.FoodPart;
import restaurant.restaurant.v3.domain.steak.SteakPart;
import restaurant.restaurant.v3.domain.steak.RibeyeSteak;
import restaurant.restaurant.v3.domain.steak.SirloinSteakPart;
import restaurant.restaurant.v3.domain.steak.TBornSteakPart;

public class CookingStepsSteak {

    public CookingStepsSteak() {

    }

    public void takeCookingSteakSteps(int detailedMenu) {
     SteakPart steakPart = null;
        switch (detailedMenu){
            case 1:
                steakPart = new TBornSteakPart("T-Born",new String[]{"T-Born meat","Butter"});
                break;
            case 2:
                steakPart = new SirloinSteakPart("Sir-loin", new String[]{"Sir-loin meat", "Butter"});
                break;
            case 3:
                steakPart = new RibeyeSteak("Rib-eye",new String[]{"Rib-eye meat","Butter"});
                break;
        }
        cookEntire(steakPart);
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
}
