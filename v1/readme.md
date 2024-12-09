# Version 1
## 문제 요약

- 철수는 스테이크,피자,파스타라는 양식을 전문으로 하는 레스토랑을 차림
- 각 요리는 다음과 같은 종류를 가짐
    - 스테이크: ```T-Born Steak```, ```Sir-loin Steak```, ```Rib-eye Steak```
    - 파스타: ```Tomato-Pasta```,```Cream-Pasta```, ```Oil-Pasta```
    - 피자: ```Peperoni-Pizza```,```Cheeze-Pizza```,```Gorgonzola-Pizza```
    - 각 요리 별로 조리 기구는 공유하지만, 재료는 각각 독립적으로 존재함
        - 스테이크 조리 기구: ```Pan```, ```Grill```,```Tongs```(집게)
        - 파스타:```Pot``` ,```Colander```(소쿠리)
        - 피자: ```Mixing Bowl```, ```Oven```
    - 또한 각 요리들은 요리 전, 요리 중,요리 후라는 과정이 있으며, 각각의 세부 과정은 아래와 같음
        - 요리 전: ~재료와 ~요리 기구를 준비 중입니다.   출력
        - 요리 중: ~재료와 ~요리기구를 이용해서 ~ 요리를 요리 중 입니다.        출력
        - 요리 후: ~ 요리가 완료 되었습니다! 맛있게 드세요.

## 문제 요구사항

- 사용자로 하여금 스테이크,파스타,피자 중 어떤 음식을 먹고 싶은 지를 물어보기
- 각 파트 중 다시 어떤 요리를 먹고 싶은 지를 물어보기
- 해당 요리 요리 전, 요리 중, 요리 후를 출력하기

위의 과정을 5번 반복하기

## 프로그래밍 요구사항

- v1이므로 후에 어떤 설계가 올지 모른다. 따라서 solid원칙에 입각한 설계를 요구함
- 클린 코드에서 말하는 깨끗한 코드의 원칙을 따른다.
- 코드에 대한 테스트 코드 역시 필수

# Version1의 리팩토링 과정 

## v1

### 1. 단순 구현 완료

SteakPart(abstract class)→SirloinSteakPart,RibeyeSteak,TBornSteakPart

PizzaPart(abstract class)→PeperoniPizzaPart,GorgonzolaPizzaPart,CheezePizzaPart

PastaPart(abstract class)→TomatoPastaPart,OilPastaPart,CreamPastaPart

의 상속을 받도록 구현함

### 2. 중복 코드에 대한 제거가 필요함

```java
SirloinSteakPart,RibeyeSteak,TBornSteakPart
PeperoniPizzaPart,GorgonzolaPizzaPart,CheezePizzaPart,
TomatoPastaPart,OilPastaPart,CreamPastaPart
```

- 위 클래스 내에서 사용하는 요리 전, 중, 후의 실행 코드가 모두 같으며, 모두 같은 행동을 하기로 기대함
- 하나의 메서드를 바꾸면-> 9개를 모두 바꿔야함
- 이 대신에 다른 모듈로 빼버리거나 하는 것이 훨씬 나은 코드가 아닐까

### 3.UI클래스의 구현 리팩토링

**중복 제거 및 출력 책임 분리 (`printSelectMenu` 메서드 생성)**:

- 반복되는 출력 코드(메뉴 안내)를 `printSelectMenu` 메서드로 위임하여 중복을 제거하고 코드의 가독성을 향상시켰습니다.
- 이로 인해 출력 메시지를 수정하거나 포맷을 변경할 때 단일 메서드만 수정하면 되는 장점이 생깁니다.
- 변경 코드

```java
private void printSelectMenu(String notice, String selectedMenu) {
    System.out.println(notice);
    System.out.println(selectedMenu);
}
```

- 변경 전

```java
System.out.println("안녕하세요! 어떤 파트의 음식을 드시고 싶으세요?");
System.out.println("1: Steak요리 || 2: Pasta요리 || 3: Pizza요리");
```

- 변경 후

```java
printSelectMenu("안녕하세요! 어떤 파트의 음식을 드시고 싶으세요?", "1: Steak요리 || 2: Pasta요리 || 3: Pizza요리");
```

**각 요리 파트의 단계를 처리하는 메서드 분리 (`takeCookingSteakSteps`, `takeCookingPastaSteps`, `takeCookingPizzaSteps`)**:

- 요리별 세부 작업 처리 로직을 별도의 메서드로 분리하여 가독성을 높였습니다.
- 각 요리 파트의 로직을 독립적으로 관리할 수 있도록 책임을 분리했습니다.
- 변경 코드

```java
private void takeCookingSteakSteps(int detailedMenu) {
    SteakPart steakPart = null;
    switch (detailedMenu) {
        case 1:
            steakPart = new TBornSteakPart("T-Born", new String[]{"T-Born meat", "Butter"});
            break;
        case 2:
            steakPart = new SirloinSteakPart("Sir-loin", new String[]{"Sir-loin meat", "Butter"});
            break;
        case 3:
            steakPart = new RibeyeSteak("Rib-eye", new String[]{"Rib-eye meat", "Butter"});
            break;
    }
    steakPart.preCook();
    steakPart.cook();
    steakPart.postCook();
}
```

- 변경 전

```java
if (menu == 1) {
    SteakPart steakPart = null;
    switch (detailedMenu) {
        case 1:
            steakPart = new TBornSteakPart("T-Born", new String[]{"T-Born meat", "Butter"});
            break;
        ...
    }
    steakPart.preCook();
    steakPart.cook();
    steakPart.postCook();
}

```

- 변경 후

```java
switch (menu) {
    case 1:
        takeCookingSteakSteps(detailedMenu);
        break;
    ...
}
```

**메인 메뉴와 세부 메뉴 선택 로직 유지 (`selectMenu`, `selectDetailMenu`)**:

- 메뉴 선택 로직은 기존 방식대로 유지하되, 출력 로직에서 중복을 제거했습니다.
- 메서드가 여전히 단일 책임을 유지하고 있어 변경 전후 로직이 단순화되었습니다.
- 변경 코드

```java
public int selectMenu() throws IOException {
    printSelectMenu("안녕하세요! 어떤 파트의 음식을 드시고 싶으세요?", "1: Steak요리 || 2: Pasta요리 || 3: Pizza요리");
    return Integer.parseInt(reader.readLine());
}
```

- 최종 UI변경 코드

```java

public class UI {
 private final   BufferedReader reader;
    public UI(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException {
        int menu=selectMenu();
        int detailedMenu=selectDetailMenu(menu);
        switch (menu){
            case 1:
                takeCookingSteakSteps(detailedMenu);
                break;
            case 2:
                takeCookingPastaStpes(detailedMenu);
                break;
            case 3:
                takeCookingPizzaSteps(detailedMenu);
                break;
        }
    }
    private int selectMenu() throws IOException {
        printSelectMenu("안녕하세요! 어떤 파트의 음식을 드시고 싶으세요?", "1: Steak요리 || 2: Pasta요리 || 3: Pizza요리");
        return Integer.parseInt(reader.readLine());
    }
    private int selectDetailMenu(int mainMenu) throws IOException {
        int detailMenu = 0;
        switch (mainMenu){
            case 1:{
                printSelectMenu("Steak 중 어떤 Steak 메뉴를 고르시겠어요?", "1: T-Born Steak || 2: Sir-loin Steak || 3: Rib-eye Steak");
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            }
            case 2:
                printSelectMenu("Pasta 중 어떤 Pasta 메뉴를 고르시겠어요?", "1: Tomato-Pasta || 2: Cream-Pasta || 3: Oil-Pasta");
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            case 3:
                printSelectMenu("Pizza 중 어떤 Pizza 메뉴를 고르시겠어요?", "1: Peperoni-Pizza || 2: Cheeze-Pizza || 3: Gorgonzola-Pizza");
                detailMenu=Integer.parseInt(reader.readLine());
                break;
        }
        return detailMenu;
    }

    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
    private void takeCookingSteakSteps(int detailedMenu) {
        SteakPart steakPart = null;
        switch (detailedMenu){
            case 1:
                steakPart = new TBornSteakPart("T-Born",new String[]{"T-Born meat","Butter"});
                break;
            case 2:
                steakPart = new SirloinSteakPart("Sir-loin", new String[]{"Sir-loin meat", "Butter"});
                break;
            case 3:
                steakPart = new RibeyeSteak("Rib-eye",new String[]{"Rib-eye meat","Butter"});
                break;
        }
        steakPart.preCook();
        steakPart.cook();
        steakPart.postCook();
    }
    private void takeCookingPastaStpes(int detailedMenu) {
        PastaPart pastaPart = null;
        switch (detailedMenu){
            case 1:
                pastaPart = new TomatoPastaPart("Tomato",new String[]{"Tomato","Noodle"});
                break;
            case 2:
                pastaPart = new CreamPastaPart("Cream", new String[]{"Cream", "Noodle"});
                break;
            case 3:
                pastaPart = new OilPastaPart("Oil",new String[]{"Oil","Noodle"});
                break;
        }
        pastaPart.preCook();
        pastaPart.cook();
        pastaPart.postCook();
    }
    private void takeCookingPizzaSteps(int detailedMenu) {
        PizzaPart pizzaPart = null;
        switch (detailedMenu){
            case 1:
                pizzaPart = new PeperoniPizzaPart("Peperoni",new String[]{"Peperoni","Bread"});
                break;
            case 2:
                pizzaPart = new CheezePizzaPart("Cheeze", new String[]{"Cheeze", "Bread"});
                break;
            case 3:
                pizzaPart = new GorgonzolaPizzaPart("Gorgonzola",new String[]{"Gorgonzola","Bread"});
        }
        pizzaPart.preCook();
        pizzaPart.cook();
        pizzaPart.postCook();
    }
}
```

- 이 위의 코드에서

```java
pizzaPart.preCook();
pizzaPart.cook();
pizzaPart.postCook();

pastaPart.preCook();
pastaPart.cook();
pastaPart.postCook();

steakPart.preCook();
steakPart.cook();
steakPart.postCook();

```

가 중복되는데, 이를 어떻게 해결 해야 할까? 

v1-v1의 최종 다이어그램

![image](https://github.com/user-attachments/assets/ec8a022d-84e8-4109-9c66-3a39b32fa7af)

파스타,피자,스테이크 모두 동일

---

## v2

### 1. 각 파트별로 구현되어 있는 메서드가 중복되어 있는 것을 추상 클래스로 추상화

- 이를 통해서 중복을 제거해서 같은 기능을 해야 하는 수정해야 할 메서드를 줄일 수 있으며,
- 각 세부 메뉴 클래스 내에서 기능 구현이 추가로 필요할 때 오버 라이딩(다형성)이나, 메서드 추가 구현을 통해서 OCP원칙을 지키면서 코딩할 수 있음
- 즉 각 part내로 메서드를 위임하여, 더 나은 코드를 작성 할 수 있음
- 변경 전

```java
public class PeperoniPizzaPart extends PizzaPart{
    private String type;
    private String [] ingredients;
    public PeperoniPizzaPart(String type,String []ingredients){
        this.type=type;
        this.ingredients=ingredients;
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
```

- 변경 후

```java

public class PeperoniPizzaPart extends PizzaPart {
    public PeperoniPizzaPart(String type,String []ingredients){
        this.type=type;
        this.ingredients=ingredients;
    }

}
```

→ 다른 파트도 마찬가지로 변경

- 다음과 같은 클래스 다이어그램을 구성

![image](https://github.com/user-attachments/assets/9499a822-fa04-45c7-aec3-ea531b0a8eab)

### 2.UI클래스 내에서 중복되는 요리하는 코드 이슈

```java
pizzaPart.preCook();
pizzaPart.cook();
pizzaPart.postCook();

pastaPart.preCook();
pastaPart.cook();
****pastaPart.postCook();

steakPart.preCook();
steakPart.cook();
steakPart.postCook();

}
```

- UI클래스 내에서 다음과 같은 코드가 중복됨
- 그래서 FoodPart 인터페이스를 만들어서 각 파트들로 상속하여, 메서드를 구현하는 방식으로 메서드를 구현함
- 다음과 같은 코드가 됨

```java
 private void takeCookingPizzaSteps(int detailedMenu) {
        PizzaPart pizzaPart = null;
        switch (detailedMenu){
            case 1:
                pizzaPart = new PeperoniPizzaPart("Peperoni",new String[]{"Peperoni","Bread"});
                break;
            case 2:
                pizzaPart = new CheezePizzaPart("Cheeze", new String[]{"Cheeze", "Bread"});
                break;
            case 3:
                pizzaPart = new GorgonzolaPizzaPart("Gorgonzola",new String[]{"Gorgonzola","Bread"});
        }
        cookEntire(pizzaPart);
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
```

- 그러나 각 파트에서 요리 전 과 중만 하고 싶을 수 있고, 중과 후만 진행하고 싶을 수 있다.→ 유지보수 차원에서
- 그런데 위와 같은 cookEntire를 사용해서 하나의 메서드로 묶으면 위의 문제를 해결할 수 없다.
- 또한 근본적으로 
`takeCookingSteakSteps` , `takeCookingPastaStpes` , `takeCookingPizzaSteps` → 이것이 모두 각 part에 대해 할당하고, 요리한다는 점에서 중복 코드임
- 과연 UI 클래스 내에서 SteakSteps를 가질 책임이 있는가?
- 위의 두 가지 생각을 연관 시켜보자

---

## v3

### 1. UI클래스 내에서 가지고 있던 SteakSteps 결정하는 책임을 위임

- 다음과 같은 CookingSteps~ 클래스로 위임

```java
public class CookingStepsPasta {

    public CookingStepsPasta() {

    }

    public void takeCookingPastaStpes(int detailedMenu) {
        PastaPart pastaPart = null;
        switch (detailedMenu){
            case 1:
                pastaPart = new TomatoPastaPart("Tomato",new String[]{"Tomato","Noodle"});
                break;
            case 2:
                pastaPart = new CreamPastaPart("Cream", new String[]{"Cream", "Noodle"});
                break;
            case 3:
                pastaPart = new OilPastaPart("Oil",new String[]{"Oil","Noodle"});
                break;
        }
        cookEntire(pastaPart);
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
}
```

- 책임을 위임한 뒤의 UI클래스는 다음과 같다

```java
public class UI {
 private final   BufferedReader reader;
 private CookingStepsSteak cookingStepsSteak;
 private CookingStepsPasta cookingStepsPasta;
 private CookingStepsPizza cookingStepsPizza;

    public UI(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        cookingStepsSteak=new CookingStepsSteak();
        cookingStepsPasta=new CookingStepsPasta();
        cookingStepsPizza=new CookingStepsPizza();

    }

    public void run() throws IOException {
        int menu=selectMenu();
        int detailedMenu=selectDetailMenu(menu);
        switch (menu){
            case 1:
                cookingStepsSteak.takeCookingSteakSteps(detailedMenu);
                break;
            case 2:
                cookingStepsPasta.takeCookingPastaStpes(detailedMenu);
                break;
            case 3:
                cookingStepsPizza.takeCookingPizzaSteps(detailedMenu);
                break;
        }
    }
    private int selectMenu() throws IOException {
        printSelectMenu("안녕하세요! 어떤 파트의 음식을 드시고 싶으세요?", "1: Steak요리 || 2: Pasta요리 || 3: Pizza요리");
        return Integer.parseInt(reader.readLine());
    }
    private int selectDetailMenu(int mainMenu) throws IOException {
        int detailMenu = 0;
        switch (mainMenu){
            case 1:{
                printSelectMenu("Steak 중 어떤 Steak 메뉴를 고르시겠어요?", "1: T-Born Steak || 2: Sir-loin Steak || 3: Rib-eye Steak");
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            }
            case 2:
                printSelectMenu("Pasta 중 어떤 Pasta 메뉴를 고르시겠어요?", "1: Tomato-Pasta || 2: Cream-Pasta || 3: Oil-Pasta");
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            case 3:
                printSelectMenu("Pizza 중 어떤 Pizza 메뉴를 고르시겠어요?", "1: Peperoni-Pizza || 2: Cheeze-Pizza || 3: Gorgonzola-Pizza");
                detailMenu=Integer.parseInt(reader.readLine());
                break;
        }
        return detailMenu;
    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
```

- 책임을 위임하였지만, UI클래스 내에서 어떤 메뉴를 내 줄지를 여전히 고민하고 있다.
- 즉 현재 단일 책임이 아닌 여러가지의 책임을 가지고 있다는 의미임→ srp위반

### 책임이란?

> ex) 만약 Soup이란 메뉴를 추가한다면, run,selectMenu,selectDetailMenu의 3가지의 메서드들에서 분기를 하는 파트 즉 메뉴를 고르는 부분에서 수정이 필요하다. 그러나 Soup파트가 추가 되더라도, 변경되지 않는 부분(printSelectMenu과 run,selectMenu,selectDetailMenu에서 입력을 하는 부분과 메뉴를 보여줘야하는 부분등)이 존재한다.  그런데, 이렇게 기능이 추가,변경 시 클래스내에서 변경 해야 하는 메서드, 변경하지 않는 메서드가 공존한다면, 이는 결과적으로 기능을 추가 변경시에 실수를 유발할 수 있을 것이다.→ 이에 따라서 **하나의 책임을 기능을 추가,변경 시에 변경되는 코드**들로 정의하고,  이렇게 기능 추가,변경시 변경되는 코드들이 하나의 클래스에 있어야 한다는 것이 **단일 책임 원칙**임.

여기서 말하는 실수란?

→ 새로운 요리 `Soup`을 추가하면서 `selectMenu`에는 추가했지만, `selectDetailMenu`의 분기문을 수정하지 않음→  코드가 복잡해지면 이러한 에러를 찾기가 매우 어려움→ 시간이 오래걸림

→ 즉 한 클래스 내에서 너무 여러 책임이 있다면 이러한 실수를 할 가능성이 올라간다는 의미임.

→ 즉 책임들을 클래스로 나누어서 변경해야 할 것을 클래스 단위로 변경함.

- 따라서 여기서 먼저 메뉴가 추가,변경 되었을 때 보여주는 메뉴가 달라져야하므로,

 메뉴 클래스를 따로 나누어서 책임을 나눔

```java
public class Menu {
    public static final String MENU_MAIN_NOTICE = "안녕하세요! 어떤 파트의 음식을 드시고 싶으세요?";
    public static final String MENU_MAIN_SELECTED = "1: Steak요리 || 2: Pasta요리 || 3: Pizza요리";
    public static final String MENU_DETAIED_STEAK_NOTICE = "Steak 중 어떤 Steak 메뉴를 고르시겠어요?";
    public static final String MENU_DETAIED_STEAK_SELECTED = "1: T-Born Steak || 2: Sir-loin Steak || 3: Rib-eye Steak";
    public static final String MENU_DETAIED_PASTA_NOTICE = "Pasta 중 어떤 Pasta 메뉴를 고르시겠어요?";
    public static final String MENU_DETAIED_PASTA_SELECTED = "1: Tomato-Pasta || 2: Cream-Pasta || 3: Oil-Pasta";
    public static final String MENU_DETAIED_PIZZA_NOTICE = "Pizza 중 어떤 Pizza 메뉴를 고르시겠어요?";
    public static final String MENU_DETAIED_PIZZA_SELECTED = "1: Peperoni-Pizza || 2: Cheeze-Pizza || 3: Gorgonzola-Pizza";
}
```

→ 그러나 여기서는 다시 메인 메뉴와 각 파트별로 나뉘어서 다음과 같이 다시 나눔

```java
public class MainNoticeMenu {
    public static final String MENU_MAIN_NOTICE = "안녕하세요! 어떤 파트의 음식을 드시고 싶으세요?";
    public static final String MENU_MAIN_SELECTED = "1: Steak요리 || 2: Pasta요리 || 3: Pizza요리";  

}
public class PastaMenu {
    public static final String MENU_DETAIED_PASTA_NOTICE = "Pasta 중 어떤 Pasta 메뉴를 고르시겠어요?";
    public static final String MENU_DETAIED_PASTA_SELECTED = "1: Tomato-Pasta || 2: Cream-Pasta || 3: Oil-Pasta";
}
public class PizzaMenu {
    public static final String MENU_DETAIED_PIZZA_NOTICE = "Pizza 중 어떤 Pizza 메뉴를 고르시겠어요?";
    public static final String MENU_DETAIED_PIZZA_SELECTED = "1: Peperoni-Pizza || 2: Cheeze-Pizza || 3: Gorgonzola-Pizza";
}
public class SteakMenu {
    public static final String MENU_DETAIED_STEAK_NOTICE = "Steak 중 어떤 Steak 메뉴를 고르시겠어요?";
    public static final String MENU_DETAIED_STEAK_SELECTED = "1: T-Born Steak || 2: Sir-loin Steak || 3: Rib-eye Steak";
}
```

→ 나눈 이유는 각 past,pizzaSteak에 대해서 변경될 때의 책임에 따라서 나눈것임

ex)Steak파트의 메뉴변경, Steak파트의 메뉴 추가등등→ SteakMenu 클래스를 변경

- 이제 UI클래스에서 아래와 같은 분기가 되는 코드들의 책임을 분리할 차례이다

```java
    ...
    switch (menu){
            case 1:
                cookingStepsSteak.takeCookingSteakSteps(detailedMenu);
                break;
            case 2:
                cookingStepsPasta.takeCookingPastaStpes(detailedMenu);
                break;
            case 3:
                cookingStepsPizza.takeCookingPizzaSteps(detailedMenu);
                break;
        }
    ...
       private int selectDetailMenu(int mainMenu) throws IOException {
        int detailMenu = 0;
        switch (mainMenu){
            case 1:{
                printSelectMenu(MENU_DETAIED_STEAK_NOTICE, MENU_DETAIED_STEAK_SELECTED);
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            }
            case 2:
                printSelectMenu(MENU_DETAIED_PASTA_NOTICE, MENU_DETAIED_PASTA_SELECTED);
                detailMenu=Integer.parseInt(reader.readLine());
                break;
            case 3:
                printSelectMenu(MENU_DETAIED_PIZZA_NOTICE, MENU_DETAIED_PIZZA_SELECTED);
                detailMenu=Integer.parseInt(reader.readLine());
                break;
        }
        return detailMenu;
    }
```

- 어떻게 하면 보다 깔끔하게 나눌 수 있을까?

### 2. 각 요리에 대한 테스트 코드 작성

```java

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
    
        private String output(OutputStream outputStream) {
        return outputStream.toString().trim().replaceAll("\\s+", "");
    }
    }
```

- ByteArrayOutputStream을 이용하면 콘솔 창의 출력에 대해서 테스트 코드 작성이 가능함

---

## v4

### 1. UI클래스에서 가지고 있던 음식을 선택하는 책임을 다른 클래스로 위임

- 기존의 v3에서는 UI클래스내에서 음식을 선택하는 책임 또한 함께 가지고 있으므로, 위에 말한 것처럼 단일 책임원칙에 위배됨
- 따라서 `FoodSelectingService` 클래스를 새로 만들고 이곳으로 선택의 책임을 위임한다.

```java
public class FoodSelectingService {
    private final CookingStepsPasta cookingStepsPasta;
    private final CookingStepsPizza cookingStepsPizza;
    private final CookingStepsSteak cookingStepsSteak;

    public FoodSelectingService(CookingStepsPasta cookingStepsPasta, CookingStepsPizza cookingStepsPizza, CookingStepsSteak cookingStepsSteak){
        this.cookingStepsPasta = cookingStepsPasta;
        this.cookingStepsPizza = cookingStepsPizza;
        this.cookingStepsSteak = cookingStepsSteak;
    }
   
    public void showDetailedMenu(int mainMenu){
        switch (mainMenu){
            case 1:{
                printSelectMenu(MENU_DETAIED_STEAK_NOTICE, MENU_DETAIED_STEAK_SELECTED);
                break;
            }
            case 2:
                printSelectMenu(MENU_DETAIED_PASTA_NOTICE, MENU_DETAIED_PASTA_SELECTED);
                break;
            case 3:
                printSelectMenu(MENU_DETAIED_PIZZA_NOTICE, MENU_DETAIED_PIZZA_SELECTED);
                break;
        }
    }
    public void cookBySelectedMenu(int mainMenu,int detailedMenu){
        switch (mainMenu){
            case 1:
                cookingStepsSteak.takeCookingSteakSteps(detailedMenu);
                break;
            case 2:
                cookingStepsPasta.takeCookingPastaStpes(detailedMenu);
                break;
            case 3:
                cookingStepsPizza.takeCookingPizzaSteps(detailedMenu);
                break;
        }

    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
```

 

- showDetailedMenu이면 UI에 있어야 하는거 아닌가 라고 반문할 수 있지만
- 새로운 음식이 추가 되었을 때 변화를 생각하면 이 파트를 다른 클래스로 빼는게 매우 당연함
- 따라서 UI클래스 내에서의 selectDetailMenu메서드에서 분기의 책임을 여리고 위임할 수 있음
- 덕분에 UI클래스는 아래와 같이 ui관련 책임만 가지고 있으면 됨

```java
public class UI {
 private final   BufferedReader reader;
 private final FoodSelectingService foodSelectingService;
    public UI(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        foodSelectingService=new FoodSelectingService(new CookingStepsPasta(),new CookingStepsPizza(),new CookingStepsSteak());
    }
    public void run() throws IOException {
        int menu=selectMainMenu();
        foodSelectingService.cookBySelectedMenu(menu,selectDetailMenu(menu));

    }
    private int selectMainMenu() throws IOException {
        printSelectMenu(MENU_MAIN_NOTICE,  MENU_MAIN_SELECTED);
        return Integer.parseInt(reader.readLine());
    }
    private int selectDetailMenu(int mainMenu) throws IOException {
        int detailMenu = 0;
        foodSelectingService.showDetailedMenu(mainMenu);
        detailMenu=Integer.parseInt(reader.readLine());
        return detailMenu;
    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
```

- 그런데 `FoodSelectingService`내에서 `CookingSteps`  `Pasta`,`Pizza` ,`Steak`  세가지의 클래스에 의존하는데, 굳이 그럴 필요가 있을까? 실제 코드에서 사용하는 코드는 하나의 cookingStep만 사용하는데.
- 또한 중복 적인 코드는 코드의 응집성을 해치며, 실수할 가능성을 올려줌

### 2. `FoodSelectingService`  리팩토링

- 기존의 `FoodSelectingService` 에는 `CookingSteps`  `Pasta`,`Pizza` ,`Steak` 를 중복해서 의존한다.
- 따라서 이를 막기 위해서 CookingSteps라는 `CookingStepsPizza,Steak,Pasta` 의 부모 인터페이스를 만든다
- 이렇게 부모 인터페이스를 사용하면 필연적으로 세가지의 자식 인스턴스 중 하나를 할당받아야하는데, 그래서 이 함수를 `selectMainMenu` 라는 함수로 만든다.
- 따라서 아래와 같은 코드로 리팩토링할 수 있음

```java
 package restaurant.restaurant.v4.service;

import restaurant.restaurant.v4.cooking_steps.CookingSteps;
import restaurant.restaurant.v4.cooking_steps.CookingStepsPasta;
import restaurant.restaurant.v4.cooking_steps.CookingStepsPizza;
import restaurant.restaurant.v4.cooking_steps.CookingStepsSteak;

import static restaurant.restaurant.v4.domain.menu.PizzaMenu.MENU_DETAIED_PIZZA_NOTICE;
import static restaurant.restaurant.v4.domain.menu.PizzaMenu.MENU_DETAIED_PIZZA_SELECTED;

public class FoodSelectingService {
    private  CookingSteps cookingSteps;

    public FoodSelectingService(){
    }
    // showDetailedMenu이면 UI에 있어야 하는거 아닌가 라고 반문할 수 있지만
    // 새로운 음식이 추가 되었을 때 변화를 생각하면 이 파트를 다른 클래스로 빼는게 매우 당연함
    // 따라서 UI클래스 내에서의 selectDetailMenu메서드에서 분기의 책임을 여리고 위임할 수 있음
    public void selectMainMenu(int mainMenu){
        switch (mainMenu){
            case 1:{
                cookingSteps=new CookingStepsSteak();
                break;
            }
            case 2:
                cookingSteps=new CookingStepsPasta();
                break;
            case 3:
                cookingSteps = new CookingStepsPizza();
                printSelectMainMenuAndAssignMainMenu(MENU_DETAIED_PIZZA_NOTICE, MENU_DETAIED_PIZZA_SELECTED,new CookingStepsPizza());
                break;
        }
    }
    public void printDetailedMenu(){
        cookingSteps.printDetailedMenu();
    }
    public void cookBySelectedMenu(int detailedMenu){
        cookingSteps.takeCookingSteps(detailedMenu);
    }
    private void printSelectMainMenuAndAssignMainMenu(String notice, String selectedMenu,CookingSteps cookingSteps) {
        System.out.println(notice);
        System.out.println(selectedMenu);
        this.cookingSteps=cookingSteps;
    }
}

```

- 여기서 `printDetailedMenu` 에서 cookingSteps로 위임하는데, 이 이유는 cookingSteps가 디테일한 메뉴들의 수정,확장에 대한 책임이 있기 때문에, 이 책임을 위임한 것임

- UI클래스는 아래와 같이 각 메서드가 의도를 더욱 명확히 하고, 간결한 코드가 되며, UI기능 외에는 수행하지 않는다.

```java
public class UI {
 private final BufferedReader reader;
 private final FoodSelectingService foodSelectingService;
    public UI(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        foodSelectingService=new FoodSelectingService();
    }
    public void run() throws IOException {
        selectMainMenu();
        foodSelectingService.cookBySelectedMenu(selectDetailMenu());
    }
    private void selectMainMenu() throws IOException {
        printSelectMenu(MENU_MAIN_NOTICE,MENU_MAIN_SELECTED);
        foodSelectingService.selectMainMenu(Integer.parseInt(reader.readLine()));
    }
    private int selectDetailMenu() throws IOException {
        foodSelectingService.printDetailedMenu();
        return Integer.parseInt(reader.readLine());
    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
```

- CookingStepsSteak 코드는 아래와 같음

```java
public class CookingStepsSteak implements CookingSteps{

    public CookingStepsSteak() {

    }

    public void takeCookingSteps(int detailedMenu) {
     SteakPart steakPart = null;
        switch (detailedMenu){
            case 1:
                steakPart = new TBornSteakPart("T-Born",new String[]{"T-Born meat","Butter"});
                break;
            case 2:
                steakPart = new SirloinSteakPart("Sir-loin", new String[]{"Sir-loin meat", "Butter"});
                break;
            case 3:
                steakPart = new RibeyeSteak("Rib-eye",new String[]{"Rib-eye meat","Butter"});
                break;
        }
        cookEntire(steakPart);
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
    public void printDetailedMenu(){
        printSelectMenu(MENU_DETAIED_STEAK_NOTICE, MENU_DETAIED_STEAK_SELECTED);

    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
```

- 여기서 사용하는 cookEntire이 과연 같은 책임이라고 할 수 있을까?
- 아니다. 요리과정,순서가 바뀌는 것이랑, 요리의 속성와 조리 기구를 통해서 실제 요리를 할당 하는 작업은 다르게 수행됨

ex) 요리 순서를 바꾸고 싶을 때는  cookEntire만 사용, 스테이크 메뉴가 추가 되었을 때는 cookEntire는 안 바꿈→ 즉 다른 책임이다.

### 3.CookingStepsSteak의 관리 책임을 SteakManager로 위임

- 따라서 cookEntire를 제외하고, SteakManager를 만들고, 여기서 스테이크의 메뉴를 선택하는 과정과 스테이크 메뉴를 출력하는 것을 수행하면 같은 책임들로 묶이게 됨
- SteakManager의 코드는 아래와 같다

```java
public class SteakManager {
    public SteakManager(){

    }
    public FoodPart selectSteak(int detailedMenu){
        return switch (detailedMenu) {
            case 1 -> new TBornSteakPart("T-Born", new String[]{"T-Born meat", "Butter"});
            case 2 -> new SirloinSteakPart("Sir-loin", new String[]{"Sir-loin meat", "Butter"});
            case 3 -> new RibeyeSteak("Rib-eye", new String[]{"Rib-eye meat", "Butter"});
            default -> throw new RuntimeException("This Menu was not in my Restaurant");
        };
    }
    public void printDetailedMenu(){
        printSelectMenu(MENU_DETAIED_STEAK_NOTICE, MENU_DETAIED_STEAK_SELECTED);
    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
```

- 파스타,피자도 마찬가지로 Manager로 관리
- FoodSelectingService 클래스 보다  MenuManager라는 이름이 더 직관적이라서 리팩토링

### 4. DetailedMenuManager로 추상화

- SteakManager, PastaManager,PizzaManager는 공통적으로 디테일한 Menu를 관리하므로 추상화를 통해서 OCP원칙을 통해서 확장성을 높임

```java
public interface DetailedMenuManager {
     FoodPart selectDetailedMenu(int detailedMenu);
    void printDetailedMenu();
}
```

현재의 다이어그램은 다음과 같음

![image](https://github.com/user-attachments/assets/29847f92-b810-4ddf-8a99-5419e2a18afe)

- 위의 CookingSteps를 어떻게 하면 불변으로 변경할수있을까

## v5

### 1. `MenuManager`  클래스 내의 `CookingSteps`  를 final형으로 어떻게 선언할 수 있을까

- `CookingSteps` 가 불변형이 아니면, 변경의 위험성이 있다.
- 그러나 어떤 메뉴를 선택할지는 사용자의 선택에 따라서 바뀌므로, 이는 변경 가능성에 대해서 열어두는게 낫지 않을까
- 일단은 final형이 아닌 그대로 선언함

### 2. 전체 결과

**Application 클래스**

```java
    public static void main(String[] args) throws IOException {
        UI restaurant=new UI(new MenuManager());
        restaurant.run();
    }
```

- Application 클래스에서는 UI클래스를 정의하고 실행한다.

**UI클래스** 

```java
public class UI {
 private final BufferedReader reader;
 private final MenuManager menuManager;
    public UI(MenuManager mainMenuManager){
        this.menuManager = mainMenuManager;
        reader = new BufferedReader(new InputStreamReader(System.in));

    }
    public void run() throws IOException {
        selectMainMenu();
        menuManager.cookBySelectedMenu(selectDetailMenu());
    }
    private void selectMainMenu() throws IOException {
        printSelectMenu(MENU_MAIN_NOTICE,MENU_MAIN_SELECTED);
        menuManager.selectMainMenu(Integer.parseInt(reader.readLine()));
    }
    private int selectDetailMenu() throws IOException {
        menuManager.printDetailedMenu();
        return Integer.parseInt(reader.readLine());
    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
```

- 현재 UI클래스는 메뉴를 출력하고, 메뉴를 입력  받는 것에 책임이 집중 되어있다.
- 여기서 메뉴의 내용물은 MenuConsol내의 static 상수들로 정의가 되어있어서, 해당 내용물에 대한 책임은 UI클래스에서 담당하지 않고, MenuConsol내의 상수들이 담당한다.

**MenuManager 클래스**

```java
public class MenuManager {
    private CookingSteps cookingSteps;

    public MenuManager(){

    }
    // showDetailedMenu이면 UI에 있어야 하는거 아닌가 라고 반문할 수 있지만
    // 새로운 음식이 추가 되었을 때 변화를 생각하면 이 파트를 다른 클래스로 빼는게 매우 당연함
    // 따라서 UI클래스 내에서의 selectDetailMenu메서드에서 분기의 책임을 여리고 위임할 수 있음
    public void selectMainMenu(int mainMenu){
        switch (mainMenu){
            case 1:
                cookingSteps=new CookingStepsSteak(new SteakManager());
                break;
            case 2:
                cookingSteps=new CookingStepsPasta(new PastaManager());
                break;
            case 3:
                cookingSteps = new CookingStepsPizza(new PizzaManager());
                break;
        }
    }
    public void printDetailedMenu(){
        cookingSteps.printDetailedMenu();
    }
    public void cookBySelectedMenu(int detailedMenu){
        cookingSteps.takeCookingSteps(detailedMenu);
    }
}

```

- MenuManager 클래스에서는 사용자가 고른 숫자에 대해서 메뉴의 Steps를 정해주는 책임을 가지고 있음
- cookingSteps에게 상세메뉴보여주기, 요리과정 수행의 책임을 위임함

**CookingSteps클래스**

```java
public class CookingStepsPasta implements CookingSteps{
    private final DetailedMenuManager manager;
    public CookingStepsPasta( DetailedMenuManager manager) {
        this.manager=manager;

    }
    public void takeCookingSteps(int detailedMenu) {
        cookEntire(manager.selectDetailedMenu(detailedMenu));
    }
    private void cookEntire(FoodPart foodPart){
        foodPart.preCook();
        foodPart.cook();
        foodPart.postCook();
    }
    public void printDetailedMenu(){
        manager.printDetailedMenu();
    }

}
```

- CookingStepsPasta는 CookingSteps를 상속받아서 구현됨
- 요리의 단계를 정하는 책임을 가지고 있으며, 정한 요리를 통해서 요리를 수행한다.
- 디테일한 Menu를 관리해주는 DetailedMenuManager로 selectDetailedMenu의 책임과 printDetailedMenu의 책임을 위임함
- FoodPart는 각 요리에 대한 추상화를 통해서 인터페이스화→ OCP원칙, DIP원칙을 지킴
- 또한 DetailedMenuManager도 `PastaManager`, `PizzaManager`, `SteakManager`  를 추상화하여 인터페이스화하였음→ OCP원칙, DIP원칙을 지킴

**PastaManager클래스**

```java
public class PastaManager implements DetailedMenuManager {
    public PastaManager(){

    }
    public FoodPart selectDetailedMenu(int detailedMenu){
        return switch (detailedMenu) {
            case 1 -> new TomatoPastaPart("Tomato", new String[]{"Tomato", "Noodle"});
            case 2 -> new CreamPastaPart("Cream", new String[]{"Cream", "Noodle"});
            case 3 -> new OilPastaPart("Oil", new String[]{"Oil", "Noodle"});
            default -> throw new RuntimeException("This Menu was not in my Restaurant");
        };
    }
    public void printDetailedMenu(){
        printSelectMenu(MENU_DETAIED_PASTA_NOTICE, MENU_DETAIED_PASTA_SELECTED);

    }
    private void printSelectMenu(String notice, String selectedMenu) {
        System.out.println(notice);
        System.out.println(selectedMenu);
    }
}
```

- 이 클래스 내에서 디테일 한 메뉴 즉, 파스타 메뉴 내에서의 메뉴를 정해주고, 출력하는 책임을 가지고 있다.
- `DetailedMenuManager`를 상속받아서 구현된 것임

**PastaPart 추상 클래스**

```java
public abstract class PastaPart implements FoodPart {
    protected String[] untensils = {"Pan", "Grill", "Tongs"};
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
```

- 이 클래스는  `FoodPart` 인터페이스를 구현해서 만든 클래스 로써,파스타의 요리 전,요리 중, 요리 후 수행하는 즉 전체 요리 과정에서 수행하는 것들에 대한 책임이 있다.
- 어떤 요리과정을 사용하는지는 `CookingSteps`에서 책임을 가지고 있다.

**CreamPastaPart클래스** 

```java
public class CreamPastaPart extends PastaPart {
    public CreamPastaPart(String type,String[] ingredients) {
        this.type = type;
        this.ingredients = ingredients;
    }
}
```

- 이 클래스를 통해서 디테일한 메뉴에 대한 type,ingredients를 통해서 메뉴를 생성하는 책임을 가지고 있다.
- 오버라이딩을 활용해서 기존의 코드 수정 없이 각 메뉴에 대한 구현이 가능하다.→  OCP

**Menu 패키지**

![image](https://github.com/user-attachments/assets/f896dc55-5daf-4c6c-8f0a-a39a302b4b72)


- 콘솔창에 나오는 UI의 컨텐츠는 여기서 각 책임별로 나누어서 관리함

위 클래스들은 각각의 메뉴들에 대해서 모두 구현되어 있음
