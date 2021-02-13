package symkey_block_ciphers;

/**
 * Advanced Encryption Standard (Rijndael)
 *
 * subset of the Rijndael block cipher
 * 
 * block cipher
 * symmetric-key algorithm for encryption, both the encryptor and the decryptor use the same key
 * ciphers with different key and block sizes
 * block size: 128 bits
 * key length: 128, 192 or 256 bits
 * 
 * this example implements the AES algorithm, without the use of a file
 */
import javax.crypto.*;
import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Polis
 */
public class AES {

    public AES() {
    }
    
    public static void main(String[] args) {
        BufferedReader ch = new BufferedReader(new InputStreamReader(System.in));
        char[] toCode;
        byte[] toCode2;
        byte[] Coded;
        char[] Coded2;
        byte[] decoded;
        char[] decoded2;
        
        KeyGenerator keygen;
        SecretKey aesKey;
        Cipher aesCipher;
        
        try {
            //Emfanisi protropis
            System.out.print("Enter text for encoding: ");
            String toEncode = ch.readLine();
            
            //Dimiourgia kleidiou
            keygen = KeyGenerator.getInstance("AES");
            aesKey = keygen.generateKey();
            
            //Arxikopoihsh tou cipher gia kryptografisi
            aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
            System.out.println("Key: " + Arrays.toString(aesKey.getEncoded()));
            
            toCode = toEncode.toCharArray();
            toCode2 = new byte[toCode.length];
            Coded = new byte[toCode.length];
            
            for(int i=0; i<toCode.length; i++)
                toCode2[i] = (byte)toCode[i];
            
            //Efarmogi kryptografisis
            Coded = aesCipher.doFinal(toCode2);
            Coded2 = new char[Coded.length];
            
            for(int i=0; i<Coded.length; i++)
                Coded2[i] = (char)Coded[i];
            System.out.println("Coded text: " + new String(Coded2));
            
            //Arxikopoihsh tou cipher gia apokryptografisi
            Cipher aesCipher2 = Cipher.getInstance("AES");
            aesCipher2.init(Cipher.DECRYPT_MODE, aesKey);
            
            //Efarmogi apokryptografisis
            decoded = aesCipher2.doFinal(Coded);
            decoded2 = new char[decoded.length];
            
            for(int i=0; i<decoded.length; i++)
                decoded2[i] = (char)decoded[i];
            System.out.println("Decoding... " + new String(decoded2));
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
