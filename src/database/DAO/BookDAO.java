package database.DAO;

import database.DBLoader;
import database.Entities.Book;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BookDAO {

    public void addBook(Book book) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(book);  // Persist the new book
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Book getBookById(int id) {
        EntityManager em = DBLoader.getEntityManager();
        try {
            return em.find(Book.class, id);  // Use 'find' to retrieve an entity by its primary key
        } finally {
            em.close();
        }
    }

    public List<Book> getAllBooks() {
        EntityManager em = DBLoader.getEntityManager();
        try {
            // Create a query that fetches all Book entities
            return em.createQuery("SELECT b FROM Book b").getResultList();
        } finally {
            em.close();
        }
    }

    public void updateBook(Book book) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(book);  // Merge the book entity (update operation)
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteBook(int id) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            Book book = em.find(Book.class, id);

            if (book != null) {
                em.remove(book);  // Remove the book if it exists
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
