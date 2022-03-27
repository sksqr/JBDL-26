package com.gfg.transaction;

import com.gfg.transaction.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String sender;
    private String receiver;
    private Double amount;

    public Transaction toTransaction(){
        return Transaction.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(amount)
                .txnId(UUID.randomUUID().toString())
                .status(TransactionStatus.PENDING)
                .build();
    }

}
