package place;

import exception.NotMenuException;
import food.Food;
import food.Kimbob;
import food.Ramyeon;
import food.ingredient.MainIngredient;
import staff.Staff;
import staff.Waiter;

import java.util.ArrayList;
import java.util.List;

public class Hall extends Place{

    private final List<Food> menu = new ArrayList<>();
    private int cash = 0;

    @Override
    Class<? extends Staff> getCorrectStaff() {
        return Waiter.class;
    }

    public Waiter getWaiter(){
        return (Waiter) this.staff;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public int getCash() {
        return cash;
    }

    public void addCash(int cash) {
        this.cash += cash;
    }

    public void prepare() {
        menu.add(new Kimbob(MainIngredient.참치));
        menu.add(new Ramyeon(MainIngredient.치즈));
    }

    //메뉴판 전체 돌면서 출력
    public void printMenu() {
        menu.forEach(food -> System.out.println(food.toString()));
    }


    //주문받은 메뉴가 메뉴판에 존재하는지 확인해서 있으면 해당 음식 리턴, 없으면 예외 발생시키기
    public Food doOrder(String orderedName) throws NotMenuException {
        return menu.stream().filter(food -> food.getName().equals(orderedName)).findFirst().orElseThrow(() -> new NotMenuException());
    }


}
