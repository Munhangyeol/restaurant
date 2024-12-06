package restaurant.restaurant.v1.domain.steak;

public abstract class SteakPart {
    protected String[] untensils = {"Mixing", "Bowl", "Oven"};

    abstract public void preCook();

    abstract public void cook();
    abstract public void postCook();

}
