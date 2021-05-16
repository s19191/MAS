package Overlapping;

import java.time.LocalTime;
import java.util.EnumSet;

public class Vehicle01 {

    private EnumSet<VehicleType> vehicleKind = EnumSet.of(VehicleType.STANDARD);

    private String model;
    private String mark;
    private Double wage;
    private Integer howManySeats;

    private int batteryCapacity;
    private double averagePowerConsumption;
    private LocalTime averageChargingTime;

    private double tankCapacity;
    private double averageCombustion;
    private TypeOfGearbox typeOfGearbox;

    public Vehicle01(String model, String mark, Double wage, Integer howManySeats) throws NotNullException {
        this.model = model;
        this.mark = mark;
        this.wage = wage;
        this.howManySeats = howManySeats;
    }

    public Vehicle01(String model, String mark, Double wage, Integer howManySeats, int batteryCapacity, double averagePowerConsumption, LocalTime averageChargingTime) throws NotNullException {
        this.model = model;
        this.mark = mark;
        this.wage = wage;
        this.howManySeats = howManySeats;
        this.batteryCapacity = batteryCapacity;
        this.averagePowerConsumption = averagePowerConsumption;
        this.averageChargingTime = averageChargingTime;
        vehicleKind.add(VehicleType.ELECTRIC);
    }

    public int getBatteryCapacity() throws Exception {
        if (vehicleKind.contains(VehicleType.ELECTRIC)) {
            return batteryCapacity;
        } else {
            throw new Exception("The vehicle is not an electric!");
        }
    }

    public void setBatteryCapacity(Integer batteryCapacity) throws Exception {
        if (vehicleKind.contains(VehicleType.ELECTRIC)) {
            if (batteryCapacity == null) {
                throw new NotNullException("Can't set value of country, value can not be null");
            }
            this.batteryCapacity = batteryCapacity;
        } else {
            throw new Exception("The vehicle is not an electric!");
        }
    }

    public double getAveragePowerConsumption() throws Exception {
        if (vehicleKind.contains(VehicleType.ELECTRIC)) {
            return averagePowerConsumption;
        } else {
            throw new Exception("The vehicle is not an electric!");
        }
    }

    public void setAveragePowerConsumption(Double averagePowerConsumption) throws Exception {
        if (vehicleKind.contains(VehicleType.ELECTRIC)) {
            if (averagePowerConsumption == null) {
                throw new NotNullException("Can't set value of country, value can not be null");
            }
            this.averagePowerConsumption = averagePowerConsumption;
        } else {
            throw new Exception("The vehicle is not an electric!");
        }
    }

    public LocalTime getAverageChargingTime() throws Exception {
        if (vehicleKind.contains(VehicleType.ELECTRIC)) {
            return averageChargingTime;
        } else {
            throw new Exception("The vehicle is not an electric!");
        }
    }

    public void setAverageChargingTime(LocalTime averageChargingTime) throws Exception {
        if (vehicleKind.contains(VehicleType.ELECTRIC)) {
            if (averageChargingTime == null) {
                throw new NotNullException("Can't set value of country, value can not be null");
            }
            this.averageChargingTime = averageChargingTime;
        } else {
            throw new Exception("The vehicle is not an electric!");
        }
    }

    public Vehicle01(String model, String mark, Double wage, Integer howManySeats, double tankCapacity, double averageCombustion, TypeOfGearbox typeOfGearbox) throws NotNullException {
        this.model = model;
        this.mark = mark;
        this.wage = wage;
        this.howManySeats = howManySeats;
        this.tankCapacity = tankCapacity;
        this.averageCombustion = averageCombustion;
        this.typeOfGearbox = typeOfGearbox;
        vehicleKind.add(VehicleType.INTERNALCOMBUSTION);
    }

    public double getTankCapacity() throws Exception {
        if (vehicleKind.contains(VehicleType.INTERNALCOMBUSTION)) {
            return tankCapacity;
        } else {
            throw new Exception("The vehicle is not an internal combustion!");
        }
    }

    public void setTankCapacity(Double tankCapacity) throws Exception {
        if (vehicleKind.contains(VehicleType.INTERNALCOMBUSTION)) {
            if (tankCapacity == null) {
                throw new NotNullException("Can't set value of country, value can not be null");
            }
            this.tankCapacity = tankCapacity;
        } else {
            throw new Exception("The vehicle is not an internal combustion!");
        }
    }

    public double getAverageCombustion() throws Exception {
        if (vehicleKind.contains(VehicleType.INTERNALCOMBUSTION)) {
            return averageCombustion;
        } else {
            throw new Exception("The vehicle is not an internal combustion!");
        }
    }

    public void setAverageCombustion(Double averageCombustion) throws Exception {
        if (vehicleKind.contains(VehicleType.INTERNALCOMBUSTION)) {
            if (averageCombustion == null) {
                throw new NotNullException("Can't set value of country, value can not be null");
            }
            this.averageCombustion = averageCombustion;
        } else {
            throw new Exception("The vehicle is not an internal combustion!");
        }
    }

    public TypeOfGearbox getTypeOfGearbox() throws Exception {
        if (vehicleKind.contains(VehicleType.INTERNALCOMBUSTION)) {
            return typeOfGearbox;
        } else {
            throw new Exception("The vehicle is not an internal combustion!");
        }
    }

    public void setTypeOfGearbox(TypeOfGearbox typeOfGearbox) throws Exception {
        if (vehicleKind.contains(VehicleType.INTERNALCOMBUSTION)) {
            if (typeOfGearbox == null) {
                throw new NotNullException("Can't set value of country, value can not be null");
            }
            this.typeOfGearbox = typeOfGearbox;
        } else {
            throw new Exception("The vehicle is not an internal combustion!");
        }
    }

    public Vehicle01(EnumSet<VehicleType> vehicleKind, String model, String mark, Double wage, Integer howManySeats, int batteryCapacity, double averagePowerConsumption, LocalTime averageChargingTime, double tankCapacity, double averageCombustion, TypeOfGearbox typeOfGearbox) {
        this.vehicleKind = vehicleKind;
        this.model = model;
        this.mark = mark;
        this.wage = wage;
        this.howManySeats = howManySeats;
        this.batteryCapacity = batteryCapacity;
        this.averagePowerConsumption = averagePowerConsumption;
        this.averageChargingTime = averageChargingTime;
        this.tankCapacity = tankCapacity;
        this.averageCombustion = averageCombustion;
        this.typeOfGearbox = typeOfGearbox;
        vehicleKind.add(VehicleType.ELECTRIC);
        vehicleKind.add(VehicleType.INTERNALCOMBUSTION);
    }

    public double calculateAverageMonthlyCost() {
        if (vehicleKind.contains(VehicleType.INTERNALCOMBUSTION) && vehicleKind.contains(VehicleType.ELECTRIC)) {
            return batteryCapacity * averagePowerConsumption + tankCapacity * averageCombustion;
        } else if (vehicleKind.contains(VehicleType.INTERNALCOMBUSTION)) {
            return tankCapacity * averageCombustion;
        } else if (vehicleKind.contains(VehicleType.ELECTRIC)) {
            return batteryCapacity * averagePowerConsumption;
        } else {
            return 0.0;
        }
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
}
