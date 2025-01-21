package upd.dev.bbfp.logistic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @Column(unique = true)
    private Long id;

    private String registerDate;
    private String role;

    private Long balance;

    public User(Long id, String role, Long balance) {
        this.id = id;
        this.role = role;
        this.balance = balance;
        registerDate = String.valueOf(System.currentTimeMillis() / 1000L);
    }
}
