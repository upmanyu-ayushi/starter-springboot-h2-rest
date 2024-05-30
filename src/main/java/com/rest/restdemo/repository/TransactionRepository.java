package com.rest.restdemo.repository;


import com.rest.restdemo.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionDetail, Long> {
}
