package restaurant.restaurant.v2.ui;

import restaurant.restaurant.v2.FoodPart;
import restaurant.restaurant.v2.pasta.CreamPastaPart;
import restaurant.restaurant.v2.pasta.OilPastaPart;
import restaurant.restaurant.v2.pasta.PastaPart;
import restaurant.restaurant.v2.pasta.TomatoPastaPart;
import restaurant.restaurant.v2.pizza.CheezePizzaPart;
import restaurant.restaurant.v2.pizza.GorgonzolaPizzaPart;
import restaurant.restaurant.v2.pizza.PeperoniPizzaPart;
import restaurant.restaurant.v2.pizza.PizzaPart;
import restaurant.restaurant.v2.steak.RibeyeSteak;
import restaurant.restaurant.v2.steak.SirloinSteakPart;
import restaurant.restaurant.v2.steak.SteakPart;
import restaurant.restaurant.v2.steak.TBornSteakPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {
 private final   BufferedReader reader;
    public UI(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException {
        int menu=selectMenu();
        int detailedMenu=selectDetailMenu(menu);
        switch (menu){
            case 1:
                takeCookingSteakSteps(detailedMenu);
                break;
            case 2:
                takeCookingPastaStpes(detailedMenu);
                break;
            case 3:
                takeCookingPizzaSteps(detailedMenu);
                break;
        }
    }
    private int selectMenu() throws IOException {
        printSelectMenu("안녕하세요! 어떤 파트의 음식을 드시고 싶으세요?", "1: Steak요리 || 2: Pasta요리 || 3: Pizza요리");
        return Integer.parseInt(reader.readLine());
    }
    private int selectDetailMenu(int mainMenu) throws IOException {
        int detailMenu = 0;
        switch (mainMenu){
            case 1:{
                printSelectMenu("Steak 중 어떤 Steak 메뉴를 고르시겠어요?", "1: T-Born Steak || 2: Sir-loin Steak || 3: Rib-eye Steak");
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            }
            case 2:
                printSelectMenu("Pasta 중 어떤 Pasta 메뉴를 고르시겠어요?", "1: Tomato-Pasta || 2: Cream-Pasta || 3: Oil-Pasta");
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            case 3:
                printSelectMenu("Pizza 중 어떤 Pizza 메뉴를 고르시겠어요?", "1: Peperoni-Pizza || 2: Cheeze-Pizza || 3: Gorgonzola-Pizza");
                detailMenu=Integer.parseInt(reader.readLine());
                break;
        }
        return detailMenu;
    }

    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
    private void takeCookingSteakSteps(int detailedMenu) {
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
    private void takeCookingPastaStpes(int detailedMenu) {
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
    private void takeCookingPizzaSteps(int detailedMenu) {
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
