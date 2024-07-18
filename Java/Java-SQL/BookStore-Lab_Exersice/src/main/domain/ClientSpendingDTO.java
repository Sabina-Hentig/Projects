package main.domain;

public class ClientSpendingDTO {
    public String clientLastName;
    public Double amount;

    public ClientSpendingDTO(String clientLastName, Double amount) {
        this.clientLastName = clientLastName;
        this.amount = amount;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ClientSpendingDTO{" +
                "client's name='" + clientLastName + '\'' +
                ", amount spent=" + amount +
                '}';
    }
}
