package com.nttdata.bc.services;

import com.nttdata.bc.models.DebitCard;

public interface IDebitCardService extends ICRUD<DebitCard, Integer> {
    public DebitCard findByCardNumber(String cardNumber);

    public boolean validateDebitCardAndClientData(String debitCardNumber, String documentType, String documentNumber);
}
