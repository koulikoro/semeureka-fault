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
import com.semeureka.fault.entity.Voltage;

public class VoltageSpecifications {
	public static Specification<Voltage> byExample(final Voltage voltage) {
		return new Specification<Voltage>() {
			@Override
			public Predicate toPredicate(Root<Voltage> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (voltage != null) {
					Group group = voltage.getGroup();
					if (group != null) {
						Line line = group.getLine();
						if (group.getId() != null) {
							predicates.add(cb.equal(root.get("group").get("id"), group.getId()));
						} else if (line != null && line.getId() != null) {
							predicates.add(cb.equal(root.get("group").get("line").get("id"),
									line.getId()));
						}
					}
				}
				return cb.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
