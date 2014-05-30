package com.semeureka.fault.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.semeureka.fault.entity.Group;
import com.semeureka.fault.entity.Line;

public class GroupSpecifications {
	public static Specification<Group> byExample(final Group group) {
		return new Specification<Group>() {
			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (group != null) {
					Line line = group.getLine();
					if (line != null && line.getId() != null) {
						predicates.add(cb.equal(root.get("line").get("id"), line.getId()));
					}
				}
				return cb.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
