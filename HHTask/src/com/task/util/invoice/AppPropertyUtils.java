package com.task.util.invoice;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppPropertyUtils {

    private static String key;
    private static String secret;

    public static String getKey() {
        return key;
    }

//    @Value("${key}")
    public void setKey(String key) {
        AppPropertyUtils.key = key;
    }

    public static String getSecret() {
        return secret;
    }

//    @Value("${secret}")
    public void setSecret(String secret) {
        AppPropertyUtils.secret = secret;
    }
}
