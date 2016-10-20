/*license TODO*/

package RouterControl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author adam
 * 
 * leave this for later. TODO
 */
public class Encryption {
        private String pKey = "9ypzE^WBod9ZLL$yPX*pFYr@%I&9ALWioCM&2JPR";  //change this to have a different encryption
        private  SecretKeySpec secretKey ;
        private  byte[] key ;        
        private  String decryptedString;
        private  String encryptedString;
    
    private void setKey(){       
        MessageDigest sha = null;

        try {
            key = pKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // use only first 128 bit
            secretKey = new SecretKeySpec(key, "AES");  
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public  String encrypt(String strToEncrypt){
        try{
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }catch (Exception e){           
            System.out.println("Error while encrypting: "+e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt){
        try{
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
        }catch (Exception e){         
            System.out.println("Error while decrypting: "+e.toString());
        }
        return null;
    }
    
}
