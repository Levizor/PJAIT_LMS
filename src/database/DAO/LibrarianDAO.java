package database.DAO;

import database.DBLoader;
import database.Entities.Librarian;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LibrarianDAO {

    public void addLibrarian(Librarian librarian) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(librarian);
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

    public Librarian getLibrarianById(int id) {
        EntityManager em = DBLoader.getEntityManager();
        try {
            return em.find(Librarian.class, id);
        } finally {
            em.close();
        }
    }

    public List<Librarian> getAllLibrarians() {
        EntityManager em = DBLoader.getEntityManager();
        try {
            return em.createQuery("SELECT l FROM Librarian l").getResultList();
        } finally {
            em.close();
        }
    }

    public void updateLibrarian(Librarian librarian) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(librarian);
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

    public void deleteLibrarian(int id) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            Librarian librarian = em.find(Librarian.class, id);

            if (librarian != null) {
                em.remove(librarian);
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
