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
import java.util.ArrayList;
import java.util.List;

@Entity
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment",strategy = "increment")
    private Long id_Opinion;
    private LocalDateTime dateOfIssue;
    private String description;
    private int score;

    @OneToOne
    private Order order;

    public Opinion() {}

    private Opinion(LocalDateTime dateOfIssue, String description, int score) {
        this.dateOfIssue = dateOfIssue;
        this.description = description;
        this.score = score;
    }

    public static Opinion createOpinion(LocalDateTime dateOfIssue, String description, Integer score) throws Exception {
        if (dateOfIssue == null || description == null || score == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkDescriptionLength(description);
        return new Opinion(dateOfIssue, description, score);
    }

    private Opinion(LocalDateTime dateOfIssue, int score) {
        this.dateOfIssue = dateOfIssue;
        this.score = score;
    }

    public static Opinion createOpinion(LocalDateTime dateOfIssue, Integer score) throws NotNullException {
        if (dateOfIssue == null || score == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        return new Opinion(dateOfIssue, score);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order newOrder) throws NotNullException {
        if (newOrder == null) {
            throw new NotNullException("Can't set order, parameter is null");
        }
        if (order != null) {
            this.order = newOrder;
            newOrder.setOpinion(this);
        }
    }

    public void removeOrder() {
        if (order != null) {
            order.removeOpinion();
            order = null;
        }
    }

    public static void checkDescriptionLength(String description) throws Exception {
        if (description.length() > 300) {
            throw new Exception(String.format("Opis: %s jest przekracza długość 300 znaków", description));
        }
    }

    public static List<Opinion> getOpinions(LocalDateTime dateFrom, LocalDateTime dateTo) throws Exception {
        if (dateFrom.isAfter(dateTo)) {
            throw new Exception("Incorect dates, dateFrom is after dateTo");
        }

        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        List<Opinion> result = new ArrayList<>();

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
            CriteriaQuery<Opinion> queryOpinion = criteriaBuilder.createQuery(Opinion.class);
            Root<Opinion> rootOpinion = queryOpinion.from(Opinion.class);
            queryOpinion.select(rootOpinion);

            List<Opinion> opinionList = session.createQuery(queryOpinion).getResultList();

            for (Opinion o : opinionList) {
                if (o.getDateOfIssue().isAfter(dateFrom) && o.getDateOfIssue().isBefore(dateFrom)) {
                    result.add(o);
                }
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

    public static Opinion enterOpinion(LocalDateTime dateOfIssue, String description, Integer score, Order order) throws Exception {
        if (dateOfIssue == null || description == null || score == null || order == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
        checkDescriptionLength(description);
        Opinion opinion = new Opinion(dateOfIssue, description, score);
        opinion.setOrder(order);
        return opinion;
    }

    public LocalDateTime getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDateTime dateOfIssue) throws NotNullException {
        if (dateOfIssue == null) {
            throw new NotNullException("Can't set dateOfIssue, parameter is null");
        }
        this.dateOfIssue = dateOfIssue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws Exception {
        if (description != null) {
            checkDescriptionLength(description);
        }
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(Integer score) throws NotNullException {
        if (score == null) {
            throw new NotNullException("Can't set score, parameter is null");
        }
        this.score = score;
    }

    @Override
    public String toString() {
        return "classes.Opinion{" +
                "dateOfIssue=" + dateOfIssue +
                ", description='" + description + '\'' +
                ", score=" + score +
                '}';
    }
}
