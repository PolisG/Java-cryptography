package symkey_block_ciphers;

/**
 * Triple Data Encryption Algorithm (3DES)
 * 
 * applies DES cipher algorithm three times to each data block
 * 
 * block cipher
 * symmetric-key algorithm for encryption
 * block size: 64-bits
 * key length: 56-bits, 112-bits, 168-bits (options 1,2,3)
 * 
 * this example implements the 3DES algorithm (DESede), without the use of a file
 */
import javax.crypto.*;
import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Polis
 */
public class TripleDES {

    public TripleDES() {
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
        SecretKey tdesKey;
        Cipher tdesCipher;      
        
        try {
            //Emfanisi protropis
            System.out.print("Enter text for encoding: ");
            String toEncode = ch.readLine();
            
            //Dimiourgia kleidiou
            keygen = KeyGenerator.getInstance("DESede");
            tdesKey = keygen.generateKey();
            
            //Arxikopoihsh tou cipher gia kryptografisi
            tdesCipher = Cipher.getInstance("DESede");
            tdesCipher.init(Cipher.ENCRYPT_MODE, tdesKey);
            System.out.println("Key: " + Arrays.toString(tdesKey.getEncoded()));
            
            toCode = toEncode.toCharArray();
            toCode2 = new byte[toCode.length];
            Coded = new byte[toCode.length];
            
            for(int i=0; i<toCode.length; i++)
                toCode2[i] = (byte)toCode[i];
            
            //Efarmogi kryptografisis
            Coded = tdesCipher.doFinal(toCode2);
            Coded2 = new char[Coded.length];
            
            for(int i=0; i<Coded.length; i++)
                Coded2[i] = (char)Coded[i];
            System.out.println("Coded text: " + new String(Coded2));
            
            //Arxikopoihsh tou cipher gia apokryptografisi
            Cipher tdesCipher2 = Cipher.getInstance("DESede");
            tdesCipher2.init(Cipher.DECRYPT_MODE, tdesKey);
            
            //Efarmogi apokryptografisis
            decoded = tdesCipher2.doFinal(Coded);
            decoded2 = new char[decoded.length];
            
            for(int i=0; i<decoded.length; i++)
                decoded2[i] = (char)decoded[i];
            System.out.println("Decoding... " + new String(decoded2));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
