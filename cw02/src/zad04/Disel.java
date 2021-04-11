package zad04;

public class Disel extends Silnik {
    public Disel(int pojemnosc) {
        super(pojemnosc);
    }

    @Override
    int wyliczWydajnosc() {
        return pojemnosc * 2;
    }

    @Override
    String jakiRodzaj() {
        return "disel";
    }
}
