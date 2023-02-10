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
@Table(name = "CreditCards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creditCardId")
    private Integer creditCardId;

    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @Column(name = "cardNumber")
    private String cardNumber; // número de la tarjeta

    @Column(name = "pin")
    private String pin;

    @Column(name = "expirationDate")
    private String expirationDate; // fecha de vencimiento

    @Column(name = "cardValidationCode")
    private String cardValidationCode; // código de validación de la tarjeta

    @Column(name = "cutoffDate")
    private LocalDate cutoffDate; // fecha de corte

    @Column(name = "monthlyPaymentDeadline")
    private LocalDate monthlyPaymentDeadline; // fecha límite de pago mensual

    @Column(name = "currentBalance")
    private BigDecimal currentBalance; // saldo actual

    @Column(name = "creditLimit")
    private BigDecimal creditLimit; // límite de crédito

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "createdAt")
    private Instant createdAt = Instant.now();
}
