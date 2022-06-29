package com.siemens.library.api.repository;

import com.siemens.library.api.entity.ResourceBook;
import com.siemens.library.api.entity.ResourceBorrowing;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ResourceBorrowingSpecification implements Specification<ResourceBorrowing>
{

    ResourceBorrowing filter;

    public ResourceBorrowingSpecification(ResourceBorrowing filter) {
        super();
        this.filter=filter;
    }

    @Override
    public Predicate toPredicate(Root<ResourceBorrowing> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.disjunction();
        if(filter.getId() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("BorrowingId"), "%" + filter.getBookId() + "%"));
        }
        if(filter.getBookId() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("BookId"), "%" + filter.getBookId() + "%"));
        }
        if(filter.getMemberId() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("MemberId"), "%" + filter.getMemberId() + "%"));
        }
        if(filter.getBorrowDate() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("BorrowDate"), "%" + filter.getBorrowDate() + "%"));
        }
        if(filter.getDueDate() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("DueDate"), "%" + filter.getDueDate() + "%"));
        }
        if(filter.getReturnDate() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("ReturnDate"), "%" + filter.getReturnDate() + "%"));
        }
        return p;
    }
}