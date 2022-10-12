package africa.semicolon.lumExpress.util;

import java.security.SecureRandom;

public class LumExpressUtil {
    public static String generateToken(){
        SecureRandom secureRandom = new SecureRandom();
       int tokenNum = secureRandom.nextInt(1000, 9999);
        return String.valueOf(tokenNum);
    }
}
