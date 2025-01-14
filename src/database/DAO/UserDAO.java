package database.DAO;

import database.DBLoader;
import database.Entities.Users;
import java.util.List;

import jakarta.persistence.EntityManager;

import jakarta.persistence.Query;

public class UserDAO {

    public void addUser(Users users) {
        EntityManager em = DBLoader.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(users);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Users getUserById(int id) {
        EntityManager em = DBLoader.getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public List<Users> getAllUsers() {
        EntityManager em = DBLoader.getEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM Users u");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void updateUser(Users users) {
        EntityManager em = DBLoader.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(users);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteUser(int id) {
        EntityManager em = DBLoader.getEntityManager();
        try {
            em.getTransaction().begin();
            Users users = em.find(Users.class, id);
            if (users != null) {
                em.remove(users);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
