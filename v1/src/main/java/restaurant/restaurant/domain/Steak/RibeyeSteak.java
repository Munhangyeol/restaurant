package restaurant.restaurant.domain.Steak;

import java.util.Arrays;

public class RibeyeSteak extends SteakPart{
    private String type;
    private String [] ingredients;
    public RibeyeSteak(String type,String []ingredients){
        this.type=type;
        this.ingredients=ingredients;
    }
    public void preCook(){
        Arrays.stream(ingredients).forEach(
                ingredient -> System.out.println(ingredient + " ")
        );
        System.out.print("재료와 ");
        Arrays.stream(untensils).forEach(
                untensil -> System.out.println(untensil + " ")
        );
        System.out.println("요리기구를 준비중입니다.");
    }
    public void cook(){
        Arrays.stream(ingredients).forEach(
                ingredient -> System.out.println(ingredient + " ")
        );
        System.out.print("재료와 ");
        Arrays.stream(untensils).forEach(
                untensil -> System.out.println(untensil + " ")
        );
        System.out.println("요리기구를 이용해서 ");
        System.out.print(this.type+" 요리를 요리 중입니다.");
    }
    public void postCook(){
        System.out.print(this.type+" 요리가 완료 되었습니다! 맛있게 드세요!");
    }
}
