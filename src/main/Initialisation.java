package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.ListIterator;
import othellier.Othellier;

public class Initialisation {

    String TOthellierB = "0000000000000000000000000001000000001000000000000000000000000000";
    String TOthellierN = "0000000000000000000000000000100000010000000000000000000000000000";
    String Othellier = "1111111111111111111111111111111111111111111111111111111111111111";
    private final ArrayList<String>[][] SBITBOARDS;
    private final ArrayList<BigInteger>[][] BITBOARD;
    private final BigInteger blanc;
    private final BigInteger noir;
    private final BigInteger casevide;
    private final BigInteger othellier;

    public Initialisation() throws CloneNotSupportedException {
        blanc = new BigInteger(TOthellierB, 2);
        noir = new BigInteger(TOthellierN, 2);
        othellier = new BigInteger(Othellier, 2);
        System.out.println(blanc.toString(2));
        System.out.println(noir.toString(2));
//        for (byte Case = 0; Case < 64; Case++) {
//            blanc.setBit(TOthellier[Case] == 1 ? 1 : 0);
//            noir.setBit(TOthellier[Case] == -1 ? 1 : 0);
//
//        }
        BITBOARD = new ArrayList[9][9];

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                BITBOARD[i][j] = new ArrayList<>();
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
        casevide =othellier.add((blanc.or(noir)).negate()) ;
        System.out.println(casevide.toString(2));
        for (int n = 0; n < 64; n++) {
            int c = n % 8 + 1;
            int r = n / 8 + 1;
            System.out.println(c + "," + r + ": --------------------- ");
            if (casevide.testBit(n)) {
                System.out.println(BITBOARD[c][r]);
            }

        }
//        affiche(); //affiche BITBOARD
    }

    private void convertToBigInteger(ArrayList<String> L, int i, int j) {
        ListIterator<String> itL = L.listIterator();
//        int index = 0;
        while (itL.hasNext()) {
            String next = itL.next();
            BigInteger r = new BigInteger(next, 2);
            BITBOARD[i][j].add(r);
//            System.out.println(++index + ":" + r);

        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Initialisation init = new Initialisation();

    }

    private void affiche() {

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                System.out.println("(" + i + "," + j + ")");
                System.out.println(BITBOARD[i][j]);

            }
            System.out.println("---");

        }
    }
}
