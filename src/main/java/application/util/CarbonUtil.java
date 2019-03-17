package application.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class CarbonUtil {

    public static final String AES_ENCRYPTION_SECRETKEY="Bar12345Bar12345";

    public static final String FOOD_OPTION1 = "Ate a vegetarian meal";
    public static final String FOOD_OPTION2 = "Bought from a biological store";

    private CarbonUtil (){

    }


    public static int getCarbonfootprint(String checkBoxValue){
        int carbonFootprint=0;
        if(FOOD_OPTION1.equalsIgnoreCase(checkBoxValue)){
            carbonFootprint=100;
        }
        else if(FOOD_OPTION2.equalsIgnoreCase(checkBoxValue)){
            carbonFootprint=50;
        }
        return carbonFootprint;
    }

    public static String encryptAESPassword(String password){
        String encryptedPassword="";
        try
        {
            // String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(AES_ENCRYPTION_SECRETKEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            encryptedPassword= new String(encrypted);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return encryptedPassword;
    }

    public static String decryptAESPassword(String encryptedPassword){
        String password="";

        try {
            Cipher cipher = Cipher.getInstance("AES");
            // decrypt the text
            Key aesKey = new SecretKeySpec(AES_ENCRYPTION_SECRETKEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(encryptedPassword.getBytes()));
            System.err.println(decrypted);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return password;
    }

//    public static void main(String[] args)
//    {
//        String encryptedPassword=encryptAESPassword("Rohan");
//        System.out.println(encryptedPassword);
//        String password=decryptAESPassword(encryptedPassword);
//        System.out.println(password);
//
//
//    }

}
