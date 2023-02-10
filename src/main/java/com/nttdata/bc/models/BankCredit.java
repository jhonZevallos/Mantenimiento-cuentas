package com.nttdata.bc.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BankCredits")
public class BankCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankCreditId")
    private Integer bankCreditId;

    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @Column(name = "startDate")
    private LocalDate startDate; // fecha de inicio

    @Column(name = "dues")
    private Integer dues; // numero de cuotas

    @Column(name = "monthlyPaymentDate")
    private String monthlyPaymentDate; // fecha de pago mensual

    @Column(name = "initialBalance")
    private BigDecimal initialBalance; // saldo inicial

    @Column(name = "currentBalance")
    private BigDecimal currentBalance; // saldo actual

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "createdAt")
    private Instant createdAt = Instant.now();
}
