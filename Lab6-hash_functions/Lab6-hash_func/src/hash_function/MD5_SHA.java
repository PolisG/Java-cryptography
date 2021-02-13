package hash_function;

/**
 * Secure Hash Algorithm (SHA-1)
 * 
 * message-digest algorithm
 * hash function producing a 160-bit hash value
 * digest size: 160-bits
 * block size: 512-bits
 */
import java.util.Scanner;
import java.security.*;
import java.math.BigInteger;

/**
 *
 * @author Polis
 */
public class MD5_SHA {
    
    public static void main(String[] args) {
        String text;
        String algorithm;
        if(args.length == 2) {
            text = args[0];
            algorithm = args[1];
        }else {
            //Input the text and algorithm for the digestion
            Scanner sc = new Scanner(System.in);
            System.out.println("Give a text message to encrypt: ");
            text = sc.nextLine();      
            System.out.println("Give the algorithm for encryption(MD5 or SHA): ");
            algorithm = sc.nextLine();
        }
        
        String hash;
        hash = getHash(text,algorithm);
    }
    
    public static String getHash(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //digest() method is called to calculate message digest
            //of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            
            // translate the digested message to String.
            BigInteger bigInt = new BigInteger(1,messageDigest);
            String hashtext = bigInt.toString(16);
            
            System.out.println(hashtext); // Print the hashtext
            
            return hashtext;
        }catch (NoSuchAlgorithmException e) {
            System.out.println(e + "/nUse MD5 or SHA algorithm");
            return "";
        }
    }
}