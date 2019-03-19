package server;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Authentication {

    //Register a new user and add the salt and hashed password to the database

    /**
     * Registers the username and password.
     * @param username the username
     * @param password the password
     * @return returns the result
     * @throws Exception exception
     */
    public boolean register(String username, String password) throws Exception {

        byte[] salt = generateSalt();
        String pwd = hashSimple(password, salt);

        return false;
    }

    /**
     * Generate the salt.
     * @return returns the salt of the user
     */

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * Generate a hash.
     * @param password the password of the user
     * @param salt the salt of the user
     * @return returns the method
     * @throws Exception thorws an exception
     */

    public static String hashSimple(String password, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f1 = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f1.generateSecret(spec).getEncoded();
        return String.valueOf(hash);
    }

}
