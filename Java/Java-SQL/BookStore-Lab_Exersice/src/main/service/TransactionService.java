package main.service;

import main.domain.*;
import main.domain.validators.ValidatorException;
import main.repository.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TransactionService {
    private Repository<Long, Transaction> transactionRepository;
    private Repository<Long, Book> bookRepository;
    private Repository<Long, Client> clientRepository;

    public TransactionService(Repository<Long, Transaction> transactionRepository, Repository<Long, Book> bookRepository, Repository<Long, Client> clientRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    public void addTransaction(Transaction transaction) {
        Optional<Transaction> transactionToVerify = transactionRepository.findOne(transaction.getId());

        if (transactionToVerify.isPresent()){
            throw new ValidatorException("The ID already exists! Try again with another ID!");
        } else {
            transactionRepository.save(transaction);
        }
    }
    public Set<Transaction> filterTransactionsByDateOfPurchase(String s) {
        Iterable<Transaction> transactions = transactionRepository.findAll();
        Set<Transaction> filteredTransactions= new HashSet<>();
        transactions.forEach(filteredTransactions::add);
        filteredTransactions.removeIf(transaction -> !transaction.getDateOfPurchase().toString().contains(s));

        return filteredTransactions;
    }
    public Set<Transaction> getAllTransactions() {
        Iterable<Transaction> transactions = transactionRepository.findAll();
        return StreamSupport.stream(transactions.spliterator(), false).collect(Collectors.toSet());
    }

    public void deleteTransaction(Long id)  {
        Optional<Transaction> transactionToDelete = transactionRepository.findOne(id);
        Transaction existingTransaction = null;
        if (transactionToDelete.isPresent()) {
            existingTransaction = transactionToDelete.get();
        }else{
            throw new ValidatorException("The Id does not exist! Try another Id!");
        }
        transactionRepository.delete(existingTransaction.getId());
        System.out.println("Transaction deleted successfully!");
    }

    public void updateTransaction(Long id,int amount) throws ValidatorException {
        Optional<Transaction> transactionToUpdate = transactionRepository.findOne(id);

        Transaction existingTransaction = null;
        if (transactionToUpdate.isPresent()) {
            existingTransaction = transactionToUpdate.get();
            existingTransaction.setAmount(amount);
        }else{
            throw new ValidatorException("The Id does not exist! Try another Id!");
        }

        transactionRepository.update(existingTransaction);

    }


    public List<BookProfitabilityDTO> getBookProfitability() {
        Map<String, Double> profitability = new HashMap<>();
        List<Transaction> transactionsList = (List<Transaction>) transactionRepository.findAll();


        for (Transaction transaction : transactionsList) {
            long bookId = transaction.getIdBook();

            bookRepository.findOne(bookId).ifPresent(book-> {
                String bookTitle = book.getTitle();
                double revenue = transaction.getAmount() * book.getPrice();
                profitability.merge(bookTitle, revenue, Double::sum);

            });
        }


        List<BookProfitabilityDTO> sortedProfitability = new ArrayList<>();
        for (String bookTitle : profitability.keySet()) {
            sortedProfitability.add(new BookProfitabilityDTO(bookTitle, profitability.get(bookTitle)));
        }

        sortedProfitability.sort(Comparator.comparingDouble(BookProfitabilityDTO::getBookProfitability).reversed());


        return sortedProfitability;
    }

    public List<ClientSpendingDTO> getClientSpending(){
        List<Transaction> transactionsList = (List<Transaction>) transactionRepository.findAll();
        Map<String, Double> spending = new HashMap<>();

        for (Transaction transaction : transactionsList) {
            long clientId = transaction.getIdClient();
            long bookId = transaction.getIdBook();

            clientRepository.findOne(clientId).ifPresent(client -> {
                String clientName = client.getLastName();

                    bookRepository.findOne(bookId).ifPresent(book -> {
                        double spendingAmount = transaction.getAmount() * book.getPrice();

                        spending.merge(clientName, spendingAmount, Double::sum);

                    });
            });

        }

        List<ClientSpendingDTO> sortedSpendings = new ArrayList<>();
        for (String clientName : spending.keySet()) {
            sortedSpendings.add(new ClientSpendingDTO(clientName, spending.get(clientName)));
        }

        sortedSpendings.sort(Comparator.comparingDouble(ClientSpendingDTO::getAmount).reversed());

        return sortedSpendings;

    }



}
