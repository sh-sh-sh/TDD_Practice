package place;

import staff.Chef;
import staff.Staff;

public class Kitchen extends Place{

    @Override
    Class<? extends Staff> getCorrectStaff() {
        return Chef.class;
    }

    public Chef getChef(){
        return (Chef) this.staff;
    }

    public void prepare() {

    }
}
