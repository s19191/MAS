import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public Opinion() {}

    private Opinion(LocalDateTime dateOfIssue, String description, int score) {
        this.dateOfIssue = dateOfIssue;
        this.description = description;
        this.score = score;
    }

    public static Opinion createOpinion(LocalDateTime dateOfIssue, String description, Integer score) throws NotNullException {
        if (dateOfIssue == null || description == null || score == null) {
            throw new NotNullException("Can't create object, one of parameters is null");
        }
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

    public void setDescription(String description) {
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
}
