package frontend.crypt;
 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

public class Hash {
	// tester    System.out.println(Hash.toSHA_384("password"));    chof wach at3tik b7al lcode li lt7t
	//a3635f80caa3fab751ae1896486d00ba1cbe9f1ac2435b3aea8f8518663187d520db4b12ce726292707f660a75989690
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
	
}