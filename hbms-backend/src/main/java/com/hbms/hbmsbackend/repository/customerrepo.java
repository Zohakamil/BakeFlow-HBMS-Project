package com.hbms.hbmsbackend.repository;

import com.hbms.hbmsbackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerrepo extends JpaRepository<Customer, Long> {
}