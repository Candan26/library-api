package com.siemens.library.api.repository;

import com.siemens.library.api.entity.ResourceBook;
import com.siemens.library.api.entity.ResourceBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<ResourceBorrowing, String> {
}
