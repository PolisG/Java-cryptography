package keygen;

import java.security.*;

/**
 *
 * @author Polis
 */
public class lab7 {
    
    public static void main(String[] args) throws Exception {
        
        byte[] data = "Test test test".getBytes();
        
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
	SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
	keyPairGenerator.initialize(1024, random);
			
	KeyPair keyPair = keyPairGenerator.genKeyPair();
	PublicKey publicKey = keyPair.getPublic();
	PrivateKey privateKey = keyPair.getPrivate();
			
	byte[] signed = sign(data, privateKey);
		
	boolean verify = verify(signed, data, publicKey);
	System.out.println(verify);
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
}