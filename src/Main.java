import UI.LibrarianUI;
import database.DAO.BookDAO;
import database.DAO.PublisherDAO;
import database.Entities.Book;
import database.Entities.Publisher;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Publisher publisher = new Publisher();
        publisher.setName("Penguin Books");
        publisher.setAddress("1234 Penguin Lane, NY");
        publisher.setPhoneNumber("+1-234-567-890");

        // Create a new Book
        Book book = new Book();
        book.setTitle("The Great Adventure");
        book.setAuthor("John Doe");
        book.setPublicationYear(2025);
        book.setIsbn("978-3-16-148410-0");

        // Set the publisher for the book
        book.setPublisher(publisher);

        // Create the DAOs
        PublisherDAO publisherDAO = new PublisherDAO();
        BookDAO bookDAO = new BookDAO();

        // Add publisher to the database
        publisherDAO.addPublisher(publisher);

        // Add book to the database
        bookDAO.addBook(book);
        Thread.sleep(1000);

        new LibrarianUI();
    }
}
