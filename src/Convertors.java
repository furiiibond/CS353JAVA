import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Convertors {
    /*
    * Calcule un MD5 et l’affiche dans la console
    */
    public static String convertStringToMD5(String str) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = digest.digest(str.getBytes());
        // Construit le hash en hexadécimal
        StringBuilder sb = new StringBuilder();
        for (byte b : hash)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }

    /*
    * Converti un entier en une chaine de caracteres
    */
    public static String numberToString(int number) {
        return String.format("%08d", number);
    }

    /*
    * Converti une chaine de caracteres en un tableau de bytes
    */
    public static byte[] stringToByteArray(String str) {
        int len = str.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
            data[i/2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i+1), 16));
       return data;
    }

    public static String byteArrayToString(byte[] pass) {
        StringBuilder sb = new StringBuilder();
        for (byte b : pass)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }
}
