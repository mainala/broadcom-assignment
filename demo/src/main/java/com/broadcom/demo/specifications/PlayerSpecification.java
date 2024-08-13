package com.broadcom.demo.specifications;

import com.broadcom.demo.entities.Player;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PlayerSpecification {

    public static Specification<Player> getPlayersByFilter(String lastName, Integer age) {
        return (Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (lastName != null && !lastName.isEmpty()) {
                Predicate lastNamePredicate = criteriaBuilder.like(root.get("Name"), "%" + lastName + "%");
                predicates.add(lastNamePredicate);
            }

            if (age != null) {
                Predicate agePredicate = criteriaBuilder.equal(root.get("Age"), age);
                predicates.add(agePredicate);
            }

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filter applied
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Player> sortBy(String sortBy) {
        return (Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if ("Name".equalsIgnoreCase(sortBy)) {
                query.orderBy(criteriaBuilder.asc(root.get("Name")));
            } else if ("Age".equalsIgnoreCase(sortBy)) {
                query.orderBy(criteriaBuilder.asc(root.get("Age")));
            }
            return query.getRestriction();
        };
    }
}
