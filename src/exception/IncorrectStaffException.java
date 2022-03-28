package exception;

public class IncorrectStaffException extends Exception {
    public IncorrectStaffException() {
        super("올바르지 않은 직업입니다.");
    }
}
