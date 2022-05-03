import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Casser {
    // exercice 4
    //load hashTable from file
    public static Noeud[] loadHashTable(String fileName) {
        Noeud[] hashTable = new Noeud[1000003];
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                hashTable[i] = Noeud.fromString(line);
                i++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashTable;
    }
    // casser un password
    public static String casser(String hash, Noeud[] hashTable) {
        // TODO: implement me
        return "";
    }

    public static void main(String[] args) {
        Noeud[] table = Casser.loadHashTable("table.txt");
        String password = "bonjour";
        String md5 = Convertors.convertStringToMD5(password);
        Casser.casser(md5, table);

    }
}
