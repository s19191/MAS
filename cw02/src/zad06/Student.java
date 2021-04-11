package zad06;

public class Student {
    String imie;
    String nazwisko;
    String index;
    int rokUrodzenia;
    int rokStudiow;
    int semestr;

    public Student(String imie, String nazwisko, String index, int rokUrodzenia, int rokStudiow, int semestr) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.index = index;
        this.rokUrodzenia = rokUrodzenia;
        this.rokStudiow = rokStudiow;
        this.semestr = semestr;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getIndex() {
        return index;
    }

    public int getRokUrodzenia() {
        return rokUrodzenia;
    }

    public int getRokStudiow() {
        return rokStudiow;
    }

    public int getSemestr() {
        return semestr;
    }

    @Override
    public String toString() {
        return "Student{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", index='" + index + '\'' +
                ", rokUrodzenia=" + rokUrodzenia +
                ", rokStudiow=" + rokStudiow +
                ", semestr=" + semestr +
                '}';
    }
}
