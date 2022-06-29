package com.siemens.library.api.repository;

import com.siemens.library.api.entity.ResourceBook;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



public final class ResourceBookSpecification implements  Specification<ResourceBook>
{

    ResourceBook filter;

    public ResourceBookSpecification(ResourceBook filter) {
        super();
        this.filter=filter;
    }

    @Override
    public Predicate toPredicate(Root<ResourceBook> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.disjunction();
        if(filter.getIsbn() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("ISBN"), "%" + filter.getIsbn() + "%"));
        }
        if(filter.getTitle() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("BookTitle"), "%" + filter.getTitle() + "%"));
        }
        if(filter.getAuthor() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("Author"), "%" + filter.getAuthor() + "%"));
        }
        if(filter.getCategory() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("Category"), "%" + filter.getCategory() + "%"));
        }
        if(filter.getPublisher() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("Publisher"), "%" + filter.getPublisher() + "%"));
        }
        if(filter.getPublishingDate() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("PublishingDate"), "%" + filter.getPublishingDate() + "%"));
        }
        if(filter.getEdition() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("Edition"), "%" + filter.getEdition() + "%"));
        }
        if(filter.getAvailable() != null){
            p.getExpressions().add(criteriaBuilder.like(root.get("BookAvailability"), "%" + filter.getAvailable() + "%"));
        }
        return p;
    }
}