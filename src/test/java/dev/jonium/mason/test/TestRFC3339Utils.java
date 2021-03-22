package dev.jonium.mason.test;

import dev.jonium.mason.serialization.RFC3339DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

@DisplayName("RFC3339 Dates")
public class TestRFC3339Utils {

    private final Collection<String> hasNanos = List.of(
            "2020-01-01 12:00:02.000000001Z",
            "2020-01-01 13:00:02.000000001-00:00",
            "2020-01-01 10:00:02.000000001+02:00"
    );

    private final Collection<String> hasMillis = List.of(
            "2020-01-01 12:00:02.0001Z",
            "2020-01-01 13:00:02.0001-00:00",
            "2020-01-01 11:00:02.0001+01:00"
    );

    private final Collection<String> misc = List.of(
            "2020-01-01 12:00:02Z",
            "2020-01-01 13:00:02-00:00",
            "2020-01-01 13:00:02+01:00",
            "2020-01-01Ö12:00:02Z",
            "2020-01-01T12:00:02Z"
    );

    @Test
    @DisplayName("Test no throw")
    public void noThrow() {
        for (var c : List.of(misc, hasNanos, hasMillis)) {
            Assertions.assertDoesNotThrow(() -> c.forEach(RFC3339DateUtils::read));
        }

    }

    @Test
    @DisplayName("Assert equals misc")
    public void equalingMisc() {
        var time = ZonedDateTime.parse("2021-01-01T12:00:02Z");
        Assertions.assertDoesNotThrow(
                () -> misc
                        .stream()
                        .map(s -> RFC3339DateUtils.isUnknownOffset(s)
                                  ? RFC3339DateUtils.readUnknownAt(s, TimeZone.getTimeZone("UTC+1"))
                                  : RFC3339DateUtils.read(s)
                        ).allMatch(time::equals)
        );
    }

    @Test
    @DisplayName("Assert equals millis")
    public void equalingMillis() {
        var time = ZonedDateTime.parse("2021-01-01T12:00:02.0001Z");
        Assertions.assertDoesNotThrow(
                () -> hasMillis
                        .stream()
                        .map(s -> RFC3339DateUtils.isUnknownOffset(s)
                                  ? RFC3339DateUtils.readUnknownAt(s, TimeZone.getTimeZone("UTC+1"))
                                  : RFC3339DateUtils.read(s)
                        ).allMatch(time::equals)
        );
    }

    @Test
    @DisplayName("Assert equals nanos")
    public void equalingNanos() {
        var time = ZonedDateTime.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse("2021-01-01T10:00:02.000000001+02:00"));
        Assertions.assertDoesNotThrow(
                () -> hasNanos
                        .stream()
                        .map(s -> RFC3339DateUtils.isUnknownOffset(s)
                                  ? RFC3339DateUtils.readUnknownAt(s, TimeZone.getTimeZone("UTC+1"))
                                  : RFC3339DateUtils.read(s)
                        ).allMatch(time::equals)
        );
    }

    @Test
    @DisplayName("Test writing")
    public void writing() {
        var date = "2021-01-01T10:00:02.000000001+02:12";
        var time = ZonedDateTime.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(date));
        var time2 = RFC3339DateUtils.read(date);
        Assertions.assertEquals(time, time2);
        Assertions.assertEquals(date, time.toString());
        Assertions.assertEquals(time.toString(), time2.toString());
        Assertions.assertEquals(time.toString(), RFC3339DateUtils.write(time));
        Assertions.assertEquals(date, RFC3339DateUtils.write(RFC3339DateUtils.read(date)));
        var unknown = "2021-01-01T10:00:02.000000001-00:00";
        Assertions.assertTrue(RFC3339DateUtils.isUnknownOffset(unknown));
        Assertions.assertEquals(unknown, RFC3339DateUtils.writeOffsetUnknown(RFC3339DateUtils.readUnknownAt(
                unknown,
                TimeZone.getTimeZone("UTC-10")
        )));
    }

    @Test
    @DisplayName("Test throws")
    public void doThrows() {
        var date = "2021-01-01T10:00:02.000000001+02:12";
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> RFC3339DateUtils.readUnknownAt(
                                        date,
                                        TimeZone.getDefault()
                                )
        );
    }


}
