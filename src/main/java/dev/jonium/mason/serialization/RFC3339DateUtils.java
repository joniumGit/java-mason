package dev.jonium.mason.serialization;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


/**
 * Utilities for handling dates present in {@link dev.jonium.mason.MasonError}
 */
public final class RFC3339DateUtils {

    private RFC3339DateUtils() {
    }

    private static final String standardSeparator = "T";
    private static final String unknownOffset = "-00:00";
    private static final String unknownOffsetReplacement = "Z";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static Boolean isUnknownOffset(String value) {
        return value.contains(unknownOffset);
    }

    private static String preprocess(String value) {
        var standard = value.substring(0, 10)
                       + standardSeparator
                       + value.substring(11);
        if (isUnknownOffset(value)) {
            standard = standard.replace(unknownOffset, unknownOffsetReplacement);
        }
        return standard;
    }

    public static ZonedDateTime readUnknownAt(String value, TimeZone tz) throws IllegalArgumentException {
        if (isUnknownOffset(value)) {
            var atUTC = preprocess(value);
            atUTC = atUTC.substring(0, atUTC.length() - 1);
            return ZonedDateTime.of(LocalDateTime.parse(atUTC), tz.toZoneId());
        } else {
            throw new IllegalArgumentException("Not an unknown offset");
        }
    }

    public static ZonedDateTime read(String value) {
        return ZonedDateTime.from(formatter.parse(preprocess(value)));
    }

    public static String write(ZonedDateTime time) {
        return formatter.format(time);
    }

    public static String writeOffsetUnknown(ZonedDateTime time) {
        var s = formatter.format(time);
        return s.substring(
                0,
                s.endsWith("Z")
                ? s.length() - 1
                : Math.max(s.lastIndexOf("+"), s.lastIndexOf("-"))
        ) + unknownOffset;
    }

}
