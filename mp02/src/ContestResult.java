public class ContestResult {
    private Contest contest;
    private Barista barista;
    private int placeWon;
    private int priceWon;

    private ContestResult(Contest contest, Barista barista, int placeWon, int priceWon) {
        this.contest = contest;
        this.barista = barista;
        this.placeWon = placeWon;
        this.priceWon = priceWon;
        contest.addContestResult(this);
        barista.addContestResult(this);
    }

    public static ContestResult createContestResult(Contest contest, Barista barista, Integer placeWon, Integer priceWon) throws NotNullException {
        if (contest == null || barista == null || placeWon == null || priceWon == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        ContestResult contestResult = new ContestResult(contest, barista, placeWon, priceWon);
        return contestResult;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest newContest) {
        if (contest != null) {
            removeContest();
        }
        contest = newContest;
        newContest.addContestResult(this);
    }

    public void removeContest() {
        if (contest != null) {
            contest.removeContestResult(this);
            contest = null;
        }
    }

    public Barista getBarista() {
        return barista;
    }

    public void setBarista(Barista newBarista) {
        if (barista != null) {
            removeBarista();
        }
        barista = newBarista;
        newBarista.addContestResult(this);
    }

    public void removeBarista() {
        if (barista != null) {
            barista.removeContestResult(this);
            barista = null;
        }
    }

    public int getPlaceWon() {
        return placeWon;
    }

    public void setPlaceWon(Integer placeWon) throws NotNullException {
        if (placeWon == null) {
            throw new NotNullException("Can't set value of placeWon, value can not be null");
        }
        this.placeWon = placeWon;
    }

    public int getPriceWon() {
        return priceWon;
    }

    public void setPriceWon(Integer priceWon) throws NotNullException {
        if (priceWon == null) {
            throw new NotNullException("Can't set value of priceWon, value can not be null");
        }
        this.priceWon = priceWon;
    }

    @Override
    public String toString() {
        return "ContestResult{" +
                "contest=" + contest.toString() +
                ", barista=" + barista.toString() +
                ", placeWon=" + placeWon +
                ", priceWon=" + priceWon +
                '}';
    }
}