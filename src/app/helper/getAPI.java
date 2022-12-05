package app.helper;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author PC
 */
public class getAPI {

    private static final String HMAC_SHA512 = "HmacSHA512";

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static String calculateHMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
        Mac mac = Mac.getInstance(HMAC_SHA512);
        mac.init(secretKeySpec);
        return toHexString(mac.doFinal(data.getBytes()));
    }

    public static String getApiById(String id) {
        String ctime = "1667412442125";

        try {
            String s = "ctime=" + ctime + "id=" + id;
            String rs = DigestUtils.sha256Hex(s);
            String data = "/song/get-song-info" + rs;
            String key = "10a01dcf33762d3a204cb96429918ff6"; //secret key        
            String hmac = calculateHMAC(data, key); //sig

            String result = "https://zingmp3.vn/api/song/get-song-info?"
                    + "ctime=" + ctime
                    + "&id=" + id
                    + "&sig=" + hmac
                    + "&api_key=38e8643fb0dc04e8d65b99994d3dafff";
            return result;
        } catch (SignatureException s) {

        } catch (NoSuchAlgorithmException n) {

        } catch (InvalidKeyException i) {

        }
        return "loi";
    }

    public static void main(String[] args) {
        //https://zingmp3.vn/api/song/get-song-info?ctime=160718424&id=ZW6Z0Z6W&sig=34accf189094a55c45d4ec2d7f88947c7d7eded943763d765569ae470c06dccc4047ea4127fc2bae38c032e4cddf70ba30bf250bc412b6b592e9063100dc36cf&api_key=38e8643fb0dc04e8d65b99994d3dafff

//        String ctime = "160718424";
//        String id = "Z6IFZWI6";
//        
//        String s = "ctime=" + ctime + "id=" + id;      
//        String rs = DigestUtils.sha256Hex(s);
//        String data = "/song/get-song-info" + rs;
//        String key = "10a01dcf33762d3a204cb96429918ff6"; //secret key        
//        String hmac = calculateHMAC(data, key); //sig
//        
//        String result = "https://zingmp3.vn/api/song/get-song-info?" 
//                + "ctime=" + ctime 
//                + "&id=" + id 
//                + "&sig=" + hmac 
//                + "&api_key=38e8643fb0dc04e8d65b99994d3dafff";
        System.out.println(getApiById("ZW67Z6F0"));

    }
}
