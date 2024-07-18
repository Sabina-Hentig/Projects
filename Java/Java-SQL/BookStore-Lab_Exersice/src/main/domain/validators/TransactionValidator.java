package main.domain.validators;

import main.domain.Transaction;

import java.time.LocalDate;

public class TransactionValidator implements Validator<Transaction> {

    @Override
    public void validate(Transaction entity) throws ValidatorException {
        if(entity.getIdBook() < 0) {
            throw new ValidatorException("Purchase ID must  not be negative.");
        }
        if(entity.getIdClient() < 0){
            throw new ValidatorException("Purchase ID must  not be negative.");
        }
        if(entity.getAmount()< 0){
            throw new ValidatorException("Amount must  not be negative.");
        }
        if(entity.getDateOfPurchase().isAfter(LocalDate.now())){
            throw new ValidatorException("Date of purchase cannot be in the future.");
        }

    }
}
