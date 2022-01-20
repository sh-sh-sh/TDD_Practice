import exception.IncorrectStaffException;
import place.Hall;
import place.Kitchen;
import staff.Chef;
import staff.Waiter;

public class KimbobHeaven {

    private final Kitchen kitchen = new Kitchen();
    private final Hall hall = new Hall();

    public Kitchen getKitchen() {
        return kitchen;
    }

    public Hall getHall() {
        return hall;
    }

    public void open() {
        try {
            prepare();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void prepare() throws IncorrectStaffException {
        prepareStaff();
        kitchen.prepare();
        hall.prepare();
    }

    private void prepareStaff() throws IncorrectStaffException {
        kitchen.setStaff(new Chef());
        hall.setStaff(new Waiter());
    }

    public void doOrder() {
        this.hall.getWaiter().getOrder(this.hall);
    }
}
