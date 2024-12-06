package restaurant.restaurant.v1.domain.pizza;

public abstract class PizzaPart {
    protected String[] untensils =  {"Pot","Colander"};
    abstract public void preCook();

    abstract public void cook();
    abstract public void postCook();

}
