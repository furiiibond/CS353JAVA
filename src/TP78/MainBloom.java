package TP78;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MainBloom {
    public static void main(String[] args) {
        final int filterLen = 200000000;
        // pour k allant de 1 à 20
        for (int k = 1; k <= 20; k++) {
            simuFauxPositifBloom(filterLen, k);
        }
    }

    private static void simuFauxPositifBloom(int filterLen, int K) {
        // creation du bloom filter
        ByteBuffer bloomFilter = null;
        try {
            bloomFilter = FileReader.generateBloomFilter("infected-urls.txt", filterLen, K);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            int nbFauxPositifs = FileReader.testBloomFilterFauxPositifs("valides-urls.txt", bloomFilter, K, filterLen);
            System.out.println("Nombre de faux positifs : " + nbFauxPositifs);
            // afficher le taux de faux positifs
            System.out.println("Taux de faux positifs : " + ((double)nbFauxPositifs / filterLen) * 100 + "%" + "pour k = " + K);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
