package jose.ex;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

/**
 * Utility methods to covert data between various formats.  Primary function is to marshall and unmarshall objects <-> json.
 * Other general purposes conversion methods go in here as well.
 * @author jose thomas
 *
 */
public class ConvertUtils {

    private ObjectMapper objectMapper;

    @Inject
    public ConvertUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Attempt to convert a json string to an object.
     * @param payload
     * @param clzz type of object to covert to.
     * @return object representation of the json
     * @throws IOException
     */
    public <T> T unmarshall(String payload, Class<T> clzz) throws IOException {
        return objectMapper.readValue(payload, clzz);

    }

    /**
     * Convert an object to json string.
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public String marshall(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Convert a integer represented by a string to an integer.
     * @param s input string
     * @return an optional integer
     * @throws NumberFormatException if the string cannot be converted to an integer.
     */
    public Optional<Integer> toPositiveInteger(String s) {
        Integer i;
        if (Objects.isNull(s) || s.length() < 1) {
            i = null;
        } else {
            i = Integer.valueOf(s);
            if (i < 0) {
                throw new IllegalArgumentException("Positive number expected, instead got "+s);
            }
        }
        return Optional.ofNullable(i);
    }
    /**
     * gson implementation public static String toJson(Object object) { return
     * new Gson().toJson(object); }
     * 
     * public static ResponseTransformer json() { return Utils::toJson; }
     */
}
