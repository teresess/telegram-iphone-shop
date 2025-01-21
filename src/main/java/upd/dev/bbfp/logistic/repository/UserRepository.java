package upd.dev.bbfp.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upd.dev.bbfp.logistic.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}
