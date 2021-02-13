package keygen;

/**
 * Digital Signature Algorithm (DSA)
 * 
 * uses SHA-1 or SHA-2
 * four operations: key generation, key distribution, signing and signature verification. 
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

/**
 *
 * @author Polis
 */
public class Public_Private_KeyGenerator {
    private final KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private SecureRandom random;
    
    public Public_Private_KeyGenerator(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
	this.random.getInstance("SHA1PRNG", "SUN");
        this.keyGen.initialize(1024, random);
    }
    
    public void createKeys() {
	this.pair = this.keyGen.generateKeyPair();
	this.privateKey = pair.getPrivate();
	this.publicKey = pair.getPublic();
    }
    
    public PrivateKey getPrivateKey() {
	return this.privateKey;
    }
    
    public PublicKey getPublicKey() {
	return this.publicKey;
    }
    
    public void writeToFile(String path, byte[] key) throws IOException {
	File f = new File(path);
	f.getParentFile().mkdirs();
        
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(key);
            fos.flush();
        }
    }
    
    public static boolean verify(byte[] signed, byte[] data, PublicKey publicKey) {
        try {
            Signature dsa = Signature.getInstance("SHA1withDSA","SUN");
            dsa.initVerify(publicKey);
            dsa.update(data);
            
            return dsa.verify(signed);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException e) {
            return false;
        }
    }
    
    public static byte[] sign(byte[] data, PrivateKey privateKey) {
        try {
            Signature dsa = Signature.getInstance("SHA1withDSA","SUN");
            dsa.initSign(privateKey);
            dsa.update(data);
            
            return dsa.sign();
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        byte[] data = "A test message.".getBytes();
	Public_Private_KeyGenerator keygen = null;
	try {
            keygen = new Public_Private_KeyGenerator(1024);
            keygen.createKeys();
            keygen.writeToFile("KeyPair/publicKey", keygen.getPublicKey().getEncoded());
            keygen.writeToFile("KeyPair/privateKey", keygen.getPrivateKey().getEncoded());
        } catch (NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
            System.err.println(e.getMessage());
        }
        
        byte[] signed = sign(data, keygen.getPrivateKey());
        boolean verify;
        verify = verify(signed, data, keygen.getPublicKey());
        System.out.println(verify);
    }
}