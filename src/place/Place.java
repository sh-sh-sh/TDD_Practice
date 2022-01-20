package place;

import exception.IncorrectStaffException;
import staff.Staff;

public abstract class Place {
    protected Staff staff;

    public void setStaff(Staff staff) throws IncorrectStaffException {
        if (isNotCorrectStaff(staff)) {
            throw new IncorrectStaffException();
        }
        this.staff = staff;
    }

     abstract Class<? extends Staff> getCorrectStaff();

    protected boolean isNotCorrectStaff(Staff t) {
        return t.getClass() != getCorrectStaff();
    }
}
