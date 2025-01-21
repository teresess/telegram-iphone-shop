package upd.dev.bbfp.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upd.dev.bbfp.logistic.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
