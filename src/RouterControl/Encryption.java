/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    private  String getDecryptedString() {return decryptedString;}
    private void setDecryptedString(String decryptedString) {decryptedString = decryptedString;}
    private String getEncryptedString() {return encryptedString;}
    private void setEncryptedString(String encryptedString) {encryptedString = encryptedString;}

    public  String encrypt(String strToEncrypt){
        try{
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            setEncryptedString(Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
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
            //setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt))));
            return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
        }catch (Exception e){         
            System.out.println("Error while decrypting: "+e.toString());
        }
        return null;
    }

/*
    public static void main(String args[]){
                final String strToEncrypt = "My text to encrypt";
                final String strPssword = "encryptor key";
                AES.setKey(strPssword);
               
                AES.encrypt(strToEncrypt.trim());
                
                System.out.println("String to Encrypt: " + strToEncrypt); 
                System.out.println("Encrypted: " + AES.getEncryptedString());
           
                final String strToDecrypt =  AES.getEncryptedString();
                AES.decrypt(strToDecrypt.trim());
               
                System.out.println("String To Decrypt : " + strToDecrypt);
                System.out.println("Decrypted : " + AES.getDecryptedString());        
    }
    
*/
    
}
