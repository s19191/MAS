package KlasaAbstrakcyjnaPolimorficzneWolanieMetodWielodziedziczenie;

import java.time.LocalTime;

public class Electric extends Vehicle implements IElectric {
    private int batteryCapacity;
    private double averagePowerConsumption;
    private LocalTime averageChargingTime;

//    private Hybrid hybrid;

    public Electric(String model, String mark, Double wage, Integer howManySeats, int batteryCapacity, double averagePowerConsumption, LocalTime averageChargingTime) {
        super(model, mark, wage, howManySeats);
        this.batteryCapacity = batteryCapacity;
        this.averagePowerConsumption = averagePowerConsumption;
        this.averageChargingTime = averageChargingTime;
    }

//    public Hybrid getHybrid() {
//        return hybrid;
//    }
//
//    public void setHybrid(Hybrid hybrid) {
//        this.hybrid = hybrid;
//    }

    @Override
    public double calculateAverageMonthlyCost() {
        return batteryCapacity * averagePowerConsumption;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) throws NotNullException {
        if (batteryCapacity == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.batteryCapacity = batteryCapacity;
    }

    public double getAveragePowerConsumption() {
        return averagePowerConsumption;
    }

    public void setAveragePowerConsumption(Double averagePowerConsumption) throws NotNullException {
        if (averagePowerConsumption == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.averagePowerConsumption = averagePowerConsumption;
    }

    public LocalTime getAverageChargingTime() {
        return averageChargingTime;
    }

    public void setAverageChargingTime(LocalTime averageChargingTime) throws NotNullException {
        if (averageChargingTime == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.averageChargingTime = averageChargingTime;
    }

    @Override
    public String toString() {
        return "Electric";
    }
}