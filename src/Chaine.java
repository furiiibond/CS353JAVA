public class Chaine {
    //constructor
    public Chaine() {
    }

    //prend en entrée un entier quelconque entre 1 et 99_999_999
    //• convertit ce nombre en une chaîne de 8 caractères (mot de passe PX, exemple
    //« 12012012 »)
    //• calcule le hash MD5 de la chaîne de caractère
    //• le hash MD5 est ensuite réduit par la fonction de réduction R0, on obtient le mot de
    //passe P0
    //• on répète ensuite l'opération : calcul du hash MD5 de P0, puis réduction par R1, on
    //obtient P1
    //• …
    //• calcul du hash MD5 de P998, puis réduction par R999, on obtient P999
    //• la fonctionne retourne ensuite P999
    public int calculChaine(int px) {
        if (px < 1 || px > 99999999) // entré non conforme
            return -1;
        for (int i = 0; i <= 999; i++)
            px = reduction(Convertors.stringToByteArray(Convertors.convertStringToMD5(Convertors.numberToString(px))), i);
        return px;
    }

    private int reduction(byte[] bs, int num) {
        int res = num;
        int mult = 1;
        for (int i = 0; i < 8; i++) {
            res = res + mult * ((bs[i] + 256) % 10);
            mult = mult * 10;
        }
        return res % 100_000_000;
    }
}
