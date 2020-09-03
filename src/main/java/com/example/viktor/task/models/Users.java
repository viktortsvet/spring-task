package com.example.viktor.task.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//Создание модели, которая будет хранить в базе данных таблицу пользователей

@Entity
public class Users {

    //создание полей в таблице

    private String name, surname;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  //автоматическое генерирование айди при создании новых пользователей
    private Long id;

    //создание геттеров и сеттеров

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    //конструктор класса, который нужен при создании пользователей

    public Users(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Users() {

    }
}
