package jose.ex;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

public class ConvertUtils {

    private ObjectMapper objectMapper;

    @Inject
    public ConvertUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T unmarshall(String payload, Class<T> clzz) throws IOException {
        return objectMapper.readValue(payload, clzz);

    }

    public String marshall(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * 
     * @param s
     * @return
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
