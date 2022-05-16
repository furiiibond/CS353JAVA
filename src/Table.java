import java.util.ArrayList;

// exercise 3
public class Table {

    /*
    *   Génére la rainbowTable et la sauvegarde dans un fichier
    *   afin de la réutiliser sans recalculer les hash
    */
    public static void generateAndSave(String fileName) {
        long startTime, endTime;

        System.out.println("Démarrage de la génération de la rainbowTable\n");
        startTime = System.currentTimeMillis(); // démarrage du chronomètre
        Noeud[] rainbowTable = Casser.generateHashTable(); // on génère la rainbowTable
        endTime = System.currentTimeMillis(); // fin du chronomètre
        System.out.println("Temps pris par la génération " + (endTime - startTime) / 1000.0 / 60.0 + "min " + (endTime - startTime) / 1000.0 % 60 + "s");

        System.out.println("Démarrage de la sauvegarde de la rainbowTable dans le fichier " + fileName + "\n");
        startTime = System.currentTimeMillis(); // démarrage du chronomètre
        Casser.saveHashTable(fileName, rainbowTable); // on sauvegarde la rainbowTable dans le fichier
        endTime = System.currentTimeMillis(); // fin du chronomètre
        System.out.println("Temps pris par la sauvegarde " + (endTime - startTime) / 1000.0 / 60.0 + "min " + (endTime - startTime) / 1000.0 % 60 + "s");
    }

    /*
    *   Crack la liste de mots de passe donnée en utilisant
    *   la rainbowTable préalablement écrite dans un fichier
    */
    public static void cracking(String fileName, ArrayList<String> passwordsToCrack) {
        long startTime, endTime;
        int hacked = -1;

        System.out.println("Démarrage du chargement de la rainbowTable depuis le fichier " + fileName + "\n");
        startTime = System.currentTimeMillis(); // démarrage du chronomètre
        Noeud[] rainbowTable = Casser.loadHashTable(fileName); // on charge la rainbowTable depuis le fichier
        endTime = System.currentTimeMillis(); // fin du chronomètre
        System.out.println("Temps pris par le chargement du fichier " + (endTime - startTime) + "ms");

        System.out.println("Question 1 : nombre de couple dans la rainbowTable " + Table.nbCouple(rainbowTable));

        System.out.println("Lancement du crackage des mots de passe\n");
        startTime = System.currentTimeMillis(); // démarrage du chronomètre
        for (int i = 0; i < passwordsToCrack.size(); i++) { // on crack chaque mot de passes de la liste
            hacked = Casser.casser(Convertors.stringToByteArray(passwordsToCrack.get(i)), rainbowTable);
            if (hacked == -1) { // on affiche le mot de passe déchiffré.  -1 sinon
                System.out.println("Mot de passe non trouvé");
            } else {
                System.out.println("Mot de passe cracké : " + Convertors.numberToString(hacked));  // on affiche le mot de passe déchiffré.
                System.out.println("Hash associé : " + passwordsToCrack.get(i)); // on affiche le hash associé
            }
        }
        endTime = System.currentTimeMillis(); // fin du chronomètre
        System.out.println("Question 2 : Temps du crackage des mots de passe : " + (endTime - startTime) + "ms");
    }

    private static int nbCouple(Noeud[] rainbowTable) { // retourne le nombre de couple dans la rainbowTable
        int nbCouple = 0;
        for (Noeud noeud : rainbowTable) {
            if (noeud == null) // si le noeud est null, on passe au suivant
                break;
            nbCouple++;
        }
        return nbCouple;
    }

    public static void main(String[] args) { // programme principal
        final String fileName = "table.txt"; // nom du fichier de la rainbowTable
        ArrayList<String> passwordsToCrack = new ArrayList<>(); // liste des mots de passe à cracker
        passwordsToCrack.add("8FC92036B2963C604DC38B2DDB305148");
        passwordsToCrack.add("367F3AC1129CC92DCBB8C4B9EA4EE55A");
        passwordsToCrack.add("38251B4C8C210841C60CDE0B7E4C7A87");

        //generateAndSave(fileName); // première étape (à faire une seule fois)

        cracking(fileName, passwordsToCrack); // si on a déjà un fichier de rainbowTable générée (table.txt)
    }
}
