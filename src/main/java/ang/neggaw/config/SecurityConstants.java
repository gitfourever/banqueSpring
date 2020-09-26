package ang.neggaw.config;

public class SecurityConstants {

    public static String SECRET = "gitfourever";
    public static long EXPIRATION_TIME = 60 * 60 * 24 * 10 * 1000; // 10 days
    public static String PREFIX_TOKEN = "Bearer ";
    public static String HEADER_TOKEN = "Authorization";
}
