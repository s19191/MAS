package dziedziczenie;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class InternalCombustion extends Vehicle {
    @Basic
    private double tankCapacity;
    @Basic
    private double averageCombustion;
    @Enumerated(EnumType.STRING)
    private TypeOfGearbox typeOfGearbox;

    public InternalCombustion() {
        super();
    }

    public InternalCombustion(String model, String mark, Double wage, Integer howManySeats, double tankCapacity, double averageCombustion, TypeOfGearbox typeOfGearbox) {
        super(model, mark, wage, howManySeats);
        this.tankCapacity = tankCapacity;
        this.averageCombustion = averageCombustion;
        this.typeOfGearbox = typeOfGearbox;
    }

    public double calculateAverageMonthlyCost() {
        return tankCapacity * averageCombustion;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(Double tankCapacity) throws NotNullException {
        if (tankCapacity == null) {
            throw new NotNullException("Can't set value of tankCapacity, value can not be null");
        }
        this.tankCapacity = tankCapacity;
    }

    public double getAverageCombustion() {
        return averageCombustion;
    }

    public void setAverageCombustion(Double averageCombustion) throws NotNullException {
        if (averageCombustion == null) {
            throw new NotNullException("Can't set value of averageCombustion, value can not be null");
        }
        this.averageCombustion = averageCombustion;
    }

    @Enumerated(EnumType.STRING)
    public TypeOfGearbox getTypeOfGearbox() {
        return typeOfGearbox;
    }

    public void setTypeOfGearbox(TypeOfGearbox typeOfGearbox) throws NotNullException {
        if (typeOfGearbox == null) {
            throw new NotNullException("Can't set value of typeOfGearbox, value can not be null");
        }
        this.typeOfGearbox = typeOfGearbox;
    }

    @Override
    public String toString() {
        return "Internal combustion";
    }
}