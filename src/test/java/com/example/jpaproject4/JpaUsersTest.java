package com.example.jpaproject4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class JpaUsersTest {
    private EntityManagerFactory emf;

    @BeforeEach
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            emf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @AfterEach
    protected void tearDown() throws Exception {
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    void save_my_object_in_db() {
        User user = new User("Marco", LocalDate.now());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(user);

        em.getTransaction().commit();
    }

    @Test
    void hql_fetch_users() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<User> users = em.createQuery("select U from User U", User.class)
                        .getResultList();
        users.forEach(System.out::println);

        em.getTransaction().commit();
    }
}
