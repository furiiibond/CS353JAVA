import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

// exercise 3
public class Table {
    //Faites maintenant un programme qui calcule 1_000_000 chaînes et qui stocke pour chaque chaîne les valeurs (PX et P999) dans une table de hachage à adressage ouvert de taille
    //1_000_003.
    public static void main(String[] args) {
        // for loop to generate 1_000_000 chaines
        Noeud[] table = new Noeud[1_000_003];
        Chaine chaine = new Chaine();
        Random r = new Random(0);

        // temps de calcule de la table
        int entry = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 999999; i++) {
            int px = r.nextInt(100_000_000); // Genere un premier nombre entre 0 et 99_999_999
            int p999 = chaine.calculChaine(px);
            if (table[entry] == null) { // Si la case est vide
                table[entry] = new Noeud(p999, px);
                entry++;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Question 1 : Taille de la table : " + entry); // affiche la taille de la table
        System.out.println("Question 2 : Temps de calcul de la table : " + (endTime - startTime) / 1000.0 + "s");
        // la table de hashage a une taille de 1000_000_003
        // TODO Question 3
        Table.writeInFile("table.txt", table);
    }

    public static void writeInFile(String fileName, Noeud[] table) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Noeud noeud : table) {
                if (noeud != null) {
                    bw.write(noeud.toString());
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
