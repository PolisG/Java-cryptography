package hash_function;

/**
 * Merkle–Damgård construction
 * 
 * message-digest algorithm
 * hash function producing a 128-bit hash value
 * digest size: 128-bits
 * block size: 512-bits
 * 
 * MD5 is prone to length extension attacks!
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;

/**
 *
 * @author Polis
 */
public class MD5 {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = checksum("../text.txt", md);
        System.out.println(bytesToHex(hashInBytes));
        
    }

    @SuppressWarnings("empty-statement")
    private static byte[] checksum(String filepath, MessageDigest md) throws IOException {
        
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md)) {
            while (dis.read() != -1) ;  //empty loop to clear the data
            md = dis.getMessageDigest();
        }
        return md.digest();
        
    }

    private static String bytesToHex(byte[] hashInBytes) {
        
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
        
    }
    
}