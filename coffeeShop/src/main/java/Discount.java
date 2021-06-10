import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private String code;

    @ManyToMany(mappedBy = "discounts")
    private List<Person> loyaltyClubMembers = new ArrayList<>();

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

    public Discount() {}

    private Discount(double discountAmount, String purpose, String code) {
        this.discountAmount = discountAmount;
        this.purpose = purpose;
        this.code = code;
    }

    public static Discount createDiscount(Double discountAmount, String purpose, String code) throws NotNullException {
        if (discountAmount == null || purpose == null || code == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        return new Discount(discountAmount, purpose, code);
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
}
