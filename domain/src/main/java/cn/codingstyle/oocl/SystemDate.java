package cn.codingstyle.oocl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class SystemDate {
    public static final long ONE_DAY = 86400 * 1000L;

    private SystemDate() {

    }

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static Supplier<Long> nowSupplier = System::currentTimeMillis;

    public static void setNowSupplier(Supplier<Long> nowSupplier) {
        SystemDate.nowSupplier = nowSupplier;
    }

    public static long now() {
        return nowSupplier.get();
    }

    public static String now(DateTimeFormatter formatter) {
        return Instant.ofEpochMilli(now()).atZone(ZoneId.systemDefault()).format(formatter);
    }

    public static String format(long time) {
        return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).format(DATE_FORMATTER);
    }

    public static String today() {
        return now(DATE_FORMATTER);
    }

    public static void setNowSupplier(String date) {
        setNowSupplier(() -> {
            LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        });
    }
}
