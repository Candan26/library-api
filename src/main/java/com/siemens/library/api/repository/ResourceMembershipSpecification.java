package com.siemens.library.api.repository;

import com.siemens.library.api.entity.ResourceMember;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ResourceMembershipSpecification  implements Specification<ResourceMember>
{
    ResourceMember filter;

    public ResourceMembershipSpecification(ResourceMember filter){
        super();
        this.filter = filter;
    }
    @Override
    public Predicate toPredicate(Root<ResourceMember> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.disjunction();
        if(filter.getName() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("Name"), "%" + filter.getName() + "%"));
        }
        if(filter.getSurname() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("Surname"), "%" + filter.getSurname() + "%"));
        }
        if(filter.getEmail() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("Email"), "%" + filter.getEmail() + "%"));
        }
        if(filter.getPhoneNumber() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("PhoneNumber"), "%" + filter.getPhoneNumber() + "%"));
        }
        if(filter.getJoinDate() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("JoinDate"), "%" + filter.getJoinDate() + "%"));
        }
        return p;
    }
}