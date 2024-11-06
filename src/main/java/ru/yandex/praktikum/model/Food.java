package ru.yandex.praktikum.model;

public abstract class Food implements Discountable {
    public int amount;
    public double price;
    public boolean isVegeterian;



    Food (int amount, double price){
        this.amount=amount;
        this.price=price;
    }

}
