package food;

import food.ingredient.MainIngredient;

public class Ramyeon extends VariationFood{

    private static final int BASIC_PRICE = 3500;
    private static final String KOR_NAME = "라면";

    public Ramyeon(MainIngredient mainIngredient) {
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
