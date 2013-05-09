package othellier;

import java.util.ArrayList;

public class Othellier {

    public static int minX = 1;
    public static int maxX = 8;
    public static int minY = 1;
    public static int maxY = 8;
    public static Curseur curseur;
    private static Case Origine;
    private ArrayList<byte[][]>[][] TBitboard;
    public ArrayList[][] BITBOARDS;
    private Case point_depart;
    private int comptage;

    public ArrayList[][] TBitboardToLongHexa() {
        ArrayList res[][] = new ArrayList[9][9];
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                res[i][j] = new ArrayList<>();
            }
        }
        String longhexa = "";

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ArrayList<byte[][]> LB = TBitboard[i][j];

                for (int d = 0; d < LB.size(); d++) {
                    for (int k = 1; k <= 8; k++) {
                        for (int l = 1; l <= 8; l++) {
                            longhexa += Byte.toString(LB.get(d)[k][l]);
                        }

                    }
                    res[i][j].add(longhexa);
                   
                    longhexa = "";
                }
            }
        }
        return res;

    }

    public void creerBitboards() throws CloneNotSupportedException {
        TBitboard = new ArrayList[9][9];
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                point_depart = new Case(i, j);
                initialiser();
            }
        }
//        System.out.println(comptage++);
        BITBOARDS = TBitboardToLongHexa();
    }

    public void initialiser() throws CloneNotSupportedException {

        Origine = new Case(point_depart.X, point_depart.Y);
        curseur = new Curseur();
        Vecteur vecteurs = new Vecteur();
        curseur._case = point_depart.copy();
        int pas = 1;
        ArrayList<byte[][]> caseBitboards = new ArrayList<>();
        for (Vecteur direction : Vecteur.Direction) {
            byte[][] dirBitboard = new byte[9][9];
            boolean nonnul = false;
            while (!curseur.sedeplace(pas, direction)) {
//                System.out.println(curseur);
                dirBitboard[curseur._case.X][curseur._case.Y] = 1;
                nonnul = true;
            }
            if (nonnul) {
                caseBitboards.add(dirBitboard);
            }
            curseur._case = point_depart.copy();
        }
        TBitboard[point_depart.X][point_depart.Y] = caseBitboards;
//        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String res = "";

        ArrayList<byte[][]> LB = TBitboard[point_depart.X][point_depart.Y];
//        System.out.println(LB.size());
        for (int d = 0; d < LB.size(); d++) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    res += Byte.toString(LB.get(d)[i][j]);
                }
                res += '\n';
            }

            comptage++;
            res += '\n';
        }

        return res;
    }

    static boolean horsDomaine(Curseur A) {
        return (A._case.X < minX) | (A._case.X > maxX) | (A._case.Y < minY) | (A._case.Y > maxY);

    }

    public static class Curseur {

        public Case _case;

        Curseur() {
            _case = Origine;
        }

        boolean sedeplace(int pas, Vecteur direction) {
            _case.X = _case.X + pas * direction.u;
            _case.Y = _case.Y + pas * direction.v;
            return horsDomaine(curseur);
        }

        @Override
        public String toString() {
            return _case.toString();
        }
    }

    public static class Case {

        int X, Y;

        public Case(int X, int Y) {

            this.X = X;
            this.Y = Y;
        }

        @Override
        public String toString() {
            return "(" + X + "," + Y + ")";
        }

        Case copy() throws CloneNotSupportedException {
            return new Case(X, Y);
        }

        @Override
        public boolean equals(Object objet) {
            if (objet == this) {
                return true;
            }
            if (objet instanceof Case) {
                Case p = (Case) objet;
                if ((X != p.X) | (Y != p.Y)) {
                    return false;
                } else {
                    return true;
                }
            }

            return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 53 * hash + this.X;
            hash = 53 * hash + this.Y;
            return hash;
        }
    }

    public static class Vecteur {

        int u, v;
        Vecteur nord, sud, est, ouest;
        Vecteur nord_est, sud_est, nord_ouest, sud_ouest;
        static Vecteur[] Direction;

        Vecteur() {
            nord = new Vecteur(1, 0);
            sud = new Vecteur(-1, 0);
            est = new Vecteur(0, 1);
            ouest = new Vecteur(0, -1);

            nord_est = nord.plus(est);
            sud_est = sud.plus(est);
            nord_ouest = nord.plus(ouest);
            sud_ouest = sud.plus(ouest);

            Direction = new Vecteur[8];
            Direction[0] = nord;
            Direction[1] = nord_est;
            Direction[2] = est;
            Direction[3] = sud_est;
            Direction[4] = sud;
            Direction[5] = sud_ouest;
            Direction[6] = ouest;
            Direction[7] = nord_ouest;
        }

        Vecteur(int u, int v) {
            this.u = u;
            this.v = v;
        }

        Vecteur plus(Vecteur vect) {
            return new Vecteur(u + vect.u, v + vect.v);
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }
}
