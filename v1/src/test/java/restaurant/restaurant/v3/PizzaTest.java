package restaurant.restaurant.v3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.restaurant.v3.cooking_steps.CookingStepsPizza;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class PizzaTest {
    private OutputStream outputStream;

    @BeforeEach
    public void before() {
        outputStream = new ByteArrayOutputStream(); // OutputStream 생성
        System.setOut(new PrintStream(outputStream)); // System.out을 OutputStream으로 설정
    }

    @AfterEach
    public void after() {
        System.setOut(System.out); // System.out 원상복구
    }

    @Test
    public void isPizzaInPeperoniPizza() {
        // CookingStepsPizza 객체 생성
        CookingStepsPizza cookingStepsPizza = new CookingStepsPizza();

        // Peperoni-Pizza 선택 (옵션 1)
        cookingStepsPizza.takeCookingPizzaSteps(1);

        // 띄어쓰기 제거된 예상 출력값
        String expectedOutput = "*****요리준비중*****" +
                "PeperoniBread재료와PotColander요리기구를준비중입니다." +
                "*****요리중*****" +
                "PeperoniBread재료와PotColander요리기구를이용해서" +
                "Peperoni요리를요리중입니다." +
                "*****요리끝*****" +
                "Peperoni요리가완료되었습니다!맛있게드세요!";

        // 출력값 비교
        Assertions.assertEquals(output(outputStream), expectedOutput);
    }

    @Test
    public void isPizzaInCheezePizza() {
        // CookingStepsPizza 객체 생성
        CookingStepsPizza cookingStepsPizza = new CookingStepsPizza();

        // Cheeze-Pizza 선택 (옵션 2)
        cookingStepsPizza.takeCookingPizzaSteps(2);

        // 띄어쓰기 제거된 예상 출력값
        String expectedOutput = "*****요리준비중*****" +
                "CheezeBread재료와PotColander요리기구를준비중입니다." +
                "*****요리중*****" +
                "CheezeBread재료와PotColander요리기구를이용해서" +
                "Cheeze요리를요리중입니다." +
                "*****요리끝*****" +
                "Cheeze요리가완료되었습니다!맛있게드세요!";

        // 출력값 비교
        Assertions.assertEquals(output(outputStream), expectedOutput);
    }

    @Test
    public void isPizzaInGorgonzolaPizza() {
        // CookingStepsPizza 객체 생성
        CookingStepsPizza cookingStepsPizza = new CookingStepsPizza();

        // Gorgonzola-Pizza 선택 (옵션 3)
        cookingStepsPizza.takeCookingPizzaSteps(3);

        // 띄어쓰기 제거된 예상 출력값
        String expectedOutput = "*****요리준비중*****" +
                "GorgonzolaBread재료와PotColander요리기구를준비중입니다." +
                "*****요리중*****" +
                "GorgonzolaBread재료와PotColander요리기구를이용해서" +
                "Gorgonzola요리를요리중입니다." +
                "*****요리끝*****" +
                "Gorgonzola요리가완료되었습니다!맛있게드세요!";

        // 출력값 비교
        Assertions.assertEquals(output(outputStream), expectedOutput);
    }

    private String output(OutputStream outputStream) {
        return outputStream.toString().trim().replaceAll("\\s+", "");
    }
}
