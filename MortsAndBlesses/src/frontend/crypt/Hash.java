package frontend.crypt;
 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

public class Hash {
    public static String toSHA_384(String passwordToHash) {
    	byte[] salt = new byte[16];
        
    	String generatedPassword = null;
        try {
        
            MessageDigest md = MessageDigest.getInstance("SHA-384");
          
            md.update(salt);
           
            byte[] bytes = md.digest(passwordToHash.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
           
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    public static boolean isEquals_SHA_384(String hash, String passwordToCompare) {
    	return hash.equals(Hash.toSHA_384(passwordToCompare));
    }
}