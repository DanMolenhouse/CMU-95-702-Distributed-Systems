package ds;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 *   @author Dan Molenhouse 02-11-2022
 *   Model for MVC of hash computing program
 *
 */
public class ComputeHashesModel {

    //Simple method for hashing inputted string based on a user given hash type
    public byte[] hash(String hash, String hashType)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String response = "";
        MessageDigest md;

        if(hashType.contains("MD5")){
            md = MessageDigest.getInstance("MD5");      //initializes MessageDigest as MD5 hasher
        }else{
            md = MessageDigest.getInstance("SHA-256");  //initializes MessageDigest as SHA-256 hasher
        }

        md.update(hash.getBytes());
        return md.digest();
    }

}
