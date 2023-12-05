package app.sinulingga.productsservice.utility;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean isEmpty(Object object) {
        if (object == null) return true;
        if (object instanceof List<?> && ((List<?>) object).isEmpty()) return true;
        if (object instanceof Set<?> && ((Set<?>) object).isEmpty()) return  true;
        if (object instanceof String && (((String) object).trim().isEmpty())) return true;
        return false;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
