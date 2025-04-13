import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException; // Import the InvalidKeyException class
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MD5MACExample {

    public static void main(String[] args) {
        try {
            // Generate a secret key for the MAC algorithm
            String secretKey = "SecretKey123"; // Replace with your secret key

            // Message to be authenticated
            String message = "Hello, this is a secret message.";

            // Generate MAC for the message using MD5
            byte[] mac = generateMD5MAC(secretKey, message.getBytes());

            // Print the original message and MAC
            System.out.println("Original Message: " + message);
            System.out.println("Generated MAC: " + bytesToHex(mac));

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    // Function to generate a MAC for the given message using MD5
    private static byte[] generateMD5MAC(String secretKey, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] keyBytes = secretKey.getBytes();
        Key key = new SecretKeySpec(keyBytes, "HmacMD5");

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);

        return mac.doFinal(message);
    }

    // Function to convert byte array to hex string for better display
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}

