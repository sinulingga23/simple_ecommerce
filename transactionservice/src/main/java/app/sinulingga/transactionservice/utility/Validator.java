package app.sinulingga.transactionservice.utility;

import java.util.List;
import java.util.Set;

public class Validator {
    public static boolean isEmpty(Object object) {
        if (object == null) return true;
        if (object instanceof List<?> && ((List<?>) object).isEmpty()) return true;
        if (object instanceof Set<?> && ((Set<?>) object).isEmpty()) return  true;
        if (object instanceof String && (((String) object).trim().isEmpty())) return true;
        return false;
    }
}
