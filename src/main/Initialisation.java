package main;

import java.util.ArrayList;
import java.util.ListIterator;
import othellier.Othellier;

public class Initialisation {

    byte[] TOthellier = {
        0, 0, 0, 0, 0, 0, 0, 0, //  0 ..  7
        0, 0, 0, 0, 0, 0, 0, 0, //  8 .. 15
        0, 0, 0, 0, 0, 0, 0, 0, // 16 .. 23
        0, 0, 0, 1, -1, 0, 0, 0, // 24 .. 31
        0, 0, 0, -1, 1, 0, 0, 0, // 32 .. 39
        0, 0, 0, 0, 0, 0, 0, 0, // 40 .. 47
        0, 0, 0, 0, 0, 0, 0, 0, // 48 .. 55
        0, 0, 0, 0, 0, 0, 0, 0 // 56 .. 63
    };
    private final long bbblancs, bbnoirs;
    private final ArrayList<Long>[][] BITBOARDS;

    public Initialisation() throws CloneNotSupportedException {
        Othellier oth = new Othellier();
        oth.creerBitboards();
        BITBOARDS = oth.BITBOARDS;

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                System.out.println("(" + i + "," + j + ")");
                print(BITBOARDS[i][j]);

            }
            System.out.println();

        }

        bbblancs = 0x0000001008000000L;
        bbnoirs = 0x0000000810000000L;


    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Initialisation init = new Initialisation();

    }

    private void print(ArrayList<Long> L) {
        ListIterator<Long> itL = L.listIterator();
        int index = 0;
        while (itL.hasNext()) {
            System.out.println(++index + ":" + itL.next());
        }
    }
}
//    private final byte[] tblancs;
//    private final byte[] tnoirs;
//        tblancs = new byte[64];
//        tnoirs = new byte[64];
//        for (byte Case = 0; Case < 64; Case++) {// int Case :Othellier ?
//            tblancs[Case] = (byte) (TOthellier[Case] == 1 ? 1 : 0);
//            tnoirs[Case] = (byte) (TOthellier[Case] == -1 ? 1 : 0);
//
//        }