package database.DAO;

import database.DBLoader;
import database.Entities.Borrowing;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class BorrowingDAO {

    // Add a new borrowing
    public void addBorrowing(Borrowing borrowing) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(borrowing);  // Persist the new borrowing
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

    // Get borrowing by id
    public Borrowing getBorrowingById(int id) {
        EntityManager em = DBLoader.getEntityManager();
        try {
            return em.find(Borrowing.class, id);  // Use 'find' to retrieve an entity by its primary key
        } finally {
            em.close();
        }
    }

    // Get all borrowings
    public List<Borrowing> getAllBorrowings() {
        EntityManager em = DBLoader.getEntityManager();
        try {
            // Create a query that fetches all Borrowing entities
            return em.createQuery("SELECT b FROM Borrowing b").getResultList();
        } finally {
            em.close();
        }
    }

    // Update an existing borrowing
    public void updateBorrowing(Borrowing borrowing) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(borrowing);  // Merge the borrowing entity (update operation)
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

    // Delete a borrowing
    public void deleteBorrowing(int id) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            Borrowing borrowing = em.find(Borrowing.class, id);

            if (borrowing != null) {
                em.remove(borrowing);  // Remove the borrowing if it exists
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
