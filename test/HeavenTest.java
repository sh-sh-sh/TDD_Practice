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
        //총 메뉴의 수가 0이 아니면 성공
        assertNotEquals(0, KimbobHeaven.MENU.size());
        KimbobHeaven.printMenu();
        System.out.println("메뉴 테스트 성공");
    }

    @Test
    void orderTest() throws NotMenuException {
        //없는 메뉴를 주문하면 익셉션 발생
        Assertions.assertThrows(NotMenuException.class, () -> {
            KimbobHeaven.doOrder("환상의스페셜김치볶음밥");
        });

        //첫번째 메뉴의 음식과 주문한 음식이 동일한지 체크
        assertEquals(KimbobHeaven.MENU.get(0), KimbobHeaven.doOrder(KimbobHeaven.MENU.get(0).getName()));
        System.out.println("주문 테스트 성공");
    }

    @Test
    void calculateTest() throws LackOfMoneyException {
        //돈을 모자라게 지불하면 예외 발생
        Assertions.assertThrows(LackOfMoneyException.class, () -> {
            KimbobHeaven.doCalculate(KimbobHeaven.MENU.get(0), 10);
        });

        //첫번째 메뉴와 두번째 메뉴 주문
        KimbobHeaven.doCalculate(KimbobHeaven.MENU.get(0), KimbobHeaven.MENU.get(0).getCost());
        KimbobHeaven.doCalculate(KimbobHeaven.MENU.get(1), KimbobHeaven.MENU.get(1).getCost() + 3000);

        //첫번째메뉴+두번째메뉴 값 합이 가게에 있는 총 매출과 동일한지 체크
        final int expectCash = KimbobHeaven.MENU.get(0).getCost() + KimbobHeaven.MENU.get(1).getCost();
        assertEquals(expectCash, KimbobHeaven.getCASH());
        System.out.println("계산 테스트 성공");
    }

}
