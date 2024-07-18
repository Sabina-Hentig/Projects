
package main.UI;

import main.domain.*;
import main.domain.validators.ValidatorException;
import main.service.BookService;
import main.service.ClientService;
import main.service.TransactionService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import static main.service.BookService.DATE_FORMAT_PUBLICATION_YEAR;

public class Console {

    private BookService bookService;
    private ClientService clientService;

    private TransactionService transactionService;

    public Console(BookService bookService, ClientService clientService, TransactionService transactionService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    private void printMenu() {
        System.out.println("1 - Client\n" +
                "2 - Book\n" +
                "3 - Transaction\n" +
                "4 - Book Profitability\n" +
                "5 - Client Spending\n" +
                "x - Exit");
    }


    public void runMenu() {
        while (true) {
            this.printMenu();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.next();
            if (option.equals("x")) {
                break;
            }
            switch (option) {
                case "1":
                    this.runSubMenuClient();
                    break;
                case "2":
                    this.runSubMenuBook();
                    break;
                case "3":
                    this.runSubMenuTransaction();
                    break;
                case "4":
                    this.bookProfitability();
                    break;
                case "5":
                    this.clientSpending();
                    break;
                default:
                    System.out.println("this option is not yet implemented");
            }
        }
    }

    private void bookProfitability()  {
        for (BookProfitabilityDTO profit : transactionService.getBookProfitability()) {
            System.out.println(profit);
        }
        System.out.println("\n");
    }

    private void clientSpending()  {
        for (ClientSpendingDTO amountSpend : transactionService.getClientSpending()) {
            System.out.println(amountSpend);
        }
        System.out.println("\n");
    }

    //-------------------------------------------- CLIENTS --------------------------------------------

    private void runSubMenuClient() {
        while (true) {
            System.out.println("1. Add ");
            System.out.println("2. Delete");
            System.out.println("3. Update");
            System.out.println("4. Print");
            System.out.println("5. Filter");
            System.out.println("0. Back");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.next();

            switch (option) {
                case "1":
                    this.addClient();
                    break;
                case "2":
                    this.deleteClient();
                    break;
                case "3":
                    this.updateClient();
                    break;
                case "4":
                    this.printClients();
                    break;
                case "5":
                    this.filterClients();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void printClients() {
        System.out.println("All clients: \n");
        Set<Client> clients = clientService.getAllClients();
        clients.stream().forEach(System.out::println);
    }

    private void addClient(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("id = ");
        Long id = scanner.nextLong();

        System.out.println("CNP = ");
        String CNP = scanner.next();

        System.out.println("Last name = ");
        String lastName = scanner.next();

        System.out.println("First name = ");
        String firstName = scanner.next();

        System.out.println("Age = ");
        int age = scanner.nextInt();

        Client client = new Client(id, CNP, lastName, firstName, age);
        try {
            clientService.addClient(client);
        } catch (ValidatorException e){
            System.out.println(e.getMessage());
        }

    }


    private void filterClients() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Searching for: ");
        String search = scanner.next();
        Set<Client> clients = clientService.filterClientsByLastName(search);

        if (clients.isEmpty()) {
            System.out.println("No Client found matching the search criteria.");
        } else {
            System.out.println("Filtered Clients:");

            clients.forEach(client -> System.out.println(client));
        }
    }



    public void updateClient() throws ValidatorException{
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter the ID of the client you want to UPDATE ");
            long idToUpdate = scanner.nextInt();
            System.out.println("Enter the new Last Name");
            String newLastName = scanner.next();

            clientService.updateClient(idToUpdate, newLastName);

        } catch (ValidatorException e){
            System.out.println(e.getMessage());
        }
    }

    private void deleteClient()  {
       try{
        System.out.println("Enter the ID of the client you want to delete  ");
        Scanner scanner = new Scanner(System.in);
        long id = scanner.nextLong();
        clientService.deleteClient(id);
       } catch (ValidatorException e){
           System.out.println(e.getMessage());
       }
    }

    //-------------------------------------------- BOOKS --------------------------------------------

    public void runSubMenuBook() {
        while (true) {
            System.out.println("1. Add ");
            System.out.println("2. Delete ");
            System.out.println("3. Update ");
            System.out.println("4. Print ");
            System.out.println("5. Filter");
            System.out.println("6. Sort year");
            System.out.println("7. Sort by title(under) and descending order(over)");
            System.out.println("0. Back");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.next();
            switch (option) {
                case "1":
                    this.addBook();
                    break;
                case "2":
                    this.deleteBook();
                    break;
                case "3":
                    this.updateBook();
                    break;
                case "4":
                    this.printBook();
                    break;
                case "5":
                    this.filterBooks();
                    break;
                case "6":
                    this.sortedBooksByYear();
                    break;
                case "7":
                    this.sortedBooksByYearAndTitle();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("this option is not yet implemented");
                    break;
            }
        }
    }

    private void printBook() {
        System.out.println("All books -->");
        Set<Book> books = bookService.getAllBooks();
        books.forEach(System.out::println);
    }


    private void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("id= ");
        long id = scanner.nextLong();

        System.out.println("Title? ");
        String title = scanner.next();

        System.out.println("Author? ");
        String author = scanner.next();


        System.out.println("Date of publication? (format: yyyy-MM-dd)");
        String dateInput = scanner.next();

        LocalDate date = null;
        date = LocalDate.parse(dateInput, DATE_FORMAT_PUBLICATION_YEAR);
        System.out.println("You entered: " + date);

        System.out.println("Price? ");
        Double price = scanner.nextDouble();

        Book book = new Book(id, title, author, date, price);
        try {
            bookService.addBook(book);
        } catch (ValidatorException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateBook() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter the ID of the book you want to UPDATE ");
            long idToUpdate = scanner.nextInt();
            System.out.println("Enter the new TITLE");
            String newTitle = scanner.next();

            bookService.updateBook(idToUpdate, newTitle);
        } catch (ValidatorException e){
            System.out.println(e.getMessage());
        }
    }

    private void deleteBook() {
        System.out.println("Enter the ID of the book you want to delete  ");
        Scanner scanner = new Scanner(System.in);
        long id = scanner.nextLong();
        try {
            bookService.deleteBook(id);
        }  catch (ValidatorException e){
            System.out.println(e.getMessage());
        }
    }

    private void filterBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Searching for: ");
        String search = scanner.next();
        Set<Book> books = bookService.filterBooksByTitle(search);

        if (books.isEmpty()) {
            System.out.println("No books found matching the search criteria.");
        } else {
            System.out.println("Filtered Books:");

            books.forEach(book -> System.out.println(book));
        }
    }

    private void sortedBooksByYear() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Searching for: ");
        int searchYear = scanner.nextInt();
        bookService.sortBooksByYear(searchYear);
    }
    private void sortedBooksByYearAndTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Searching for: ");
        int searchYear = scanner.nextInt();
        bookService.sortBooksByYearAndTitle(searchYear);
    }

    //-------------------------------------------- TRANSACTIONS --------------------------------------------

    private void runSubMenuTransaction() {
        while (true) {
            System.out.println("1. Add ");
            System.out.println("2. Delete");
            System.out.println("3. Update");
            System.out.println("4. Print");
            System.out.println("5. Filter");
            System.out.println("0. Back");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.next();

            switch (option) {
                case "1":
                    this.addTransaction();
                    break;
                case "2":
                    this.deleteTransaction();
                    break;
                case "3":
                    this.updateTransaction();
                    break;
                case "4":
                    this.printTransaction();
                    break;
                case "5":
                    this.filterTransaction();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void printTransaction() {
        System.out.println("All transactions: \n");
        Set<Transaction> transactions = transactionService.getAllTransactions();
        transactions.stream().forEach(System.out::println);
    }

    private void addTransaction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("id transaction= ");
        Long idTransaction = scanner.nextLong();

        System.out.println("id book = ");
        Long idBook = scanner.nextLong();

        System.out.println("id client= ");
        Long idClient = scanner.nextLong();

        System.out.println("Amount = ");
        int amount = scanner.nextInt();

        System.out.println("Date of purchase (yyyy-MM-dd) = ");
        String dateInput = scanner.next();

        LocalDate date = null;
        date = LocalDate.parse(dateInput, DATE_FORMAT_PUBLICATION_YEAR);

        Transaction transaction = new Transaction(idTransaction, idBook, idClient, amount, date);
        try {
            transactionService.addTransaction(transaction);
        }catch (ValidatorException e){
            System.out.println(e.getMessage());
        }
    }

    private void filterTransaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Searching for transactions in date: ");
        String search = scanner.next();
        Set<Transaction> transactions = transactionService.filterTransactionsByDateOfPurchase(search);

        if (transactions.isEmpty()) {
            System.out.println("No transaction found matching the search criteria.");
        } else {
            System.out.println("Filtered transactions:");

            transactions.forEach(transaction -> System.out.println(transaction));
        }


    }

    public void updateTransaction() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter the ID of the transaction you want to UPDATE ");
            long idToUpdate = scanner.nextInt();
            System.out.println("Enter the new amount");
            int newAmount = scanner.nextInt();
            transactionService.updateTransaction(idToUpdate, newAmount);

        } catch (ValidatorException e){
            System.out.println(e.getMessage());
        }
    }

    private void deleteTransaction()  {
        System.out.println("Enter the ID of the transaction you want to delete  ");
        Scanner scanner = new Scanner(System.in);
        long id = scanner.nextLong();
        try{
       transactionService.deleteTransaction(id);
        }catch (ValidatorException e){
            System.out.println(e.getMessage());
        }
    }
}
