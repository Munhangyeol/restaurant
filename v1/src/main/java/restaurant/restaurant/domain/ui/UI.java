package restaurant.restaurant.domain.ui;

import restaurant.restaurant.domain.pasta.CreamPastaPart;
import restaurant.restaurant.domain.pasta.OilPastaPart;
import restaurant.restaurant.domain.pasta.PastaPart;
import restaurant.restaurant.domain.pasta.TomatoPastaPart;
import restaurant.restaurant.domain.pizza.CheezePizzaPart;
import restaurant.restaurant.domain.pizza.GorgonzolaPizzaPart;
import restaurant.restaurant.domain.pizza.PeperoniPizzaPart;
import restaurant.restaurant.domain.pizza.PizzaPart;
import restaurant.restaurant.domain.steak.RibeyeSteak;
import restaurant.restaurant.domain.steak.SirloinSteakPart;
import restaurant.restaurant.domain.steak.SteakPart;
import restaurant.restaurant.domain.steak.TBornSteakPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI {
    BufferedReader reader;
    public UI(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    public int selectMenu() throws IOException {
        System.out.println("안녕하세요! 어떤 파트의 음식을 드시고 싶으세요?");
        System.out.println("1: Steak요리 || 2: Pasta요리 || 3: Pizza요리");
        return Integer.parseInt(reader.readLine());
    }
    public int selectDetailMenu(int mainMenu) throws IOException {
        int detailMenu = 0;
        switch (mainMenu){
        case 1:{
            System.out.println("Steak 중 어떤 Steak 메뉴를 고르시겠어요?");
            System.out.println("1: T-Born Steak || 2: Sir-loin Steak || 3: Rib-eye Steak");
            detailMenu=Integer.parseInt(reader.readLine());
            break;
            }
        case 2:
            System.out.println("Pasta 중 어떤 Pasta 메뉴를 고르시겠어요?");
            System.out.println("1: Tomato-Pasta || 2: Cream-Pasta || 3: Oil-Pasta");
            detailMenu=Integer.parseInt(reader.readLine());
            break;
        case 3:
            System.out.println("Pizza 중 어떤 Pizza 메뉴를 고르시겠어요?");
            System.out.println("1: Peperoni-Pizza || 2: Cheeze-Pizza || 3: Gorgonzola-Pizza");
            detailMenu=Integer.parseInt(reader.readLine());
            break;
        }
        return detailMenu;
    }
    public void run() throws IOException {
        int menu=selectMenu();
        int detailedMenu=selectDetailMenu(menu);
        if(menu==1){
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
            System.out.println("!!");
            steakPart.preCook();
            steakPart.cook();
            steakPart.postCook();
        }
        if(menu==2){
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
            pastaPart.preCook();
            pastaPart.cook();
            pastaPart.postCook();
        }
        if(menu==3){
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
            pizzaPart.preCook();
            pizzaPart.cook();
            pizzaPart.postCook();

        }

    }

}
