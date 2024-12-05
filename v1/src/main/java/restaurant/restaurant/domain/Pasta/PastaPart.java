package restaurant.restaurant.domain.Pasta;

import java.util.Arrays;

//PastaPart만 독립적으로 존재하지는 않으므로, 추상 클래스로 정의함
public abstract class PastaPart {
    protected String[] untensils = {"Pan", "Grill", "Tongs"};

}
