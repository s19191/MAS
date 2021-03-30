package zad04;

public class Benzyna extends Silnik {
    public Benzyna(int pojemnosc) {
        super(pojemnosc);
    }

    @Override
    int wyliczWydajnosc() {
        return pojemnosc * 4;
    }

    @Override
    String jakiRodzaj() {
        return "benzynowego";
    }
}
