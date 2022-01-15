package exception;

public class NotMenuException extends Exception{

    public NotMenuException() {
        super("존재하지 않는 메뉴입니다.");
    }

}
