package com.gfg.transaction.repo;

import com.gfg.transaction.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Transaction findByTxnId(String txnId);
}
