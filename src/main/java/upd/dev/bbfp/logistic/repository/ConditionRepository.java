package upd.dev.bbfp.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upd.dev.bbfp.logistic.model.Condition;

public interface ConditionRepository extends JpaRepository<Condition, Long> {}
