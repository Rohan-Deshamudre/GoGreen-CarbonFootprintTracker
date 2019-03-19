package application.server;

import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;

public class Authentication {

    //Register a new user and add the salt and hashed password to the database

    /**
     * Registers the username and password.
     * @param username the username
     * @param password the password
     * @return returns the result
     * @throws Exception exception
     */
    public boolean Register(String username, String password) throws Exception {

        byte[] salt = generateSalt();
        String pwd = hashSimple(password, salt);

        return false;
    }

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);
        return bytes;
    }

    public static String hashSimple(String password, byte[] salt) throws Exception{
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        return String.valueOf(hash);
    }

}
