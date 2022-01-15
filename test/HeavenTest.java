import exception.LackOfMoneyException;
import exception.NotMenuException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * - 주문 추가
 *     - 메뉴는 상수로 구성 되어 있습니다.
 *     - 메뉴 외에 있는 주문은 받지 않습니다.
 *     - 주문을 추가하고 해당 손님의 주문의 합계를 볼 수 있게 합니다.
 */
public class HeavenTest {

    @BeforeAll
    static void beforeTest() {
        KimbobHeaven.prepareMenu();
    }

    @Test
    void menuTest() {
        assertNotEquals(0, KimbobHeaven.MENU.size());
        KimbobHeaven.printMenu();
        System.out.println("메뉴 테스트 성공");
    }

    @Test
    void orderTest() throws NotMenuException {
        KimbobHeaven.prepareMenu();
        Assertions.assertThrows(Exception.class, () -> {
            KimbobHeaven.doOrder("없는메뉴");
        });

        assertEquals(KimbobHeaven.MENU.get(0), KimbobHeaven.doOrder(KimbobHeaven.MENU.get(0).getName()));
        System.out.println("주문 테스트 성공");
    }

    @Test
    void calculateTest() throws LackOfMoneyException {
        KimbobHeaven.prepareMenu();
        Assertions.assertThrows(LackOfMoneyException.class, () -> {
            KimbobHeaven.doCalculate(KimbobHeaven.MENU.get(0), 10);
        });

        KimbobHeaven.doCalculate(KimbobHeaven.MENU.get(0), KimbobHeaven.MENU.get(0).getCost());
        KimbobHeaven.doCalculate(KimbobHeaven.MENU.get(1), KimbobHeaven.MENU.get(1).getCost() + 3000);

        final int expectCash = KimbobHeaven.MENU.get(0).getCost() + KimbobHeaven.MENU.get(1).getCost();
        assertEquals(expectCash, KimbobHeaven.getCASH());
        System.out.println("계산 테스트 성공");
    }

}
