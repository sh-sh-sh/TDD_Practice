package food;

import food.ingredient.MainIngredient;

public abstract class VariationFood extends Food{

    public VariationFood(MainIngredient mainIngredient) {
        this.name = mainIngredient.name() + getKorName();
        this.cost = mainIngredient.getPrice() + getBasicPrice();
    }

    protected abstract String getKorName();

    protected abstract int getBasicPrice();
}
