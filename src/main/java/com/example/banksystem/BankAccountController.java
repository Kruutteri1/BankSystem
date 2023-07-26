package com.example.banksystem;

import com.example.banksystem.Model.TransferBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public BigDecimal getBalance(@PathVariable Long accountId) {
        return bankAccountService.getBalanceById(accountId);
    }

    @PostMapping("/add")
    public BigDecimal addMoney(@RequestBody TransferBalance transferBalance) {
        return bankAccountService.addMoney(transferBalance);
    }

    @PostMapping("/transfer")
    public BigDecimal transferMoney(@RequestBody TransferBalance transferBalance) {
        return bankAccountService.transferMoney(transferBalance);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Specify the HTTP response code (e.g., 400 Bad Request)
    @ResponseBody // An annotation to indicate that the method should return a response body
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return new ErrorResponse(e.getMessage());
    }

}
