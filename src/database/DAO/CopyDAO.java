package database.DAO;

import database.DBLoader;
import database.Entities.Copy;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CopyDAO {

    public void addCopy(Copy copy) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(copy);
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

    public Copy getCopyById(int id) {
        EntityManager em = DBLoader.getEntityManager();
        try {
            return em.find(Copy.class, id);
        } finally {
            em.close();
        }
    }

    public List<Copy> getAllCopies() {
        EntityManager em = DBLoader.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Copy c").getResultList();
        } finally {
            em.close();
        }
    }

    public void updateCopy(Copy copy) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(copy);
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

    public void deleteCopy(int id) {
        EntityManager em = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            Copy copy = em.find(Copy.class, id);

            if (copy != null) {
                em.remove(copy);
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
