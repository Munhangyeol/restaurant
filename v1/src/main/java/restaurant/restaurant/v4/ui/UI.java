package restaurant.restaurant.v4.ui;

import restaurant.restaurant.v4.cooking_steps.CookingStepsPasta;
import restaurant.restaurant.v4.cooking_steps.CookingStepsPizza;
import restaurant.restaurant.v4.cooking_steps.CookingStepsSteak;
import restaurant.restaurant.v4.service.FoodSelectingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static restaurant.restaurant.v3.domain.menu.MainNoticeMenu.MENU_MAIN_NOTICE;
import static restaurant.restaurant.v3.domain.menu.MainNoticeMenu.MENU_MAIN_SELECTED;

public class UI {
 private final   BufferedReader reader;
 private final FoodSelectingService foodSelectingService;
    public UI(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        foodSelectingService=new FoodSelectingService(new CookingStepsPasta(),new CookingStepsPizza(),new CookingStepsSteak());
    }
    public void run() throws IOException {
        int menu=selectMainMenu();
        foodSelectingService.cookBySelectedMenu(menu,selectDetailMenu(menu));

    }
    private int selectMainMenu() throws IOException {
        printSelectMenu(MENU_MAIN_NOTICE,  MENU_MAIN_SELECTED);
        return Integer.parseInt(reader.readLine());
    }
    private int selectDetailMenu(int mainMenu) throws IOException {
        int detailMenu = 0;
        foodSelectingService.showDetailedMenu(mainMenu);
        detailMenu=Integer.parseInt(reader.readLine());
        return detailMenu;
    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
