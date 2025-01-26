package Library.Transaction;

import Library.Book.Book;
import Library.User.Member;
import Library.User.User;

import java.util.Date;
import java.util.List;

public class BorrowTransaction {
    private String transactionID;
    private List<Book> book;
    private Member member;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private TransactionStatus transactionStatus;
    private double charge;

    public BorrowTransaction(String transactionID, List<Book> book, Member member, Date borrowDate, Date dueDate) {
        this.transactionID = transactionID;
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.transactionStatus = TransactionStatus.ACTIVE;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Member getMember() {
        return this.member;
    }

    public double getCharge() {
        return this.charge;
    }

    public List<Book> getBooks() {
        return this.book;
    }

    public String getTransactionID() {
        return this.transactionID;
    }

    public TransactionStatus getTransactionStatus() {
        return this.transactionStatus;
    }
}
