import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "OrderTable")
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

    @ManyToOne
    private Person assignedBarista;

    @ManyToOne
    private Person loyaltyClubMember;

    @ManyToMany
    @JoinTable(
            name = "Beverage_Order",
            joinColumns = { @JoinColumn(name = "id_order") },
            inverseJoinColumns = { @JoinColumn(name = "id_beverage") }
    )
    private List<Beverage> beverages = new ArrayList<>();

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Opinion opinion;

    public Order() {
        dateOfAcceptance = LocalDateTime.now();
        orderStatus = OrderStatus.WAITING;
        orderNr = nr;
        nr++;
    }

    public static Order createOrder(List<Beverage> beverages, Person loyaltyClubMember) throws Exception {
        if (beverages == null || beverages.isEmpty() || loyaltyClubMember == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Order order = new Order();
        for (Beverage b : beverages) {
            order.addBeverage(b);
        }
        order.setLoyaltyClubMember(loyaltyClubMember);
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
                removeLoyaltyClubMember();
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

    public Opinion getOpinion() {
        return opinion;
    }

    public void setOpinion(Opinion newOpinion) throws NotNullException {
        if (newOpinion == null) {
            throw new NotNullException("Can't set order, parameter is null");
        }
        if (opinion != null) {
            this.opinion = newOpinion;
            newOpinion.setOrder(this);
        }
    }

    public void removeOpinion() {
        if (opinion != null) {
            opinion.removeOrder();
            opinion = null;
        }
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void addBeverage(Beverage newBeverage) throws Exception {
        if (newBeverage == null) {
            throw new NotNullException("Can't add value of order, value can not be null");
        }
        if (!beverages.contains(newBeverage)) {
            beverages.add(newBeverage);
            newBeverage.addOrder(this);
        }
    }

    public void removeOrder(Beverage oldBeverage) {
        if (beverages.contains(oldBeverage)) {
            beverages.remove(oldBeverage);
            oldBeverage.removeOrder(this);
        }
    }

    public void acceptOrder() {
        orderStatus = OrderStatus.INPROGRESS;
    }

    public void rejectOrder() {
        orderStatus = OrderStatus.CANCELED;
    }

    public OrderStatus checkOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getDateOfAcceptance() {
        return dateOfAcceptance;
    }

    @Transient
    public LocalDateTime getPredictedDateOfRealisation () {
        return dateOfAcceptance.plusMinutes(beverages.size() * 5L);
    }

    @Transient
    public double getOrderPrice() {
        double result = 0.0;
        for (Beverage b : beverages) {
            result += b.getPrice();
        }
        return result;
    }

    public LocalDateTime getDateOfActualLead() {
        return dateOfActualLead;
    }

    public void setDateOfActualLead(LocalDateTime dateOfActualLead) throws NotNullException {
        if (dateOfActualLead == null) {
            throw new NotNullException("Can't set dateOfActualLead, parameter is null");
        }
        this.dateOfActualLead = dateOfActualLead;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) throws NotNullException {
        if (orderStatus == null) {
            throw new NotNullException("Can't set orderStatus, parameter is null");
        }
        this.orderStatus = orderStatus;
    }

    public int getOrderNr() {
        return orderNr;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateOfAcceptance=" + dateOfAcceptance +
                ", dateOfActualLead=" + dateOfActualLead +
                ", orderStatus=" + orderStatus +
                ", orderNr=" + orderNr +
                '}';
    }
}