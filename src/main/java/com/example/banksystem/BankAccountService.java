package com.example.banksystem;

import com.example.banksystem.Exceptions.UserNotFoundException;
import com.example.banksystem.Model.TransferBalance;
import com.example.banksystem.Model.bank_accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BigDecimal getBalanceById(Long accountId) {
        bank_accounts bank_accounts = bankAccountRepository.findBankAccountById(accountId);

        if (bank_accounts != null) {
            return bank_accounts.getAmount();
        } else {
            throw new UserNotFoundException("User with " + accountId + " not found");
        }
    }
    
    public BigDecimal addMoney(TransferBalance transferBalance) {
        Long toAccountId = transferBalance.getTo();
        BigDecimal amount = transferBalance.getAmount();

        // try to find user in database else user not found return exception
        Optional<bank_accounts> toAccount = Optional.ofNullable(bankAccountRepository.findById(toAccountId)
                .orElseThrow(() -> new UserNotFoundException("User with " + toAccountId + " not found")));

        // Execute add operation
        BigDecimal toAccountBalance = toAccount.get().getAmount();
        toAccount.get().setAmount(toAccountBalance.add(amount));

        bankAccountRepository.save(toAccount.get());

        return toAccount.get().getAmount(); // return new account balance
    }

    public BigDecimal transferMoney(TransferBalance transferBalance) {
        Long fromAccountId = transferBalance.getFrom();
        Long toAccountId = transferBalance.getTo();
        BigDecimal amount = transferBalance.getAmount();

        // try to find users in database else some user not found return exception
        Optional<bank_accounts> fromAccount = Optional.ofNullable(bankAccountRepository.findById(fromAccountId)
                .orElseThrow(() -> new UserNotFoundException("User with " + fromAccountId + " not found")));
        Optional<bank_accounts> toAccount = Optional.ofNullable(bankAccountRepository.findById(toAccountId)
                .orElseThrow(() -> new UserNotFoundException("User with " + toAccountId + " not found")));

        if (fromAccount.isPresent() && toAccount.isPresent()) {
            // Verification of sufficient account balance on fromAccount
            BigDecimal fromAccountBalance = fromAccount.get().getAmount();
            if (fromAccountBalance.compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient balance in the from account.");
            }

            // Execute operation
            BigDecimal toAccountBalance = toAccount.get().getAmount();
            toAccount.get().setAmount(toAccountBalance.add(amount));
            fromAccount.get().setAmount(fromAccountBalance.subtract(amount));

            bankAccountRepository.save(toAccount.get());
            bankAccountRepository.save(fromAccount.get());

            return toAccount.get().getAmount(); // return of the new account balance where the money was transferred
        } else {
            throw new IllegalArgumentException("One of the accounts not found.");
        }
    }
}
