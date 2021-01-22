package enums;

import org.apache.commons.lang3.StringUtils;

public final class EnumUtils {

    private EnumUtils() {
    }

    public static <T extends Enum<T>> T asEnum(final String value, final Class<T> type) {
        return T.valueOf(type, value.toUpperCase().replace(StringUtils.SPACE, "_"));
    }

}
