package sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * This class using SHA-512 algorithm generate the hash value of the text Used
 * for validating the user password
 *
 * @author haris
 */
public class SHA {
    /**
     * 
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException 
     */
    private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");

        byte[] salt = new byte[16];
        sr.nextBytes(salt);

        return Arrays.toString(salt);

    }

    /**
     * 
     * @param passwordToHash
     * @param salt
     * @return 
     */
    private static String get_SHA_512_SecurePassword(String passwordToHash, String salt) {

        String generatedPass = null;
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());

            byte[] bytes = md.digest(passwordToHash.getBytes());

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

            }
            generatedPass = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPass;
    }
    /**
     * 
     * @param userPassword
     * @param storedPassHash
     * @return 
     */
    public static boolean validate(String userPassword, String storedPassHash) {
        try {

            String salt = getSalt();
            String userPassHash = get_SHA_512_SecurePassword(userPassword, salt);

            if (storedPassHash.equals(userPassHash)) {
                return true;
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException exp) {
            System.out.println(exp.getMessage());
        }
        return false;
    }

    /**
     *
     * This hash is generated using SHA-512 public key i.e salt is email id of
     * user private key i.e password of user
     * why are we using email as salt :
     * - Hash is depend on the salt if salt is changing again and again then we 
     * don't get the correct hash 
     * - To get hash we used email that password and email are depend on each other 
     * 
     * @param userPassword
     * @param email
     * @return
     */
    public static String getHash(String userPassword, String email) {
        return get_SHA_512_SecurePassword(userPassword, email);

    }
}
