package asymkey;

/**
 * Rivest–Shamir–Adleman
 * 
 * asymmetric algorithm
 * public-key cryptosystem
 * encryption key is public and distinct from the decryption key, which is kept secret (private)
 * key length: 1024-bits to 4096-bits
 * 
 * slow algorithm, used to transmit shared keys for symmetric key cryptography(DES, 3DES, AES)
 * 
 * this example implements the RSA algorithm, without the use of a file
 */
import java.io.*;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 *
 * @author Polis
 */
public class RSA {
    
    public static void main(String [] args) throws Exception {
        //Dimiourgia dimosiou kai idiotikou kleidiou
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        BufferedReader ch = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter text for encoding: ");
        String message = ch.readLine();
        
        //Encryption
        byte[] encrypted = encrypt(privateKey, message);
        System.out.println(new String(encrypted));
        
        //Decryption
        byte[] verified = decrypt(pubKey, encrypted);
        System.out.println(new String(verified));
    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;   // 1024 to 4096 bit typical
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(message.getBytes());
    }
    
    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(encrypted);
    }
    
}
