package classes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne
    private Person assignedBarista;

    @ManyToOne
    private Person loyaltyClubMember;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Beverage_Order",
            joinColumns = { @JoinColumn(name = "id_order") },
            inverseJoinColumns = { @JoinColumn(name = "id_beverage") }
    )
    private Set<Beverage> beverages = new HashSet<>();

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Opinion opinion;

    @ManyToOne
    private Discount discount;

    public Order() {}

    private Order(LocalDateTime dateOfAcceptance) {
        this.dateOfAcceptance = dateOfAcceptance;
        orderStatus = OrderStatus.WAITING;
    }

    public static Order createOrder() throws Exception {
        return new Order(LocalDateTime.now());
    }

    public static Order createOrder(List<Beverage> beverages, Person loyaltyClubMember) throws Exception {
        if (beverages == null || beverages.isEmpty() || loyaltyClubMember == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Order order = new Order(LocalDateTime.now());
        for (Beverage b : beverages) {
            order.addBeverage(b);
        }
        order.setLoyaltyClubMember(loyaltyClubMember);
        return order;
    }

    public static Order createOrder(List<Beverage> beverages, Person loyaltyClubMember, Discount discount) throws Exception {
        if (beverages == null || beverages.isEmpty() || loyaltyClubMember == null || discount == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        Order order = new Order(LocalDateTime.now());
        for (Beverage b : beverages) {
            order.addBeverage(b);
        }
        order.setLoyaltyClubMember(loyaltyClubMember);
        order.setDiscount(discount);
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
            opinion = newOpinion;
            setOrderStatus(OrderStatus.RATED);
            newOpinion.setOrder(this);
        }
    }

    public void removeOpinion() {
        if (opinion != null) {
            opinion.removeOrder();
            opinion = null;
        }
    }

    public Set<Beverage> getBeverages() {
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount newDiscount) throws Exception {
        if (newDiscount == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        boolean check = false;
        for (Discount d : loyaltyClubMember.getDiscounts()) {
            if (d.equals(newDiscount)) {
                check = true;
            }
        }
        if (!check) {
            throw new Exception("Loyalty club member don't have this discount");
        }
        if (newDiscount != discount) {
            if (discount != null) {
                removeDiscount();
            }
            discount = newDiscount;
            newDiscount.addOrder(this);
        }
    }

    public void removeDiscount() throws Exception {
        if (discount != null) {
            discount.removeOrder(this);
            discount = null;
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
        if (discount != null) {
            result -= discount.getDiscountAmount();
        }
        if (result <= 0.0) {
            result = 0.0;
        }
        return result;
    }

    public static void deleteOldOrders() {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> queryOrder = criteriaBuilder.createQuery(Order.class);
            Root<Order> rootOrder = queryOrder.from(Order.class);
            queryOrder.select(rootOrder);

            List<Order> orderList = session.createQuery(queryOrder).getResultList();
            for (Order o : orderList) {
                session.delete(o);
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        finally {
            if(sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    public Long getOrderNr() {
        return id_Order;
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

    @Override
    public String toString() {
        return "Order{" +
                "dateOfAcceptance=" + dateOfAcceptance +
                ", dateOfActualLead=" + dateOfActualLead +
                ", orderStatus=" + orderStatus +
                ", orderNr=" + id_Order +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Order o = (Order) obj;
        if (id_Order != null && o.getOrderNr() != null) {
            return id_Order.equals(o.getOrderNr());
        } else {
            return super.equals(obj);
        }
    }
}