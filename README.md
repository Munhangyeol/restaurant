# 레스토랑 예제

## 목적
철수는 스테이크, 피자, 파스타라는 양식을 전문으로 하는 레스토랑을 운영하는 중입니다.  
이 프로젝트는 레스토랑에 들어가는 여러 기능들을 구현하면서 **SOLID 원칙**과 **Clean Code**를 적용해보는 것을 목표로 합니다.

---

# 버전
## V1
### 문제 요약

- 철수는 스테이크,피자,파스타라는 양식을 전문으로 하는 레스토랑을 차림
- 각 요리는 다음과 같은 종류를 가짐
    - 스테이크: T-Born Steak, Sir-loin Steak, Rib-eye Steak
    - 파스타: Tomato-Pasta,Cream-Pasta, Oil-Pasta
    - 피자: Peperoni-Pizza,Cheeze-Pizza,Gorgonzola-Pizza
    - 각 요리 별로 조리 기구는 공유하지만, 재료는 각각 독립적으로 존재함
        - 스테이크 조리 기구: Pan, Grill,Tongs(집게)
        - 파스타:Pot ,Colander(소쿠리)
        - 피자: Mixing Bowl, Oven
    - 또한 각 요리들은 요리 전, 요리 중,요리 후라는 과정이 있으며, 각각의 세부 과정은 아래와 같음
        - 요리 전: ~ 재료와 ~ 요리 기구를 준비 중입니다.   출력
        - 요리 중: ~ 재료와 ~ 요리 기구를 이용해서 ~ 요리를 요리 중 입니다.        출력
        - 요리 후: ~ 요리가 완료 되었습니다! 맛있게 드세요.

### 문제 요구사항

- 사용자로 하여금 스테이크,파스타,피자 중 어떤 음식을 먹고 싶은 지를 물어보기
- 각 파트 중 다시 어떤 요리를 먹고 싶은 지를 물어보기
- 해당 요리 요리 전, 요리 중, 요리 후를 출력하기

위의 과정을 5번 반복하기

### 프로그래밍 요구사항

- v1이므로 후에 어떤 설계가 올지 모른다. 따라서 solid원칙에 입각한 설계를 요구함
- 클린 코드에서 말하는 깨끗한 코드의 원칙을 따른다.
    - 코드에 대한 테스트 코드 역시 필수

### Result
[V1-과정 Repository 링크](https://github.com/Munhangyeol/restaurant/blob/main/v1/readme.md)

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
- 여기서 메뉴의 내용물은 MenuConsol 패키지내의 클래스내에 static 상수들로 정의가 되어있어서, 해당 내용물에 대한 책임은 UI클래스에서 담당하지 않고, MenuConsol내의 상수들이 담당한다.

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

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/a2dae048-db02-4188-9d60-447f1709ad77/35cbba04-c303-4b8c-b9b3-c643b1968da0/image.png)

- 콘솔창에 나오는 UI의 컨텐츠는 여기서 각 책임별로 나누어서 관리함

위 클래스들은 각각의 메뉴들에 대해서 모두 구현되어 있음

### **설계 특징**

- OCP (Open-Closed Principle): 새로운 메뉴나 세부 메뉴를 추가할 때 기존 코드를 수정하지 않고 확장 가능
- DIP (Dependency Inversion Principle): CookingSteps, DetailedMenuManager, FoodPart는 인터페이스를 통해 추상화되어 의존성을 줄임
- 책임 분리: UI, 메뉴 관리, 요리 단계 등이 각각 별도의 클래스로 분리되어 유지보수가 용이
- 재사용성: CookingSteps와 DetailedMenuManager는 다양한 요리에 대해 재사용 가능.
