import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<byte[]> hackedPassword = new ArrayList<>(); // mots de passe hackés par Jack Le Hacker
        hackedPassword.add(Convertors.stringToByteArray("8FC92036B2963C604DC38B2DDB305148"));
        hackedPassword.add(Convertors.stringToByteArray("367F3AC1129CC92DCBB8C4B9EA4EE55A"));
        hackedPassword.add(Convertors.stringToByteArray("38251B4C8C210841C60CDE0B7E4C7A87"));
        final int maxPassword = 99999999; // dernier password possible

        // on genere les nombres allant de 0 a 99999999 puis on les converti en chaine de caracteres
        // on cherche le mot de passe qui correspond au mot de passe hacké
        int nbOfAccount = hackedPassword.size(); // combien de password à trouver
        long startTime = System.nanoTime(); // calcul du temps
        for (int i = 0; i <= maxPassword; i++) {
            byte[] byteTab = Convertors.convertStringToMD5(Convertors.numberToString(i)); // on hash le i
            for (byte[] passHacked: hackedPassword) { // pour ce i haché, on test si on a des password qui matchent
                if (Arrays.equals(passHacked, byteTab)) {
                    nbOfAccount--; // 1 de moins à trouver
                    System.out.println("Encrypted password: " + Convertors.byteArrayToString(passHacked));
                    System.out.println("Decrypted password: " + Convertors.numberToString(i));
                    if (nbOfAccount <= 0) { // tous les mots de passes ont été trouvés
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime) / (1000000L * hackedPassword.size()); // in milliseconds
                        System.out.println("Average duration: " + duration + " ms");
                    }
                }
            }
        }
    }
}
