import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Casser {

    public static final int NODE_LEN = 1000003;

    /*
     *     Calcule 1 000 000 de chaînes et stocke pour chaque
     *     chaîne les valeurs (PX et P999) dans une table de hachage
     *     à adressage ouvert de taille 1 000 003
     */
    public static Noeud[] generateHashTable() {
        Random r = new Random(0); // set la seed pour de la reproductabilité
        Noeud[] rainbowTable = new Noeud[NODE_LEN]; // Création de la rainbowTable
        Chaine chaine = new Chaine();
        int px, p999, nbElm = 0;
        boolean found;

        for (int i = 0; i < 999999; i++) {//999999
            px = r.nextInt(99999999); // Génère un nombre entre 0 et 99 999 999
            p999 = chaine.calculChaine(px); // Egalement entre 0 et 99 999 999
            found = false;
            for (int j = 0; j < nbElm; j++) {
                if (rainbowTable[j].p999 == p999) { // déjà inséré
                    found = true;
                    break;
                }
            }
            if (!found) { // clé non existante
                rainbowTable[nbElm] = new Noeud(px, p999);
                nbElm++;
            }
        }
        return rainbowTable;
    }

    /*
     *   Sauvegarde la table de hachage dans un fichier
     *   afin de pouvoir l'utiliser plus tard
     */
    public static void saveHashTable(String fileName, Noeud[] rainbowtable) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(rainbowtable);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *   Charge la table de hachage depuis un fichier
     */
    public static Noeud[] loadHashTable(String fileName) {
        Noeud[] rainbowtable = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            rainbowtable = (Noeud[]) in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return rainbowtable;
    }

    /*
     *  Casse le mot de passe du hash donné grâce à la rainbowTable
     */
    public static int casser(byte[] hash, Noeud[] rainbowTable) {
        Chaine chaine = new Chaine();
        Noeud node = null;
        int px = 0, p999 = 0;
        byte[] byteTab;

        for (int i = 0; i <= 999; i++) {
            p999 = chaine.reduction(hash, 999 - i);
            p999 = chaine.calculChaine(p999, 1000 - i);

            for (int j = 0; j < rainbowTable.length; j++) {
                node = rainbowTable[j];
                if (node == null)
                    break;  // on a atteint la fin de la table
                if (node.p999 == p999) { // correspond donc on vérifie qu'il n'y ai pas de colision
                    px = node.px;
                    for (int j = 0; j < 999; j++) {
                        byteTab = Convertors.convertStringToMD5(Convertors.numberToString(px));  // hash de px
                        if (byteTab == null) // par précaution
                            break;
                        if (Arrays.equals(byteTab, hash)) // matching
                            return px; // got it

                        px = chaine.reduction(byteTab, j); // sinon on le réduit une fois
                    }

                    break;
                }
            }
        }
        return -1;  // retourne -1 si le mot de passe n'est pas trouvé
    }
}