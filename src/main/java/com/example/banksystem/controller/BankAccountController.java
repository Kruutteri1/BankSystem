package com.example.banksystem.controller;

import org.springframework.ui.Model;
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

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("transferBalance", new TransferBalance());
        return "add";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMoney(@ModelAttribute TransferBalance transferBalance) {
        BigDecimal newBalance = bankAccountService.addMoney(transferBalance);
        return new ResponseEntity<>("Money added successfully. New balance: " + newBalance, HttpStatus.OK);
    }

    @GetMapping("/transfer")
    public String showTransferForm(Model model) {
        model.addAttribute("transferBalance", new TransferBalance());
        return "transfer";
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@ModelAttribute TransferBalance transferBalance) {
        BigDecimal newBalance = bankAccountService.transferMoney(transferBalance);
        return new ResponseEntity<>("Transfer successful. New balance: " + newBalance, HttpStatus.OK);
    }
}
