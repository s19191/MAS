package zad04;

abstract public class Silnik {
    int pojemnosc;

    public Silnik(int pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    abstract int wyliczWydajnosc();
    abstract String jakiRodzaj();
}
