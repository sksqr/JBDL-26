package com.gfg.wallet.repo;

import com.gfg.wallet.dbentities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWalletRepository extends JpaRepository<Wallet,Long> {
}
