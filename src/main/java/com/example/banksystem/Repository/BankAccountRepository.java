package com.example.banksystem.Repository;

import com.example.banksystem.Model.bank_accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<bank_accounts, Long> {
    bank_accounts findBankAccountById(long AccountId);
}
