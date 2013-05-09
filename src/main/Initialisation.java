package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.ListIterator;
import othellier.Othellier;

public class Initialisation {

    private final ArrayList<String>[][] SBITBOARDS;
    private final ArrayList<BigInteger>[][] BITBOARDS;

    public Initialisation() throws CloneNotSupportedException {
        BITBOARDS = new ArrayList[9][9];
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                BITBOARDS[i][j] = new ArrayList<>();
            }
        }
        Othellier oth = new Othellier();
        oth.creerBitboards();
        SBITBOARDS = oth.BITBOARDS;

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                convertToBigInteger(SBITBOARDS[i][j], i, j);

            }

        }

        //affiche BITBOARDS
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                System.out.println("(" + i + "," + j + ")");
                System.out.println(BITBOARDS[i][j]);

            }
            System.out.println("---");

        }
    }

    private void convertToBigInteger(ArrayList<String> L, int i, int j) {
        ListIterator<String> itL = L.listIterator();
//        int index = 0;
        while (itL.hasNext()) {
            String next = itL.next();
            BigInteger r = new BigInteger(next, 2);
            BITBOARDS[i][j].add(r);
//            System.out.println(++index + ":" + r);

        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Initialisation init = new Initialisation();

    }
}
