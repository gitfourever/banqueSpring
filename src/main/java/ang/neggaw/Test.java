package ang.neggaw;

import net.minidev.json.JSONObject;

import java.util.Base64;

public class Test {

    public static void main(String[] args) {

        String emailCoded = "ImNsaWVudDAwMUBlbWFpbC5jb20i";
        String emailDecoded = "client001@email.com";

        String s = "ZGVtb0BkZW1vLmNvbQ==";

        JSONObject jsonObject = new JSONObject();

        System.out.println(jsonObject.toJSONString());
        System.out.println(jsonObject.getAsString(emailDecoded));



        String encoded = new String(Base64.getEncoder().encode(emailDecoded.getBytes()));
        String decoded = new String(Base64.getDecoder().decode(encoded));
        System.out.println("Base64 => " + emailDecoded + " : " + encoded + " : " + decoded);
    }
}
