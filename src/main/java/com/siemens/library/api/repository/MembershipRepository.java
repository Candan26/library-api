package com.siemens.library.api.repository;

import com.siemens.library.api.entity.ResourceMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<ResourceMember, String> {
}

