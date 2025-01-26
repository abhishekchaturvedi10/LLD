package Library.Book;

import java.util.Date;

public class Book {
    private String bookID;
    private String ISBN;
    private String title;
    private String author;
    private Date publicationDate;
    private BookType bookType;
    private BookAvailabilityStatus availabilityStatus;

    public Book(String bookID, String ISBN, String title, String author, Date publicationDate, BookType bookType) {
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.bookType = bookType;
        this.availabilityStatus = BookAvailabilityStatus.AVAILABLE;
    }

    public String getBookID() {
        return bookID;
    }

    public String getISBN() {
        return ISBN;
    }

    public synchronized BookAvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public synchronized void setAvailabilityStatus(BookAvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getTitle() {
        return title;
    }
}
