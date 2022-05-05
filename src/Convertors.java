import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Convertors {
    /*
    *   Hache le mot de passe donné en MD5
    */
    public static byte[] convertStringToMD5(String str) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return digest.digest(str.getBytes());
    }

    /*
    *   Converti un entier en une chaine de 8 caractères
    */
    public static String numberToString(int number) {
        return String.format("%08d", number);
    }

    /*
    *   Converti une chaine de caractères en un tableau de bytes
    */
    public static byte[] stringToByteArray(String str) {
        int len = str.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
            data[i/2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i+1), 16));
       return data;
    }

    /*
     *  Converti un tableau de bytes en une chaine de caractères
     */
    public static String byteArrayToString(byte[] pass) {
        StringBuilder sb = new StringBuilder();
        for (byte b : pass)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }
}
