package com.rest.restdemo.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.restdemo.Exception.TransactionSaveException;
import com.rest.restdemo.entity.TransactionDetail;
import com.rest.restdemo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {
    @Autowired
    private TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;


    @Test
    void testSave_success_response() throws Exception {
        when(transactionService.save(Mockito.<TransactionDetail>any())).thenReturn(true);

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setAccountNumber("42");
        transactionDetail.setId(1L);
        transactionDetail.setTransactionId("42");
        String content = (new ObjectMapper()).writeValueAsString(transactionDetail);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/transaction/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Transaction saved successfully"));
    }

    @Test
    void testSaveThrowException_with_failed_response() throws Exception {
        when(transactionService.save(Mockito.<TransactionDetail>any())).thenReturn(false);

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setAccountNumber("42");
        transactionDetail.setId(1L);
        transactionDetail.setTransactionId("42");
        String content = (new ObjectMapper()).writeValueAsString(transactionDetail);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/transaction/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Failed to save transaction"));
    }


    @Test
    void testSaveThrowException_with_error_response() throws Exception {
        when(transactionService.save(Mockito.<TransactionDetail>any()))
                .thenThrow(new TransactionSaveException("An error occurred", new Throwable()));

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setAccountNumber("42");
        transactionDetail.setId(1L);
        transactionDetail.setTransactionId("42");
        String content = (new ObjectMapper()).writeValueAsString(transactionDetail);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/transaction/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("An error occurred while processing your request"));
    }

    @Test
    void testGetTransactionDetailById() throws Exception {
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setAccountNumber("42");
        transactionDetail.setId(1L);
        transactionDetail.setTransactionId("42");
        when(transactionService.findById(Mockito.<Long>any())).thenReturn(transactionDetail);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/transaction/{id}", 1L);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":1,\"transactionId\":\"42\",\"accountNumber\":\"42\"}"));
    }
}

