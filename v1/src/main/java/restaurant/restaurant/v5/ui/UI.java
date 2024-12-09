package restaurant.restaurant.v5.ui;

import restaurant.restaurant.v5.service.MenuManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static restaurant.restaurant.v4.domain.menu.MainNoticeMenu.MENU_MAIN_NOTICE;
import static restaurant.restaurant.v4.domain.menu.MainNoticeMenu.MENU_MAIN_SELECTED;

public class UI {
 private final BufferedReader reader;
 private final MenuManager menuManager;
    public UI(MenuManager mainMenuManager){
        this.menuManager = mainMenuManager;
        reader = new BufferedReader(new InputStreamReader(System.in));

    }
    public void run() throws IOException {
        selectMainMenu();
        menuManager.cookBySelectedMenu(selectDetailMenu());
    }
    private void selectMainMenu() throws IOException {
        printSelectMenu(MENU_MAIN_NOTICE,MENU_MAIN_SELECTED);
        menuManager.selectMainMenu(Integer.parseInt(reader.readLine()));
    }
    private int selectDetailMenu() throws IOException {
        menuManager.printDetailedMenu();
        return Integer.parseInt(reader.readLine());
    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
