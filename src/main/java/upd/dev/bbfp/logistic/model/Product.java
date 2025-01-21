package upd.dev.bbfp.logistic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String productId;
    String productModel;
    String productBio;
    String color;
    String publicTime;

    Long price;

    Long newPrice;
    Long ownerId;
    boolean used;

    public Product(String productId, String productModel, String productBio, String color, Long price, Long ownerId, boolean used) {
        this.productId = productId;
        this.productModel = productModel;
        this.productBio = productBio;
        this.color = color;
        this.publicTime = String.valueOf(System.currentTimeMillis() / 1000L);
        this.price = price;
        this.newPrice = 0L;
        this.ownerId = ownerId;
        this.used = used;
    }
}