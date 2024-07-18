package main;

import main.UI.Console;
import main.domain.Book;
import main.domain.Client;
import main.domain.Transaction;
import main.domain.validators.BookValidator;
import main.domain.validators.ClientValidator;
import main.domain.validators.TransactionValidator;
import main.domain.validators.Validator;
import main.repository.*;
import main.repository.Database.BookDatabaseRepository;
import main.repository.Database.ClientDatabaseRepository;
import main.repository.Database.TransactionDatabaseRepository;
import main.service.BookService;
import main.service.ClientService;
import main.service.TransactionService;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class Main {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Validator<Client> clientValidator = new ClientValidator();
        Repository<Long, Client> clientRepository = new ClientDatabaseRepository(clientValidator);
        ClientService clientService = new ClientService(clientRepository);

        Validator<Book> bookValidator = new BookValidator();
        Repository<Long, Book> bookRepository = new BookDatabaseRepository(bookValidator);
        BookService bookService = new BookService(bookRepository);

        Validator<Transaction> transactionValidator = new TransactionValidator();
        Repository<Long, Transaction> transactionRepository = new TransactionDatabaseRepository(transactionValidator);
        TransactionService transactionService = new TransactionService(transactionRepository,bookRepository, clientRepository);

        Console console = new Console(bookService, clientService, transactionService);

        console.runMenu();

        System.out.println("bye ^_^ thank you >(*_*)<");
    }


}
