package database.DAO;

import database.DBLoader;
import database.Entities.Publisher;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PublisherDAO {

    public void addPublisher(Publisher publisher) {
        EntityManager entityManager = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(publisher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Publisher getPublisherById(int id) {
        EntityManager entityManager = DBLoader.getEntityManager();
        try {
            return entityManager.find(Publisher.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Publisher> getAllPublishers() {
        EntityManager entityManager = DBLoader.getEntityManager();
        try {
            return entityManager.createQuery("from Publisher").getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void updatePublisher(Publisher publisher) {
        EntityManager entityManager = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(publisher);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void deletePublisher(int id) {
        EntityManager entityManager = DBLoader.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Publisher publisher = entityManager.find(Publisher.class, id);

            if (publisher != null) {
                entityManager.remove(publisher);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
