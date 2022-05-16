public class Chaine {
    // Constructor
    public Chaine() {}

    /* Prend en entrée un entier quelconque entre 1 et 99 999 999
    *  convertit ce nombre en une chaîne de 8 caractères
    *  (mot de passe PX, exemple « 12012012 »)
    *  calcule le hash MD5 de la chaîne de caractère
    *  le hash MD5 est ensuite réduit par la fonction de réduction R0,
    *  on obtient le mot de passe P0
    *  on répète ensuite l'opération : calcul du hash MD5 de P0, puis réduction par R1,
    *  on obtient P1
    *  …
    *  calcul du hash MD5 de P998, puis réduction par R999, on obtient P999
    *  la fonction retourne ensuite P999
    */
    public int calculChaine(int px) {
        if (px < 1 || px > 99999999) // entrée non conforme
            return -1;

        for (int i = 0; i <= 999; i++) // on répète l'opération jusqu'à obtenir le mot de passe P999
            px = reduction(Convertors.convertStringToMD5(Convertors.numberToString(px)), i);
        return px;
    }

    /*
    *   Surchage de calculChaine
    */
    public int calculChaine(int px, int deb) {
        return calculChaine(px, deb, 999);
    }

    /*
    *   Calcul de la chaine entre début et fin (voir schéma wikipédia)
    */
    public int calculChaine(int px, int deb, int fin) {
        if (px < 1 || px > 99999999) // entrée non conforme
            return -1;

        for (int i = deb ; i <= fin ; i++) {
            String pass = Convertors.numberToString(px);
            px = reduction(Convertors.convertStringToMD5(pass), i);
        }
        return px;
    }

    /*
    *   Fonction de réduction
    */
    public int reduction(byte[] bs, int num) {
        int res = num;
        int mult = 1;
        for (int i = 0; i < 8; i++) {
            res = res + mult * ((bs[i] + 256) % 10);
            mult = mult * 10;
        }
        return res % 100_000_000;
    }
}
