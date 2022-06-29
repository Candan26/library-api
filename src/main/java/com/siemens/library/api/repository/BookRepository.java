package com.siemens.library.api.repository;

import com.siemens.library.api.entity.ResourceBook;
import com.siemens.library.api.model.RequestBodyToCreateBook;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<ResourceBook, String> , JpaSpecificationExecutor<ResourceBook> {
}
