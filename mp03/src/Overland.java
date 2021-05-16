import java.time.LocalDate;

public class Overland extends MovementEnvironment {
    private int howManyWheels;

    public Overland(int howManyWheels) {
        this.howManyWheels = howManyWheels;
    }

    public LocalDate getPredictedTimeOfChangingTires() {
        LocalDate now = LocalDate.now();
        if (now.isBefore(LocalDate.of(now.getYear(), 11, 1)) && now.isAfter(LocalDate.of(now.getYear(), 3, 1))) {
            return LocalDate.of(now.getYear(), 11, 1);
        } else {
            return LocalDate.of(now.getYear(), 3, 1);
        }
    }

    public int getHowManyWheels() {
        return howManyWheels;
    }

    public void setHowManyWheels(Integer howManyWheels) throws NotNullException {
        if (howManyWheels == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.howManyWheels = howManyWheels;
    }
}