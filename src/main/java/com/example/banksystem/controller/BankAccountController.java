package com.example.banksystem.controller;

import com.example.banksystem.service.BankAccountService;
import com.example.banksystem.model.TransferBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@Slf4j
//@RestController
@Controller
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
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
