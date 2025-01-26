package Library;

import Library.Book.BookAvailabilityStatus;
import Library.Book.Book;
import Library.Book.BookType;
import Library.Transaction.BorrowTransaction;
import Library.Transaction.TransactionStatus;
import Library.User.Member;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LibraryManager {
    private static LibraryManager libraryManager;
    private Map<String, Member> members;
    private Map<String, Book> books;
    private Map<String, BorrowTransaction> borrowTransactions;

    public LibraryManager() {
        members = new ConcurrentHashMap<>();
        books = new ConcurrentHashMap<>();
        borrowTransactions = new ConcurrentHashMap<>();
    }

    public static LibraryManager getInstance() {
        if (libraryManager == null) {
            libraryManager = new LibraryManager();
        }
        return libraryManager;
    }

    public synchronized String borrow(String memberID, ArrayList<String> bookIDs, Date dueDate) {
        if (bookIDs.size()>10) {
            System.out.println("You cannot borrow more than 10 books at a time");
            return null;
        }
        Date currentDate = new Date();
        System.out.println("Current Datetime: " + currentDate);
        System.out.println("Due Datetime: " + dueDate);
        long borrowDays = (dueDate.getTime()-currentDate.getTime())/(1000 * 60 * 60 * 24);
        System.out.println("Borrow Days: " + borrowDays);
        if (borrowDays > 14) {
            System.out.println("You cannot borrow books for more than 14 days");
            return null;
        }
        String transactionID = generateTransactionID();
        Member member = members.get(memberID);
        List<Book> borrowedBooks = new CopyOnWriteArrayList<>();
        for (String bookID : bookIDs) {
            if(!books.containsKey(bookID) || books.get(bookID).getAvailabilityStatus() == BookAvailabilityStatus.BORROWED) {
                System.out.println("Book with ID: " + bookID + " is not available");
                continue;
            }
            Book book = this.books.get(bookID);
            borrowedBooks.add(book);
            member.borrowBook(book);
            book.setAvailabilityStatus(BookAvailabilityStatus.BORROWED);
        }
        if(borrowedBooks.isEmpty()) {
            System.out.println("No books available for borrowing");
            return null;
        }
        BorrowTransaction borrowTransaction = new BorrowTransaction(transactionID, borrowedBooks, member, new Date(), dueDate);
        borrowTransactions.put(transactionID, borrowTransaction);
        return transactionID;
    }

    public synchronized void returnBook(String transactionID) {
        BorrowTransaction borrowTransaction = borrowTransactions.get(transactionID);
        Member member = borrowTransaction.getMember();
        for (Book book : borrowTransaction.getBooks()) {
            member.returnBook(book);
            book.setAvailabilityStatus(BookAvailabilityStatus.AVAILABLE);
        }
        borrowTransaction.setReturnDate(new Date());
        borrowTransaction.setCharge(transactionCharge(borrowTransaction));
        borrowTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
    }

    private double transactionCharge(BorrowTransaction borrowTransaction) {
        long borrowedDays = (borrowTransaction.getReturnDate().getTime() - borrowTransaction.getBorrowDate().getTime()) / (1000 * 60 * 60 * 24);
        long overDueDays = (borrowTransaction.getReturnDate().getTime() - borrowTransaction.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
        return borrowedDays * 10 + overDueDays * 20;
    }

    public void borrowingHistory(String userID) {
        for (BorrowTransaction borrowTransaction : borrowTransactions.values()) {
            if (borrowTransaction.getMember().getUserID().equals(userID)) {
                System.out.println("Transaction ID: " + borrowTransaction.getTransactionID());
                System.out.println("Books Borrowed");
                for (Book book : borrowTransaction.getBooks()) {
                    System.out.println("Book ID: " + book.getBookID());
                }
                System.out.println("Borrow Date: " + borrowTransaction.getBorrowDate());
                System.out.println("Due Date: " + borrowTransaction.getDueDate());
                System.out.println("Return Date: " + borrowTransaction.getReturnDate());
                System.out.println("Charge: " + borrowTransaction.getCharge());
                System.out.println("Transaction Status: " + borrowTransaction.getTransactionStatus());
            }
        }
    }

    public Book searchBook(String title) {
        for(Book book : books.values()) {
            if(book.getTitle().equals(title)) {
                return book;
            }
        }
        System.out.println("Book with title: " + title + " not found");
        return null;
    }

    private String generateTransactionID() {
        return "TRX-" + UUID.randomUUID();
    }

    private String generateBookID() {
        return "BOOK-" + UUID.randomUUID();
    }

    private String generateMemberID() {
        return "MEM-" + UUID.randomUUID();
    }

    public String addBook(String ISBN, String title, String author, Date publicationDate, BookType bookType) {
        String bookID = generateBookID();
        Book book = new Book(bookID, ISBN, title, author, publicationDate, bookType);
        books.put(bookID, book);
        return bookID;
    }

    public String addMember(String name, String address, String email, String phone) {
        String memberID = generateMemberID();
        Member member = new Member(memberID, name, address, email, phone);
        members.put(memberID, member);
        return memberID;
    }

    public void printBorrowedBooks(String memberID) {
        System.out.println("Borrowed Books by memeber with ID: " + memberID);
        Member member = members.get(memberID);
        List<Book> borrowedBooks = member.getBorrowedBooks();
        for (Book book : borrowedBooks) {
            System.out.println(book.getISBN());
        }
    }
}
