package restaurant.restaurant.v3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.restaurant.v3.cooking_steps.CookingStepsPasta;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class PastaTest {
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
    public void isPastaInTomatoPasta() {
        // CookingStepsPasta 객체 생성
        CookingStepsPasta cookingStepsPasta = new CookingStepsPasta();

        // Tomato-Pasta 선택 (옵션 1)
        cookingStepsPasta.takeCookingPastaStpes(1);

        // 띄어쓰기 제거된 예상 출력값
        String expectedOutput = "*****요리준비중*****" +
                "TomatoNoodle재료와PanGrillTongs요리기구를준비중입니다." +
                "*****요리중*****" +
                "TomatoNoodle재료와PanGrillTongs요리기구를이용해서" +
                "Tomato요리를요리중입니다." +
                "*****요리끝*****" +
                "Tomato요리가완료되었습니다!맛있게드세요!";

        // 출력값 비교
        Assertions.assertEquals(output(outputStream), expectedOutput);
    }

    @Test
    public void isPastaInCreamPasta() {
        // CookingStepsPasta 객체 생성
        CookingStepsPasta cookingStepsPasta = new CookingStepsPasta();

        // Cream-Pasta 선택 (옵션 2)
        cookingStepsPasta.takeCookingPastaStpes(2);

        // 띄어쓰기 제거된 예상 출력값
        String expectedOutput = "*****요리준비중*****" +
                "CreamNoodle재료와PanGrillTongs요리기구를준비중입니다." +
                "*****요리중*****" +
                "CreamNoodle재료와PanGrillTongs요리기구를이용해서" +
                "Cream요리를요리중입니다." +
                "*****요리끝*****" +
                "Cream요리가완료되었습니다!맛있게드세요!";

        // 출력값 비교
        Assertions.assertEquals(output(outputStream), expectedOutput);
    }

    @Test
    public void isPastaInOilPasta() {
        // CookingStepsPasta 객체 생성
        CookingStepsPasta cookingStepsPasta = new CookingStepsPasta();

        // Oil-Pasta 선택 (옵션 3)
        cookingStepsPasta.takeCookingPastaStpes(3);

        // 띄어쓰기 제거된 예상 출력값
        String expectedOutput = "*****요리준비중*****" +
                "OilNoodle재료와PanGrillTongs요리기구를준비중입니다." +
                "*****요리중*****" +
                "OilNoodle재료와PanGrillTongs요리기구를이용해서" +
                "Oil요리를요리중입니다." +
                "*****요리끝*****" +
                "Oil요리가완료되었습니다!맛있게드세요!";

        // 출력값 비교
        Assertions.assertEquals(output(outputStream), expectedOutput);
    }

    private String output(OutputStream outputStream) {
        return outputStream.toString().trim().replaceAll("\\s+", "");
    }
}
