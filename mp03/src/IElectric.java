import java.time.LocalTime;

public interface IElectric {
    public double calculateAverageMonthlyCost();
    public int getBatteryCapacity();
    public void setBatteryCapacity(Integer batteryCapacity) throws NotNullException;
    public double getAveragePowerConsumption();
    public void setAveragePowerConsumption(Double averagePowerConsumption) throws NotNullException;
    public LocalTime getAverageChargingTime();
    public void setAverageChargingTime(LocalTime averageChargingTime) throws NotNullException;
}
