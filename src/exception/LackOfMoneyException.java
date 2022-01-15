package exception;

public class LackOfMoneyException extends Exception {

    public LackOfMoneyException() {
        super("금액이 부족합니다.");
    }

}
