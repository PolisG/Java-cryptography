package symkey_block_ciphers;

/**
 * Data Encryption Standard
 * 
 * block cipher
 * symmetric-key algorithm for encryption
 * block size: 64-bits
 * key length: 56-bits
 * 
 * this example implements the DES algorithm, without the use of a file
 */
import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 *
 * @author Polis
 */
public class SimpleDES {
    
    /** Creates a new instance of EasyDes */
    public SimpleDES() {
    }
    
    public static void main(String args[]){
        BufferedReader ch = new BufferedReader(new InputStreamReader(System.in));
        char[] toCode;
        byte[] toCode2;
        byte[] Coded;
        char[] Coded2;
        byte[] decoded;
        char[] decoded2;
        
        try {
            //Emfanisi protropis
            System.out.print("Enter text for encoding: ");
            String toMake = ch.readLine();
            
            //Dimiourgia kleidiou
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            SecretKey desKey = keyGen.generateKey();
            
            //Arxikopoihsh tou cipher gia kryptografisi
            Cipher desCipher = Cipher.getInstance("DES");
            desCipher.init(Cipher.ENCRYPT_MODE, desKey);
            System.out.println("Key: " + Arrays.toString(desKey.getEncoded()));
            
            toCode = toMake.toCharArray();
            toCode2 = new byte[toCode.length];
            Coded = new byte[toCode.length];
            
            for(int i=0; i<toCode.length; i++)
                toCode2[i] = (byte)toCode[i];
            
            Coded = desCipher.doFinal(toCode2);
            Coded2 = new char[Coded.length];
            
            for(int i=0; i<Coded.length; i++)
                Coded2[i] = (char)Coded[i];
            System.out.println("Coded text: " + new String(Coded2));
            
            Cipher desCipher2 = Cipher.getInstance("DES");
            desCipher2.init(Cipher.DECRYPT_MODE, desKey);
            decoded = desCipher2.doFinal(Coded);
            decoded2 = new char[decoded.length];
            for(int i=0; i<decoded.length; i++)
                decoded2[i] = (char)decoded[i];
            System.out.println("Decoding... " + new String(decoded2));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
