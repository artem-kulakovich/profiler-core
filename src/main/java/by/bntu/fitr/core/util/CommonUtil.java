package by.bntu.fitr.core.util;

import java.util.List;

public class CommonUtil {

    public static boolean isValueFromPropertiesCorrected(final String value, final List<String> whiteListValues) {
        for (String whiteListValue : whiteListValues) {
            if (whiteListValue.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
