package restaurant.restaurant.v3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.restaurant.v2.ui.UI;
import restaurant.restaurant.v3.cooking_steps.CookingStepsSteak;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class SteakTest {
    private UI ui;
    private OutputStream outputStream;
    @BeforeEach
    public void before(){
        outputStream = new ByteArrayOutputStream();// outputSteam 생성
        System.setOut(new PrintStream(outputStream));//생성한 outputSteam으로 설정
    }
    @AfterEach
    public void after(){
        System.setOut(System.out);
        //원상복귀
    }

    @Test
    public void isSteakInTBornSteak(){
        CookingStepsSteak cookingStepsSteak=new CookingStepsSteak();
        cookingStepsSteak.takeCookingSteakSteps(1);
        Assertions.assertEquals(output(outputStream),"*****요리준비중*****"+"T-BornmeatButter재료와MixingBowlOven요리기구를준비중입니다." +
                "*****요리중*****" +
                "T-BornmeatButter재료와MixingBowlOven요리기구를이용해서" +
                "T-Born요리를요리중입니다." +
                "*****요리끝*****" +
                "T-Born요리가완료되었습니다!맛있게드세요!".trim().replaceAll("\\s+", ""));
    }

    @Test
    public void isSteakInSirLoinSteak() {
        // CookingStepsSteak 객체 생성
        CookingStepsSteak cookingStepsSteak = new CookingStepsSteak();

        // Sir-loin Steak 선택 (옵션 2)
        cookingStepsSteak.takeCookingSteakSteps(2);

        // 띄어쓰기 제거된 예상 출력값
        String expectedOutput = "*****요리준비중*****" +
                "Sir-loinmeatButter재료와MixingBowlOven요리기구를준비중입니다." +
                "*****요리중*****" +
                "Sir-loinmeatButter재료와MixingBowlOven요리기구를이용해서" +
                "Sir-loin요리를요리중입니다." +
                "*****요리끝*****" +
                "Sir-loin요리가완료되었습니다!맛있게드세요!";

        // 출력값 비교
        Assertions.assertEquals(output(outputStream), expectedOutput);
    }
    @Test
    public void isSteakInRibEyeSteak() {
        // CookingStepsSteak 객체 생성
        CookingStepsSteak cookingStepsSteak = new CookingStepsSteak();

        // Rib-eye Steak 선택 (옵션 3)
        cookingStepsSteak.takeCookingSteakSteps(3);

        // 띄어쓰기 제거된 예상 출력값
        String expectedOutput = "*****요리준비중*****" +
                "Rib-eyemeatButter재료와MixingBowlOven요리기구를준비중입니다." +
                "*****요리중*****" +
                "Rib-eyemeatButter재료와MixingBowlOven요리기구를이용해서" +
                "Rib-eye요리를요리중입니다." +
                "*****요리끝*****" +
                "Rib-eye요리가완료되었습니다!맛있게드세요!";

        // 출력값 비교
        Assertions.assertEquals(output(outputStream), expectedOutput);
    }
    private String output(OutputStream outputStream){
        return outputStream.toString().trim().replaceAll("\\s+", "");
    }

}
