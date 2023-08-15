package com.example.banksystem.Controller;

import com.example.banksystem.Service.BankAccountService;
import com.example.banksystem.Model.TransferBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@Slf4j
@RestController
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getBalance(@PathVariable Long accountId) {
        BigDecimal userBalance = bankAccountService.getBalanceById(accountId);
        return new ResponseEntity<>(userBalance, HttpStatus.OK);
    }

    @PostMapping("/add")
    public BigDecimal addMoney(@RequestBody TransferBalance transferBalance) {
        BigDecimal newBalance = bankAccountService.addMoney(transferBalance);
        return new ResponseEntity<>(newBalance, HttpStatus.OK).getBody();
    }

    @PostMapping("/transfer")
    public BigDecimal transferMoney(@RequestBody TransferBalance transferBalance) {
        BigDecimal newBalance = bankAccountService.transferMoney(transferBalance);
        return new ResponseEntity<>(newBalance, HttpStatus.OK).getBody();
    }
}
