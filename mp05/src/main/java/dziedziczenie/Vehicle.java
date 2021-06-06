package dziedziczenie;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Vehicle;
    @Basic
    private String model;
    @Basic
    private String mark;
    @Basic
    private Double wage;
    @Basic
    private Integer howManySeats;

    public Vehicle() { }

    public Vehicle(String model, String mark, Double wage, Integer howManySeats) {
        this.model = model;
        this.mark = mark;
        this.wage = wage;
        this.howManySeats = howManySeats;
    }

    public double calculateAverageMonthlyCost() {
        return 0.0;
    }

    public String getModel() {
        return model;
    }

    protected void setModel(String model) throws NotNullException {
        if (model == null) {
            throw new NotNullException("Can't set value of model, value can not be null");
        }
        this.model = model;
    }

    protected String getMark() {
        return mark;
    }

    protected void setMark(String mark) throws NotNullException {
        if (mark == null) {
            throw new NotNullException("Can't set value of mark, value can not be null");
        }
        this.mark = mark;
    }

    protected Double getWage() {
        return wage;
    }

    protected void setWage(Double wage) throws NotNullException {
        if (wage == null) {
            throw new NotNullException("Can't set value of wage, value can not be null");
        }
        this.wage = wage;
    }

    protected Integer getHowManySeats() {
        return howManySeats;
    }

    protected void setHowManySeats(Integer howManySeats) throws NotNullException {
        if (howManySeats == null) {
            throw new NotNullException("Can't set value of howManySeats, value can not be null");
        }
        this.howManySeats = howManySeats;
    }

    @Override
    public String toString() {
        return "Vehicle";
    }
}