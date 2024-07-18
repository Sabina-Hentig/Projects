package main.domain;

public class BookProfitabilityDTO {
    private String bookTitle;
    public double bookProfitability;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public double getBookProfitability() {
        return bookProfitability;
    }

    public void setBookProfitability(double bookProfitability) {
        this.bookProfitability = bookProfitability;
    }

    public BookProfitabilityDTO(String bookTitle, double bookProfitability){
        this.bookTitle =bookTitle;
        this.bookProfitability=bookProfitability;

    }
    public String toString() {
        return "BookProfitabilityDTO{" +
                "bookTitle='" + bookTitle + '\'' +
                ", bookProfitability=" + bookProfitability +
                '}';
    }
}
