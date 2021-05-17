package KlasaAbstrakcyjnaPolimorficzneWolanieMetodWielodziedziczenie;

import java.time.LocalTime;

public class Hybrid extends InternalCombustion implements IElectric {
    private Electric electric;

    public Hybrid(String model, String mark, Double wage, Integer howManySeats, double tankCapacity, double averageCombustion, int batteryCapacity, double averagePowerConsumption) {
        super(model, mark, wage, howManySeats, tankCapacity, averageCombustion, TypeOfGearbox.AUTOMATIC);
        electric = new Electric(null, null, null, null, batteryCapacity, averagePowerConsumption, LocalTime.of(0,0));
    }

    public double calculateAverageMonthlyCost() {
        return super.calculateAverageMonthlyCost() + electric.calculateAverageMonthlyCost();
    }

    @Override
    public int getBatteryCapacity() {
        return electric.getBatteryCapacity();
    }

    @Override
    public void setBatteryCapacity(Integer batteryCapacity) throws NotNullException {
        electric.setBatteryCapacity(batteryCapacity);
    }

    @Override
    public double getAveragePowerConsumption() {
        return electric.getAveragePowerConsumption();
    }

    @Override
    public void setAveragePowerConsumption(Double averagePowerConsumption) throws NotNullException {
        electric.setAveragePowerConsumption(averagePowerConsumption);
    }

    @Override
    public LocalTime getAverageChargingTime() {
        return electric.getAverageChargingTime();
    }

    @Override
    public void setAverageChargingTime(LocalTime averageChargingTime) throws NotNullException {
        electric.setAverageChargingTime(averageChargingTime);
    }

    @Override
    public String toString() {
        return "Hybrid";
    }
}