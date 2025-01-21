package upd.dev.bbfp.logistic.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "condition")
public class Condition {

    @Id
    @Column(unique = true)
    private Long id;

    private Long lastEdit;
    private String cond;

    public Condition() {}

    public Condition(Long id, String cond) {
        this.id = id;
        this.lastEdit = System.currentTimeMillis() / 1000;
        this.cond = cond;
    }
}
