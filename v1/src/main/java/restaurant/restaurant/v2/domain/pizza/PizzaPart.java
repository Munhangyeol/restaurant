package restaurant.restaurant.v2.domain.pizza;

import restaurant.restaurant.v2.FoodPart;

import java.util.Arrays;

public abstract class PizzaPart implements FoodPart {
    protected String[] untensils =  {"Pot","Colander"};
    protected String []ingredients;
    protected String type;
    public void preCook(){
        System.out.println("***** 요리 준비중 *****");
        Arrays.stream(ingredients).forEach(
                ingredient -> System.out.print(ingredient + " ")
        );
        System.out.print("재료와 ");
        Arrays.stream(untensils).forEach(
                untensil -> System.out.print(untensil + " ")
        );
        System.out.println("요리기구를 준비중입니다.");
    }

    public void cook(){
        System.out.println("***** 요리중 *****");
        Arrays.stream(ingredients).forEach(
                ingredient -> System.out.print(ingredient + " ")
        );
        System.out.print("재료와 ");
        Arrays.stream(untensils).forEach(
                untensil -> System.out.print(untensil + " ")
        );
        System.out.println("요리기구를 이용해서 ");
        System.out.println(this.type+" 요리를 요리 중입니다.");
    }
    public void postCook(){
        System.out.println("***** 요리끝 *****");
        System.out.println(this.type+" 요리가 완료 되었습니다! 맛있게 드세요!");
    }


}
