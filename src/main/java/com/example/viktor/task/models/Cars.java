package com.example.viktor.task.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Создание модели, которая будет хранить в базе данных таблицу автомобилей

@Entity
public class Cars {

    //создание полей в таблице автомобилей

    private String carModel, ownerName, ownerSurname;
    private int price;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   //для автоматического генерирования айди
    private Long id;


    //создание геттеров и сеттеров

    public void setId(Long id) {
        this.id = id;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public Cars() {}

    public Cars(String carModel, String ownerName, String ownerSurname, int price) {
        this.carModel = carModel;
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.price = price;
    }
}
