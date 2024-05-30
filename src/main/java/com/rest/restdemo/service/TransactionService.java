package com.rest.restdemo.service;


import com.rest.restdemo.entity.TransactionDetail;

public interface TransactionService {


    TransactionDetail findById(Long id);

    boolean save(TransactionDetail transactionDetail);
}
