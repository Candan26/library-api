package com.siemens.library.api.repository;

import com.siemens.library.api.entity.ResourceBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<ResourceBook, String> {
}
