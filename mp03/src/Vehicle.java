public abstract class Vehicle {
    private String model;
    private String mark;
    private Double wage;
    private Integer howManySeats;

    public Vehicle(String model, String mark, Double wage, Integer howManySeats) {
        this.model = model;
        this.mark = mark;
        this.wage = wage;
        this.howManySeats = howManySeats;
    }

    public abstract double calculateAverageMonthlyCost();

    public String getModel() {
        return model;
    }

    protected void setModel(String model) throws NotNullException {
        if (model == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.model = model;
    }

    protected String getMark() {
        return mark;
    }

    protected void setMark(String mark) throws NotNullException {
        if (mark == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.mark = mark;
    }

    protected Double getWage() {
        return wage;
    }

    protected void setWage(Double wage) throws NotNullException {
        if (wage == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.wage = wage;
    }

    protected Integer getHowManySeats() {
        return howManySeats;
    }

    protected void setHowManySeats(Integer howManySeats) throws NotNullException {
        if (howManySeats == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.howManySeats = howManySeats;
    }
}