import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

// exercise 3
public class Table {
    //Faites maintenant un programme qui calcule 1_000_000 chaînes et qui stocke pour chaque chaîne les valeurs (PX et P999) dans une table de hachage à adressage ouvert de taille
    //1_000_003.
    public static void main(String[] args) {
        final String fileName = "table.txt";
        ArrayList<String> passwordsToCrack = new ArrayList<>();
        passwordsToCrack.add("8FC92036B2963C604DC38B2DDB305148");
        passwordsToCrack.add("367F3AC1129CC92DCBB8C4B9EA4EE55A");
        passwordsToCrack.add("38251B4C8C210841C60CDE0B7E4C7A87");

        //generateAndSave(fileName); // première étape (à faire une seule fois)

        cracking(fileName, passwordsToCrack); // si on a déjà un fichier de rainbowTable générée

        //Question 3
    }
}
