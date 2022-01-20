package staff;

import exception.LackOfMoneyException;
import exception.NotMenuException;
import food.Food;
import place.Hall;

import java.util.Scanner;

public class Waiter extends Staff{

    final static Scanner SC = new Scanner(System.in);

    @Override
    public String getKorJobName() {
        return "웨이터";
    }

    //주문받기
    public void getOrder(Hall hall) {
        hall.printMenu();
        System.out.println("주문하실 메뉴명을 입력해주세요. 취소를 입력하면 종료됩니다.");
        System.out.print(">");
        final String orderedName = SC.nextLine();
        if ("취소".equals(orderedName)) {
            System.out.println("이용해주셔서 감사합니다.");
            return;
        }
        try {
            Food orderedFood = hall.doOrder(orderedName);
            payForFood(hall, orderedFood);
        } catch (NotMenuException e) {
            System.out.println(e.getMessage());
            getOrder(hall);
        }
    }
    //주문 금액에 따른 돈 받기
    public void payForFood(Hall hall, Food food) {
        System.out.printf("%s(을)를 주문하였습니다. %d원을 지불해주세요.\n", food.getName(), food.getCost());
        System.out.println("(*마이너스 금액을 입력하면 주문이 취소됩니다.)");
        System.out.print(">");
        try {
            final int payment = Integer.parseInt(SC.nextLine());
            doCalculate(hall, food, payment);
        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 것을 입력하셨습니다.");
        } catch (LackOfMoneyException e) {
            System.out.println("다시 지불해주세요.");
            payForFood(hall, food);
            return;
        }
        getOrder(hall);
    }

    //받은 돈 계산해서 거스름돈 줘야하면 주고 가게 총 매출에 더하기
    public void doCalculate(Hall hall, Food food, int payment) throws LackOfMoneyException {
        if (payment == food.getCost()) {
            System.out.println("주문이 완료되었습니다. 이용해주셔서 감사합니다.");
        } else if (payment > food.getCost()) {
            System.out.printf("%d원을 거슬러 드렸습니다. 이용해주셔서 감사합니다.\n", payment - food.getCost());
        } else if (payment < 0){
            System.out.println("주문이 취소되었습니다.");
            return;
        } else {
            System.out.printf("%d원이 부족합니다.\n", food.getCost() - payment);
            throw new LackOfMoneyException();
        }

        hall.addCash(food.getCost());
        System.out.printf("현재 총 매출액 : %d원\n", hall.getCash());
    }
}
