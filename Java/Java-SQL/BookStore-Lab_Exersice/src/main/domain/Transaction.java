package main.domain;

import java.time.LocalDate;

public class Transaction extends BaseEntity<Long> {

    private long idBook;
    private long idClient;
    private int amount;
    private LocalDate dateOfPurchase;
    public Transaction() {}
    public Transaction(long id, long idBook, long idClient, int amount, LocalDate dateOfPurchase) {
        super(id);
        this.idBook = idBook;
        this.idClient = idClient;
        this.amount = amount;
        this.dateOfPurchase = dateOfPurchase;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                " idBook=" + idBook + '\'' +
                ", idClient=" + idClient + '\'' +
                ", amount=" + amount + '\'' +
                ", dateOfPurchase='" + dateOfPurchase + '\'' +
                '}';
    }
}
