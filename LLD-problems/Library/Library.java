package Library;

import Library.Book.BookType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Library {
    public static void main(String[] args) {
        System.out.println("Library management system");

        LibraryManager libraryManager = LibraryManager.getInstance();

        // Create a new user
        String member1ID = libraryManager.addMember("Alice", "abc", "abc@xyz.com", "1234567890");
        String member2ID = libraryManager.addMember("Bob", "def", "abc1@xyz.com", "1234567899");

        String book1ID = libraryManager.addBook("Book1", "Author1", "1234567890", new Date((new Date()).getTime()-100), BookType.FICTION);
        String book2ID = libraryManager.addBook("Book2", "Author2", "1234567891", new Date(564564564), BookType.NON_FICTION);
        String book3ID = libraryManager.addBook("Book3", "Author3", "1234567892", new Date(456456456), BookType.HISTORY);
        String book4ID = libraryManager.addBook("Book4", "Author4", "1234567893", new Date(345345345), BookType.LITERATURE);

        String transaction1ID = libraryManager.borrow(member1ID, new ArrayList<>(Arrays.asList(book1ID, book4ID)), new Date((new Date()).getTime()+900000000));

        libraryManager.printBorrowedBooks(member1ID);

        libraryManager.returnBook(transaction1ID);

        libraryManager.printBorrowedBooks(member1ID);
    }
}
