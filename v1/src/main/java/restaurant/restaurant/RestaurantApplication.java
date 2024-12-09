
package restaurant.restaurant;

import restaurant.restaurant.v5.service.MenuManager;
import restaurant.restaurant.v5.ui.UI;

import java.io.IOException;


public class RestaurantApplication {

    public static void main(String[] args) throws IOException {
        UI restaurant=new UI(new MenuManager());
        restaurant.run();
    }

}
