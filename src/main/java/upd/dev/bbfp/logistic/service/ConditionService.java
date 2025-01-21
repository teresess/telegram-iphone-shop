package upd.dev.bbfp.logistic.service;


import org.springframework.stereotype.Service;
import upd.dev.bbfp.logistic.model.Condition;
import upd.dev.bbfp.logistic.repository.ConditionRepository;

import java.util.List;

@Service
public class ConditionService {

    public ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public String getCond(Long id) {
        Condition condition = conditionRepository.findById(id)
                .orElse(null);

        if (condition != null) {
            return condition.getCond();
        } else {
            conditionRepository.save(new Condition(id, "start"));
            return "Cond created: 'start'";
        }
    }
    public List<Condition> getAllCond() {
        return conditionRepository.findAll();
    }
    public void updateCond(Long id, String newCond) {
        conditionRepository.save(new Condition(id, newCond));
    }
}
