import exception.IncorrectStaffException;
import exception.LackOfMoneyException;
import exception.NotMenuException;
import food.Food;
import food.Kimbob;
import food.Ramyeon;
import food.ingredient.MainIngredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import place.Hall;
import place.Kitchen;
import staff.Chef;
import staff.Waiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * - 주문 추가
 *     - 메뉴는 상수로 구성 되어 있습니다.
 *     - 메뉴 외에 있는 주문은 받지 않습니다.
 *     - 주문을 추가하고 해당 손님의 주문의 합계를 볼 수 있게 합니다.
 */
public class HeavenTest {
    
    final static KimbobHeaven heaven = new KimbobHeaven();

    @BeforeAll
    static void beforeTest() {
        heaven.open();
    }

    @Test
    void menuTest() {
        //총 메뉴의 수가 0이 아니면 성공
        assertNotEquals(0, heaven.getHall().getMenu().size());
        heaven.getHall().printMenu();
        System.out.println("메뉴 테스트 성공");
    }

    @Test
    void orderTest() throws NotMenuException {
        Food firstMenu = heaven.getHall().getMenu().get(0);
        //없는 메뉴를 주문하면 익셉션 발생
        Assertions.assertThrows(NotMenuException.class, () -> {
            heaven.getHall().doOrder("환상의김치볶음밥");
        });

        //첫번째 메뉴의 음식과 주문한 음식이 동일한지 체크
        assertEquals(firstMenu, heaven.getHall().doOrder(firstMenu.getName()));
        System.out.println("주문 테스트 성공");
    }

    @Test
    void calculateTest() throws LackOfMoneyException {
        Food firstMenu = heaven.getHall().getMenu().get(0);
        Food secondMenu = heaven.getHall().getMenu().get(1);
        //돈을 모자라게 지불하면 예외 발생
        Assertions.assertThrows(LackOfMoneyException.class, () -> {
            heaven.getHall().getWaiter().doCalculate(heaven.getHall(), firstMenu, 10);
        });

        //첫번째 메뉴와 두번째 메뉴 주문
        heaven.getHall().getWaiter().doCalculate(heaven.getHall(), firstMenu, firstMenu.getCost());
        heaven.getHall().getWaiter().doCalculate(heaven.getHall(), secondMenu, secondMenu.getCost() + 3000);

        //첫번째메뉴+두번째메뉴 값 합이 가게에 있는 총 매출과 동일한지 체크
        final int expectCash = firstMenu.getCost() + secondMenu.getCost();
        assertEquals(expectCash, heaven.getHall().getCash());
        System.out.println("계산 테스트 성공");
    }

    @Test
    void menuNameAndCostTest() {
        Kimbob KimchiKimbob = new Kimbob(MainIngredient.김치);
        assertEquals("김치김밥", KimchiKimbob.getName());
        assertEquals(2500, KimchiKimbob.getCost());

        Ramyeon beefRamyeon = new Ramyeon(MainIngredient.소고기);
        assertEquals("소고기라면", beefRamyeon.getName());
        assertEquals(5500, beefRamyeon.getCost());
    }

    /**
     * 웨이터는 홀에 접근이 가능하다.
     * 셰프는 주방에 접근이 가능하다.
     */
    @Test
    void staffTest() {
        Kitchen kitchen = new Kitchen();
        Hall hall = new Hall();

        //웨이터가 주방에 접근하면 익셉션
        Assertions.assertThrows(IncorrectStaffException.class, () -> {
            kitchen.setStaff(new Waiter());
        });
        //셰프가 홀에 접근하면 익셉션
        Assertions.assertThrows(IncorrectStaffException.class, () -> {
            hall.setStaff(new Chef());
        });

        Assertions.assertDoesNotThrow(() -> {
            kitchen.setStaff(new Chef());
            hall.setStaff(new Waiter());
        });
    }

    /**
     * 홀에 주문을 하면 키친에서 홀로 준 음식을 홀에서 리턴한다.
     */

//    @Test
//    void kitchenTest() throws NotMenuException {
//        heaven.getHall().doOrder(heaven.getHall().getMenu().get(0).getName());
//        assertEquals(heaven.getHall().getMenu().get(0), heaven.getHall().serve());
//    }
}
