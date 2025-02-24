package com.example.jpaproject4;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private LocalDate birthday;

    public User() {

    }

    public User(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
