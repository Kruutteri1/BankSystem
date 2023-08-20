package com.example.banksystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class bank_accounts {
    @Id
    private Long id;
    private BigDecimal amount;
}
