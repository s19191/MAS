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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Beverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Beverage;
    private String name;
    private double price;
    @Column(
            unique = true,
            length = 64
    )
    private String code;

    @OneToMany(
            mappedBy = "beverage",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToMany(
            mappedBy = "beverages",
            fetch = FetchType.EAGER
    )
    private Set<Order> orders = new HashSet<>();

    public Beverage() {}

    private Beverage(String name, double price, String code) {
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public static Beverage createBeverage(String name, Double price, String code) throws Exception {
        if (name == null || price == null || code == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        return new Beverage(name, price, code);
    }

    public Set<Recipe> getCompositions() {
        return recipes;
    }

    public void addComposition(Recipe newRecipe) throws NotNullException {
        if (newRecipe == null) {
            throw new NotNullException("Can't set value of newComposition, value can not be null");
        }
        if (!recipes.contains(newRecipe)) {
            recipes.add(newRecipe);
            newRecipe.setBeverage(this);
        }
    }

    public void removeComposition(Recipe oldRecipe) {
        if (recipes.contains(oldRecipe)) {
            recipes.remove(oldRecipe);
            oldRecipe.removeBeverage();
        }
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order newOrder) throws Exception {
        if (newOrder == null) {
            throw new NotNullException("Can't add value of order, value can not be null");
        }
        if (!orders.contains(newOrder)) {
            orders.add(newOrder);
            newOrder.addBeverage(this);
        }
    }

    public void removeOrder(Order oldOrder) {
        if (orders.contains(oldOrder)) {
            orders.remove(oldOrder);
            oldOrder.removeOrder(this);
        }
    }

    public static List<Beverage> getAllBeverages() {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        List<Beverage> result = new ArrayList<>();

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
            CriteriaQuery<Beverage> queryBeverage = criteriaBuilder.createQuery(Beverage.class);
            Root<Beverage> beverageRoot = queryBeverage.from(Beverage.class);
            queryBeverage.select(beverageRoot);

            result = session.createQuery(queryBeverage).getResultList();

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

    public Long getId_Beverage() {
        return id_Beverage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NotNullException {
        if (name == null) {
            throw new NotNullException("Can't set value of name, value can not be null");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) throws NotNullException {
        if (price == null) {
            throw new NotNullException("Can't set value of ingredient, value can not be null");
        }
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws NotNullException {
        if (code == null) {
            throw new NotNullException("Can't set value of code, value can not be null");
        }
        this.code = code;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", code='" + code + '\'' +
                ", compositions=" + recipes +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Beverage b = (Beverage) obj;
        return id_Beverage.equals(b.getId_Beverage());
    }
}