import java.time.LocalDate;
import java.util.*;

public class LoyaltyClubMember {
    private String name;
    private String surname;
    private Sex sex;
    private LocalDate dateOfBirth;
    private String addressMail;
    private String phoneNumber;
    private LocalDate dateOfJoining;
    private int numberOfStars;
    private Map<String, Discount> discounts = new HashMap<>();
    private static Set<Discount> allDiscounts = new HashSet<>();
    private static Set<String> codes = new HashSet<>();

    private LoyaltyClubMember(String name, String surname, Sex sex, LocalDate dateOfBirth, String addressMail, String phoneNumber, LocalDate dateOfJoining, int numberOfStars) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.addressMail = addressMail;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        this.numberOfStars = numberOfStars;
    }

    public static LoyaltyClubMember createLoyaltyClubMember(String name, String surname, Sex sex, LocalDate dateOfBirth, String addressMail, String phoneNumber, LocalDate dateOfJoining, Integer numberOfStars) throws NotNullException {
        if (name == null || surname == null || sex == null || dateOfBirth == null || addressMail == null || phoneNumber == null || dateOfJoining == null || numberOfStars == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        LoyaltyClubMember loyaltyClubMember = new LoyaltyClubMember(name, surname, sex, dateOfBirth, addressMail, phoneNumber, dateOfJoining, numberOfStars);
        return loyaltyClubMember;
    }

    public void addDiscount(Discount discount) throws Exception {
        if (discounts.containsValue(discount)) {
            throw new Exception("The discount is already connected with this loyalty club member!");
        }
        if (allDiscounts.contains(discount)) {
            throw new Exception("The discount is already connected with a loyalty club member!");
        }
        discounts.put(discount.getCode(), discount);
        codes.add(discount.getCode());
        allDiscounts.add(discount);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws NotNullException {
        if (surname == null) {
            throw new NotNullException("Can't set value of surname, value can not be null");
        }
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) throws NotNullException {
        if (sex == null) {
            throw new NotNullException("Can't set value of sex, value can not be null");
        }
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) throws NotNullException {
        if (dateOfBirth == null) {
            throw new NotNullException("Can't set value of sex, value can not be null");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddressMail() {
        return addressMail;
    }

    public void setAddressMail(String addressMail) throws NotNullException {
        if (addressMail == null) {
            throw new NotNullException("Can't set value of sex, value can not be null");
        }
        this.addressMail = addressMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws NotNullException {
        if (phoneNumber == null) {
            throw new NotNullException("Can't set value of sex, value can not be null");
        }
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) throws NotNullException {
        if (dateOfJoining == null) {
            throw new NotNullException("Can't set value of sex, value can not be null");
        }
        this.dateOfJoining = dateOfJoining;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) throws NotNullException {
        if (numberOfStars == null) {
            throw new NotNullException("Can't set value of numberOfStars, value can not be null");
        }
        this.numberOfStars = numberOfStars;
    }

    @Override
    public String toString() {
        return "LoyaltyClubMember{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", dateOfBirth=" + dateOfBirth +
                ", addressMail='" + addressMail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", numberOfStars=" + numberOfStars +
                ", discounts=" + discounts +
                '}';
    }

    public double getDiscountAmount(String key) {
        return discounts.get(key).getDiscountAmount();
    }

    public void setDiscountAmount(String key, Double newDiscountAmount) throws NotNullException {
        discounts.get(key).setDiscountAmount(newDiscountAmount);
    }

    public String getDiscountPurpose(String key) {
        return discounts.get(key).getPurpose();
    }

    public void setDiscountPurpose(String key, String newPurpose) throws NotNullException {
        discounts.get(key).setPurpose(newPurpose);
    }

    public void setDiscountCode(String key, String newCode) throws NotNullException {
        discounts.get(key).setCode(newCode);
    }

    public void createDiscount(Double discountAmount, String purpose, String code) throws Exception {
        if (discountAmount == null || purpose == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        if (codes.contains(code)) {
            throw new Exception("Can't create object, another discount have this code");
        }
        Discount discount = new Discount(discountAmount, purpose, code);
        addDiscount(discount);
    }

    public class Discount {
        private double discountAmount;
        private String purpose;
        private String code;

        private Discount(double discountAmount, String purpose, String code) {
            this.discountAmount = discountAmount;
            this.purpose = purpose;
            this.code = code;
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
            return "Discount{" +
                    "discountAmount=" + discountAmount +
                    ", purpose='" + purpose + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
}