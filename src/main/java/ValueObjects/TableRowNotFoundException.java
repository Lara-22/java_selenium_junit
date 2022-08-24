package ValueObjects;

public class TableRowNotFoundException extends Exception{
    public TableRowNotFoundException() {
    }

    public TableRowNotFoundException(String message) {
        super(message);
    }

    public TableRowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableRowNotFoundException(Throwable cause) {
        super(cause);
    }


}
