package spring.boot.TodoList.repositories.Specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import spring.boot.TodoList.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskSpecification {
    public static Specification<Task> columnEqual(Map<String, String> filters) {
        return new Specification<Task>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                for (String key : filters.keySet()) {
                    var value = filters.get(key);
                    if(key.equals("completed")) { //Mesma lógica para utilizar em datas e etc
                        Predicate predicate = criteriaBuilder.equal(root.get(key), Boolean.parseBoolean(value));
                        predicates.add(predicate);
                        continue;
                    }
                    Predicate predicate = criteriaBuilder.equal(root.get(key), filters.get(key));
                    predicates.add(predicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
