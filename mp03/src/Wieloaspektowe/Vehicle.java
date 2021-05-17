package Wieloaspektowe;

import java.time.LocalDate;

public class Vehicle {
    private String model;
    private String mark;
    private Double wage;
    private Integer howManySeats;

    private TypeOfMovementEnvironment typeOfMovementEnvironment;

    private Double displacement;
    private BodyOfWater bodyOfWater;

    private Integer howManyWheels;

    public Vehicle(String model, String mark, Double wage, Integer howManySeats, Double displacement, BodyOfWater bodyOfWater) {
        this.model = model;
        this.mark = mark;
        this.wage = wage;
        this.howManySeats = howManySeats;
        this.displacement = displacement;
        this.bodyOfWater = bodyOfWater;
        typeOfMovementEnvironment = TypeOfMovementEnvironment.AQUEOUS;
    }

    public String findPermissions() throws Exception {
        if (typeOfMovementEnvironment == TypeOfMovementEnvironment.AQUEOUS) {
            String result;
            if (bodyOfWater == BodyOfWater.INLAND) {
                result = "Można pływać na akweanach śródlądowych";
            } else {
                result = "Można pływach na morzach i oceanach";
            }
            return result;
        } else {
            throw new Exception("The vehicle is not an aqueous!");
        }
    }

    public Double getDisplacement() throws Exception {
        if (typeOfMovementEnvironment == TypeOfMovementEnvironment.AQUEOUS) {
            return displacement;
        } else {
            throw new Exception("The vehicle is not an aqueous!");
        }
    }

    public void setDisplacement(Double displacement) throws Exception {
        if (typeOfMovementEnvironment == TypeOfMovementEnvironment.AQUEOUS) {
            this.displacement = displacement;
        } else {
            throw new Exception("The vehicle is not an aqueous!");
        }
    }

    public BodyOfWater getBodyOfWater() throws Exception {
        if (typeOfMovementEnvironment == TypeOfMovementEnvironment.AQUEOUS) {
            return bodyOfWater;
        } else {
            throw new Exception("The vehicle is not an aqueous!");
        }
    }

    public void setBodyOfWater(BodyOfWater bodyOfWater) throws Exception {
        if (typeOfMovementEnvironment == TypeOfMovementEnvironment.AQUEOUS) {
            this.bodyOfWater = bodyOfWater;
        } else {
            throw new Exception("The vehicle is not an aqueous!");
        }
    }

    public Vehicle(String model, String mark, Double wage, Integer howManySeats, Integer howManyWheels) {
        this.model = model;
        this.mark = mark;
        this.wage = wage;
        this.howManySeats = howManySeats;
        this.howManyWheels = howManyWheels;
        typeOfMovementEnvironment = TypeOfMovementEnvironment.OVERLAND;
    }

    public LocalDate getPredictedTimeOfChangingTires() throws Exception {
        if (typeOfMovementEnvironment == TypeOfMovementEnvironment.OVERLAND) {
            LocalDate now = LocalDate.now();
            if (now.isBefore(LocalDate.of(now.getYear(), 11, 1)) && now.isAfter(LocalDate.of(now.getYear(), 3, 1))) {
                return LocalDate.of(now.getYear(), 11, 1);
            } else {
                return LocalDate.of(now.getYear(), 3, 1);
            }
        } else {
            throw new Exception("The vehicle is not an aqueous!");
        }
    }

    public Integer getHowManyWheels() throws Exception {
        if (typeOfMovementEnvironment == TypeOfMovementEnvironment.OVERLAND) {
            return howManyWheels;
        } else {
            throw new Exception("The vehicle is not an aqueous!");
        }
    }

    public void setHowManyWheels(Integer howManyWheels) throws Exception {
        if (typeOfMovementEnvironment == TypeOfMovementEnvironment.OVERLAND) {
            this.howManyWheels = howManyWheels;
        } else {
            throw new Exception("The vehicle is not an aqueous!");
        }
    }

    public double calculateAverageMonthlyCost() {
        return 0.0;
    }

    public TypeOfMovementEnvironment getTypeOfMovementEnvironment() {
        return typeOfMovementEnvironment;
    }

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

    @Override
    public String toString() {
        if (typeOfMovementEnvironment == TypeOfMovementEnvironment.AQUEOUS) {
            return "Aqueous vehicle";
        } else if (typeOfMovementEnvironment == TypeOfMovementEnvironment.OVERLAND) {
            return "Overland vehicle";
        } else {
            return "Standard vehicle";
        }
    }
}