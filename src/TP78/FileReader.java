package TP78;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static ByteBuffer generateBloomFilter(String path, int filterLen, int k) throws IOException {
        ByteBuffer bloomFilter = ByteBuffer.allocate(filterLen); // all 0
        long start = System.currentTimeMillis();
        SeekableByteChannel sbc = Files.newByteChannel(Paths.get(path), StandardOpenOption.READ);
        //int count = 0;

        // Lecture des 50 000 premiers octets du fichier
        ByteBuffer buf = ByteBuffer.allocate(50000);
        int ret = sbc.read(buf);
        BloomFiltre bloomUtils = new BloomFiltre();

        while (ret > 0) {
            // Conversion des 50 000 octets en 1000 chaine de caractères
            for (int i = 0; i < 1000; i++) {
                String str = new String(buf.array(),i*50,50);
                // Suppression des espaces en fin de chaine
                str = str.trim();
                // add in ByteBuffer
                for (int j = 0; j < k; j++) {
                    bloomFilter.put(bloomUtils.hash(str, j, filterLen), (byte) 1);
                }
                //count++;
            }
            // Lecture des 50 000 octets suivants
            buf.clear();
            ret = sbc.read(buf);
        }
        //System.out.println("Nombre d'URL dans le fichier =" + count);
        // Fermeture du fichier
        System.out.println("Elapsed Time=" + (System.currentTimeMillis()-start)/1000 + "s");
        sbc.close();
        return bloomFilter;
    }

    public static int testBloomFilterFauxPositifs(String path, ByteBuffer bloomFilter, int k, int filterLen) throws IOException {
        long start = System.currentTimeMillis();
        SeekableByteChannel sbc = Files.newByteChannel(Paths.get(path), StandardOpenOption.READ);
        int count = 0;

        // Lecture des 50 000 premiers octets du fichier
        ByteBuffer buf = ByteBuffer.allocate(50000);
        int ret = sbc.read(buf);
        BloomFiltre bloomUtils = new BloomFiltre();

        while (ret > 0) {
            // Conversion des 50 000 octets en 1000 chaine de caractères
            for (int i = 0; i < 1000; i++) {
                String str = new String(buf.array(),i*50,50);
                // Suppression des espaces en fin de chaine
                str = str.trim();
                if (bloomUtils.isProbablyIn(str, bloomFilter, k, filterLen)) // faux positif
                    count++;
            }
            // Lecture des 50 000 octets suivants
            buf.clear();
            ret = sbc.read(buf);
        }
        //System.out.println("Nombre d'URL dans le fichier =" + count);
        // Fermeture du fichier
        System.out.println("Elapsed Time=" + (System.currentTimeMillis()-start)/1000 + "s");
        sbc.close();

        return count;
    }

}
