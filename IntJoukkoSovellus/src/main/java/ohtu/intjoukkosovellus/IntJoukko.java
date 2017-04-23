package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;         // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;   // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        luvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        alkioidenLkm++;
        if (alkioidenLkm > luvut.length) {
            kasvata();
        }
        luvut[alkioidenLkm - 1] = luku;
        return true;
    }

    private void kasvata() {
        luvut = Arrays.copyOf(luvut, alkioidenLkm + kasvatuskoko);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) {
            return false;
        }
        int i = 0;
        while (luku != luvut[i]) {
            i++;
        }
        alkioidenLkm--;
        korjaaOikeillePaikoille(i);
        return true;
    }

    private void korjaaOikeillePaikoille(int i) {
        while (i < alkioidenLkm) {
            luvut[i] = luvut[i + 1];
            i++;
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        }
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += luvut[i] + ", ";
        }
        tuotos += luvut[alkioidenLkm - 1] + "}";
        return tuotos;
    }

    public int[] toIntArray() {
        return Arrays.copyOf(luvut, alkioidenLkm);
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        for (Integer i : a.toIntArray()) {
            yhdiste.lisaa(i);
        }
        for (Integer i : b.toIntArray()) {
            yhdiste.lisaa(i);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        for (Integer i : a.toIntArray()) {
            if (b.kuuluu(i)) {
                leikkaus.lisaa(i);
            }
        }
        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        for (Integer i : a.toIntArray()) {
            erotus.lisaa(i);
        }
        for (Integer i : b.toIntArray()) {
            erotus.poista(i);
        }
        return erotus;
    }

}
