package food.ingredient;

public enum MainIngredient {
    기본(0),
    치즈(500),
    김치(500),
    어묵(1000),
    참치(1000),
    소고기(2000);

    private final int price;

    MainIngredient(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
