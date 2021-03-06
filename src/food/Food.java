package food;

public class Food {
    protected String name;
    protected int cost;

    public Food(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Food() {
    }

    @Override
    public String toString() {
        return String.format("[이름 : %s, 가격 : %d]", this.name, this.cost);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

}
