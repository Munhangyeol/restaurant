package restaurant.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
import restaurant.restaurant.domain.ui.UI;

import java.io.IOException;


public class RestaurantApplication {

	public static void main(String[] args) throws IOException {
		UI restaurant=new UI();
		restaurant.run();

	}

}
