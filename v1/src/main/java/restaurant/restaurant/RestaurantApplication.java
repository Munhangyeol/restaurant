package restaurant.restaurant;

import restaurant.restaurant.v1.domain.ui.UI;

import java.io.IOException;


public class RestaurantApplication {

	public static void main(String[] args) throws IOException {
		UI restaurant=new UI();
		restaurant.run();

	}

}
