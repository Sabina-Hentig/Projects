package main.service;

import main.domain.Book;
import main.domain.sortTitle;
import main.domain.sortYear;
import main.domain.validators.ValidatorException;
import main.repository.Repository;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookService {
    public static final DateTimeFormatter DATE_FORMAT_PUBLICATION_YEAR = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Repository<Long, Book> bookRepository;

    public BookService(Repository<Long, Book> bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void addBook(Book book) throws ValidatorException {
        Optional<Book> bookToVerify = bookRepository.findOne(book.getId());

        if (bookToVerify.isPresent()){
            throw new ValidatorException("The ID already exists! Try again with another ID!");
        } else {
            bookRepository.save(book);
        }
    }

    public Set<Book> getAllBooks() {
        Iterable<Book> books= bookRepository.findAll();
        return StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public void deleteBook(long id) throws ValidatorException {
        Optional<Book> bookToDelete = bookRepository.findOne(id);
        Book existingBook = null;
        if (bookToDelete.isPresent()) {
            existingBook = bookToDelete.get();
        }else{
            throw new ValidatorException("The Id does not exist! Try another Id!");
        }
        bookRepository.delete(existingBook.getId());
        System.out.println("Client deleted successfully!");
    }

    public void updateBook(Long id,String title) {

        Optional<Book> bookToUpdate = bookRepository.findOne(id);

        Book existingBook = null;
        if (bookToUpdate.isPresent()) {
            existingBook = bookToUpdate.get();
            existingBook.setTitle(title);
        }else{
            throw new ValidatorException("The Id does not exist! Try another Id!");
        }

        bookRepository.update(existingBook);

    }

    public Set<Book> filterBooksByTitle(String s) {
        Iterable<Book> books = bookRepository.findAll();
        Set<Book> filteredBooks= new HashSet<>();
        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> !book.getTitle().contains(s));

        return filteredBooks;
    }

    public List<Book> sortBooksUnderYear(int y) {
        Iterable<Book> books = bookRepository.findAll();
        List<Book> filteredBooks= new ArrayList<>();

        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> book.getYearOfPublication().getYear()>y);

        List<Book> sortedBookUnderYear = new ArrayList<>();
        Collections.sort(filteredBooks, new sortYear());

        for (Book b : filteredBooks) {
            sortedBookUnderYear.add(b);
        }
        return sortedBookUnderYear;
    }

    public List<Book> sortBooksOverYear(int y) {
        Iterable<Book> books = bookRepository.findAll();
        List<Book> filteredBooks= new ArrayList<>();

        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> book.getYearOfPublication().getYear()<y);

        List<Book> sortedBookOverYear = new ArrayList<>();
        Collections.sort(filteredBooks, new sortYear());

        for (Book b : filteredBooks) {
            sortedBookOverYear.add(b);
        }
        return sortedBookOverYear;
    }

    public void sortBooksByYear(int y) {
        List<Book> sortedBookUnderYear = sortBooksUnderYear(y);
        List<Book> sortedBookOverYear = sortBooksOverYear(y);

        if (sortedBookUnderYear.isEmpty()) {
            System.out.println("No books found matching under year "+y+" .");
        } else {
            System.out.println("Filtered and sorted books under the year "+ y +" :");
            sortedBookUnderYear.forEach(book -> System.out.println(book));
        }
        System.out.println("\n");

        if (sortedBookOverYear.isEmpty()) {
            System.out.println("No books found matching over year "+y+" .");
        } else {
            System.out.println("Filtered and sorted books over the year "+ y +" :");
            sortedBookOverYear.forEach(book -> System.out.println(book));
        }
    }

    public List<Book> sortBooksUnderYearByTitle(int y) {
        Iterable<Book> books = bookRepository.findAll();
        List<Book> filteredBooks= new ArrayList<>();

        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> book.getYearOfPublication().getYear()> y);

        List<Book> sortedBookUnderYearByTitle = new ArrayList<>();
        Collections.sort(filteredBooks, new sortTitle());

        for (Book b : filteredBooks) {
            sortedBookUnderYearByTitle.add(b);
        }
        return sortedBookUnderYearByTitle;
    }

    public List<Book> sortBooksOverYearDescending(int y) {
        Iterable<Book> books = bookRepository.findAll();
        List<Book> filteredBooks= new ArrayList<>();

        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> book.getYearOfPublication().getYear()< y);

        List<Book> sortedBookOverYearDescending = new ArrayList<>();
        Collections.sort(filteredBooks, new sortYear().reversed());

        for (Book b : filteredBooks) {
            sortedBookOverYearDescending.add(b);
        }
        return sortedBookOverYearDescending;
    }

    public void sortBooksByYearAndTitle(int y) {
        List<Book> sortedBookUnderYearByTitle = sortBooksUnderYearByTitle(y);
        List<Book> sortedBookOverYearDescending = sortBooksOverYearDescending(y);

        if (sortedBookUnderYearByTitle.isEmpty()) {
            System.out.println("No books found matching under year "+y+" .");
        } else {
            System.out.println("Filtered and sorted books under the year "+ y +" :");
            sortedBookUnderYearByTitle.forEach(book -> System.out.println(book));
        }
        System.out.println("\n");

        if (sortedBookOverYearDescending.isEmpty()) {
            System.out.println("No books found matching over year "+y+" .");
        } else {
            System.out.println("Filtered and sorted books over the year "+ y +" :");
            sortedBookOverYearDescending.forEach(book -> System.out.println(book));
        }
    }

}
