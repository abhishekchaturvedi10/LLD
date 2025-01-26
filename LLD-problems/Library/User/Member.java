package Library.User;

import Library.Book.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Member extends User {
    private String memberID;
    private String address;
    private String phone;
    private final List<Book> borrowedBooks;

    public Member(String userID, String memberID, String name, String address, String phone) {
        super(userID, name);
        this.memberID = memberID;
        this.address = address;
        this.phone = phone;
        this.borrowedBooks = new CopyOnWriteArrayList<>();
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
