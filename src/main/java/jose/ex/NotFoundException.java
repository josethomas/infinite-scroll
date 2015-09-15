package jose.ex;

/**
 * Thrown when an item is not found.  this results in a 404 response when used
 * in the context of a RESTful interface.
 * @author jose thomas
 *
 */
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
