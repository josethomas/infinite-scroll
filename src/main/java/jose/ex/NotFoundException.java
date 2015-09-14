package jose.ex;

public class NotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -4137000584738946384L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
