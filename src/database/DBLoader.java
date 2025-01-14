package database;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBLoader {
    private static EntityManagerFactory entityManagerFactory;
    static {
        try{
            entityManagerFactory = Persistence.createEntityManagerFactory("unit");
            System.out.println("Database loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManagerFactory.close();
    }

}
