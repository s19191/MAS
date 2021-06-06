package dziedziczenie;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.time.LocalTime;

@Entity
public class Electric extends Vehicle {
    @Basic
    private int batteryCapacity;
    @Basic
    private double averagePowerConsumption;
    @Basic
    private LocalTime averageChargingTime;

    public Electric() {
        super();
    }

    public Electric(String model, String mark, Double wage, Integer howManySeats, int batteryCapacity, double averagePowerConsumption, LocalTime averageChargingTime) {
        super(model, mark, wage, howManySeats);
        this.batteryCapacity = batteryCapacity;
        this.averagePowerConsumption = averagePowerConsumption;
        this.averageChargingTime = averageChargingTime;
    }

    public double calculateAverageMonthlyCost() {
        return batteryCapacity * averagePowerConsumption;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) throws NotNullException {
        if (batteryCapacity == null) {
            throw new NotNullException("Can't set value of batteryCapacity, value can not be null");
        }
        this.batteryCapacity = batteryCapacity;
    }

    public double getAveragePowerConsumption() {
        return averagePowerConsumption;
    }

    public void setAveragePowerConsumption(Double averagePowerConsumption) throws NotNullException {
        if (averagePowerConsumption == null) {
            throw new NotNullException("Can't set value of averagePowerConsumption, value can not be null");
        }
        this.averagePowerConsumption = averagePowerConsumption;
    }

    public LocalTime getAverageChargingTime() {
        return averageChargingTime;
    }

    public void setAverageChargingTime(LocalTime averageChargingTime) throws NotNullException {
        if (averageChargingTime == null) {
            throw new NotNullException("Can't set value of averageChargingTime, value can not be null");
        }
        this.averageChargingTime = averageChargingTime;
    }

    @Override
    public String toString() {
        return "Electric";
    }
}