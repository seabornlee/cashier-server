package cn.codingstyle.server;

import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class RandomString {
    static Supplier<String> resultSupplier;
    private static final String SEED = "01234567890";

    public static void setResultSupplier(Supplier<String> resultSupplier) {
        RandomString.resultSupplier = resultSupplier;
    }

    public static String randomUniqueString(int len) {
        if (resultSupplier != null) {
            return resultSupplier.get();
        }
        return UUID.randomUUID() + getRandomString(len);
    }

    private static String getRandomString(int len) {
        StringBuffer result = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            result.append(SEED.charAt(random.nextInt(SEED.length())));
        }
        return result.toString();
    }
}
