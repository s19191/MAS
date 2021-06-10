import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Order;
    private LocalDateTime dateOfAcceptance;
    private LocalDateTime dateOfActualLead;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private int orderNr;
    private static int nr = 1;

    @ManyToOne()
    private Person assignedBarista;

    @ManyToOne()
    private Person loyaltyClubMember;

    @ManyToMany
    private List<Beverage> beverages = new ArrayList<>();

    public Order() {}

    private Order(List<String> coffees) {
        this.dateOfAcceptance = LocalDateTime.now();
        orderNr = nr;
        nr++;
    }

    public static Order createOrder(List<String> coffees) throws NotNullException {
        if (coffees == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Order order = new Order(coffees);
        return order;
    }

    public Person getAssignedBarista() {
        return assignedBarista;
    }

    public void setAssignedBarista(Person newAssignedBarista) throws Exception {
        if (newAssignedBarista == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        if (!newAssignedBarista.getPersonKind().contains(PersonType.BARISTA)) {
            throw new Exception("Can't set new assigned Barista, because this person it's not Barista!");
        }
        if (newAssignedBarista != assignedBarista) {
            if (assignedBarista != null) {
                removeAssignedBarista();
            }
            assignedBarista = newAssignedBarista;
            newAssignedBarista.addAssignedOrder(this);
        }
    }

    public void removeAssignedBarista() throws Exception {
        if (assignedBarista != null) {
            assignedBarista.removeAssignedOrder(this);
            assignedBarista = null;
        }
    }

    public Person getLoyaltyClubMember() {
        return loyaltyClubMember;
    }

    public void setLoyaltyClubMember(Person newLoyaltyClubMember) throws Exception {
        if (newLoyaltyClubMember == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        if (!newLoyaltyClubMember.getPersonKind().contains(PersonType.LOYALTYCLUBMEMBER)) {
            throw new Exception("Can't set new assigned Barista, because this person it's not Barista!");
        }
        if (newLoyaltyClubMember != loyaltyClubMember) {
            if (loyaltyClubMember != null) {
                removeAssignedBarista();
            }
            loyaltyClubMember = newLoyaltyClubMember;
            newLoyaltyClubMember.addOrderPlaced(this);
        }
    }

    public void removeLoyaltyClubMember() throws Exception {
        if (loyaltyClubMember != null) {
            loyaltyClubMember.removeOrderPlaced(this);
            loyaltyClubMember = null;
        }
    }

    public LocalDateTime getDateOfAcceptance() {
        return dateOfAcceptance;
    }

    public LocalDateTime getPredictedDateOfRealisation () {
        return dateOfAcceptance.plusMinutes(beverages.size() * 5L);
    }

    public int getOrderNr() {
        return orderNr;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateOfAcceptance=" + dateOfAcceptance +
                ", orderNr=" + orderNr +
                '}';
    }
}