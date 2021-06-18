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
import java.util.ArrayList;
import java.util.List;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Discount;
    private double discountAmount;
    private String purpose;
    @Column(
            unique = true,
            length = 64
    )
    private String code;

    //TODO: Tu coś
    @ManyToMany(
            mappedBy = "discounts"
//            fetch = FetchType.EAGER
    )
    private List<Person> loyaltyClubMembers = new ArrayList<>();

    public Discount() {}

    private Discount(double discountAmount, String purpose, String code) {
        this.discountAmount = discountAmount;
        this.purpose = purpose;
        this.code = code;
    }

    public static Discount createDiscount(Double discountAmount, String purpose, String code) throws Exception {
        if (discountAmount == null || purpose == null || code == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        return new Discount(discountAmount, purpose, code);
    }

    public List<Person> getLoyaltyClubMembers() {
        return loyaltyClubMembers;
    }

    public void addLoyaltyClubMember(Person newLoyaltyClubMember) throws Exception {
        if (newLoyaltyClubMember == null) {
            throw new NotNullException("Can't add value of newLoyaltyClubMember, value can not be null");
        }
        if (!newLoyaltyClubMember.getPersonKind().contains(PersonType.LOYALTYCLUBMEMBER)) {
            throw new Exception("Can't add barista, because this person it's not Barista!");
        }
        if (!loyaltyClubMembers.contains(newLoyaltyClubMember)) {
            loyaltyClubMembers.add(newLoyaltyClubMember);
            newLoyaltyClubMember.addDiscount(this);
        }
    }

    public void removeLoyaltyClubMember(Person oldLoyaltyClubMember) throws Exception {
        if (loyaltyClubMembers.contains(oldLoyaltyClubMember)) {
            loyaltyClubMembers.remove(oldLoyaltyClubMember);
            oldLoyaltyClubMember.removeDiscount(this);
        }
    }

    //To jakby miała to być metoda na obiekt
    public boolean checkDiscountCode01(Person loyaltyClubMember, String code) throws Exception {
        if (loyaltyClubMember == null || code == null) {
            throw new NotNullException("Can't make operation one of parameters is null");
        }
        if (!loyaltyClubMember.getPersonKind().contains(PersonType.LOYALTYCLUBMEMBER)) {
            throw new Exception("Can't do it, person is not Barista!");
        }
        if (this.code != null) {
            if (loyaltyClubMembers.contains(loyaltyClubMember)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //To jakby miała to by być metoda klasowa
    public static boolean checkDiscountCode02(Person loyaltyClubMember, String code) throws Exception {
        if (loyaltyClubMember == null || code == null) {
            throw new NotNullException("Can't make operation one of parameters is null");
        }
        if (!loyaltyClubMember.getPersonKind().contains(PersonType.LOYALTYCLUBMEMBER)) {
            throw new Exception("Can't do it, person is not Barista!");
        }

        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        boolean result = false;

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
            CriteriaQuery<Discount> queryDiscount = criteriaBuilder.createQuery(Discount.class);
            Root<Discount> rootDiscount = queryDiscount.from(Discount.class);
            queryDiscount.select(rootDiscount);
            queryDiscount.where(criteriaBuilder.equal(rootDiscount.get("code"), code));

            List<Discount> discountList = session.createQuery(queryDiscount).getResultList();

            if (discountList.get(0).getLoyaltyClubMembers().contains(loyaltyClubMember)) {
                result = true;
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
        return result;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) throws NotNullException {
        if (discountAmount == null) {
            throw new NotNullException("Can't set value of discountAmount, value can not be null");
        }
        this.discountAmount = discountAmount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) throws NotNullException {
        if (purpose == null) {
            throw new NotNullException("Can't set value of statusName, value can not be null");
        }
        this.purpose = purpose;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws NotNullException {
        if (code == null) {
            throw new NotNullException("Can't set value of statusName, value can not be null");
        }
        this.code = code;
    }

    @Override
    public String toString() {
        return "Kwota zniżki: " + discountAmount +
                ", cel: " + purpose +
                ", kod: " + code;
    }
}