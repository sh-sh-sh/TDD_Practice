package place;

import exception.IncorrectStaffException;
import staff.Chef;
import staff.Staff;

public class Kitchen extends Place{

    @Override
    Class<? extends Staff> getCorrectStaff() {
        return Chef.class;
    }

    @Override
    public void setStaff(Staff staff) throws IncorrectStaffException {
        if (isNotCorrectStaff(staff)) {
            throw new IncorrectStaffException();
        }
    }

    public Chef getChef(){
        return (Chef) this.staff;
    }

    public void prepare() {

    }
}
