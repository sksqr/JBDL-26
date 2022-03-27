package com.gfg.transaction;

import com.gfg.transaction.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    ResponseEntity<String> doTransaction(@RequestBody  TransactionRequest transactionRequest){
        String txnId = transactionService.doTransaction(transactionRequest);
        return ResponseEntity.ok(txnId);
    }

}
