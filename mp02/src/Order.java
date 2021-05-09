import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDateTime dateOfAcceptance;
    private List<String> coffees = new ArrayList<>();
    private Barista assignedBarista;

    private Order(List<String> coffees) {
        this.dateOfAcceptance = LocalDateTime.now();
        this.coffees = coffees;
    }

    public static Order createOrder(List<String> coffees) throws NotNullException {
        if (coffees == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Order order = new Order(coffees);
        return order;
    }

    public Barista getAssignedBarista() {
        return assignedBarista;
    }

    public void setAssignedBarista(Barista newAssignedBarista) {
        if (assignedBarista != null) {
            removeBarista();
        }
        this.assignedBarista = newAssignedBarista;
        newAssignedBarista.addOrder(this);
    }

    public void removeBarista() {
        if (assignedBarista != null) {
            assignedBarista.removeOrder(this);
            assignedBarista = null;
        }
    }

    public LocalDateTime getDateOfAcceptance() {
        return dateOfAcceptance;
    }

    public List<String> getCoffees() {
        return coffees;
    }

    public void setCoffees(List<String> coffees) throws NotNullException {
        if (coffees == null) {
            throw new NotNullException("Can't set value of coffees, value can not be null");
        }
        this.coffees = coffees;
    }

    public void addCoffee(String coffee) throws NotNullException {
        if (coffee == null) {
            throw new NotNullException("Can't add coffee, value can not be null");
        }
        coffees.add(coffee);
    }

    public LocalDateTime getPredictedDateOfRealisation () {
        return dateOfAcceptance.plusMinutes(coffees.size() * 5L);
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateOfAcceptance=" + dateOfAcceptance.toString() +
                ", coffees=" + coffees +
                ", assignedBarista=" + assignedBarista.toString() +
                '}';
    }
}
