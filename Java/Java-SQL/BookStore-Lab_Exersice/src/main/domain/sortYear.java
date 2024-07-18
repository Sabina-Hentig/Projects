package main.domain;

import java.util.Comparator;

public class sortYear implements Comparator<Book> {

    public int compare(Book a, Book b)
    {

        return a.getYearOfPublication().compareTo(b.getYearOfPublication());
    }
}