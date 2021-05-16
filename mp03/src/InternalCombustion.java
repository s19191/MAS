public class InternalCombustion extends Vehicle {
    private double tankCapacity;
    private double averageCombustion;
    private TypeOfGearbox typeOfGearbox;

    public InternalCombustion(String model, String mark, Double wage, Integer howManySeats, double tankCapacity, double averageCombustion, TypeOfGearbox typeOfGearbox) {
        super(model, mark, wage, howManySeats);
        this.tankCapacity = tankCapacity;
        this.averageCombustion = averageCombustion;
        this.typeOfGearbox = typeOfGearbox;
    }

    @Override
    public double calculateAverageMonthlyCost() {
        return tankCapacity * averageCombustion;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(Double tankCapacity) throws NotNullException {
        if (tankCapacity == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.tankCapacity = tankCapacity;
    }

    public double getAverageCombustion() {
        return averageCombustion;
    }

    public void setAverageCombustion(Double averageCombustion) throws NotNullException {
        if (averageCombustion == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.averageCombustion = averageCombustion;
    }

    public TypeOfGearbox getTypeOfGearbox() {
        return typeOfGearbox;
    }

    public void setTypeOfGearbox(TypeOfGearbox typeOfGearbox) throws NotNullException {
        if (typeOfGearbox == null) {
            throw new NotNullException("Can't set value of country, value can not be null");
        }
        this.typeOfGearbox = typeOfGearbox;
    }
}
