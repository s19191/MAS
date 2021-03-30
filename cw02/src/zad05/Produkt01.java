package zad05;

public class Produkt01 {
    private int cena;

    public Produkt01(int cena) {
        this.cena = cena;
    }

    boolean sprawdz(int wartosc) {
        if (wartosc < cena) {
            return true;
        } else {
            return false;
        }
    }

    public int getCena() {
        return cena;
    }
}
