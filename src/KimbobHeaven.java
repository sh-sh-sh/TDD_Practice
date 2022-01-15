import exception.LackOfMoneyException;
import exception.NotMenuException;
import food.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class KimbobHeaven {

    final static List<Food> MENU = new ArrayList<>();
    final static Scanner SC = new Scanner(System.in);
    private static int CASH = 0;

    public static void main(String[] args) {
        prepareMenu();
        getOrder();
    }

    public static int getCASH() {
        return CASH;
    }

    public static void setCASH(int CASH) {
        KimbobHeaven.CASH = CASH;
    }

    public static void prepareMenu() {
        MENU.add(new Food("김밥", 2500));
        MENU.add(new Food("라면", 3500));
    }

    public static void printMenu() {
        MENU.forEach(food -> System.out.println(food.toString()));
    }

    private static void getOrder() {
        printMenu();
        System.out.println("주문하실 메뉴명을 입력해주세요. 취소를 입력하면 종료됩니다.");
        System.out.print(">");
        final String orderedName = SC.nextLine();
        if ("취소".equals(orderedName)) {
            System.out.println("이용해주셔서 감사합니다.");
            return;
        }
        try {
            Food orderedFood = doOrder(orderedName);
            getPayment(orderedFood);
        } catch (NotMenuException e) {
            System.out.println(e.getMessage());
            getOrder();
        }
    }

    public static Food doOrder(String orderedName) throws NotMenuException {
        final Optional<Food> orderedFoodOptional = MENU.stream().filter(food -> food.getName().equals(orderedName)).findFirst();
        if (orderedFoodOptional.isPresent()) {
            return orderedFoodOptional.get();
        } else {
            throw new NotMenuException();
        }
    }

    private static void getPayment(Food food) {
        System.out.printf("%s(을)를 주문하였습니다. %d원을 지불해주세요.\n", food.getName(), food.getCost());
        System.out.println("(*마이너스 금액을 입력하면 주문이 취소됩니다.)");
        System.out.print(">");
        try {
            final int payment = Integer.parseInt(SC.nextLine());
            doCalculate(food, payment);
        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 것을 입력하셨습니다.");
        } catch (LackOfMoneyException e) {
            System.out.println("다시 지불해주세요.");
            getPayment(food);
            return;
        }
         getOrder();
    }

    public static void doCalculate(Food food, int payment) throws LackOfMoneyException {
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

        CASH += food.getCost();
        System.out.printf("현재 총 매출액 : %d원\n", CASH);
    }
}
