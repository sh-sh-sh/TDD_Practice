package food;

import food.ingredient.MainIngredient;

public class Kimbob extends VariationFood{

    private static final int BASIC_PRICE = 2000;
    private static final String KOR_NAME = "김밥";

    public  Kimbob(MainIngredient mainIngredient) {
        super(mainIngredient);
    }

    @Override
    protected String getKorName() {
        return KOR_NAME;
    }

    @Override
    protected int getBasicPrice() {
        return BASIC_PRICE;
    }
}
