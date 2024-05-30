package com.rest.restdemo.service.impl;


import com.rest.restdemo.Exception.TransactionSaveException;
import com.rest.restdemo.entity.TransactionDetail;
import com.rest.restdemo.repository.TransactionRepository;
import com.rest.restdemo.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public TransactionDetail findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public boolean save(TransactionDetail transactionDetail) {
        try {
            transactionRepository.save(transactionDetail);
            return true;
        } catch (Exception e) {
            log.error("Error occurred :{}", e.getMessage());
            throw new TransactionSaveException("Failed to save transaction", e);
        }
    }

}
