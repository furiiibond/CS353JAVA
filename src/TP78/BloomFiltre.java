package TP78;

import java.nio.ByteBuffer;

public class BloomFiltre {
    public int hash(String value , int numFonction, int m) {
        int h1 = h1(value);
        int h2 = h2(value);
        int h = ( (h1+numFonction*h2) % m);
        h = (h+m) % m;
        return h;
    }

    /*
    *   Premiere fonction de hachage
    */
    private int h1(String value) {
        char val[] = value.toCharArray();
        int h=0;
        for (int i = 0; i < val.length; i++) {
            h = 31 * h + val[i]; }
        return h;
    }

    /*
     *  Deuxieme fonction de hachage
     */
    private int h2(String value) {
        char val[] = value.toCharArray();
        int h=0;
        for (int i = 0; i < val.length; i++) {
            h = 57 * (h <<2) + val[i]; }
        return h;
    }

    /*
    *   Test si la chaine est dans le filtre (probabiliste par rapport aux colisions)
    */
    public boolean isProbablyIn(String value, ByteBuffer bloomFilter, int k, int filterLen) {
        boolean isIn = true;
        int i = 0;
        while (i < k && isIn) {
            if (bloomFilter.get(this.hash(value, i, filterLen)) == (byte) 0)
                isIn = false;
            i++;
        }
        return isIn;
    }
}