package restaurant.restaurant.v1.domain.pasta;

import java.util.Arrays;

public class TomatoPastaPart extends PastaPart{
    private String []ingredients;
    private String type;
    public TomatoPastaPart(String type,String[] ingredients){
        this.ingredients=ingredients;
        this.type=type;
    }
    @Override
    public void preCook(){
        System.out.println("***** 요리 준비중 *****");
        Arrays.stream(ingredients).forEach(
                ingredient -> System.out.print(ingredient + " ")
        );
        System.out.print("재료와 ");
        Arrays.stream(untensils).forEach(
                untensil -> System.out.print(untensil + " ")
        );
        System.out.println();
        System.out.println("요리기구를 준비중입니다.");
    }
    @Override
    public void cook(){
        System.out.println("***** 요리중 *****");

        Arrays.stream(ingredients).forEach(
                ingredient -> System.out.print(ingredient + " ")
        );
        System.out.print("재료와 ");
        Arrays.stream(untensils).forEach(
                untensil -> System.out.print(untensil + " ")
        );
        System.out.println();
        System.out.print("요리기구를 이용해서 ");
        System.out.println(this.type+" 요리를 요리 중입니다.");
    }
    @Override
    public void postCook(){
        System.out.println("***** 요리끝 *****");
        System.out.println(this.type+" 요리가 완료 되었습니다! 맛있게 드세요!");
    }

}
