package com.example.jpaproject4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class UsersTest {
    private SessionFactory sessionFactory;

    @BeforeEach
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Test
    void save_my_first_object_db() {
        User user = new User("Lisa", LocalDate.now());
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Test
    void hql_fetch_users() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("select U from User U", User.class).list();

            users.forEach(System.out::println);

            session.getTransaction().commit();
        }
    }


}
