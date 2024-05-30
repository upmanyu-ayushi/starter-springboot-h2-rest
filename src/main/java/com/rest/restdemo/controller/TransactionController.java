package com.rest.restdemo.controller;


import com.rest.restdemo.Exception.TransactionSaveException;
import com.rest.restdemo.entity.TransactionDetail;
import com.rest.restdemo.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {


    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody TransactionDetail transactionDetail) {
        try {
            boolean result = transactionService.save(transactionDetail);
            return ResponseEntity.ok(result ? "Transaction saved successfully" : "Failed to save transaction");
        } catch (TransactionSaveException e) {
            log.error("Exception caught: {]", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request");
        }

    }

    @GetMapping("/{id}")
    public TransactionDetail getTransactionDetailById(@PathVariable Long id) {
        return transactionService.findById(id);
    }
}
