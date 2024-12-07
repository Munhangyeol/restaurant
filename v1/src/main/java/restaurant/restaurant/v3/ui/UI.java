package restaurant.restaurant.v3.ui;

import restaurant.restaurant.v3.cooking_steps.CookingStepsPasta;
import restaurant.restaurant.v3.cooking_steps.CookingStepsPizza;
import restaurant.restaurant.v3.cooking_steps.CookingStepsSteak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import static restaurant.restaurant.v3.domain.menu.MainNoticeMenu.MENU_MAIN_NOTICE;
import static restaurant.restaurant.v3.domain.menu.MainNoticeMenu.MENU_MAIN_SELECTED;
import static restaurant.restaurant.v3.domain.menu.PastaMenu.MENU_DETAIED_PASTA_NOTICE;
import static restaurant.restaurant.v3.domain.menu.PastaMenu.MENU_DETAIED_PASTA_SELECTED;
import static restaurant.restaurant.v3.domain.menu.PizzaMenu.MENU_DETAIED_PIZZA_NOTICE;
import static restaurant.restaurant.v3.domain.menu.PizzaMenu.MENU_DETAIED_PIZZA_SELECTED;
import static restaurant.restaurant.v3.domain.menu.SteakMenu.MENU_DETAIED_STEAK_NOTICE;
import static restaurant.restaurant.v3.domain.menu.SteakMenu.MENU_DETAIED_STEAK_SELECTED;

public class UI {
 private final   BufferedReader reader;
 private final CookingStepsSteak cookingStepsSteak;
 private final CookingStepsPasta cookingStepsPasta;
 private final CookingStepsPizza cookingStepsPizza;

    public UI(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        cookingStepsSteak=new CookingStepsSteak();
        cookingStepsPasta=new CookingStepsPasta();
        cookingStepsPizza=new CookingStepsPizza();

    }

    public void run() throws IOException {
        int menu=selectMainMenu();
        int detailedMenu=selectDetailMenu(menu);
        switch (menu){
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
    private int selectMainMenu() throws IOException {
        printSelectMenu(MENU_MAIN_NOTICE,  MENU_MAIN_SELECTED);
        return Integer.parseInt(reader.readLine());
    }
    private int selectDetailMenu(int mainMenu) throws IOException {
        int detailMenu = 0;
        switch (mainMenu){
            case 1:{
                printSelectMenu(MENU_DETAIED_STEAK_NOTICE, MENU_DETAIED_STEAK_SELECTED);
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            }
            case 2:
                printSelectMenu(MENU_DETAIED_PASTA_NOTICE, MENU_DETAIED_PASTA_SELECTED);
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            case 3:
                printSelectMenu(MENU_DETAIED_PIZZA_NOTICE, MENU_DETAIED_PIZZA_SELECTED);
                detailMenu=Integer.parseInt(reader.readLine());
                break;
        }
        return detailMenu;
    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
